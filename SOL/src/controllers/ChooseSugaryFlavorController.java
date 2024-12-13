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
import java.util.List;

public class ChooseSugaryFlavorController {

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
    private GridPane toppingsGrid;

    @FXML
    private GridPane fruitsGrid;

    @FXML
    private GridPane condimentsGrid;

    @FXML
    private VBox toppingsVBox;

    @FXML
    private VBox fruitsVBox;

    @FXML
    private VBox condimentsVBox;

    private SharedControl sharedControl = SharedControl.getInstance();

    public void initialize() {
        flavourNumberLabel.setText("ESCOLHA OS INGREDIENTES DO " + (sharedControl.getFlavorsCounter() + 1) + "º SABOR");

        ToggleGroup pizzaToppingGroup = new ToggleGroup();
        ToggleGroup pizzaFruitGroup = new ToggleGroup();
        ToggleGroup pizzaCondimentGroup = new ToggleGroup();

        initializeToggleGroup(pizzaToppingGroup, "topping", toppingsGrid);
        initializeToggleGroup(pizzaFruitGroup, "fruit", fruitsGrid);
        initializeToggleGroup(pizzaCondimentGroup, "condiment", condimentsGrid);

        double previousFlavourPrice = initializeFlavorPrice(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup);

        if (previousFlavourPrice == 0) // a pizza não existe
            goAheadButton.setDisable(true);
        else {
            Pizza currentPizza = sharedControl.getPizza();
            Order currentOrder = sharedControl.getOrder();

            currentPizza.setPrice(currentPizza.getPrice() - previousFlavourPrice);
            currentOrder.setTotalPrice(currentOrder.getPrice() - previousFlavourPrice);
        }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double currentFlavorPrice = getCurrentFlavourPrice(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup);

            if (currentFlavorPrice == 0)
                goAheadButton.setDisable(true);
            else
                goAheadButton.setDisable(false);

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", sharedControl.getOrder().getPrice() + currentFlavorPrice));
        };

        pizzaToppingGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFruitGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaCondimentGroup.selectedToggleProperty().addListener(updateTotalListener);

        // O valor inicial vai ter que ser corrigido depois
       priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", sharedControl.getOrder().getPrice() + previousFlavourPrice));

       goBackButton.setOnAction(event -> goBack(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup));
       goAheadButton.setOnAction(event -> goAhead(pizzaToppingGroup, pizzaFruitGroup, pizzaCondimentGroup));
       changeFlavourTypeButton.setOnAction(event -> goToSaltyFlavorsPage());
    }


    private double initializeFlavorPrice(ToggleGroup pizzaToppingGroup, ToggleGroup pizzaFruitGroup, ToggleGroup pizzaCondimentGroup){
        Sugary sugary = new Sugary();
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        Flavour currentFlavour;
        double flavourPrice = 0.0;

        if(currentFlavorNumber < sharedControl.getPizza().getFlavors().size()){
            currentFlavour = sharedControl.getPizza().getFlavors().get(currentFlavorNumber);

            for(String ingredient : currentFlavour.getIngredients()) {

                String ingredientType = sugary.findType(ingredient);
                double priceOfCurrentIngredient = sugary.getPrice(ingredientType, ingredient);


                switch (ingredientType) {
                    case "topping" ->
                            selectButtonByIngredient(pizzaToppingGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    case "fruit" ->
                            selectButtonByIngredient(pizzaFruitGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                    case "condiment" ->
                            selectButtonByIngredient(pizzaCondimentGroup, ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));
                }

                flavourPrice += priceOfCurrentIngredient;
            }
        }

        return flavourPrice;
    }

    private static double getCurrentFlavourPrice(ToggleGroup pizzaToppingGroup, ToggleGroup pizzaFruitGroup, ToggleGroup pizzaCondimentGroup) {

        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingGroup.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitGroup.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

        double toppingPrice = toppingSelectedToggle != null ? (double) toppingSelectedToggle.getUserData() : 0;
        double fruitPrice = fruitSelectedToggle != null ? (double) fruitSelectedToggle.getUserData() : 0;
        double condimentPrice = condimentSelectedToggle != null ? (double) condimentSelectedToggle.getUserData() : 0;


        return  toppingPrice + fruitPrice + condimentPrice;

    }

    public void selectButtonByIngredient(ToggleGroup toggleGroup, String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof ChangeableButton UnselectableRadioGroup) {
                if (UnselectableRadioGroup.getText().equals(text)) {
                    toggleGroup.selectToggle(UnselectableRadioGroup);
                    break;
                }
            }
        }
    }


    private void initializeToggleGroup(ToggleGroup toggleGroup, String ingredientsType, GridPane container) {
        Sugary sugary = new Sugary();
        Pair[] ingredients = sugary.getIngredientsByType(ingredientsType);

        container.setHgap(container.getWidth() / 4); // Espaçamento horizontal entre as células
        container.setVgap(15); // Espaçamento vertical entre as células (botões)
        container.setPadding(new Insets(15, 0, 50, 50)); // Espaçamento entre o GridPane e os limites externos (borda laranja)

        // Configurar colunas para distribuir uniformemente
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        // Cada coluna ocupa 50% do espaço disponível dentro do GridPane
        col1.setPercentWidth(50);
        col2.setPercentWidth(50);

        container.getColumnConstraints().clear(); // Limpa colunas existentes (só pra garantir)
        container.getColumnConstraints().addAll(col1, col2); // Adiciona novas colunas

        // Garante que ele não vai cortar elementos e vai usar a altura que for necessária para abrigar todos os elementos
        container.setPrefHeight(Region.USE_COMPUTED_SIZE);
        container.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // Centraliza o texto em relação às colunas do GridPane
        container.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;

        for (Pair ingredient : ingredients) {

            // Obtendo o texto do
            ChangeableButton newButton = createChangeableButton(toggleGroup, ingredient);

            // Finalmente adiciona o botão ao GridPane
            container.add(newButton, col, row);

            // Preenche o gripane linha por linha com botões
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    private static ChangeableButton createChangeableButton(ToggleGroup toggleGroup, Pair ingredient) {
        String text = ingredient.getOption();

        // Configurando os dados do usuário
        double price = ingredient.getPrice();


        ChangeableButton newButton = new ChangeableButton(text + "\n" + "R$ " + String.format("%.2f", price));

        // Atribui o valor "price" ao botão
        newButton.setUserData(price);

        // Adiciona o botão ao ToggleGroup
        newButton.setToggleGroup(toggleGroup);

        // Consertando problemas do JavaFX
        newButton.setStyle("-fx-text-fill: #303030;");

        return newButton;
    }

    private void setCurrentFlavor(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup, int index){
        ArrayList<String> ingredients = getIngredients(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);

        Flavour currentFlavour = new Flavour("doce", ingredients);

        if(index < sharedControl.getPizza().getFlavors().size()){
            sharedControl.getPizza().getFlavors().set(index, currentFlavour);
        }
        else
            sharedControl.getPizza().getFlavors().add(currentFlavour);

    }

    private void setCurrentPrice(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        double previousTotalPrice = sharedControl.getOrder().getPrice();
        double previousCurrentPizzaPrice = sharedControl.getPizza().getPrice();
        double flavourPrice = getCurrentFlavourPrice(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);

        sharedControl.getPizza().setPrice(previousCurrentPizzaPrice + flavourPrice);
        sharedControl.getOrder().setTotalPrice(previousTotalPrice + flavourPrice);

    }

    private void goBack(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup) {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();

        setCurrentFlavor(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup, currentFlavorNumber);
        setCurrentPrice(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);


        if(currentFlavorNumber == 0)
            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        else {
            sharedControl.decrementFlavorsCounter();

            // Aqui deveria checar se o flavour anterior eh salgado ou doce
            SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        }
    }

    private void goAhead(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        int currentFlavorNumber =sharedControl.getFlavorsCounter();

        setCurrentFlavor(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup, currentFlavorNumber);
        setCurrentPrice(pizzaToppingFlavor, pizzaFruitFlavor, pizzaCondimentGroup);

        Order currentOrder = sharedControl.getOrder();
        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        if(currentFlavorNumber + 1 == sharedControl.getPizza().getNumFlavor()){

            if(!sharedControl.isEditingAddedPizza())
                pizzas.add(sharedControl.getPizza());
            else{
                sharedControl.setPizza(pizzas.getLast());
                sharedControl.setEditingAddedPizza(false);
            }

            if(currentOrder.getClient().isRegister()){
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
            else{
                SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
            }
        }


        // Aqui também deveria checar se o próximo flavour será editado. Se estiver, checa o tipo dele e já carrega na página do tipo
        else{
            sharedControl.incrementFlavorsCounter();
            SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        }
    }

    private ArrayList<String> getIngredients(ToggleGroup pizzaToppingFlavor, ToggleGroup pizzaFruitFlavor, ToggleGroup pizzaCondimentGroup){
        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingFlavor.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitFlavor.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

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
