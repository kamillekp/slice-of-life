package application;

public class PizzaInfo {
    private final Pair[] sizes = {
            new Pair("Pequena (25cm)", 1),
            new Pair("Média (30cm)", 2),
            new Pair("Grande (35cm)", 3),
            new Pair("Família (40cm)", 4),
    };

    private final Pair[] numFlavors = {
            new Pair("Um sabor", 1),
            new Pair("Dois sabores", 2),
            new Pair("Três sabores", 3),
            new Pair("Quatro sabores", 4),
    };

    public Pair[] getSizes() {
        return sizes;
    }

    public Pair[] getNumFlavors() {
        return numFlavors;
    }
}
