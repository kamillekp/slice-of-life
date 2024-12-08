package controllers;

import application.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FinalController {
    @FXML
    private void BackToReviewPage(ActionEvent event) {
        SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
    }
}
