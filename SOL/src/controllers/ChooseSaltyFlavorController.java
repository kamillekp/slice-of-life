package controllers;

import application.*;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Region;

import java.util.ArrayList;

public class ChooseSaltyFlavorController {

    @FXML
    private Label priceText;

    @FXML
    private Button changeFlavourTypeButton;

    @FXML
    private Button goAheadButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Label flavourNumberLabel;


    @FXML
    private GridPane cheesesGrid;

    @FXML
    private GridPane vegetablesGrid;

    @FXML
    private GridPane proteinsGrid;

    @FXML
    private GridPane greensGrid;

    private final SharedControl sharedControl = SharedControl.getInstance();
    private final Pizza currentPizza = sharedControl.getPizza();
    private final Order currentOrder = sharedControl.getOrder();

    public void initialize() {
        flavourNumberLabel.setText("ESCOLHA OS INGREDIENTES DO " + (sharedControl.getFlavorsCounter() + 1) + "º SABOR");

        ToggleGroup pizzaCheeseGroup = new ToggleGroup();
        ToggleGroup pizzaVegetableGroup = new ToggleGroup();
        ToggleGroup pizzaProteinGroup = new ToggleGroup();
        ToggleGroup pizzaGreensGroup = new ToggleGroup();

        initializeToggleGroup(pizzaCheeseGroup, "cheese", cheesesGrid);
        initializeToggleGroup(pizzaVegetableGroup, "vegetable", vegetablesGrid);
        initializeToggleGroup(pizzaProteinGroup, "protein", proteinsGrid);
        initializeToggleGroup(pizzaGreensGroup, "green leaf", greensGrid);

        double previousFlavourPrice = initializeFlavorPrice(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

        if (previousFlavourPrice == 0) // a pizza não existe
            goAheadButton.setDisable(true);
        else {
            currentPizza.setPrice(currentPizza.getPrice() - previousFlavourPrice);
            currentOrder.setTotalPrice(currentOrder.getPrice() - previousFlavourPrice);
        }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double currentFlavorPrice = getCurrentFlavourPrice(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

            if (currentFlavorPrice == 0)
                goAheadButton.setDisable(true);
            else
                goAheadButton.setDisable(false);

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentFlavorPrice));
        };

        pizzaCheeseGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaVegetableGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaProteinGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaGreensGroup.selectedToggleProperty().addListener(updateTotalListener);

        priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + previousFlavourPrice));

        goBackButton.setOnAction(event -> goBack(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup));
        goAheadButton.setOnAction(event -> goAhead(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup));
        changeFlavourTypeButton.setOnAction(event -> goToSugaryFlavorsPage());
    }

    private double initializeFlavorPrice(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {
        Salty salty = new Salty();
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        Flavour currentFlavour;
        double flavourPrice = 0.0;

        if (currentFlavorNumber < currentPizza.getFlavors().size()) {
            currentFlavour = currentPizza.getFlavors().get(currentFlavorNumber);

            for (String ingredient : currentFlavour.getIngredients()) {

                String ingredientType = salty.findType(ingredient);
                double priceOfCurrentIngredient = salty.getPrice(ingredientType, ingredient);

                switch (ingredientType) {
                    case "cheese" ->
                            selectButtonByIngredient(pizzaCheeseGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    case "vegetable" ->
                            selectButtonByIngredient(pizzaVegetableGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    case "protein" ->
                            selectButtonByIngredient(pizzaProteinGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    case "green leaf" ->
                            selectButtonByIngredient(pizzaGreensGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                }

                flavourPrice += priceOfCurrentIngredient;
            }
        }

        return flavourPrice;
    }

    private static double getCurrentFlavourPrice(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {

        ChangeableButton cheeseSelectedToggle = (ChangeableButton) pizzaCheeseGroup.getSelectedToggle();
        ChangeableButton vegetableSelectedToggle = (ChangeableButton) pizzaVegetableGroup.getSelectedToggle();
        ChangeableButton proteinSelectedToggle = (ChangeableButton) pizzaProteinGroup.getSelectedToggle();
        ChangeableButton greensSelectedToggle = (ChangeableButton) pizzaGreensGroup.getSelectedToggle();

        double cheesePrice = cheeseSelectedToggle != null ? (double) cheeseSelectedToggle.getUserData() : 0;
        double vegetablePrice = vegetableSelectedToggle != null ? (double) vegetableSelectedToggle.getUserData() : 0;
        double proteinPrice = proteinSelectedToggle != null ? (double) proteinSelectedToggle.getUserData() : 0;
        double greensPrice = greensSelectedToggle != null ? (double) greensSelectedToggle.getUserData() : 0;

        return cheesePrice + vegetablePrice + proteinPrice + greensPrice;
    }

    private void goBack(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();

        setCurrentFlavor(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup, currentFlavorNumber);
        setCurrentPrice(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

        if (currentFlavorNumber == 0)
            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        else {
            sharedControl.decrementFlavorsCounter();
            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
        }
    }

    private void goAhead(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();

        setCurrentFlavor(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup, currentFlavorNumber);
        setCurrentPrice(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        if (currentFlavorNumber + 1 == currentPizza.getNumFlavor()) {

            if (!sharedControl.isEditingAddedPizza())
                pizzas.add(currentPizza);
            else {
                sharedControl.setPizza(pizzas.getLast());
                sharedControl.setEditingAddedPizza(false);
            }

            if (currentOrder.getClient().isRegister()) {
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            } else {
                SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
            }
        } else {
            sharedControl.incrementFlavorsCounter();
            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
        }
    }

    private void setCurrentFlavor(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup, int index) {
        ArrayList<String> ingredients = getIngredients(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

        Flavour currentFlavour = new Flavour("salgado", ingredients);

        if (index < currentPizza.getFlavors().size()) {
            currentPizza.getFlavors().set(index, currentFlavour);
        } else
            currentPizza.getFlavors().add(currentFlavour);

    }

    private void setCurrentPrice(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {
        double previousTotalPrice = currentOrder.getPrice();
        double previousCurrentPizzaPrice = currentPizza.getPrice();
        double flavourPrice = getCurrentFlavourPrice(pizzaCheeseGroup, pizzaVegetableGroup, pizzaProteinGroup, pizzaGreensGroup);

        currentPizza.setPrice(previousCurrentPizzaPrice + flavourPrice);
        currentOrder.setTotalPrice(previousTotalPrice + flavourPrice);

    }

    private ArrayList<String> getIngredients(ToggleGroup pizzaCheeseGroup, ToggleGroup pizzaVegetableGroup, ToggleGroup pizzaProteinGroup, ToggleGroup pizzaGreensGroup) {
        ChangeableButton cheeseSelectedToggle = (ChangeableButton) pizzaCheeseGroup.getSelectedToggle();
        ChangeableButton vegetableSelectedToggle = (ChangeableButton) pizzaVegetableGroup.getSelectedToggle();
        ChangeableButton proteinSelectedToggle = (ChangeableButton) pizzaProteinGroup.getSelectedToggle();
        ChangeableButton greensSelectedToggle = (ChangeableButton) pizzaGreensGroup.getSelectedToggle();

        String cheeseIngredient = cheeseSelectedToggle != null ? cheeseSelectedToggle.getText().split("\n")[0] : null;
        String vegetableIngredient = vegetableSelectedToggle != null ? vegetableSelectedToggle.getText().split("\n")[0] : null;
        String proteinIngredient = proteinSelectedToggle != null ? proteinSelectedToggle.getText().split("\n")[0] : null;
        String greensIngredient = greensSelectedToggle != null ? greensSelectedToggle.getText().split("\n")[0] : null;

        ArrayList<String> ingredients = new ArrayList<>();

        if (cheeseIngredient != null)
            ingredients.add(cheeseIngredient);
        if (vegetableIngredient != null)
            ingredients.add(vegetableIngredient);
        if (proteinIngredient != null)
            ingredients.add(proteinIngredient);
        if (greensIngredient != null)
            ingredients.add(greensIngredient);

        return ingredients;
    }

    private void goToSugaryFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
    }

    public void selectButtonByIngredient(ToggleGroup toggleGroup, String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof ChangeableButton button) {
                if (button.getText().equals(text)) {
                    toggleGroup.selectToggle(button);
                    break;
                }
            }
        }
    }

    private void initializeToggleGroup(ToggleGroup toggleGroup, String ingredientsType, GridPane container) {
        Salty salty = new Salty();
        Pair[] ingredients = salty.getIngredientsByType(ingredientsType);

        container.setHgap(container.getWidth() / 4);
        container.setVgap(15);
        container.setPadding(new Insets(15, 0, 50, 50));

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setPercentWidth(50);
        col2.setPercentWidth(50);

        container.getColumnConstraints().clear();
        container.getColumnConstraints().addAll(col1, col2);

        container.setPrefHeight(Region.USE_COMPUTED_SIZE);
        container.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        container.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;

        for (Pair ingredient : ingredients) {

            ChangeableButton newButton = createChangeableButton(toggleGroup, ingredient);

            container.add(newButton, col, row);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    private static ChangeableButton createChangeableButton(ToggleGroup toggleGroup, Pair ingredient) {
        String option = ingredient.getOption();

        double price = ingredient.getPrice();

        ChangeableButton newButton = new ChangeableButton(option + "\n" + "R$ " + String.format("%.2f", price));

        newButton.setUserData(price);

        newButton.setToggleGroup(toggleGroup);

        newButton.setStyle("-fx-text-fill: #303030;");

        return newButton;
    }
}
