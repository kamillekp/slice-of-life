package application;

import javafx.scene.control.RadioButton;

public class ScreenChoosePizzaState {
    // O Toggle selecionado é um RadioButton
    private static RadioButton tgPizzaSizeButtons;
    private static RadioButton tgPizzaFlavorButtons;
    private static RadioButton tgPizzaBorderButtons;

    public static RadioButton getTgPizzaSizeButtons() {
        return tgPizzaSizeButtons;
    }

    public static void setTgPizzaSizeButtons(RadioButton tgPizzaSizeButtons) {
        ScreenChoosePizzaState.tgPizzaSizeButtons = tgPizzaSizeButtons;
    }

    public static RadioButton getTgPizzaFlavorButtons() {
        return tgPizzaFlavorButtons;
    }

    public static void setTgPizzaFlavorButtons(RadioButton tgPizzaFlavorButtons) {
        ScreenChoosePizzaState.tgPizzaFlavorButtons = tgPizzaFlavorButtons;
    }

    public static RadioButton getTgPizzaBorderButtons() {
        return tgPizzaBorderButtons;
    }

    public static void setTgPizzaBorderButtons(RadioButton tgPizzaBorderButtons) {
        ScreenChoosePizzaState.tgPizzaBorderButtons = tgPizzaBorderButtons;
    }
}

