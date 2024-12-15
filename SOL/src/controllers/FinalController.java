package controllers;

import application.SceneNavigator;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FinalController {
    @FXML private void BackToReviewPage(ActionEvent event) {
        SharedControl.getInstance().resetInstance();
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }
}
