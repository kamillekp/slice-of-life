package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneNavigator.setStage(primaryStage);
			SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}



