package application;

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

        if(ingredientType.equals("cheese"))
            ingredients = cheese;
        else if(ingredientType.equals("vegetable"))
            ingredients = vegetable;
        else if(ingredientType.equals("protein"))
            ingredients = protein;
        else if (ingredientType.equals("green leaf")) {
            ingredients = greenLeafy;
        }
        else
            return -1;

        for(Pair ingredient : ingredients){
            if(ingredient.getOption().equals(ingredientName))
                return ingredient.getPrice();
        }

        return -1;
    }
    
}
