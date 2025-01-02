package controllers;

import application.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;

/**
 * Controlador para escolha de sabor da pizza.
 * Este controlador é abstrato e precisa ser estendido para definir o comportamento específico
 * para diferentes tipos de sabores.
 */
public abstract class ChooseFlavourController {
    // Atributos "protegidos" para serem acessados pelas classes filhas

    /** Campo de texto para exibir o preço. */
    @FXML Label priceText;

    /** Botão para alterar o tipo de sabor. */
    @FXML Button changeFlavourTypeButton;

    /** Botão para avançar para o próximo passo. */
    @FXML Button goAheadButton;

    /** Botão para voltar ao passo anterior. */
    @FXML Button goBackButton;

    /** Controle compartilhado entre diferentes partes do sistema. */
    final SharedControl sharedControl = SharedControl.getInstance();

    /** Pizza atualmente em edição. */
    final Pizza currentPizza = sharedControl.getPizza();

    /** Pedido atual sendo processado. */
    final Order currentOrder = sharedControl.getOrder();

    /**
     * Inicializa o controlador. Este método deve ser implementado pelas classes filhas.
     */
    public abstract void initialize();

    /**
     * Inicializa o preço do sabor atual.
     * @return O preço do sabor atual.
     */
    abstract double initializeFlavorPrice();

    /**
     * Obtém o preço do sabor atual.
     * @return O preço do sabor atual.
     */
    abstract double getCurrentFlavourPrice();

    /**
     * Navega para a tela anterior, dependendo do tipo de sabor.
     * @param type O tipo de sabor.
     */
    public void goBack(final String type) {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        setCurrentFlavor(type, currentFlavorNumber);
        setCurrentPrice();

        if (currentFlavorNumber == 0) {
            SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
        } else {
            sharedControl.decrementFlavorsCounter();

            if (currentPizza.getFlavors().get(currentFlavorNumber - 1).getType().equals("salgado")) {
                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
            } else {
                SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
            }
        }
    }

    /**
     * Navega para a próxima tela, dependendo do tipo de sabor.
     * @param type O tipo de sabor.
     */
    public void goAhead(final String type) {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        setCurrentFlavor(type, currentFlavorNumber);
        setCurrentPrice();

        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        // Se não cabe mais nenhum sabor na pizza
        if (currentFlavorNumber + 1 == currentPizza.getNumFlavor()) {
            // E eu não estou editando uma pizza
            if (!sharedControl.isEditingAddedPizza()) {
                pizzas.add(currentPizza);
            } else {
                sharedControl.setPizza(pizzas.get(pizzas.size() - 1));
                sharedControl.setEditingAddedPizza(false); // Terminou a pizza
            }

            if (currentOrder.getClient().isRegister()) {
                SceneNavigator.navigateTo("/views/Tela5.fxml", "/styles/Tela5.css");
            } else {
                SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
            }
        } else {
            sharedControl.incrementFlavorsCounter();

            // Se o próximo sabor já foi editado
            if (sharedControl.getPizza().getFlavors().size() > currentFlavorNumber + 1) {
                if (sharedControl.getPizza().getFlavors().get(currentFlavorNumber + 1).getType().equals("salgado")) {
                    SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
                } else {
                    SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
                }
            } else {
                SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
            }
        }
    }

    /**
     * Obtém a lista de ingredientes disponíveis.
     * @return Lista de ingredientes.
     */
    abstract ArrayList<String> getIngredients();

    /**
     * Inicializa o grupo de botões de alternância (ToggleGroup) e adiciona os botões ao contêiner.
     * @param ingredients Ingredientes a serem exibidos.
     * @param toggleGroup O grupo de alternância.
     * @param container O contêiner onde os botões serão adicionados.
     */
    void initializeToggleGroup(final Pair[] ingredients, final ToggleGroup toggleGroup, final GridPane container) {
        container.setHgap(container.getWidth() / 4);
        container.setVgap(15);
        container.setPadding(new Insets(15, 0, 50, 50));

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setPercentWidth(50);
        col2.setPercentWidth(50);

        container.getColumnConstraints().clear();
        container.getColumnConstraints().addAll(col1, col2);

        container.setPrefHeight(Region.USE_COMPUTED_SIZE);
        container.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        container.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;

        for (Pair ingredient : ingredients) {
            ChangeableButton newButton = createChangeableButton(toggleGroup, ingredient);
            container.add(newButton, col, row);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    /* Setters */

    /**
     * Define o sabor atual da pizza.
     * @param flavourType Tipo de sabor.
     * @param index Índice do sabor na lista de sabores.
     */
    void setCurrentFlavor(final String flavourType, final int index) {
        ArrayList<String> ingredients = getIngredients();

        Flavour currentFlavour = new Flavour(flavourType, ingredients);

        if (index < currentPizza.getFlavors().size()) {
            currentPizza.getFlavors().set(index, currentFlavour);
        } else {
            currentPizza.getFlavors().add(currentFlavour);
        }
    }

    /**
     * Atualiza o preço total da pizza e do pedido.
     */
    void setCurrentPrice() {
        double previousTotalPrice = currentOrder.getPrice();
        double previousCurrentPizzaPrice = currentPizza.getPrice();
        double flavourPrice = getCurrentFlavourPrice();

        currentPizza.setPrice(previousCurrentPizzaPrice + flavourPrice);
        currentOrder.setTotalPrice(previousTotalPrice + flavourPrice);
    }

    /**
     * Navega para a página de sabores doces.
     */
    void goToSugaryFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-2.fxml", "/styles/Tela3.css");
    }

    /**
     * Navega para a página de sabores salgados.
     */
    void goToSaltyFlavorsPage() {
        SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
    }

    /**
     * Seleciona um botão de ingrediente baseado no texto.
     * @param toggleGroup O grupo de alternância.
     * @param text O texto do ingrediente.
     */
    void selectButtonByIngredient(final ToggleGroup toggleGroup, final String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof ChangeableButton button) {
                if (button.getText().equals(text)) {
                    toggleGroup.selectToggle(button);
                    break;
                }
            }
        }
    }

    /**
     * Cria um botão alternável para selecionar ingredientes.
     * @param toggleGroup O grupo de alternância.
     * @param ingredient O ingrediente associado ao botão.
     * @return O botão criado.
     */
    static ChangeableButton createChangeableButton(final ToggleGroup toggleGroup, final Pair ingredient) {
        String option = ingredient.getOption();
        double price = ingredient.getPrice();

        ChangeableButton newButton = new ChangeableButton(option + "\n" + "R$ " + String.format("%.2f", price));
        newButton.setUserData(price);
        newButton.setToggleGroup(toggleGroup);
        newButton.setStyle("-fx-text-fill: #303030;");

        return newButton;
    }
}
