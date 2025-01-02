package controllers;

import application.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador responsável por gerenciar a tela de seleção de pizza.
 */
public class ChoosePizzaController {

    /**
     * Rótulo para exibir o preço total do pedido.
     */
    @FXML
    private Label totalPriceLabel;

    /**
     * Grupo de botões para seleção do tamanho da pizza.
     */
    @FXML
    private ToggleGroup pizzaSizeButtons;

    /**
     * Grupo de botões para seleção do número de sabores da pizza.
     */
    @FXML
    private ToggleGroup pizzaFlavorButtons;

    /**
     * Grupo de botões para seleção da borda da pizza.
     */
    @FXML
    private ToggleGroup pizzaBorderButtons;

    /**
     * Rótulo para exibir o preço da pizza pequena.
     */
    @FXML
    private Label smallPizzaPrice;

    /**
     * Rótulo para exibir o preço da pizza média.
     */
    @FXML
    private Label mediumPizzaPrice;

    /**
     * Rótulo para exibir o preço da pizza grande.
     */
    @FXML
    private Label bigPizzaPrice;

    /**
     * Rótulo para exibir o preço da maior pizza.
     */
    @FXML
    private Label biggestPizzaPrice;

    /**
     * Rótulo para exibir o preço da borda com recheio.
     */
    @FXML
    private Label borderPriceLabel;

    /**
     * Rótulo para exibir o preço da pizza sem borda.
     */
    @FXML
    private Label noBorderPriceLabel;

    /**
     * Rótulo para exibir o preço de uma pizza com quatro sabores.
     */
    @FXML
    private Label fourFlavourPrice;

    /**
     * Rótulo para exibir o preço de uma pizza com um sabor.
     */
    @FXML
    private Label oneFlavourPrice;

    /**
     * Rótulo para exibir o preço de uma pizza com três sabores.
     */
    @FXML
    private Label threeFlavourPrice;

    /**
     * Rótulo para exibir o preço de uma pizza com dois sabores.
     */
    @FXML
    private Label twoFlavourPrice;

    /**
     * Botão para navegar para a próxima tela de seleção de sabores.
     */
    @FXML
    private Button goToChooseFlavourButton;

    /**
     * Componente para exibir a imagem ilustrativa da pizza.
     */
    @FXML
    private ImageView pizzaImage;

    /**
     * Preço das propriedades atuais da pizza.
     */
    private double currentPropertiesPrice;

    /**
     * Inicializa o controlador após o carregamento completo do FXML.
     */
    @FXML
    public void initialize() {
        final Label[] numFlavourPrices = {oneFlavourPrice, twoFlavourPrice, threeFlavourPrice, fourFlavourPrice};
        final Label[] borderPrices = {borderPriceLabel, noBorderPriceLabel};
        final Label[] sizePrices = {smallPizzaPrice, mediumPizzaPrice, bigPizzaPrice, biggestPizzaPrice};

        Image image1 = new Image(getClass().getResourceAsStream("/assets/image1.png"));
        pizzaImage.setImage(image1);

        initializeButtons(numFlavourPrices, sizePrices, borderPrices);

        Order currentOrder = SharedControl.getInstance().getOrder();
        Pizza currentPizza = SharedControl.getInstance().getPizza();

        double previousPropertiesPrice = 0;

        if (currentPizza != null) {
            previousPropertiesPrice = getCurrentPropertiesPrice();
            currentPizza.setPrice(currentPizza.getPrice() - previousPropertiesPrice);
        }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            currentPropertiesPrice = getCurrentPropertiesPrice();

            goToChooseFlavourButton.setDisable(!areAllButtonGroupsSelected());

            totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentPropertiesPrice));
        };

        pizzaSizeButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFlavorButtons.selectedToggleProperty().addListener(updateTotalListener);
        pizzaBorderButtons.selectedToggleProperty().addListener(updateTotalListener);

        totalPriceLabel.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice()));

        currentPropertiesPrice = previousPropertiesPrice;

        goToChooseFlavourButton.setDisable(!areAllButtonGroupsSelected());

        currentOrder.setTotalPrice(currentOrder.getPrice() - previousPropertiesPrice);
    }

    /**
     * Verifica se todos os grupos de botões possuem uma opção selecionada.
     *
     * @return true se todas as opções foram selecionadas; false caso contrário.
     */
    private boolean areAllButtonGroupsSelected() {
        return pizzaSizeButtons.getSelectedToggle() != null
                && pizzaFlavorButtons.getSelectedToggle() != null
                && pizzaBorderButtons.getSelectedToggle() != null;
    }

    /**
     * Calcula o preço atual com base nas opções selecionadas nos botões.
     *
     * @return preço total calculado.
     */
    private double getCurrentPropertiesPrice() {
        RadioButton numFlavourSelectedButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
        RadioButton sizeSelectedButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
        RadioButton borderSelectedButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

        double numFlavourPrice = numFlavourSelectedButton != null ? (double) numFlavourSelectedButton.getUserData() : 0;
        double sizePrice = sizeSelectedButton != null ? (double) sizeSelectedButton.getUserData() : 0;
        double borderPrice = borderSelectedButton != null ? (double) borderSelectedButton.getUserData() : 0;

        return numFlavourPrice + sizePrice + borderPrice;
    }


    /**
     * Inicializa os botões com os preços e seleciona as opções previamente definidas, se houver.
     *
     * @param numFlavourPrices rótulos que exibem os preços para o número de sabores.
     * @param sizePrices       rótulos que exibem os preços para os tamanhos.
     * @param borderPrices     rótulos que exibem os preços para as bordas.
     */
    private void initializeButtons(final Label[] numFlavourPrices, final Label[] sizePrices, final Label[] borderPrices) {
        setButtonsPrices(pizzaSizeButtons, "sizes", sizePrices);
        setButtonsPrices(pizzaFlavorButtons, "number of flavours", numFlavourPrices);
        setButtonsPrices(pizzaBorderButtons, "border", borderPrices);

        Pizza currentPizza = SharedControl.getInstance().getPizza();

        if (currentPizza != null) {
            int numFlavour = currentPizza.getNumFlavor();
            String numFlavourText = String.format("%d", numFlavour);
            String borderText = currentPizza.isBorder() ? "Com Borda" : "Sem Borda";

            selectButtonByText(pizzaSizeButtons, currentPizza.getSize());
            selectButtonByText(pizzaFlavorButtons, numFlavourText);
            selectButtonByText(pizzaBorderButtons, borderText);
        }
    }

    /**
     * Seleciona um botão em um grupo de botões se o rótulo dele for o texto passado.
     *
     * @param toggleGroup o grupo de botões.
     * @param text        o texto do botão a ser selecionado.
     */
    private void selectButtonByText(final ToggleGroup toggleGroup, final String text) {
        for (Toggle toggle : toggleGroup.getToggles()) {
            RadioButton currentButton = (RadioButton) toggle;
            if (currentButton.getText().equals(text)) {
                currentButton.setSelected(true);
                return;
            }
        }
    }

    /**
     * Define os preços nos botões de um grupo e atualiza os rótulos correspondentes.
     *
     * @param toggleGroup o grupo de botões.
     * @param type        o tipo de propriedade da pizza (e.g., tamanho, borda, sabores).
     * @param priceLabels os rótulos que exibem os preços.
     */
    private void setButtonsPrices(final ToggleGroup toggleGroup, final String type, final Label[] priceLabels) {
        PizzaInfo pizzaInfo = new PizzaInfo();
        int counter = 0;

        for (Toggle toggle : toggleGroup.getToggles()) {
            RadioButton currentButton = (RadioButton) toggle;
            double price = pizzaInfo.getPrice(type, currentButton.getText());
            currentButton.setUserData(price);

            String priceText = "R$ " + String.format("%.2f", price);
            priceLabels[counter].setText(priceText);
            counter++;
        }
    }

    /**
     * Retorna à página inicial do aplicativo.
     */
    @FXML
    private void backToInitialPage() {
        SceneNavigator.navigateTo("/views/Tela1.fxml", "/styles/Tela1.css");
    }

    /**
     * Navega para a página de escolha de sabores, verificando se todas as informações da pizza foram selecionadas.
     *
     * @param event o evento de ação disparado pelo botão.
     */
    @FXML
    private void goToChooseFlavorPage(final ActionEvent event) {
        boolean pizzaInfosPressed = areAllButtonGroupsSelected();

        if (pizzaInfosPressed) {
            RadioButton selectedPSButton = (RadioButton) pizzaSizeButtons.getSelectedToggle();
            RadioButton selectedPFButton = (RadioButton) pizzaFlavorButtons.getSelectedToggle();
            RadioButton selectedPBButton = (RadioButton) pizzaBorderButtons.getSelectedToggle();

            String size = selectedPSButton.getText();
            String numFlavorText = selectedPFButton.getText();
            int numFlavor = Integer.parseInt(numFlavorText);
            String borderText = selectedPBButton.getText();
            boolean border = borderText.equals("Com Borda");

            Pizza currentPizza = SharedControl.getInstance().getPizza();
            Order currentOrder = SharedControl.getInstance().getOrder();

            if (currentPizza == null) {
                SharedControl.getInstance().initPizza();
                currentPizza = SharedControl.getInstance().getPizza();
            }

            currentPizza.setSize(size);
            currentPizza.setNumFlavor(numFlavor);
            currentPizza.setBorder(border);

            currentOrder.setTotalPrice(currentOrder.getPrice() + currentPropertiesPrice);
            currentPizza.setPrice(currentPizza.getPrice() + currentPropertiesPrice);

            SharedControl.getInstance().resetCounter();

            SceneNavigator.navigateTo("/views/Tela3-1.fxml", "/styles/Tela3.css");
        }
    }
}


