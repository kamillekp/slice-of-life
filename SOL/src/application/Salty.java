package application;

import java.util.ArrayList;

public class Salty {
    private final Pair[] cheese = {
            new Pair("Gorgonzola", 1),
            new Pair("Ricota", 2),
            new Pair("Requeijão", 3),
            new Pair("Cheddar", 4),
            new Pair("Brie", 5)
    };
    private final Pair[] vegetable = {
            new Pair("Brócolis", 1),
            new Pair("Tomate", 2),
            new Pair("Pimentão", 3),
            new Pair("Cebola", 4),
            new Pair("Palmito", 5),
            new Pair("Cogumelo", 6)
    };
    private final Pair[] protein = {
            new Pair("Filé", 1),
            new Pair("Salmão", 2),
            new Pair("Frango", 3),
            new Pair("Lombo", 4),
            new Pair("Bacon", 5),
            new Pair("Camarão", 6)
    };
    private final Pair[] greenLeafy = {
            new Pair("Manjericão", 1),
            new Pair("Salsa", 2),
            new Pair("Rúcula", 3),
            new Pair("Agrião", 4),
            new Pair("Coentro", 5),
            new Pair("Espinafre", 6)
    };

    public Pair[] getCheese() {
        return cheese;
    }

    public Pair[] getVegetable() {
        return vegetable;
    }

    public Pair[] getProtein() {
        return protein;
    }

    public Pair[] getGreenLeafy() {
        return greenLeafy;
    }


    public double getPrice(String ingredientType, String ingredientName){
        Pair[] ingredients;

        switch (ingredientType) {
            case "cheese" -> ingredients = cheese;
            case "vegetable" -> ingredients = vegetable;
            case "protein" -> ingredients = protein;
            case "green leaf" -> ingredients = greenLeafy;

            default -> {
                return -1;
            }
        }

        for(Pair ingredient : ingredients){
            if(ingredient.getOption().equals(ingredientName))
                return ingredient.getPrice();
        }

        return -1;
    }


    public Pair[] getIngredientsByType(String type) {
        return switch (type) {
            case "cheese" -> cheese;
            case "protein" -> protein;
            case "vegetable" -> vegetable;
            case "green leaf" -> greenLeafy;
            default -> null;
        };
    }



    public String findType(String ingredient){
        for(Pair pair : this.cheese){
            if (pair.getOption().equals(ingredient))
                return "cheese";
        }

        for(Pair pair : this.protein){
            if (pair.getOption().equals(ingredient))
                return "protein";
        }

        for(Pair pair : this.vegetable){
            if(pair.getOption().equals(ingredient))
                return "vegetable";
        }

        for(Pair pair : this.greenLeafy){
            if(pair.getOption().equals(ingredient))
                return "green leaf";
        }


        return null;
    }

    public String getFirstFromType(String type, ArrayList<String> ingredients){
        Pair[] ingredientsFromType = getIngredientsByType(type);

        for(Pair ingredient : ingredientsFromType){
            if(ingredients.contains(ingredient.getOption()))
                return ingredient.getOption();
        }

        return null;
    }
    
}
