package controllers;

import application.SceneNavigator;
import javafx.fxml.FXML;

public class ChooseSaltyFlavorController {
    @FXML
    private void goToSweetFlavorsPage (){
        SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
    }

    @FXML
    private void goToChoosePizzaPage (){
        SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
    }

    @FXML
    private void goToNextPage (){
        SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
    }
}
