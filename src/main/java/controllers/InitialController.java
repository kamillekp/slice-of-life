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

    /**
     * Campo de entrada para o nome do usuário.
     * Permite que o usuário insira seu primeiro nome.
     */
    @FXML private TextField nameInput;

    /**
     * Campo de entrada para o sobrenome do usuário.
     * Permite que o usuário insira seu sobrenome.
     */
    @FXML private TextField surnameInput;

    /**
     * Rótulo que informa ao usuário para inserir seu nome e sobrenome.
     * Exibe uma mensagem de instrução até que os campos de nome e sobrenome sejam preenchidos.
     */
    @FXML private Label informNameLabel;

    /**
     * Botão que inicia o pedido.
     * Fica desabilitado até que o usuário preencha corretamente os campos de nome e sobrenome.
     */
    @FXML private Button startOrderButton;

    /** Inicializa as componentes exibidas na tela inicial.
     */
    public void initialize() {
        startOrderButton.setDisable(true);
        informNameLabel.setText("Informe seu nome e sobrenome para prosseguir!");

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            String name = nameInput.getText();
            String surname = surnameInput.getText();

            if (!name.isEmpty() && !surname.isEmpty()) {
                startOrderButton.setDisable(false);
                informNameLabel.setText("");
            } else {
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
