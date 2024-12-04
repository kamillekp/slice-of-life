
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

 
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			SceneNavigator.setStage(primaryStage);
			
			
			ArrayList<String> conjunto1 = new  ArrayList<String>();
			ArrayList<String> conjunto2 = new  ArrayList<String>();
			ArrayList<String> conjunto3 = new  ArrayList<String>();
			
			
			conjunto1.add("chocolate");
			conjunto1.add("MMs");
			conjunto1.add("leite condensado");
			conjunto1.add("");
			
			conjunto2.add("gorgonzola");
			conjunto2.add("frango");
			conjunto2.add("pimentao");
			conjunto2.add("");
			
			conjunto3.add("cheddar");
			conjunto3.add("bacon");
			conjunto3.add("");
			conjunto3.add("");
			
			
			Flavor flavor1 = new Flavor("doce", conjunto1);
			Flavor flavor2 = new Flavor("salgado", conjunto2);
			Flavor flavor3 = new Flavor("salgado", conjunto3);
			
			 
			ArrayList<Flavor> sabores = new ArrayList<Flavor>();
			sabores.add(flavor2);
			sabores.add(flavor1);
			
			
			Pizza pizza1 = new Pizza();
			pizza1.setBorder(false);
			pizza1.setNumFlavor(2);
			pizza1.setSize(0);
			pizza1.setFlavors(sabores);
			
			
			Pizza pizza2 = new Pizza();
			pizza2.setBorder(true);
			pizza2.setNumFlavor(2);
			pizza2.setSize(1);
			pizza2.setFlavors(sabores);
			
			Pizza pizza3 = new Pizza();
			pizza3.setBorder(false);
			pizza3.setNumFlavor(2);
			pizza3.setSize(2);
			pizza3.setFlavors(sabores);
		 
			Pizza pizza4 = new Pizza();
			pizza4.setBorder(false);
			pizza4.setNumFlavor(2);
			pizza4.setSize(2);
			pizza4.setFlavors(sabores);
			
			Pizza pizza5 = new Pizza();
			pizza5.setBorder(false);
			pizza5.setNumFlavor(2);
			pizza5.setSize(2);
			pizza5.setFlavors(sabores);

		     
			SharedState.getInstance().addPizza(pizza1);
			SharedState.getInstance().addPizza(pizza2);
			SharedState.getInstance().addPizza(pizza3);
			SharedState.getInstance().addPizza(pizza4);
			SharedState.getInstance().addPizza(pizza5);

			 
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Tela5.fxml"));
			Parent root = loader.load();
			
 
			
 
			
			
			
			
			Scene scene = new Scene(root);

			
			scene.getStylesheets().add(getClass().getResource("styles/Tela5.css").toExternalForm());
			
 
			primaryStage.setScene(scene);
			primaryStage.setTitle("Um teste");
			primaryStage.show();
			 
			
			
			
		     
		    
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
}



