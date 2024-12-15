package application;


public class SugaryMenu extends IngredientsMenu {
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

    @Override
    public Pair[] getIngredientsByType(String type) {
        return switch (type) {
            case "condiment" -> condiment;
            case "fruit" -> fruit;
            case "topping" -> topping;
            default -> null;
        };
    }

    @Override
    public String findType(String ingredient){
        for(Pair pair : this.condiment){
            if (pair.getOption().equals(ingredient))
                    return "condiment";
        }

        for(Pair pair : this.fruit){
            if (pair.getOption().equals(ingredient))
                return "fruit";
        }

        for(Pair pair : this.topping){
            if(pair.getOption().equals(ingredient))
                return "topping";
        }

        return null;
    }


}
