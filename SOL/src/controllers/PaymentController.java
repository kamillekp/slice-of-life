package controllers;

import application.*;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;


public class PaymentController {
    @FXML private ToggleGroup typePaymentButtons;
    @FXML private TextField nameInput, validityInput, numberCardInput, cvvInput;
    @FXML private TextField cityInput, streetInput, complementInput, numberAddressInput, zipInput;

    @FXML private Label priceText;

    private final SharedControl sharedControl = SharedControl.getInstance();
    
    @FXML public void initialize() {
        Client client = sharedControl.getOrder().getClient();

        priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", sharedControl.getOrder().getPrice()));

        if(client.isRegister()){
            Card card = client.getPayment().getCard();
            String typePaymentText = client.getPayment().getType();

            for(Toggle toggle: typePaymentButtons.getToggles()){
                RadioButton radioButton = (RadioButton) toggle;
                if(typePaymentText.equals(radioButton.getText())){
                    radioButton.setSelected(true);
                }
            }


            if(cityInput.getText().isEmpty() || streetInput.getText().isEmpty() || numberAddressInput.getText().isEmpty() || zipInput.getText().isEmpty()){
                cityInput.setText(client.getAddress().getCity());
                streetInput.setText(client.getAddress().getStreet());
                numberAddressInput.setText(client.getAddress().getNumber());
                zipInput.setText(client.getAddress().getZipCode());

                if(client.getAddress().getComplement() != null){
                    complementInput.setText(client.getAddress().getComplement());
                }
            }

            boolean isCardFilled = card.getName() != null
                    && card.getNumber() != null
                    && card.getCvv() != null
                    && card.getValidity() != null;

            if(typePaymentText.equals("Cartão de crédito/débito") && isCardFilled){

                cardInformationActivated();
                if (nameInput.getText().isEmpty() || validityInput.getText().isEmpty() || numberCardInput.getText().isEmpty()) {
                    nameInput.setText(card.getName());
                    validityInput.setText(card.getValidity());
                    numberCardInput.setText(card.getNumber());
                    cvvInput.setText(card.getCvv());
                }
            }
        }
    }

    @FXML private void backToFlavorPage(){
        sharedControl.getOrder().getPizzas().removeLast();
        String lastPizzaFlavorType = sharedControl.getPizza().getFlavors().getLast().getType();

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
        Client client = sharedControl.getOrder().getClient();

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

        if(!client.isRegister()){
            if(somethingOnButton && addressInformationFilled){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();


                String complement = null;
                String city = cityInput.getText();
                String street = streetInput.getText();
                String numberAddress = numberAddressInput.getText();
                String zip = zipInput.getText();
                if(!complementInput.getText().isEmpty()){
                    complement = complementInput.getText();
                }

                client.initAddress(street, numberAddress, city, zip, complement);

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(cardInformationFilled){
                        String name = nameInput.getText();
                        String validity = validityInput.getText();
                        String numberCard = numberCardInput.getText();
                        String cvv = cvvInput.getText();

                        client.initPayment(typePayment, new Card(name, validity, numberCard, cvv));
                        client.changeRegister();

                        //client.print();
                        SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                    }
                }
                else{
                    client.initPayment(typePayment, new Card());
                    client.changeRegister();

                    //client.print();
                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
        }
        else{
            if(somethingOnButton){
                RadioButton selectedPButton = (RadioButton) typePaymentButtons.getSelectedToggle();
                String typePayment = selectedPButton.getText();
                Card card = client.getPayment().getCard();

                if(typePayment.equals("Cartão de crédito/débito")){
                    if(client.getPayment().getType().equals(typePayment)) {
                        if (someCardInfo) {
                            String name, validity, numberCard, cvv;

                            if (!nameInput.getText().isEmpty()) {
                                name = nameInput.getText();
                                card.setName(name);
                            }
                            if (!validityInput.getText().isEmpty()) {
                                validity = validityInput.getText();
                                card.setValidity(validity);
                            }
                            if (!numberCardInput.getText().isEmpty()) {
                                numberCard = numberCardInput.getText();
                                card.setNumber(numberCard);
                            }
                            if (!cvvInput.getText().isEmpty()) {
                                cvv = cvvInput.getText();
                                card.setCvv(cvv);
                            }

                            //client.getPayment().print();
                            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                        }
                    }
                    else{
                        if(cardInformationFilled) {
                            String name = nameInput.getText();
                            String validity = validityInput.getText();
                            String numberCard = numberCardInput.getText();
                            String cvv = cvvInput.getText();

                            client.getPayment().setType(typePayment);
                            client.getPayment().setCard(new Card(name, validity, numberCard, cvv));

                            SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                        }
                    }
                }
                else {
                    if(client.getPayment().getType().equals("Cartão de crédito/débito")) {
                        card.setName(null);
                        card.setValidity(null);
                        card.setNumber(null);
                        card.setCvv(null);
                    }

                    client.getPayment().setType(typePayment);

                    SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
                }
            }
            if(someAddressInfo){
                String city, street, numberAddress, zip;

                if(!cityInput.getText().isEmpty()){
                    city = cityInput.getText();
                    client.getAddress().setCity(city);
                }
                if(!streetInput.getText().isEmpty()) {
                    street = streetInput.getText();
                    client.getAddress().setStreet(street);
                }
                if(!numberAddressInput.getText().isEmpty()) {
                    numberAddress = numberAddressInput.getText();
                    client.getAddress().setNumber(numberAddress);
                }
                if(!zipInput.getText().isEmpty()) {
                    zip = zipInput.getText();
                    client.getAddress().setZipCode(zip);
                }

                //client.getAddress().print();
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
            if(!somethingOnButton && !someAddressInfo){
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            }
        }
    }
}
