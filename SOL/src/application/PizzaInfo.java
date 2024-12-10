package application;

public class PizzaInfo {
    private final Pair[] sizes = {
            new Pair("(25cm)", 25),
            new Pair("(30cm)", 30),
            new Pair("(35cm)", 35),
            new Pair("(40cm)", 40),
    };

    private final Pair[] numFlavors = {
            new Pair("Um sabor", 0),
            new Pair("Dois sabores", 1),
            new Pair("Três sabores", 2),
            new Pair("Quatro sabores", 3),
    };

    private final Pair[] pizzaBorder = {
            new Pair("Com Borda", 1),
            new Pair("Sem Borda", 0),
    };
}