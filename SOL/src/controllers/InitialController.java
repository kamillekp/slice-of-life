package controllers;

import application.SceneNavigator;
import application.SharedControl;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe controller para a tela inicial da aplicação.
 * Lida com a entrada do usuário para nome e sobrenome, e navega para a página de seleção de pizza.
 */
public class InitialController {
    @FXML private TextField nameInput;
    @FXML private TextField surnameInput;

    @FXML private Label informNameLabel;
    @FXML private Button startOrderButton;

    public void initialize() {
        startOrderButton.setDisable(true);
        informNameLabel.setText("Informe seu nome e sobrenome para prosseguir!");

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            String name = nameInput.getText();
            String surname = surnameInput.getText();

            if(!name.isEmpty() && !surname.isEmpty()) {
                startOrderButton.setDisable(false);
                informNameLabel.setText("");
            }
            else {
                startOrderButton.setDisable(true);
                informNameLabel.setText("Informe seu nome e sobrenome para prosseguir!");
            }
        };

        nameInput.textProperty().addListener(updateTotalListener);
        surnameInput.textProperty().addListener(updateTotalListener);

    }

    /**
     * Lida com a ação de navegar para a página de seleção de pizza.
     * Recupera o nome e sobrenome do usuário dos campos de entrada, define-os no objeto de pedido compartilhado,
     * e navega para a próxima cena se ambos os campos não estiverem vazios.
     */
    @FXML private void goToChoosePizzaPage() {
        String name = nameInput.getText();
        String surname = surnameInput.getText();

        SharedControl.getInstance().getOrder().getClient().setName(name);
        SharedControl.getInstance().getOrder().getClient().setSurname(surname);

        SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");

    }
}