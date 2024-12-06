package application;

public class Sugary {
    private final Pair[] topping = {
            new Pair("Ao leite", 2.0),
            new Pair("Meio Amargo", 2.0),
            new Pair("Branco", 2.0),
            new Pair("Amargo", 2.0),
            new Pair("Pistache", 2.5),
            new Pair("Avelã", 2.5),
            new Pair("Doce de Leite", 1.5),
            new Pair("Sorvete", 3.0),
            new Pair("Brigadeiro", 1.5),
            new Pair("Pasta de amendoim", 2.0),
            new Pair("Beijinho", 1.5)
    };

    private final Pair[] fruit = {
            new Pair("Morango", 1.0),
            new Pair("Framboesa", 1.5),
            new Pair("Abacaxi", 1.0),
            new Pair("Uva", 1.0),
            new Pair("Kiwi", 1.5),
            new Pair("Tâmara", 2.0)
    };

    private final Pair[] condiment = {
            new Pair("M&M's", 1.5),
            new Pair("Amêndoas", 2.0),
            new Pair("Coco ralado", 1.0),
            new Pair("Nozes", 2.0),
            new Pair("Paçoca", 1.5),
            new Pair("Castanhas", 2.0)
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
}
