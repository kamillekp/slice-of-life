package controllers;

import application.Order;
import application.Pizza;
import application.SceneNavigator;
import application.SharedControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

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
    private GridPane greenLeavesGrid;

    private final SharedControl sharedControl = SharedControl.getInstance();
    private final Pizza currentPizza = sharedControl.getPizza();
    private final Order currentOrder = sharedControl.getOrder();

    void initialize(){
        flavourNumberLabel.setText("ESCOLHA OS INGREDIENTES DO " + (SharedControl.getInstance().getFlavorsCounter() + 1) + "º SABOR");

        ToggleGroup pizzaCheeseGroup = new ToggleGroup();
        ToggleGroup pizzaVegetableGroup = new ToggleGroup();
        ToggleGroup pizzaProteinGroup = new ToggleGroup();
        ToggleGroup pizzaGreenLeavesGroup = new ToggleGroup();

    }








    @FXML private void goToSweetFlavorsPage (){
        SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
    }

    @FXML public void goToChoosePizzaPage (){
        SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
    }

    @FXML private void goToNextPage (){
        SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela5.css");
    }

    @FXML private void goToSaltyFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }
}
