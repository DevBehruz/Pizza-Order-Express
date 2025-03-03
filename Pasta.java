package org.openjfx.mainjfx;

public class Pasta extends Food {
    public enum Type {
        SPAGHETTI(12.99),
        FETTUCCINE(13.99),
        PENNE(11.99);

        private final double price;

        Type(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }

    public enum Sauce {
        MARINARA("Marinara", 0),
        ALFREDO("Alfredo", 1.50),
        PESTO("Pesto", 2.00);

        private final String name;
        private final double extraCost;

        Sauce(String name, double extraCost) {
            this.name = name;
            this.extraCost = extraCost;
        }

        public double getExtraCost() {
            return extraCost;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final Type type;
    private final Sauce sauce;

    public Pasta(Type type, Sauce sauce) {
        this.type = type;
        this.sauce = sauce;
    }

    public double getPrice() {
        return type.getPrice() + sauce.getExtraCost();
    }

    @Override
    public String toString() {
        return String.format("%s Pasta with %s Sauce ($%.2f)", 
            type.toString().charAt(0) + type.toString().substring(1).toLowerCase(), 
            sauce, 
            getPrice());
    }
}