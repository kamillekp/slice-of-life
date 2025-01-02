package controllers;

import application.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Controlador responsável por gerenciar a escolha dos ingredientes doces para uma pizza.
 */
public class ChooseSugaryFlavourController extends ChooseFlavourController {

    /**
     * Grid para exibição dos toppings (coberturas) da pizza.
     */
    @FXML private GridPane toppingsGrid;

    /**
     * Grid para exibição das frutas da pizza.
     */
    @FXML private GridPane fruitsGrid;

    /**
     * Rótulo que exibe o número do sabor a ser escolhido.
     */
    @FXML private Label flavourNumberLabel;

    /**
     * Grid para exibição dos condimentos da pizza.
     */
    @FXML private GridPane condimentsGrid;

    /**
     * Tipo do sabor para identificação de sabores doces.
     */
    private static final String FLAVOUR_TYPE = "doce";

    /**
     * Menu com os ingredientes doces disponíveis para a pizza.
     */
    private static final SugaryMenu SUGARY_MENU_INGREDIENTS = new SugaryMenu();

    /**
     * Grupo de botões de seleção para os toppings da pizza.
     */
    private final ToggleGroup pizzaToppingGroup = new ToggleGroup();

    /**
     * Grupo de botões de seleção para as frutas da pizza.
     */
    private final ToggleGroup pizzaFruitGroup = new ToggleGroup();

    /**
     * Grupo de botões de seleção para os condimentos da pizza.
     */
    private final ToggleGroup pizzaCondimentGroup = new ToggleGroup();

    /**
     * Imagem da pizza sendo montada, exibida na interface gráfica.
     */
    @FXML
    private ImageView pizzaImage;

    /**
     * Inicializa os componentes da tela e as interações com os ingredientes da pizza.
     */
    @Override
    public void initialize() {
        Image image1 = new Image(getClass().getResourceAsStream("/assets/image1.png"));
        pizzaImage.setImage(image1);

        flavourNumberLabel.setText(
                "ESCOLHA OS INGREDIENTES DO " + (sharedControl.getFlavorsCounter() + 1) + "º SABOR"
        );

        // Inicializa os grupos de toggle para os ingredientes
        initializeToggleGroup(
                SUGARY_MENU_INGREDIENTS.getIngredientsByType("topping"),
                pizzaToppingGroup,
                toppingsGrid);
        initializeToggleGroup(
                SUGARY_MENU_INGREDIENTS.getIngredientsByType("fruit"),
                pizzaFruitGroup,
                fruitsGrid);
        initializeToggleGroup(
                SUGARY_MENU_INGREDIENTS.getIngredientsByType("condiment"),
                pizzaCondimentGroup,
                condimentsGrid);

        double previousFlavourPrice = initializeFlavorPrice();

        if (previousFlavourPrice == 0) {
            goAheadButton.setDisable(true); // A pizza não existe
        } else {
            currentPizza.setPrice(currentPizza.getPrice() - previousFlavourPrice);
            currentOrder.setTotalPrice(currentOrder.getPrice() - previousFlavourPrice);
        }

        // Listener para atualizar o total do pedido
        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double currentFlavourPrice = getCurrentFlavourPrice();

            goAheadButton.setDisable(currentFlavourPrice == 0);

            priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentFlavourPrice));
        };

        pizzaToppingGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaFruitGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaCondimentGroup.selectedToggleProperty().addListener(updateTotalListener);

        priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + previousFlavourPrice));

        goBackButton.setOnAction(event -> goBack(FLAVOUR_TYPE));
        goAheadButton.setOnAction(event -> goAhead(FLAVOUR_TYPE));
        changeFlavourTypeButton.setOnAction(event -> goToSaltyFlavorsPage());

        if (pizzaToppingGroup.getSelectedToggle() == null && currentPizza.getFlavors().size() > sharedControl.getFlavorsCounter()) {
            System.out.println(currentPizza.getFlavors().get(sharedControl.getFlavorsCounter()).getIngredients());
            System.out.println(previousFlavourPrice);
        }
    }

    /**
     * Inicializa o preço do sabor atual com base nos ingredientes selecionados.
     *
     * @return O preço do sabor atual.
     */
    @Override
    double initializeFlavorPrice() {
        int currentFlavorNumber = sharedControl.getFlavorsCounter();
        Flavour currentFlavour;
        double flavourPrice = 0.0;

        if (currentFlavorNumber < currentPizza.getFlavors().size()) {
            currentFlavour = currentPizza.getFlavors().get(currentFlavorNumber);

            if (currentFlavour.getType().equals(FLAVOUR_TYPE)) {
                for (String ingredient : currentFlavour.getIngredients()) {

                    String ingredientType = SUGARY_MENU_INGREDIENTS.findType(ingredient);

                    double priceOfCurrentIngredient = SUGARY_MENU_INGREDIENTS.getPrice(ingredientType, ingredient);

                    switch (ingredientType) {
                        case "topping" -> selectButtonByIngredient(
                                pizzaToppingGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                        case "fruit" -> selectButtonByIngredient(
                                pizzaFruitGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                        case "condiment" -> selectButtonByIngredient(
                                pizzaCondimentGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient));

                        default -> throw new IllegalStateException("Unexpected ingredient type: " + ingredientType);
                    }

                    flavourPrice += priceOfCurrentIngredient;
                }
            }
        }

        return flavourPrice;
    }

    /**
     * Obtém o preço total dos ingredientes atualmente selecionados para o sabor.
     *
     * @return O preço total dos ingredientes selecionados.
     */
    @Override
    double getCurrentFlavourPrice() {

        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingGroup.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitGroup.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

        double toppingPrice = toppingSelectedToggle != null ? (double) toppingSelectedToggle.getUserData() : 0;
        double fruitPrice = fruitSelectedToggle != null ? (double) fruitSelectedToggle.getUserData() : 0;
        double condimentPrice = condimentSelectedToggle != null ? (double) condimentSelectedToggle.getUserData() : 0;

        return toppingPrice + fruitPrice + condimentPrice;
    }

    /**
     * Retorna os ingredientes selecionados para o sabor atual.
     *
     * @return Uma lista de ingredientes selecionados para o sabor.
     */
    @Override
    ArrayList<String> getIngredients() {
        ChangeableButton toppingSelectedToggle = (ChangeableButton) pizzaToppingGroup.getSelectedToggle();
        ChangeableButton fruitSelectedToggle = (ChangeableButton) pizzaFruitGroup.getSelectedToggle();
        ChangeableButton condimentSelectedToggle = (ChangeableButton) pizzaCondimentGroup.getSelectedToggle();

        String toppingIngredient = toppingSelectedToggle != null ? toppingSelectedToggle.getText().split("\n")[0] : null;
        String fruitIngredient = fruitSelectedToggle != null ? fruitSelectedToggle.getText().split("\n")[0] : null;
        String condimentIngredient = condimentSelectedToggle != null ? condimentSelectedToggle.getText().split("\n")[0] : null;

        ArrayList<String> ingredients = new ArrayList<>();

        if (toppingIngredient != null) {
            ingredients.add(toppingIngredient);
        }
        if (fruitIngredient != null) {
            ingredients.add(fruitIngredient);
        }
        if (condimentIngredient != null) {
            ingredients.add(condimentIngredient);
        }

        return ingredients;
    }
}
