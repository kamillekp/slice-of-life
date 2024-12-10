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
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChooseSugaryFlavorController {

    @FXML
    Label priceText;

    @FXML
    GridPane toppingsGrid;

    @FXML
    GridPane fruitsGrid;

    @FXML
    GridPane condimentsGrid;

    @FXML
    VBox toppingsVBox;

    @FXML
    VBox fruitsVBox;

    @FXML
    VBox condimentsVBox;

    @FXML
    Button changeFlavourTypeButton;

    @FXML
    Button goAheadButton;

    @FXML
    Button goBackButton;

    @FXML
    Label flavorNumberLabel;

    public void initialize() {

        flavorNumberLabel.setText("ESCOLHA OS INGREDIENTES DO " + (SharedControl.getInstance().getFlavorsCounter() + 1) + "º SABOR");

        ToggleGroup pizzaToppingGroup = new ToggleGroup();
        ToggleGroup pizzaFruitGroup = new ToggleGroup();
        ToggleGroup pizzaCondimentGroup = new ToggleGroup();

        InitializeToggleGroup(pizzaToppingGroup, "topping", toppingsGrid);
        InitializeToggleGroup(pizzaFruitGroup, "fruit", fruitsGrid);
        InitializeToggleGroup(pizzaCondimentGroup, "condiment", condimentsGrid);

        double flavorPrice = initializeFlavorPrice(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup);

        if(flavorPrice == 0)
            goAheadButton.setDisable(true);
        else
            SharedControl.getInstance().getOrder().setTotalPrice(SharedControl.getInstance().getOrder().getPrice() - flavorPrice);

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double total = getTotal(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup);

            if(total == 0)
                goAheadButton.setDisable(true);
            else
                goAheadButton.setDisable(false);

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice() + total));
        };

        pizzaToppingGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFruitGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaCondimentGroup.selectedToggleProperty().addListener(updateTotalListener);

        // O valor inicial vai ter que ser corrigido depois
       priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice() + flavorPrice));

       goBackButton.setOnAction(event -> goBack(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup));
       goAheadButton.setOnAction(event -> goAhead(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup));
       changeFlavourTypeButton.setOnAction(event -> goToSaltyFlavorsPage());
    }


    private double initializeFlavorPrice(ToggleGroup pizzaToppingGroup, ToggleGroup pizzaFruitGroup, ToggleGroup pizzaCondimentGroup){
        Sugary sugary = new Sugary();
        int currentFlavorNumber = SharedControl.getInstance().getFlavorsCounter();
        Flavor currentFlavor;
        double flavorPrice = 0.0;

        if(currentFlavorNumber < SharedControl.getInstance().getPizza().getFlavors().size()){
            currentFlavor = SharedControl.getInstance().getPizza().getFlavors().get(currentFlavorNumber);

            for(String ingredient : currentFlavor.getIngredients()) {

                String ingredientType = sugary.findType(ingredient);
                double priceOfCurrentIngredient = sugary.getPrice(ingredientType, ingredient);


                if(ingredientType.equals("topping"))
                    selectUnselectableRadioGroupByText(pizzaToppingGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                else if(ingredientType.equals("fruit"))
                    selectUnselectableRadioGroupByText(pizzaFruitGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                else if(ingredientType.equals("condiment"))
                    selectUnselectableRadioGroupByText(pizzaCondimentGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                flavorPrice += priceOfCurrentIngredient;
            }
        }

        return flavorPrice;
    }

    private static double getTotal(ToggleGroup pizzaToppingGroup, ToggleGroup pizzaFruitGroup, ToggleGroup pizzaCondimentGroup) {

        UnselectableRadioGroup toppingSelectedToggle = (UnselectableRadioGroup) pizzaToppingGroup.getSelectedToggle();
        UnselectableRadioGroup fruitSelectedToggle = (UnselectableRadioGroup) pizzaFruitGroup.getSelectedToggle();
        UnselectableRadioGroup condimentSelectedToggle = (UnselectableRadioGroup) pizzaCondimentGroup.getSelectedToggle();

        double toppingPrice = toppingSelectedToggle != null ? (double) toppingSelectedToggle.getUserData() : 0;
        double fruitPrice = fruitSelectedToggle != null ? (double) fruitSelectedToggle.getUserData() : 0;
        double condimentPrice = condimentSelectedToggle != null ? (double) condimentSelectedToggle.getUserData() : 0;

        // O valor inicial vai ter que ser corrigido depois
        return  toppingPrice + fruitPrice + condimentPrice;

    }

    public void selectUnselectableRadioGroupByText(ToggleGroup toggleGroup, String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof UnselectableRadioGroup UnselectableRadioGroup) {
                if (UnselectableRadioGroup.getText().equals(text)) {
                    toggleGroup.selectToggle(UnselectableRadioGroup);
                    break;
                }
            }
        }
    }


    private void InitializeToggleGroup(ToggleGroup toggleGroup, String ingredientsType, GridPane container) {
        Sugary sugary = new Sugary();
        Pair[] ingredients = sugary.getIngredientsByType(ingredientsType);
        int numIngredientes = ingredients.length;


        container.setHgap(container.getWidth() / 4); // Espaçamento horizontal entre as células
        container.setVgap(15); // Espaçamento vertical entre as células
        container.setPadding(new Insets(15, 0, 50, 50)); // Espaçamento entre o GridPane e os limites externos

        // Configurar colunas para distribuir uniformemente
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setPercentWidth(50); // Cada coluna ocupa 50% do espaço disponível
        col2.setPercentWidth(50);

        container.getColumnConstraints().clear(); // Limpa colunas existentes
        container.getColumnConstraints().addAll(col1, col2); // Adiciona novas colunas


        container.setPrefHeight(Region.USE_COMPUTED_SIZE);
        container.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);


        container.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;

        for (Pair ingredient : ingredients) {

            // Obtendo o texto do UnselectableRadioGroup
            String text = ingredient.getOption();

            // Configurando os dados do usuário
            double price = ingredient.getPrice();


            UnselectableRadioGroup newButton = new UnselectableRadioGroup(text + "\n" + "R$ " + String.format("%.2f", price));
            newButton.setUserData(price);
            newButton.setToggleGroup(toggleGroup);
            newButton.setStyle("-fx-text-fill: #303030;");

            newButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                if (toggleGroup.getSelectedToggle() == newButton) {
                    System.out.println("Aqui");
                    //toggleGroup.selectToggle(null);
                }
            });

            container.add(newButton, col++, row);

            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    private void setCurrentFlavor(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup, int index){
        ArrayList<String> ingredients = getIngredients(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);


        Flavor currentFlavor = new Flavor("doce", ingredients);

        if(index < SharedControl.getInstance().getPizza().getFlavors().size()){
            SharedControl.getInstance().getPizza().getFlavors().set(index, currentFlavor);
        }
        else
            SharedControl.getInstance().getPizza().getFlavors().add(currentFlavor);

    }

    private void setCurrentPrice(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        double previousPrice = SharedControl.getInstance().getOrder().getPrice();
        double flavorPrice = getTotal(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);

        SharedControl.getInstance().getOrder().setTotalPrice(previousPrice + flavorPrice);

    }

    private void goBack(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup) {
        int currentFlavorNumber = SharedControl.getInstance().getFlavorsCounter();

        setCurrentFlavor(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup, currentFlavorNumber);
        setCurrentPrice(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);


        if(currentFlavorNumber == 0)
            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        else {
            SharedControl.getInstance().decrementFlavorsCounter();

            // Aqui deveria checar se o flavor anterior eh salgado ou doce
            SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        }
    }

    private void goAhead(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        int currentFlavorNumber = SharedControl.getInstance().getFlavorsCounter();

        setCurrentFlavor(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup, currentFlavorNumber);
        setCurrentPrice(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);

        if(currentFlavorNumber + 1 == SharedControl.getInstance().getPizza().getNumFlavor()){
            SharedControl.getInstance().getOrder().getPizzas().add(SharedControl.getInstance().getPizza());
            if(SharedControl.getInstance().getOrder().getClient().isRegister()){
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
            else{
                SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
            }
        }


        // Aqui também deveria checar se o próximo flavour será editado. Se estiver, checa o tipo dele e já carrega na página do tipo
        else{
            SharedControl.getInstance().incrementFlavorsCounter();
            SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        }
    }

    private ArrayList<String> getIngredients(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        UnselectableRadioGroup toppingSelectedToggle = (UnselectableRadioGroup) pizzaToppingFlavor.getSelectedToggle();
        UnselectableRadioGroup fruitSelectedToggle = (UnselectableRadioGroup) pizzaFruitFlavor.getSelectedToggle();
        UnselectableRadioGroup condimentSelectedToggle = (UnselectableRadioGroup) pizzaCondimentGroup.getSelectedToggle();

        String toppingIngredient = toppingSelectedToggle != null ? toppingSelectedToggle.getText().split("\n")[0] : null;
        String fruitIngredient = fruitSelectedToggle != null ? fruitSelectedToggle.getText().split("\n")[0] : null;
        String condimentIngredient = condimentSelectedToggle != null ? condimentSelectedToggle.getText().split("\n")[0] : null;

        ArrayList<String> ingredients = new ArrayList<>();

        if(toppingIngredient != null)
            ingredients.add(toppingIngredient);
        if(fruitIngredient != null)
            ingredients.add(fruitIngredient);
        if(condimentIngredient != null)
            ingredients.add(condimentIngredient);

        return ingredients;
    }

    private void goToSaltyFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }
}
