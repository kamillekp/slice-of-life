package application;

import java.util.ArrayList;

/**
 * Classe abstrata que representa um menu de ingredientes.
 * Fornece métodos para buscar ingredientes por tipo, encontrar o tipo de um ingrediente
 * e obter informações sobre preços e opções de ingredientes.
 */
    public abstract class IngredientsMenu {

    /**
     * Método abstrato para obter ingredientes por tipo.
     *
     * @param type o tipo de ingrediente desejado (ex: "Doce", "Salgado")
     * @return um array de pares contendo as opções e preços dos ingredientes do tipo especificado
     */
    public abstract Pair[] getIngredientsByType(String type);

    /**
     * Método abstrato para buscar o tipo de um ingrediente.
     *
     * @param ingredient o nome do ingrediente
     * @return o tipo do ingrediente
     */
    public abstract String findType(String ingredient);

    /**
     * Método genérico para buscar o preço de um ingrediente.
     *
     * @param ingredientType o tipo do ingrediente
     * @param ingredientName o nome do ingrediente
     * @return o preço do ingrediente, ou -1 se o ingrediente não for encontrado
     */
    public double getPrice(final String ingredientType, final String ingredientName) {
        Pair[] ingredients = getIngredientsByType(ingredientType);

        if (ingredients == null) {
            return -1;
        }

        for (Pair ingredient : ingredients) {
            if (ingredient.getOption().equals(ingredientName)) {
                return ingredient.getPrice();
            }
        }
        return -1;
    }

    /**
     * Método genérico para obter o primeiro ingrediente de um tipo específico que está na lista de ingredientes.
     *
     * @param type o tipo de ingrediente desejado
     * @param ingredients a lista de ingredientes
     * @return o primeiro ingrediente encontrado do tipo especificado, ou null se não encontrado
     */
    public String getFirstFromType(final String type, final ArrayList<String> ingredients) {
        Pair[] ingredientsFromType = getIngredientsByType(type);

        if (ingredientsFromType == null) {
            return null;
        }

        for (Pair ingredient : ingredientsFromType) {
            if (ingredients.contains(ingredient.getOption())) {
                return ingredient.getOption();
            }
        }
        return null;
    }
}
