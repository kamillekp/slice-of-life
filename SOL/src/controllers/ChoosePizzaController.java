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

            String size = selectedPSButton.getText();

            String numFlavorText = selectedPFButton.getText();
            int numFlavor = Integer.parseInt(numFlavorText);

            String borderText = selectedPBButton.getText();
            boolean border = borderText.equals("Com Borda");

            System.out.println(border + " " + numFlavor + " " + size);

            Pizza pizza = new Pizza(border, numFlavor, size);

            SceneNavigator.navigateTo("/views/Tela3.fxml", "/styles/Tela3.css");
        }
    }
}


