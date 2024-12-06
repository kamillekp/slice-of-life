package application;

public class Salty {
    private final Pair[] cheese = {
            new Pair("Gorgonzola", 1.5),
            new Pair("Ricota", 1.5),
            new Pair("Requeijão", 1.5),
            new Pair("Cheddar", 1.5),
            new Pair("Brie", 1.5)
    };
    private final Pair[] vegetable = {
            new Pair("Brócolis", 1.0),
            new Pair("Tomate", 1.0),
            new Pair("Pimentão", 1.0),
            new Pair("Cebola", 1.0),
            new Pair("Palmito", 1.0),
            new Pair("Cogumelo", 1.0)
    };
    private final Pair[] protein = {
            new Pair("Filé", 2.5),
            new Pair("Salmão", 2.5),
            new Pair("Frango", 2.5),
            new Pair("Lombo", 2.5),
            new Pair("Bacon", 2.5),
            new Pair("Camarão", 2.5)
    };
    private final Pair[] greenLeafy = {
            new Pair("Manjericão", 0.5),
            new Pair("Salsa", 0.5),
            new Pair("Rúcula", 0.5),
            new Pair("Agrião", 0.5),
            new Pair("Coentro", 0.5),
            new Pair("Espinafre", 0.5)
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
    
}
