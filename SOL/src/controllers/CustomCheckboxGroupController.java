package controllers;

import application.Pair;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.List;

public class CustomCheckboxGroupController {

    @FXML
    private Label titleLabel;

    @FXML
    private VBox leftGroup;

    @FXML
    private VBox rightGroup;

    private final ToggleGroup toggleGroup = new ToggleGroup(); // Grupo de botões para garantir que apenas um seja selecionado.

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public void setOptions(List<Pair> options) {
        int midIndex = options.size() / 2;

        // Dividindo as opções entre os dois grupos
        List<Pair> leftOptions = options.subList(0, midIndex);
        List<Pair> rightOptions = options.subList(midIndex, options.size());

        // Criando e adicionando os RadioButtons ao grupo da esquerda
        leftOptions.forEach(option -> leftGroup.getChildren().add(createRadioButton(option)));

        // Criando e adicionando os RadioButtons ao grupo da direita
        rightOptions.forEach(option -> rightGroup.getChildren().add(createRadioButton(option)));
    }

    private RadioButton createRadioButton(Pair option) {
        RadioButton radioButton = new RadioButton(option.getOption() + " R$" + String.format("%.2f", option.getPrice()));
        radioButton.setToggleGroup(toggleGroup); // Adicionando ao grupo de botões
        return radioButton;
    }
}

