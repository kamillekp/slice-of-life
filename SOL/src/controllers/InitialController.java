package controllers;

import application.SceneNavigator;
import application.SharedControl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Classe controller para a tela inicial da aplicação.
 * Lida com a entrada do usuário para nome e sobrenome, e navega para a página de seleção de pizza.
 */
public class InitialController {
    @FXML private TextField nameInput;
    @FXML private TextField surnameInput;

    /**
     * Lida com a ação de navegar para a página de seleção de pizza.
     * Recupera o nome e sobrenome do usuário dos campos de entrada, define-os no objeto de pedido compartilhado,
     * e navega para a próxima cena se ambos os campos não estiverem vazios.
     */
    @FXML private void goToChoosePizzaPage() {
        String name = nameInput.getText();
        String surname = surnameInput.getText();

        if(!name.isEmpty() && !surname.isEmpty()) {
            SharedControl.getInstance().getOrder().getClient().setName(name);
            SharedControl.getInstance().getOrder().getClient().setSurname(surname);

            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        }
    }
}