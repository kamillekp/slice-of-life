package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;

public class UnselectableRadioGroup extends RadioButton {
    @Override
    public void fire() {
        if (!isDisabled()) {
            setSelected(!isSelected());
            fireEvent(new ActionEvent());
        }
    }

    public UnselectableRadioGroup(String text) {
        super(text);
    }
}
