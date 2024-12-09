package application;

import java.util.Arrays;

public class Sugary {
    private final Pair[] topping = {
            new Pair("Ao leite", 1),
            new Pair("Meio Amargo", 2),
            new Pair("Branco", 3),
            new Pair("Amargo", 4),
            new Pair("Pistache", 5),
            new Pair("Avelã", 6),
            new Pair("Doce de Leite", 7),
            new Pair("Sorvete", 8),
            new Pair("Brigadeiro", 9),
            new Pair("Pasta de Amendoim", 10),
            new Pair("Beijinho", 11)
    };

    private final Pair[] fruit = {
            new Pair("Morango", 1),
            new Pair("Framboesa", 2),
            new Pair("Abacaxi", 3),
            new Pair("Uva", 4),
            new Pair("Kiwi", 5),
            new Pair("Tâmara", 6)
    };

    private final Pair[] condiment = {
            new Pair("M&M's", 1),
            new Pair("Amêndoas", 2),
            new Pair("Coco ralado", 3),
            new Pair("Nozes", 4),
            new Pair("Paçoca", 5),
            new Pair("Castanhas", 6)
    };

    public Pair[] getTopping() {
        return topping;
    }

    public Pair[] getCondiment() {
        return condiment;
    }

    public Pair[] getFruit() {
        return fruit;
    }


    public Pair[] getIngredientsByType(String type) {
        if(type.equals("condiment"))
            return condiment;
        else if(type.equals("fruit"))
            return fruit;
        else
            return topping;
    }
    public double getPrice(String ingredientType, String ingredientName){
        Pair[] ingredients;

        ingredients = getIngredientsByType(ingredientType);

        for(Pair ingredient : ingredients){
            if(ingredient.getOption().equals(ingredientName))
                return ingredient.getPrice();
        }

        return -1;
    }

}
