package org.openjfx.mainjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class App extends Application {

    private static Scene scene;
    private static final String PRIMARY_COLOR = "#FF6B6B";
    private static final String ACCENT_COLOR = "#FFD93D";
    private static final String SUCCESS_COLOR = "#4CAF50";

    @Override
    public void start(Stage stage) {
        stage.setTitle("Behruz's App");

        // Create main container
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header
        HBox header = createHeader();
        
        // Main content
        VBox mainContent = new VBox(20);
        mainContent.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        mainContent.setPadding(new Insets(20));
        mainContent.setMaxWidth(700);
        
        // Center the main content
        StackPane centerContainer = new StackPane(mainContent);
        centerContainer.setPadding(new Insets(20));
        
        // Title
        Label titleLabel = new Label("Your Order");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setAlignment(Pos.CENTER);
        
        // Order number section
        HBox orderNumberSection = createOrderNumberSection("0001");
        
        // Grid for options
        GridPane optionsGrid = new GridPane();
        optionsGrid.setHgap(50);
        optionsGrid.setVgap(10);
        optionsGrid.setPadding(new Insets(20, 0, 20, 0));

        // Size options
        final ToggleGroup sizeGroup = new ToggleGroup();
        addStyledSizeOptions(optionsGrid, sizeGroup);

        // Flavor options
        final ToggleGroup flavorGroup = new ToggleGroup();
        addStyledFlavorOptions(optionsGrid, flavorGroup);

        // Buttons section
        HBox buttonSection = createButtonSection();

        // Text area for order summary
        TextArea orderSummary = createOrderSummary();

        // Add all components to main content
        mainContent.getChildren().addAll(
            titleLabel,
            orderNumberSection,
            optionsGrid,
            buttonSection,
            orderSummary
        );

        // Add everything to root
        root.getChildren().addAll(header, centerContainer);

        // Set up button actions
        setupButtonActions(buttonSection, sizeGroup, flavorGroup, orderSummary);

        // Create and show scene
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: " + PRIMARY_COLOR + ";");
        header.setPrefHeight(60);
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label headerLabel = new Label("Welcome to Behruz's Ordering App!");
        headerLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        headerLabel.setTextFill(Color.WHITE);
        header.getChildren().add(headerLabel);

        return header;
    }

    private HBox createOrderNumberSection(String orderNumber) {
        HBox orderBox = new HBox(10);
        orderBox.setAlignment(Pos.CENTER_LEFT);

        Label orderLabel = new Label("Order Number:");
        orderLabel.setFont(Font.font("System", FontWeight.NORMAL, 16));

        TextField orderField = new TextField(orderNumber);
        orderField.setEditable(false);
        orderField.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #ccc;" +
            "-fx-border-radius: 5;" +
            "-fx-padding: 5 10 5 10;"
        );
        orderField.setPrefWidth(120);

        orderBox.getChildren().addAll(orderLabel, orderField);
        return orderBox;
    }

    private void addStyledSizeOptions(GridPane grid, ToggleGroup sizeGroup) {
        Label sizeLabel = new Label("Choose Size:");
        sizeLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        grid.add(sizeLabel, 0, 0);

        VBox sizeBox = new VBox(15);
        sizeBox.setPadding(new Insets(10, 0, 0, 0));

        RadioButton[] sizeButtons = {
            createStyledRadioButton("Small", Pizza.Size.SMALL, sizeGroup, true),
            createStyledRadioButton("Medium", Pizza.Size.MEDIUM, sizeGroup, false),
            createStyledRadioButton("Large", Pizza.Size.LARGE, sizeGroup, false)
        };

        sizeBox.getChildren().addAll(sizeButtons);
        grid.add(sizeBox, 0, 1);
    }

    private void addStyledFlavorOptions(GridPane grid, ToggleGroup flavorGroup) {
        Label flavorLabel = new Label("Choose Flavor:");
        flavorLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        grid.add(flavorLabel, 1, 0);

        VBox flavorBox = new VBox(15);
        flavorBox.setPadding(new Insets(10, 0, 0, 0));

        RadioButton[] flavorButtons = {
            createStyledRadioButton("Cheese", Pizza.Flavor.CHEESE, flavorGroup, true),
            createStyledRadioButton("Pepperoni", Pizza.Flavor.PEPPERONI, flavorGroup, false),
            createStyledRadioButton("Veggie", Pizza.Flavor.VEGGIE, flavorGroup, false)
        };

        flavorBox.getChildren().addAll(flavorButtons);
        grid.add(flavorBox, 1, 1);
    }

    private RadioButton createStyledRadioButton(String text, Object userData, ToggleGroup group, boolean selected) {
        RadioButton button = new RadioButton(text);
        button.setToggleGroup(group);
        button.setUserData(userData);
        button.setSelected(selected);
        button.setFont(Font.font("System", 16));
        return button;
    }

    private HBox createButtonSection() {
        HBox buttonBox = new HBox(15);
        buttonBox.setPadding(new Insets(20, 0, 20, 0));
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Button addPizza = createStyledButton("Add Pizza", ACCENT_COLOR, Color.BLACK);
        Button remove = createStyledButton("Remove", "white", Color.web(PRIMARY_COLOR));
        remove.setStyle(remove.getStyle() + 
            "-fx-border-color: " + PRIMARY_COLOR + ";" +
            "-fx-border-width: 2px;"
        );

        Button placeOrder = createStyledButton("Place Order", SUCCESS_COLOR, Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonBox.getChildren().addAll(addPizza, remove, spacer, placeOrder);
        return buttonBox;
    }

    private Button createStyledButton(String text, String bgColor, Color textColor) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: " + bgColor + ";" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 10 20 10 20;"
        );
        button.setTextFill(textColor);
        button.setFont(Font.font("System", FontWeight.NORMAL, 16));
        return button;
    }

 // ... (previous code remains the same until createOrderSummary method)

    private TextArea createOrderSummary() {
        TextArea orderSummary = new TextArea();
        orderSummary.setStyle(
            "-fx-background-color: #f8f9fa;" +
            "-fx-border-color: #dee2e6;" +
            "-fx-border-radius: 5;" +
            "-fx-font-family: 'System';" +
            "-fx-font-size: 14px;"
        );
        
        // Increase the preferred size
        orderSummary.setPrefRowCount(8);  // Increased from 4 to 8 rows
        orderSummary.setPrefWidth(660);    // Match the main content width
        orderSummary.setPrefHeight(200);   // Set a fixed height
        
        // Add some padding inside the TextArea
        orderSummary.setPadding(new Insets(10));
        
        orderSummary.setEditable(false);
        orderSummary.setWrapText(true);
        
        // Add a title/header to the order summary section
        Label summaryLabel = new Label("Order Summary");
        summaryLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        summaryLabel.setPadding(new Insets(0, 0, 5, 0));
        
        // Create a container for the label and TextArea
        VBox summaryContainer = new VBox(5);  // 5px spacing between elements
        summaryContainer.getChildren().addAll(summaryLabel, orderSummary);
        
        return orderSummary;
    }

    private void setupButtonActions(HBox buttonSection, ToggleGroup sizeGroup, 
                                  ToggleGroup flavorGroup, TextArea orderSummary) {
        FoodOrder myOrder = new FoodOrder("0001");

        // Get buttons from the button section
        Button addPizza = (Button) buttonSection.getChildren().get(0);
        Button remove = (Button) buttonSection.getChildren().get(1);
        Button placeOrder = (Button) buttonSection.getChildren().get(3);

        addPizza.setOnAction(event -> {
            Pizza.Flavor flavor = (Pizza.Flavor) flavorGroup.getSelectedToggle().getUserData();
            Pizza.Size size = (Pizza.Size) sizeGroup.getSelectedToggle().getUserData();
            Food myFood = new Pizza(flavor, size);
            myOrder.addFood(myFood);
            
            // Format the order display with more structure
            if (orderSummary.getText().isEmpty()) {
                orderSummary.appendText("Current Order:\n" +
                                      "----------------------------------------\n");
            }
            orderSummary.appendText(String.format("• %s\n", myFood.toString()));
        });

        remove.setOnAction(event -> {
            myOrder.removeLastItem();
            orderSummary.clear();
            orderSummary.appendText("Order Status: UPDATED\n" +
                                  "----------------------------------------\n" +
                                  myOrder + "\n");
        });

        placeOrder.setOnAction(event -> {
            orderSummary.clear();
            if (myOrder.getItems().isEmpty()) {
                orderSummary.appendText("No items in the order.\n");
            } else {
                orderSummary.appendText("Order Status: PLACED\n" +
                                      "----------------------------------------\n" +
                                      "Items:\n" +
                                      myOrder + "\n" +
                                      "----------------------------------------\n" +
                                      "Total Items: " + myOrder.getItems().size() + "\n");
            }
        });
    }



    public static void main(String[] args) {
        launch();
    }
}