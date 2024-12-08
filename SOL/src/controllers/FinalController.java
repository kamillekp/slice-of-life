package controllers;

import application.SceneNavigator;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FinalController {
    @FXML
    private void BackToReviewPage(ActionEvent event) {
        new SharedControl();
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }
}
