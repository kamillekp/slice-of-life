package controllers;

import application.SceneNavigator;
import application.SharedControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class InitialController {
    @FXML private TextField nameInput;
    @FXML private TextField surnameInput;

    @FXML private void goToChoosePizzaPage() {
        String name = nameInput.getText();
        String surname = surnameInput.getText();

        if(!name.isEmpty() && !surname.isEmpty()) {
            SharedControl.getInstance().getOrder().getClient().setName(name);
            SharedControl.getInstance().getOrder().getClient().setSurname(surname);

            //System.out.println(SharedControl.getInstance().getOrder().getClient().getName() + " " + SharedControl.getInstance().getOrder().getClient().getSurname());

            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        }
    }
}
