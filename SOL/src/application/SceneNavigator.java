package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneNavigator {
    private static Stage stage;
     
    // Define o stage principal
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static Stage getStage() {
    	return stage;
    }

    // Carrega a nova cena
    public static void navigateTo(String fxmlFile, String cssFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(SceneNavigator.class.getResource(cssFile)).toExternalForm());;
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}