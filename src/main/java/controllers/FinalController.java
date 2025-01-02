package controllers;

import application.SceneNavigator;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador da página final, responsável por exibir as imagens e navegar de volta à página de revisão.
 */
public class FinalController {

    /**
     * Imagem exibida na interface gráfica, utilizada para o primeiro conteúdo visual.
     */
    @FXML private ImageView pizzaImage;

    /**
     * Imagem exibida na interface gráfica, utilizada para o segundo conteúdo visual.
     */
    @FXML private ImageView deliveryImage;

    /**
     * Inicializa as imagens exibidas na página final.
     * As imagens são carregadas e atribuídas às respectivas views.
     */
    public void initialize() {
        Image image1 = new Image(getClass().getResourceAsStream("/assets/image1.png"));
        pizzaImage.setImage(image1);

        Image image2 = new Image(getClass().getResourceAsStream("/assets/image2.png"));
        deliveryImage.setImage(image2);
    }

    /**
     * Método acionado para voltar à página de revisão.
     * Reseta a instância compartilhada e navega para a página de revisão.
     *
     * @param event O evento que aciona a navegação.
     */
    @FXML private void backToReviewPage(final ActionEvent event) {
        SharedControl.getInstance().resetInstance();
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }
}
