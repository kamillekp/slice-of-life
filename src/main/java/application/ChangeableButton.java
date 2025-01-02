package application;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;

/**
 * Um botão de seleção alterável, que herda de {@link RadioButton}.
 */
public class ChangeableButton extends RadioButton {

    /**
     * Dispara a ação do botão, alternando o estado de seleção.
     * Caso o botão não esteja desabilitado, o estado de seleção
     * será invertido e um evento de ação será disparado.
     */
    @Override
    public void fire() {
        if (!isDisabled()) {
            setSelected(!isSelected());
            fireEvent(new ActionEvent());
        }
    }

    /**
     * Constrói um novo botão de seleção alterável com o texto fornecido.
     *
     * @param text o texto exibido no botão
     */
    public ChangeableButton(final String text) {
        super(text);
    }
}

