package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import application.SceneNavigator;

public class Tela6Controller {

	
    @FXML
    void BackToReviewPage(ActionEvent event) {
    	SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
    }

}
