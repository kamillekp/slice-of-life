package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			new SharedControl();

			SceneNavigator.setStage(primaryStage);
			SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}



