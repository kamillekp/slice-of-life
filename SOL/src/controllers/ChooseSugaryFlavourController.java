package controllers;

import application.*;


import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ChooseSugaryFlavourController extends ChooseFlavourController {

    @FXML
    private GridPane toppingsGrid;

    @FXML
    private GridPane fruitsGrid;

    @FXML
    private Label flavourNumberLabel;

    @FXML
    private GridPane condimentsGrid;

    private static final String FLAVOUR_TYPE = "doce";
    private static final SugaryMenu SUGARY_MENU_INGREDIENTS = new SugaryMenu();


    private final ToggleGroup pizzaToppingGroup = new ToggleGroup();
    private final ToggleGroup pizzaFruitGroup = new ToggleGroup();
    private final ToggleGroup pizzaCondimentGroup = new ToggleGroup();



    @Override
    public void initialize() {
        flavourNumberLabel.setText("ESCOLHA OS INGREDIENTES DO " + (sharedControl.getFlavorsCounter() + 1) + "º SABOR"); // O contador de sabores já selecionados começa em 0

        initializeToggleGroup(SUGARY_MENU_INGREDIENTS.getIngredientsByType("topping"), pizzaToppingGroup, toppingsGrid);
        initializeToggleGroup(SUGARY_MENU_INGREDIENTS.getIngredientsByType("fruit"), pizzaFruitGroup, fruitsGrid);
        initializeToggleGroup(SUGARY_MENU_INGREDIENTS.getIngredientsByType("condiment"), pizzaCondimentGroup, condimentsGrid);

        double previousFlavourPrice = initializeFlavorPrice();


        if (previousFlavourPrice == 0) // a pizza não existe
            goAheadButton.setDisable(true);
        else {
            currentPizza.setPrice(currentPizza.getPrice() - previousFlavourPrice);
            currentOrder.setTotalPrice(currentOrder.getPrice() - previousFlavourPrice);
        }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double currentFlavourPrice = getCurrentFlavourPrice();

            
            if (currentFlavourPrice == 0)
                goAheadButton.setDisable(true);
            else
                goAheadButton.setDisable(false);

            if(currentPizza.getFlavors().size() > sharedControl.getFlavorsCounter()) {
                System.out.println(currentPizza.getFlavors().get(sharedControl.getFlavorsCounter()).getIngredients());
                System.out.println(currentFlavourPrice);
            }

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentFlavourPrice));

        };

        pizzaToppingGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFruitGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaCondimentGroup.selectedToggleProperty().addListener(updateTotalListener);

        priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + previousFlavourPrice));

        goBackButton.setOnAction(event -> goBack(FLAVOUR_TYPE));
        goAheadButton.setOnAction(event -> goAhead(FLAVOUR_TYPE));
        changeFlavourTypeButton.setOnAction(event -> goToSaltyFlavorsPage());

        if(pizzaToppingGroup.getSelectedToggle() == null && currentPizza.getFlavors().size() > sharedControl.getFlavorsCounter()){
            System.out.println(currentPizza.getFlavors().get(sharedControl.getFlavorsCounter()).getIngredients());
            System.out.println(previousFlavourPrice);
            }
    }

    @Override
    double initializeFlavorPrice() {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        Flavour currentFlavour;
        double flavourPrice = 0.0;

        if (currentFlavorNumber < currentPizza.getFlavors().size()) {
            currentFlavour = currentPizza.getFlavors().get(currentFlavorNumber);

            if(currentFlavour.getType().equals(FLAVOUR_TYPE)) {
                for (String ingredient : currentFlavour.getIngredients()) {

                    String ingredientType = SUGARY_MENU_INGREDIENTS.findType(ingredient);


                    double priceOfCurrentIngredient = SUGARY_MENU_INGREDIENTS.getPrice(ingredientType, ingredient);

                    switch (ingredientType) {
                        case "topping" -> selectButtonByIngredient(pizzaToppingGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                        case "fruit" -> selectButtonByIngredient(pizzaFruitGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                        case "condiment" -> selectButtonByIngredient(pizzaCondimentGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    }

                    flavourPrice += priceOfCurrentIngredient;
                }
            }

            else
                currentFlavour = new Flavour(FLAVOUR_TYPE);

        }

        return flavourPrice;
    }

    @Override
    double getCurrentFlavourPrice() {

        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingGroup.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitGroup.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

        double toppingPrice = toppingSelectedToggle != null ? (double) toppingSelectedToggle.getUserData() : 0;
        double fruitPrice = fruitSelectedToggle != null ? (double) fruitSelectedToggle.getUserData() : 0;
        double condimentPrice = condimentSelectedToggle != null ? (double) condimentSelectedToggle.getUserData() : 0;


        return toppingPrice + fruitPrice + condimentPrice;
    }

    @Override
    ArrayList<String> getIngredients() {
        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingGroup.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitGroup.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

        String toppingIngredient = toppingSelectedToggle != null ? toppingSelectedToggle.getText().split("\n")[0] : null;
        String fruitIngredient = fruitSelectedToggle != null ? fruitSelectedToggle.getText().split("\n")[0] : null;
        String condimentIngredient = condimentSelectedToggle != null ? condimentSelectedToggle.getText().split("\n")[0] : null;

        ArrayList<String> ingredients = new ArrayList<>();

        if (toppingIngredient != null)
            ingredients.add(toppingIngredient);
        if (fruitIngredient != null)
            ingredients.add(fruitIngredient);
        if (condimentIngredient != null)
            ingredients.add(condimentIngredient);

        return ingredients;
    }
}


