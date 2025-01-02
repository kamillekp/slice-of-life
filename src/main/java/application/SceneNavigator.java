package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitária para navegação entre cenas na aplicação.
 * Permite configurar o stage principal e carregar novas cenas com arquivos FXML e CSS.
 */
public final class SceneNavigator {
    /** O stage principal da aplicação. */
    private static Stage stage;

    // Impede a criação de instâncias da classe SceneNavigator.
    private SceneNavigator() {
        throw new UnsupportedOperationException("SceneNavigator is a utility class and cannot be instantiated.");
    }

    /**
     * Define o stage principal da aplicação.
     *
     * @param primaryStage O stage principal a ser configurado.
     */
    public static void setStage(final Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Navega para uma nova cena especificada pelos arquivos FXML e CSS.
     *
     * @param fxmlFile O arquivo FXML da cena a ser carregada.
     * @param cssFile O arquivo CSS a ser aplicado à cena.
     */
    public static void navigateTo(final String fxmlFile, final String cssFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(cssFile);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
