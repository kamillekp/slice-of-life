package controllers;

import application.SceneNavigator;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ChoosePizzaController {

    @FXML
    private void goToInitialPage(ActionEvent event) {
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }

    @FXML
    private ToggleGroup pizzaSizeButtons;

    @FXML
    private ToggleGroup pizzaFlavorButtons;

    @FXML
    private ToggleGroup pizzaBorderButtons;

    @FXML
    private void GoToChooseFlavorPage(ActionEvent event) {
        boolean case0 =  pizzaSizeButtons.getSelectedToggle() != null
                        && pizzaFlavorButtons.getSelectedToggle() != null
                        && pizzaBorderButtons.getSelectedToggle() != null;

        if(!SharedControl.getInstance().isFinishedPizzaInfo()){
            if(case0){
                RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
                RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
                RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

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

                SharedControl.getInstance().InitPizza();
                SharedControl.getInstance().getPizza().setSize(size);
                SharedControl.getInstance().getPizza().setNumFlavor(numFlavor);
                SharedControl.getInstance().getPizza().setBorder(border);
                SharedControl.getInstance().changeFinishedPizzaInfo();
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
        }

        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }
}


