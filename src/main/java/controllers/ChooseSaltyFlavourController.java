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
 * Controller responsável pela seleção de sabores salgados na aplicação.
 */
public class ChooseSaltyFlavourController extends ChooseFlavourController {

    /** O tipo de sabor associado a este controller. */
    private static final String FLAVOUR_TYPE = "salgado";

    /** Menu contendo os ingredientes disponíveis para sabores salgados. */
    private static final SaltyMenu SALTY_MENU_INGREDIENTS = new SaltyMenu();

    /** Grade para exibição dos queijos disponíveis. */
    @FXML private GridPane cheesesGrid;

    /** Grade para exibição dos vegetais disponíveis. */
    @FXML private GridPane vegetablesGrid;

    /** Grade para exibição das proteínas disponíveis. */
    @FXML private GridPane proteinsGrid;

    /** Grade para exibição das folhas verdes disponíveis. */
    @FXML private GridPane greensGrid;

    /** Rótulo exibindo o número do sabor que está sendo configurado. */
    @FXML private Label flavourNumberLabel;

    /** Exibição de imagem associada à interface. */
    @FXML private ImageView pizzaImage;

    /** Grupo de botões para seleção de queijos. */
    private final ToggleGroup pizzaCheeseGroup = new ToggleGroup();

    /** Grupo de botões para seleção de vegetais. */
    private final ToggleGroup pizzaVegetableGroup = new ToggleGroup();

    /** Grupo de botões para seleção de proteínas. */
    private final ToggleGroup pizzaProteinGroup = new ToggleGroup();

    /** Grupo de botões para seleção de folhas verdes. */
    private final ToggleGroup pizzaGreensGroup = new ToggleGroup();

    /**
     * Inicializa o controller e configura os componentes da interface.
     */
    @Override
    public void initialize() {
        Image image1 = new Image(getClass().getResourceAsStream("/assets/image1.png"));
        pizzaImage.setImage(image1);

        flavourNumberLabel.setText(
                "ESCOLHA OS INGREDIENTES DO " + (sharedControl.getFlavorsCounter() + 1) + "º SABOR"
        );

        initializeToggleGroup(
                SALTY_MENU_INGREDIENTS.getIngredientsByType("cheese"),
                pizzaCheeseGroup,
                cheesesGrid
        );
        initializeToggleGroup(
                SALTY_MENU_INGREDIENTS.getIngredientsByType("vegetable"),
                pizzaVegetableGroup,
                vegetablesGrid
        );
        initializeToggleGroup(
                SALTY_MENU_INGREDIENTS.getIngredientsByType("protein"),
                pizzaProteinGroup,
                proteinsGrid
        );
        initializeToggleGroup(
                SALTY_MENU_INGREDIENTS.getIngredientsByType("green leaf"),
                pizzaGreensGroup,
                greensGrid
        );

        double previousFlavourPrice = initializeFlavorPrice();

        if (previousFlavourPrice == 0) {
            goAheadButton.setDisable(true);
        } else {
            currentPizza.setPrice(currentPizza.getPrice() - previousFlavourPrice);
            currentOrder.setTotalPrice(currentOrder.getPrice() - previousFlavourPrice);
        }

        ChangeListener<Object> updateTotalListener = (observable, oldValue, newValue) -> {
            double currentFlavorPrice = getCurrentFlavourPrice();

            goAheadButton.setDisable(currentFlavorPrice == 0);
            priceText.setText(
                    "TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + currentFlavorPrice)
            );
        };

        pizzaCheeseGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaVegetableGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaProteinGroup.selectedToggleProperty().addListener(updateTotalListener);
        pizzaGreensGroup.selectedToggleProperty().addListener(updateTotalListener);

        priceText.setText(
                "TOTAL DO PEDIDO: R$ " + String.format("%.2f", currentOrder.getPrice() + previousFlavourPrice)
        );

        goBackButton.setOnAction(event -> goBack(FLAVOUR_TYPE));
        goAheadButton.setOnAction(event -> goAhead(FLAVOUR_TYPE));
        changeFlavourTypeButton.setOnAction(event -> goToSugaryFlavorsPage());
    }

    /**
     * Inicializa o preço do sabor com base nos ingredientes selecionados.
     *
     * @return o preço total do sabor atual.
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
                    String ingredientType = SALTY_MENU_INGREDIENTS.findType(ingredient);
                    double priceOfCurrentIngredient = SALTY_MENU_INGREDIENTS.getPrice(
                            ingredientType, ingredient
                    );

                    switch (ingredientType) {
                        case "cheese" -> selectButtonByIngredient(
                                pizzaCheeseGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient)
                        );
                        case "vegetable" -> selectButtonByIngredient(
                                pizzaVegetableGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient)
                        );
                        case "protein" -> selectButtonByIngredient(
                                pizzaProteinGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient)
                        );
                        case "green leaf" -> selectButtonByIngredient(
                                pizzaGreensGroup,
                                ingredient + "\n" + "R$ " + String.format("%.2f", priceOfCurrentIngredient)
                        );
                        default -> throw new IllegalStateException("Unexpected ingredient type: " + ingredientType);
                    }

                    flavourPrice += priceOfCurrentIngredient;
                }
            } else {
                currentFlavour = new Flavour(FLAVOUR_TYPE);
            }
        }

        return flavourPrice;
    }

    /**
     * Calcula o preço atual do sabor com base nos ingredientes selecionados.
     *
     * @return o preço total do sabor.
     */
    @Override
    double getCurrentFlavourPrice() {
        double cheesePrice = getSelectedTogglePrice(pizzaCheeseGroup);
        double vegetablePrice = getSelectedTogglePrice(pizzaVegetableGroup);
        double proteinPrice = getSelectedTogglePrice(pizzaProteinGroup);
        double greensPrice = getSelectedTogglePrice(pizzaGreensGroup);

        return cheesePrice + vegetablePrice + proteinPrice + greensPrice;
    }

    /**
     * Obtém os ingredientes atualmente selecionados pelo usuário.
     *
     * @return uma lista de ingredientes selecionados.
     */
    @Override
    ArrayList<String> getIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        addIngredientFromGroup(pizzaCheeseGroup, ingredients);
        addIngredientFromGroup(pizzaVegetableGroup, ingredients);
        addIngredientFromGroup(pizzaProteinGroup, ingredients);
        addIngredientFromGroup(pizzaGreensGroup, ingredients);

        return ingredients;
    }

    /**
     * Recupera o preço do ingrediente selecionado em um grupo de botões.
     *
     * @param toggleGroup o grupo de botões.
     * @return o preço do ingrediente selecionado ou 0 se nenhum estiver selecionado.
     */
    private double getSelectedTogglePrice(final ToggleGroup toggleGroup) {
        ChangeableButton selectedToggle = (ChangeableButton) toggleGroup.getSelectedToggle();
        return selectedToggle != null ? (double) selectedToggle.getUserData() : 0;
    }

    /**
     * Adiciona o ingrediente selecionado de um grupo de botões à lista de ingredientes.
     *
     * @param group o grupo de botões.
     * @param ingredients a lista de ingredientes.
     */
    private void addIngredientFromGroup(final ToggleGroup group, final ArrayList<String> ingredients) {
        ChangeableButton selectedToggle = (ChangeableButton) group.getSelectedToggle();
        if (selectedToggle != null) {
            String ingredient = selectedToggle.getText().split("\n")[0];
            ingredients.add(ingredient);
        }
    }
}
