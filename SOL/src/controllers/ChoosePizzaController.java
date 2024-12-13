package controllers;

import application.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;


public class ChoosePizzaController {
    @FXML private Label totalPriceLabel;
    @FXML private ToggleGroup pizzaSizeButtons;
    @FXML private ToggleGroup pizzaFlavorButtons;
    @FXML private ToggleGroup pizzaBorderButtons;


    @FXML private Label smallPizzaPrice;
    @FXML private Label mediumPizzaPrice;
    @FXML private Label bigPizzaPrice;
    @FXML private Label biggestPizzaPrice;

    @FXML private Label borderPrice;
    @FXML private Label noBorderPrice;

    @FXML private Label fourFlavourPrice;
    @FXML private Label oneFlavourPrice;
    @FXML private Label threeFlavourPrice;
    @FXML private Label twoFlavourPrice;

    private double currentPropertiesPrice;


    @FXML public void initialize() {
        final Label[] numFlavourPrices = {oneFlavourPrice, twoFlavourPrice, threeFlavourPrice, fourFlavourPrice};
        final Label[] borderPrices = {borderPrice, noBorderPrice};
        final Label[] sizePrices = {smallPizzaPrice, mediumPizzaPrice, bigPizzaPrice, biggestPizzaPrice};

       initializeButtons(numFlavourPrices, sizePrices, borderPrices);


       Order currentOrder = SharedControl.getInstance().getOrder();
       Pizza currentPizza = SharedControl.getInstance().getPizza();

       double previousPropertiesPrice = 0;

       if(currentPizza != null){
           previousPropertiesPrice = getCurrentPropertiesPrice(pizzaSizeButtons, pizzaFlavorButtons, pizzaBorderButtons);
           currentPizza.setPrice(currentPizza.getPrice() - previousPropertiesPrice);
       }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            currentPropertiesPrice = getCurrentPropertiesPrice(pizzaSizeButtons, pizzaFlavorButtons, pizzaBorderButtons);
            totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentPropertiesPrice));
        };

        pizzaSizeButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFlavorButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaBorderButtons.selectedToggleProperty().addListener(updateTotalListener);

        totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice()));

        currentPropertiesPrice = previousPropertiesPrice;

        currentOrder.setTotalPrice(currentOrder.getPrice() - previousPropertiesPrice);
    }



    private double getCurrentPropertiesPrice(ToggleGroup pizzaSizeButtons, ToggleGroup pizzaFlavorButtons, ToggleGroup pizzaBorderButtons){
        RadioButton numFlavourSelectedButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
        RadioButton sizeSelectedButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
        RadioButton borderSelectedButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

        double numFlavourPrice = numFlavourSelectedButton != null ? (double) numFlavourSelectedButton.getUserData() : 0;
        double sizePrice = sizeSelectedButton != null ? (double) sizeSelectedButton.getUserData() : 0;
        double borderPrice = borderSelectedButton != null ? (double) borderSelectedButton.getUserData() : 0;


        return  numFlavourPrice + sizePrice + borderPrice;
    }


    private void initializeButtons(Label[] numFlavourPrices, Label[] sizePrices, Label[] borderPrices) {
        setButtonsPrices(pizzaSizeButtons, "sizes", sizePrices);
        setButtonsPrices(pizzaFlavorButtons, "number of flavours", numFlavourPrices);
        setButtonsPrices(pizzaBorderButtons, "border", borderPrices);

        Pizza currentPizza = SharedControl.getInstance().getPizza();


        if(currentPizza != null) {
            int numFlavour = currentPizza.getNumFlavor();

            String numFlavourText = String.format("%d", numFlavour);

            String borderText = currentPizza.isBorder() ? "Com Borda" : "Sem Borda";

            selectButtonByText(pizzaSizeButtons, currentPizza.getSize());
            selectButtonByText(pizzaFlavorButtons, numFlavourText);
            selectButtonByText(pizzaBorderButtons, borderText);
        }

    }

    private void selectButtonByText(ToggleGroup toggleGroup, String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            RadioButton currentButton = (RadioButton) toggle;

            if(currentButton.getText().equals(text)) {
                currentButton.setSelected(true);
                return;
            }
        }
    }


    private void setButtonsPrices(ToggleGroup toggleGroup, String type, Label[] priceLabels) {
        PizzaInfo pizzaInfo = new PizzaInfo();


        int counter = 0;

        for (Toggle toggle :toggleGroup.getToggles()) {
            RadioButton currentButton = (RadioButton) toggle;

            double price = pizzaInfo.getPrice(type, currentButton.getText());

            currentButton.setUserData(price);

            String priceText = "R$ " + String.format("%.2f", price);
            priceLabels[counter].setText(priceText);

            counter++;
        }
    }


    @FXML private void backToInitialPage() {
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }

    @FXML private void goToChooseFlavorPage(ActionEvent event) {
        boolean pizzaInfosPressed =  pizzaSizeButtons.getSelectedToggle() != null
                                     && pizzaFlavorButtons.getSelectedToggle() != null
                                     && pizzaBorderButtons.getSelectedToggle() != null;



        if(!SharedControl.getInstance().isFinishedPizzaInfo()){
            if(pizzaInfosPressed){
                RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
                RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
                RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

                ScreenChoosePizzaState.setTgPizzaSizeButtons(selectedPSButton);
                ScreenChoosePizzaState.setTgPizzaFlavorButtons(selectedPFButton);
                ScreenChoosePizzaState.setTgPizzaBorderButtons(selectedPBButton);

                System.out.println(ScreenChoosePizzaState.getTgPizzaSizeButtons());

                String size = selectedPSButton.getText();
                /*double sizePrice = switch (selectedPSButton.getId()) {
                    case "option0PSG" -> 25;
                    case "option1PSG" -> 30;
                    case "option2PSG" -> 35;
                    default -> 40;
                };*/

                String numFlavorText = selectedPFButton.getText();
                int numFlavor = Integer.parseInt(numFlavorText);
                /*double flavourPrice = switch (selectedPFButton.getId()) {
                    case "option0PFG" -> 0;
                    case "option1PFG" -> 1;
                    case "option2PFG" -> 2;
                    default -> 3;
                };*/

                String borderText = selectedPBButton.getText();
                boolean border = borderText.equals("Com Borda");
                /*double borderPrice = switch (selectedPBButton.getId()) {
                    case "option0PBG" -> 1;
                    default -> 0;
                };*/


                SharedControl.getInstance().initPizza();

                Pizza currentPizza = SharedControl.getInstance().getPizza();
                Order currentOrder = SharedControl.getInstance().getOrder();

                currentPizza.setSize(size);
                currentPizza.setNumFlavor(numFlavor);
                currentPizza.setBorder(border);

                SharedControl.getInstance().changeFinishedPizzaInfo();

                currentOrder.setTotalPrice(currentOrder.getPrice() + currentPropertiesPrice);
                currentPizza.setPrice(currentPizza.getPrice() + currentPropertiesPrice);

                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
            }
        }
        else{
            if(pizzaSizeButtons.getSelectedToggle() != null){
                RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
                String size = selectedPSButton.getText();
                /*double sizePrice = switch (selectedPSButton.getId()) {
                    case "option0PSG" -> 25;
                    case "option1PSG" -> 30;
                    case "option2PSG" -> 35;
                    default -> 40;
                };*/
                SharedControl.getInstance().getPizza().setSize(size);
            }
            if(pizzaFlavorButtons.getSelectedToggle() != null){
                RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
                String numFlavorText = selectedPFButton.getText();
                int numFlavor = Integer.parseInt(numFlavorText);
                /*double flavourPrice = switch (selectedPFButton.getId()) {
                    case "option0PFG" -> 0;
                    case "option1PFG" -> 1;
                    case "option2PFG" -> 2;
                    default -> 3;
                };*/


                int numPreviouslyAddedFlavors = SharedControl.getInstance().getPizza().getFlavors().size();

                while(numPreviouslyAddedFlavors > numFlavor){
                    SharedControl.getInstance().getPizza().getFlavors().remove(numPreviouslyAddedFlavors - 1);
                    numPreviouslyAddedFlavors--;
                }

                SharedControl.getInstance().getPizza().setNumFlavor(numFlavor);



            }
            if(pizzaBorderButtons.getSelectedToggle() != null){
                RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();
                String borderText = selectedPBButton.getText();
                boolean border = borderText.equals("Com Borda");
                /*double borderPrice = switch (selectedPBButton.getId()) {
                    case "option0PBG" -> 1;
                    default -> 0;
                };*/
                SharedControl.getInstance().getPizza().setBorder(border);
            }

            Pizza currentPizza = SharedControl.getInstance().getPizza();
            Order currentOrder = SharedControl.getInstance().getOrder();

            currentOrder.setTotalPrice(currentOrder.getPrice() + currentPropertiesPrice);
            currentPizza.setPrice(currentPizza.getPrice() + currentPropertiesPrice);

            SharedControl.getInstance().resetCounter();
            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
        }
    }
}


