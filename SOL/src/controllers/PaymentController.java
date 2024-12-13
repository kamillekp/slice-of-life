package controllers;

import application.*;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class PaymentController {
    @FXML private ToggleGroup typePaymentButtons;
    @FXML private TextField nameInput, validityInput, numberCardInput, cvvInput;
    @FXML private TextField cityInput, streetInput, complementInput, numberAddressInput, zipInput;

    @FXML private Label priceText;

    @FXML public void initialize() {

        priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice()));

        if(SharedControl.getInstance().getOrder().getClient().isRegister()){
            boolean isCardFilled = SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getName() != null
                    && SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getNumber() != null
                    && SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getCvv() != null
                    && SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getValidity() != null;

            String typePaymentText = SharedControl.getInstance().getOrder().getClient().getPayment().getType();

            typePaymentButtons.selectToggle(ScreenPaymentState.getTgTypePayment());

            cityInput.setText(SharedControl.getInstance().getOrder().getClient().getAddress().getcity());
            streetInput.setText(SharedControl.getInstance().getOrder().getClient().getAddress().getStreet());
            numberAddressInput.setText(SharedControl.getInstance().getOrder().getClient().getAddress().getNumber());
            zipInput.setText(SharedControl.getInstance().getOrder().getClient().getAddress().getZipCode());
            if(SharedControl.getInstance().getOrder().getClient().getAddress().getComplement() != null){
                complementInput.setText(SharedControl.getInstance().getOrder().getClient().getAddress().getComplement());
            }

            if(typePaymentText.equals("Cartão de crédito/débito") && isCardFilled) {
                cardInformationActivated();

                nameInput.setText(SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getName());
                validityInput.setText(SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getValidity());
                numberCardInput.setText(SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getNumber());
                cvvInput.setText(SharedControl.getInstance().getOrder().getClient().getPayment().getCard().getCvv());
            }
        }
    }

    @FXML private void backToFlavorPage(){

        SharedControl.getInstance().getOrder().getPizzas().removeLast();
        String lastPizzaFlavorType = SharedControl.getInstance().getPizza().getFlavors().getLast().getType();

        if(lastPizzaFlavorType.equals("doce"))
            SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
        else if(lastPizzaFlavorType.equals("salgado"))
            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }

    @FXML private void cardInformationActivated(){
        nameInput.setDisable(false);
        validityInput.setDisable(false);
        numberCardInput.setDisable(false);
        cvvInput.setDisable(false);
    }

    @FXML private void cardInformationInactivated(){
        nameInput.setDisable(true);
        validityInput.setDisable(true);
        numberCardInput.setDisable(true);
        cvvInput.setDisable(true);

        nameInput.setText("");
        validityInput.setText("");
        numberCardInput.setText("");
        cvvInput.setText("");
    }

    @FXML private void goToReviewPage(){
        boolean somethingOnButton = typePaymentButtons.getSelectedToggle() != null;

        boolean cardInformationFilled = !nameInput.getText().isEmpty()
                        && !validityInput.getText().isEmpty()
                        && !numberCardInput.getText().isEmpty()
                        && !cvvInput.getText().isEmpty();

        boolean addressInformationFilled = !cityInput.getText().isEmpty()
                        && !streetInput.getText().isEmpty()
                        && !numberAddressInput.getText().isEmpty()
                        && !zipInput.getText().isEmpty();

        boolean someCardInfo = !nameInput.getText().isEmpty()
                        || !validityInput.getText().isEmpty()
                        || !numberCardInput.getText().isEmpty()
                        || !cvvInput.getText().isEmpty();

        boolean someAddressInfo = !cityInput.getText().isEmpty()
                        || !streetInput.getText().isEmpty()
                        || !numberAddressInput.getText().isEmpty()
                        || !zipInput.getText().isEmpty()
                        || !complementInput.getText().isEmpty();

        if(!SharedControl.getInstance().getOrder().getClient().isRegister()){
            if(somethingOnButton && addressInformationFilled){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();

                ScreenPaymentState.setTgTypePayment((typePaymentButtons.getSelectedToggle()));

                String complement = null;
                String city = cityInput.getText();
                String street = streetInput.getText();
                String numberAddress = numberAddressInput.getText();
                String zip = zipInput.getText();
                if(!complementInput.getText().isEmpty()){
                    complement = complementInput.getText();
                }

                SharedControl.getInstance().getOrder().getClient().initAddress(street, numberAddress, city, zip, complement);

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(cardInformationFilled){
                        String name = nameInput.getText();
                        String validity = validityInput.getText();
                        String numberCard = numberCardInput.getText();
                        String cvv = cvvInput.getText();

                        SharedControl.getInstance().getOrder().getClient().initPayment(typePayment, new Card(name, validity, numberCard, cvv));
                        SharedControl.getInstance().getOrder().getClient().changeRegister();

                        //SharedControl.getInstance().getOrder().getClient().print();
                        SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                    }
                }
                else{
                    SharedControl.getInstance().getOrder().getClient().initPayment(typePayment, new Card());
                    SharedControl.getInstance().getOrder().getClient().changeRegister();

                    //SharedControl.getInstance().getOrder().getClient().print();
                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
        }
        else{
            if(somethingOnButton){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(SharedControl.getInstance().getOrder().getClient().getPayment().getType().equals(typePayment)) {
                        if (someCardInfo) {
                            String name, validity, numberCard, cvv;

                            if (!nameInput.getText().isEmpty()) {
                                name = nameInput.getText();
                                SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setName(name);
                            }
                            if (!validityInput.getText().isEmpty()) {
                                validity = validityInput.getText();
                                SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setValidity(validity);
                            }
                            if (!numberCardInput.getText().isEmpty()) {
                                numberCard = numberCardInput.getText();
                                SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setNumber(numberCard);
                            }
                            if (!cvvInput.getText().isEmpty()) {
                                cvv = cvvInput.getText();
                                SharedControl.getInstance().getOrder().getClient().getPayment().getCard().setCvv(cvv);
                            }

                            //SharedControl.getInstance().getOrder().getClient().getPayment().print();
                            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                        }
                    }
                    else{
                        if(cardInformationFilled) {
                            String name = nameInput.getText();
                            String validity = validityInput.getText();
                            String numberCard = numberCardInput.getText();
                            String cvv = cvvInput.getText();

                            SharedControl.getInstance().getOrder().getClient().getPayment().setType(typePayment);
                            SharedControl.getInstance().getOrder().getClient().getPayment().setCard(new Card(name, validity, numberCard, cvv));

                            //SharedControl.getInstance().getOrder().getClient().getPayment().print();
                            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
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

                    SharedControl.getInstance().getOrder().getClient().getPayment().setType(typePayment);

                    //SharedControl.getInstance().getOrder().getClient().getPayment().print();
                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
            if(someAddressInfo){
                String city, street, numberAddress, zip;

                if(!cityInput.getText().isEmpty()){
                    city = cityInput.getText();
                    SharedControl.getInstance().getOrder().getClient().getAddress().setCity(city);
                }
                if(!streetInput.getText().isEmpty()) {
                    street = streetInput.getText();
                    SharedControl.getInstance().getOrder().getClient().getAddress().setStreet(street);
                }
                if(!numberAddressInput.getText().isEmpty()) {
                    numberAddress = numberAddressInput.getText();
                    SharedControl.getInstance().getOrder().getClient().getAddress().setNumber(numberAddress);
                }
                if(!zipInput.getText().isEmpty()) {
                    zip = zipInput.getText();
                    SharedControl.getInstance().getOrder().getClient().getAddress().setZipCode(zip);
                }

                //SharedControl.getInstance().getOrder().getClient().getAddress().print();
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
            if(!somethingOnButton && !someAddressInfo){
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
        }
    }
}
