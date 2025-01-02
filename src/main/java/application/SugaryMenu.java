package application;

/**
 * Classe que representa o menu de ingredientes doces, incluindo coberturas, frutas e condimentos.
 * Esta classe estende a classe {@link IngredientsMenu} e fornece uma implementação para
 * obter ingredientes por tipo e encontrar o tipo de um ingrediente.
 */
public class SugaryMenu extends IngredientsMenu {

    /**
     * Lista de coberturas disponíveis no menu.
     */
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

    /**
     * Lista de frutas disponíveis no menu.
     */
    private final Pair[] fruit = {
            new Pair("Morango", 1),
            new Pair("Framboesa", 2),
            new Pair("Abacaxi", 3),
            new Pair("Uva", 4),
            new Pair("Kiwi", 5),
            new Pair("Tâmara", 6)
    };

    /**
     * Lista de condimentos disponíveis no menu.
     */
    private final Pair[] condiment = {
            new Pair("M&M's", 1),
            new Pair("Amêndoas", 2),
            new Pair("Coco ralado", 3),
            new Pair("Nozes", 4),
            new Pair("Paçoca", 5),
            new Pair("Castanhas", 6)
    };

    /**
     * Retorna uma lista de ingredientes de acordo com o tipo informado.
     *
     * @param type o tipo de ingrediente (por exemplo, "condiment", "fruit" ou "topping").
     * @return um array de {@link Pair} correspondente ao tipo de ingrediente fornecido.
     */
    @Override
    public Pair[] getIngredientsByType(final String type) {
        return switch (type) {
            case "condiment" -> condiment;
            case "fruit" -> fruit;
            case "topping" -> topping;
            default -> null;
        };
    }

    /**
     * Encontra o tipo de ingrediente com base no nome fornecido.
     *
     * @param ingredient o nome do ingrediente.
     * @return o tipo de ingrediente ("condiment", "fruit" ou "topping"), ou {@code null} se não encontrado.
     */
    @Override
    public String findType(final String ingredient) {
        for (Pair pair : this.condiment) {
            if (pair.getOption().equals(ingredient)) {
                return "condiment";
            }
        }

        for (Pair pair : this.fruit) {
            if (pair.getOption().equals(ingredient)) {
                return "fruit";
            }
        }

        for (Pair pair : this.topping) {
            if (pair.getOption().equals(ingredient)) {
                return "topping";
            }
        }

        return null;
    }
}
