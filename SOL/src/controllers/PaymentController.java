package controllers;

import application.SceneNavigator;
import application.SharedControl;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PaymentController {
    @FXML
    ToggleGroup typePaymentButtons;

    @FXML
    TextField nameInput, validityInput, numberCardInput, cvvInput;

    @FXML
    TextField cityInput, streetInput, complementInput, numberAddressInput, zipInput;

    @FXML
    private void goToFlavorPage(){
        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }

    @FXML
    private void goToReviewPage(){
        boolean case0 = typePaymentButtons.getSelectedToggle() != null;

        boolean case1 = !nameInput.getText().isEmpty()
                        && !validityInput.getText().isEmpty()
                        && !numberCardInput.getText().isEmpty()
                        && !cvvInput.getText().isEmpty();

        boolean case2 = !cityInput.getText().isEmpty()
                        && !streetInput.getText().isEmpty()
                        && !complementInput.getText().isEmpty()
                        && !numberAddressInput.getText().isEmpty()
                        && !zipInput.getText().isEmpty();

        if(!SharedControl.getInstance().getOrder().getClient().isRegister()){
            if(case0 && case2){

                if(case1){

                }

                SharedControl.getInstance().getOrder().getClient().changeRegister();
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
        }
        else{
            if(case0){

            }
            if(case1){

            }
            if(case2){

            }
            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
        }
    }
}
