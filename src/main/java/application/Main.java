package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação JavaFX.
 * Inicializa a aplicação e navega para a tela inicial.
 */
public class Main extends Application {

	/**
	 * Método de inicialização da aplicação.
	 *
	 * @param primaryStage o estágio principal da aplicação, fornecido pela plataforma JavaFX
	 */
	@Override
	public void start(final Stage primaryStage) { // Parâmetro final
		try {
			new SharedControl();

			SceneNavigator.setStage(primaryStage);
			SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método principal que inicia a aplicação JavaFX.
	 *
	 * @param args os argumentos de linha de comando passados para a aplicação
	 */
	public static void main(final String[] args) { // Parâmetro final
		launch(args);
	}
}
