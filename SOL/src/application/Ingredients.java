package application;

import java.util.ArrayList;

public abstract class Ingredients {
    // Método abstrato para obter ingredientes por tipo
    public abstract Pair[] getIngredientsByType(String type);

    // Método abstrato para buscar o tipo de um ingrediente
    public abstract String findType(String ingredient);

    // Método genérico para buscar o preço de um ingrediente
    public double getPrice(String ingredientType, String ingredientName) {
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

    // Método genérico para obter o primeiro ingrediente de um tipo
    public String getFirstFromType(String type, ArrayList<String> ingredients) {
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
