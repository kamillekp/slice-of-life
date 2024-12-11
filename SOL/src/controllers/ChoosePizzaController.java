package controllers;

import application.SceneNavigator;
import application.ScreenChoosePizzaState;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ChoosePizzaController {
    @FXML private Label totalPriceLabel;
    @FXML private ToggleGroup pizzaSizeButtons;
    @FXML private ToggleGroup pizzaFlavorButtons;
    @FXML private ToggleGroup pizzaBorderButtons;

    /*public void initialize(){

        totalPriceLabel.setText("TOTAL DO PEDIDO: " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice()));

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double total = getTotal(pizzaSizeButtons, pizzaFlavorButtons, pizzaBorderButtons);

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice() + total));
        };

    }

    private double getTotal(ToggleGroup pizzaSizeButtons, ToggleGroup pizzaFlavorButtons, ToggleGroup pizzaBorderButtons) {

    } */

    @FXML public void initialize() {
        if(ScreenChoosePizzaState.getTgPizzaSizeButtons() != null && ScreenChoosePizzaState.getTgPizzaFlavorButtons() != null && ScreenChoosePizzaState.getTgPizzaBorderButtons() != null) {
            pizzaSizeButtons.selectToggle(ScreenChoosePizzaState.getTgPizzaSizeButtons());
            pizzaFlavorButtons.selectToggle(ScreenChoosePizzaState.getTgPizzaFlavorButtons());
            pizzaBorderButtons.selectToggle(ScreenChoosePizzaState.getTgPizzaBorderButtons());
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
                /*double flavorPrice = switch (selectedPFButton.getId()) {
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
                SharedControl.getInstance().getPizza().setSize(size);
                SharedControl.getInstance().getPizza().setNumFlavor(numFlavor);
                SharedControl.getInstance().getPizza().setBorder(border);
                SharedControl.getInstance().changeFinishedPizzaInfo();

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
                /*double flavorPrice = switch (selectedPFButton.getId()) {
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

            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
        }
    }
}


