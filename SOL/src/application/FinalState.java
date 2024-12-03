package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FinalState extends State {
	Tela6Controller controller;
	
    public FinalState(Client client, String fxmlFile, String cssFile) {
        super(client);
        
	    try {
	        // Carregar o FXML
	        FXMLLoader loader = new FXMLLoader(ReviewState.class.getResource(fxmlFile));
	        Parent root = loader.load();

	        // Obter o controlador
	        this.controller = loader.getController();
	        
	        // Configurar o estado antes de exibir a cena
	        this.controller.setState(this);

	        // Criar a cena e aplicá-la ao estágio
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(ReviewState.class.getResource(cssFile).toExternalForm());
	       
	        SceneNavigator.getStage().setScene(scene);
	        SceneNavigator.getStage().show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }

    public void processOrder() {
        this.nextState();
    }

    public void nextState() {
        this.getClient().setState(new InitialState(this.getClient()));
    }
    
    
    public void returnState(){
    	this.getClient().setState(new ReviewState(this.getClient(), "views/Tela5.fxml", "styles/Tela5.css"));
    }
    
}
