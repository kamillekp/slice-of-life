package controllers;

import application.SharedControl;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;

import javafx.geometry.HPos;

import application.Pair;
import application.SceneNavigator;
import application.Sugary;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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
/*
    ToggleGroup group1 = new ToggleGroup();
    RadioButton option1Group1 = new RadioButton("Opção 1 (10)");
    RadioButton option2Group1 = new RadioButton("Opção 2 (20)");
    RadioButton option3Group1 = new RadioButton("Opção 3 (30)");
        option1Group1.setToggleGroup(group1);
        option2Group1.setToggleGroup(group1);
        option3Group1.setToggleGroup(group1);

    // Associar valores aos botões do primeiro grupo usando setUserData
        option1Group1.setUserData(10);
        option2Group1.setUserData(20);
        option3Group1.setUserData(30);

    // Segundo ToggleGroup e suas opções
    ToggleGroup group2 = new ToggleGroup();
    RadioButton option1Group2 = new RadioButton("Opção A (5)");
    RadioButton option2Group2 = new RadioButton("Opção B (15)");
    RadioButton option3Group2 = new RadioButton("Opção C (25)");
        option1Group2.setToggleGroup(group2);
        option2Group2.setToggleGroup(group2);
        option3Group2.setToggleGroup(group2);

    // Associar valores aos botões do segundo grupo usando setUserData
        option1Group2.setUserData(5);
        option2Group2.setUserData(15);
        option3Group2.setUserData(25);

    // Listener para atualizar o valor total
    ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
        int value1 = group1.getSelectedToggle() != null ? (int) group1.getSelectedToggle().getUserData() : 0;
        int value2 = group2.getSelectedToggle() != null ? (int) group2.getSelectedToggle().getUserData() : 0;
        int total = value1 + value2;
        totalLabel.setText("Valor total: " + total);
    };

    // Adiciona o listener aos dois grupos
        group1.selectedToggleProperty().addListener(updateTotalListener);
        group2.selectedToggleProperty().addListener(updateTotalListener);
*/
    public void initialize() {
        Sugary sugary = new Sugary();

        Pair[] topping = sugary.getTopping();
        Pair[] fruit = sugary.getFruit();
        Pair[] condiment = sugary.getCondiment();

        ToggleGroup pizzaToppingFlavor = new ToggleGroup();
        ToggleGroup pizzaFruitFlavor = new ToggleGroup();
        ToggleGroup pizzaCondimentGroup = new ToggleGroup();

        setIngredientsPrice(pizzaToppingFlavor, "topping", toppingsGrid);
        setIngredientsPrice(pizzaFruitFlavor, "fruit", fruitsGrid);
        setIngredientsPrice(pizzaCondimentGroup, "condiment", condimentsGrid);


        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double toppingPrice = pizzaToppingFlavor.getSelectedToggle() != null ? (double) pizzaToppingFlavor.getSelectedToggle().getUserData() : 0;
            double fruitPrice = pizzaFruitFlavor.getSelectedToggle() != null ? (double) pizzaFruitFlavor.getSelectedToggle().getUserData() : 0;
            double condimentPrice = pizzaCondimentGroup.getSelectedToggle() != null ? (double) pizzaCondimentGroup.getSelectedToggle().getUserData() : 0;


            // O valor inicial vai ter que ser corrigido depois
            double total = SharedControl.getInstance().getOrder().getPrice() + toppingPrice + fruitPrice + condimentPrice;

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", total));
        };

        pizzaToppingFlavor.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFruitFlavor.selectedToggleProperty().addListener(updateTotalListener);
        pizzaCondimentGroup.selectedToggleProperty().addListener(updateTotalListener);

        // O valor inicial vai ter que ser corrigido depois
       priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice()));

    }

    private void setIngredientsPrice(ToggleGroup toggleGroup, String ingredientsType, GridPane container) {
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

        for(int i = 0; i < numIngredientes; i++) {

            // Obtendo o texto do RadioButton
            String text = ingredients[i].getOption();

            // Configurando os dados do usuário
            double price = ingredients[i].getPrice();


            RadioButton a = new RadioButton(text + "\n" + "R$ " + String.format("%.2f", price));
            a.setUserData(price);
            a.setToggleGroup(toggleGroup);

            a.setStyle("-fx-text-fill: #303030;");

            container.add(a, col++, row);

            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void goToChoosePizzaPage (){
        SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
    }

    @FXML
    private void goToSaltyFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }

    @FXML
    private void goToNextPage (){
        SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
    }

    @FXML
    private void goToSugaryFlavorsPage (){
        SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
    }
}
