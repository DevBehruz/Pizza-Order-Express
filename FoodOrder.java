package org.openjfx.mainjfx;

import java.util.ArrayList;

public class FoodOrder {
	
	ArrayList<Food> items = new ArrayList<>();
	private double totalCost;
	private String orderNumber;
	private OrderStatus status;
	public enum OrderStatus {
		PLACED,
		READY,
		INROUTE,
		DELIVERED
	}
	
	// constructor
	public FoodOrder(String orderNumber) {
		this.orderNumber = orderNumber;
		
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public boolean setStatus(OrderStatus status) {
		if (this.status.ordinal() < status.ordinal()) {
			this.status = status;
			return true;
		}
		return false;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void placeOrder() {
		status = OrderStatus.PLACED;
	}
	
	public void addFood(Food food) {
		items.add(food);
		totalCost += food.getPrice();
	}
	
	public void removeFood(Food food) {
		items.remove(food);
		totalCost -= food.getPrice();
	}
	
	public void removeLastItem() {
        if (!items.isEmpty()) {
            Food lastItem = items.remove(items.size() - 1); // Remove the last item
            totalCost -= lastItem.getPrice(); // Subtract the price of the removed item

        }
    }

	public double getTax() {
		return totalCost * 0.08;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public ArrayList<Food> getItems(){
		return items;
	}
	
	public String toString() {
		
		
		String message = "\nOrder Number: " + getOrderNumber() + "\n";
		for (Food f : items) {
			message += f.toString();
		}
		double total = (double) Math.round(getTotalCost() * 100) / 100;
		
		if(total == 0) {
			return "";
		}
		
		message += "TOTAL = $" + total;
		double tax = (double) Math.round(getTax() * 100) / 100;
		message += "\nSALES TAX = $" + tax;
		return message.trim();
	}
	

}
