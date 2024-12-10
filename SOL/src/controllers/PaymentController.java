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
    private void cardInformationActivated(){
        nameInput.setDisable(false);
        validityInput.setDisable(false);
        numberCardInput.setDisable(false);
        cvvInput.setDisable(false);
    }

    @FXML
    private void cardInformationInactivated(){
        nameInput.setDisable(true);
        validityInput.setDisable(true);
        numberCardInput.setDisable(true);
        cvvInput.setDisable(true);
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
                        && !numberAddressInput.getText().isEmpty()
                        && !zipInput.getText().isEmpty();

        if(!SharedControl.getInstance().getOrder().getClient().isRegister()){
            if(case0 && case2){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();

                String city = cityInput.getText();
                String street = streetInput.getText();
                String numberAddress = numberAddressInput.getText();
                String zip = zipInput.getText();
                if(!complementInput.getText().isEmpty()){
                    String complement = complementInput.getText();
                }

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(case1){
                        String name = nameInput.getText();
                        String validity = validityInput.getText();
                        String numberCard = numberCardInput.getText();
                        String cvv = cvvInput.getText();

                        SharedControl.getInstance().getOrder().getClient().changeRegister();
                        SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                    }
                }
                else{
                    SharedControl.getInstance().getOrder().getClient().changeRegister();
                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
        }
        else{
            if(case0){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(SharedControl.getInstance().getOrder().getClient().getPayment().getType().equals("Cartão de crédito/débito")) {
                        if (!nameInput.getText().isEmpty() || !validityInput.getText().isEmpty() || !numberCardInput.getText().isEmpty() || !cvvInput.getText().isEmpty()) {
                            String name, validity, numberCard, cvv;
                            if (!nameInput.getText().isEmpty()) {name = nameInput.getText();}
                            if (!validityInput.getText().isEmpty()) {validity = validityInput.getText();}
                            if (!numberCardInput.getText().isEmpty()) {numberCard = numberCardInput.getText();}
                            if (!cvvInput.getText().isEmpty()) {cvv = cvvInput.getText();}

                            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                        }
                        else{
                            if(case1) {
                                String name = nameInput.getText();
                                String validity = validityInput.getText();
                                String numberCard = numberCardInput.getText();
                                String cvv = cvvInput.getText();

                                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                            }
                        }
                    }
                }
                else {
                    if(SharedControl.getInstance().getOrder().getClient().getPayment().getType().equals("Cartão de crédito/débito")) {
                        SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setName(null);
                        SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setValidity(null);
                        SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setNumber(null);
                        SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setCvv(null);
                    }
                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
            if(!cityInput.getText().isEmpty() || !streetInput.getText().isEmpty() || !numberAddressInput.getText().isEmpty() || !zipInput.getText().isEmpty()){
                    String city, street, numberAddress, zip;

                    if(!cityInput.getText().isEmpty()){city = cityInput.getText();}
                    if(!streetInput.getText().isEmpty()) {street = streetInput.getText();}
                    if(!numberAddressInput.getText().isEmpty()) {numberAddress = numberAddressInput.getText();}
                    if(!zipInput.getText().isEmpty()) {zip = zipInput.getText();}

                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
        }
    }
}
