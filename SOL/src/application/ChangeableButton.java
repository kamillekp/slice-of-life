package application;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;

public class ChangeableButton extends RadioButton {
    @Override
    public void fire() {
        if (!isDisabled()) {
            setSelected(!isSelected());
            fireEvent(new ActionEvent());
        }
    }

    public ChangeableButton(String text) {
        super(text);
    }
}
