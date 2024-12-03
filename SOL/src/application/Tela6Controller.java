package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Tela6Controller extends Controller{

	private FinalState state;
	
    @FXML
    void BackToReviewPage(ActionEvent event) {
    	this.state.returnState();
    	
    }
    
    public void setState(FinalState state) {
    	this.state = state;
    }
    
    public FinalState getState(){
    	return this.state;
    }
}
 