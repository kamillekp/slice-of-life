package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneNavigator.setStage(primaryStage);
			SceneNavigator.navigateTo("/views/Tela3.fxml", "/styles/Tela3.css");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}



