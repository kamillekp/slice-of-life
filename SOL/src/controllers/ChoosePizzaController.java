package controllers;

import application.Pizza;
import application.SceneNavigator;
import application.SharedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

public class ChoosePizzaController {
    @FXML
    private ToggleGroup pizzaSizeButtons;

    @FXML
    private ToggleGroup pizzaFlavorButtons;

    @FXML
    private ToggleGroup pizzaBorderButtons;

    @FXML
    private void GoToChooseFlavorPage(ActionEvent event) {
        if (pizzaSizeButtons.getSelectedToggle() != null && pizzaFlavorButtons.getSelectedToggle() != null && pizzaBorderButtons.getSelectedToggle() != null) {
            RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
            RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
            RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

            System.out.println(selectedPSButton.getId());

            String size = selectedPSButton.getText();
            double sizePrice = switch (selectedPSButton.getId()) {
                case "option0PSG" -> 25;
                case "option1PSG" -> 30;
                case "option2PSG" -> 35;
                default -> 40;
            };

            String numFlavorText = selectedPFButton.getText();
            int numFlavor = Integer.parseInt(numFlavorText);
            double flavorPrice = switch (selectedPFButton.getId()) {
                case "option0PFG" -> 0;
                case "option1PFG" -> 1;
                case "option2PFG" -> 2;
                default -> 3;
            };

            String borderText = selectedPBButton.getText();
            boolean border = borderText.equals("Com Borda");
            double borderPrice = switch (selectedPBButton.getId()) {
                case "option0PBG" -> 1;
                default -> 0;
            };

            //System.out.println(border + " " + numFlavor + " " + size);

            SharedControl.getInstance().InitPizza(border, numFlavor, size);
            SharedControl.getInstance().getOrder().updateTotalPrice((sizePrice + numFlavor + borderPrice));

            //System.out.println(SharedControl.getInstance().getOrder().getPrice());

            SceneNavigator.navigateTo("/views/Tela3.fxml", "/styles/Tela3.css");
        }
    }
}


