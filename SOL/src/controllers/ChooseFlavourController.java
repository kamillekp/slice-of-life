package controllers;

import application.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public abstract class ChooseFlavourController {
    @FXML
    Label priceText;

    @FXML
    Button changeFlavourTypeButton;

    @FXML
    Button goAheadButton;

    @FXML
    Button goBackButton;

    final SharedControl sharedControl = SharedControl.getInstance();
    final Pizza currentPizza = sharedControl.getPizza();
    final Order currentOrder = sharedControl.getOrder();

    public abstract void initialize();

    abstract double initializeFlavorPrice();

    abstract double getCurrentFlavourPrice();

    public void goBack(String type){
        int currentFlavorNumber = sharedControl.getFlavorsCounter();

        setCurrentFlavor(type, currentFlavorNumber);
        setCurrentPrice();

        if (currentFlavorNumber == 0)
            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        else {
            sharedControl.decrementFlavorsCounter();

            if(currentPizza.getFlavors().get(currentFlavorNumber - 1).getType().equals("salgado"))
                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
            else
                SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        }
    };

    public void goAhead(String type){
        int currentFlavorNumber = sharedControl.getFlavorsCounter();

        setCurrentFlavor(type, currentFlavorNumber);
        setCurrentPrice();

        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        // Se não tem cabe mais nenhum sabor na pizza
        if (currentFlavorNumber + 1 == currentPizza.getNumFlavor()) {
            // E eu não estou editando uma pizza
            if (!sharedControl.isEditingAddedPizza())
                pizzas.add(currentPizza);
            else {
                sharedControl.setPizza(pizzas.getLast());
                sharedControl.setEditingAddedPizza(false); // Terminou a pizza
            }

            if (currentOrder.getClient().isRegister()) {
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            } else {
                SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
            }
        } else {
            sharedControl.incrementFlavorsCounter();

            // Se o próximo sabor já foi editado
            if(SharedControl.getInstance().getPizza().getFlavors().size() > currentFlavorNumber + 1){
                if(SharedControl.getInstance().getPizza().getFlavors().get(currentFlavorNumber + 1).getType().equals("salgado"))
                    SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
                else
                    SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
            }
            else
                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");

        }
    };

    abstract ArrayList<String> getIngredients();

    void initializeToggleGroup(Pair[] ingredients, ToggleGroup toggleGroup, GridPane container) {
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

    /* Setters */

    private void setCurrentFlavor(String flavourType, int index) {
        ArrayList<String> ingredients = getIngredients();

        Flavour currentFlavour = new Flavour(flavourType, ingredients);

        if (index < currentPizza.getFlavors().size()) {
            currentPizza.getFlavors().set(index, currentFlavour);
        } else
            currentPizza.getFlavors().add(currentFlavour);

    }

    void setCurrentPrice() {
        double previousTotalPrice = currentOrder.getPrice();
        double previousCurrentPizzaPrice = currentPizza.getPrice();
        double flavourPrice = getCurrentFlavourPrice();

        currentPizza.setPrice(previousCurrentPizzaPrice + flavourPrice);
        currentOrder.setTotalPrice(previousTotalPrice + flavourPrice);

    }


    void goToSugaryFlavorsPage() {SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");}
    void goToSaltyFlavorsPage()  {SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");}

    void selectButtonByIngredient(ToggleGroup toggleGroup, String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof ChangeableButton button) {
                if (button.getText().equals(text)) {
                    toggleGroup.selectToggle(button);
                    break;
                }
            }
        }
    }

    static ChangeableButton createChangeableButton(ToggleGroup toggleGroup, Pair ingredient) {
        String option = ingredient.getOption();

        double price = ingredient.getPrice();

        ChangeableButton newButton = new ChangeableButton(option + "\n" + "R$ " + String.format("%.2f", price));

        newButton.setUserData(price);

        newButton.setToggleGroup(toggleGroup);

        newButton.setStyle("-fx-text-fill: #303030;");

        return newButton;
    }
}
