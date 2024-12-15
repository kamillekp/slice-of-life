package controllers;

import application.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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

    @FXML private Button goToChooseFlavourButton;

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

            goToChooseFlavourButton.setDisable(!areAllButtonGroupsSelected());

            totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentPropertiesPrice));
        };

        pizzaSizeButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFlavorButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaBorderButtons.selectedToggleProperty().addListener(updateTotalListener);

        totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice()));

        currentPropertiesPrice = previousPropertiesPrice;

        goToChooseFlavourButton.setDisable(!areAllButtonGroupsSelected());

        currentOrder.setTotalPrice(currentOrder.getPrice() - previousPropertiesPrice);
    }


    private boolean areAllButtonGroupsSelected(){
        return pizzaSizeButtons.getSelectedToggle() != null && pizzaFlavorButtons.getSelectedToggle() != null && pizzaBorderButtons.getSelectedToggle() != null;
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
        boolean pizzaInfosPressed =  areAllButtonGroupsSelected();

            if(pizzaInfosPressed){
                RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
                RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
                RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();


                String size = selectedPSButton.getText();


                String numFlavorText = selectedPFButton.getText();
                int numFlavor = Integer.parseInt(numFlavorText);


                String borderText = selectedPBButton.getText();
                boolean border = borderText.equals("Com Borda");


                Pizza currentPizza = SharedControl.getInstance().getPizza();
                Order currentOrder = SharedControl.getInstance().getOrder();

                if(currentPizza == null) {
                    System.out.println("Pizza is null");
                    SharedControl.getInstance().initPizza();
                    currentPizza = SharedControl.getInstance().getPizza();
                }

                currentPizza.setSize(size);
                currentPizza.setNumFlavor(numFlavor);
                currentPizza.setBorder(border);

                currentOrder.setTotalPrice(currentOrder.getPrice() + currentPropertiesPrice);
                currentPizza.setPrice(currentPizza.getPrice() + currentPropertiesPrice);

                SharedControl.getInstance().resetCounter();

                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
            }
    }
}


