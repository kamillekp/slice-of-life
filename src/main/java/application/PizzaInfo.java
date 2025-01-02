package application;

/**
 * Classe que contém informações sobre os tipos de pizza, tamanhos, número de sabores e bordas.
 * Ela fornece métodos para recuperar essas propriedades e calcular o preço com base na seleção do usuário.
 */
public final class PizzaInfo {

    /**
     * Propriedades do tamanho da pizza.
     */
    private final Pair[] sizes = {
            new Pair("Pequena (25 cm)", 25),
            new Pair("Média (30 cm)", 30),
            new Pair("Grande (35 cm)", 35),
            new Pair("Família (40 cm)", 40),
    };

    /**
     * Propriedades do número de sabores da pizza.
     */
    private final Pair[] numFlavors = {
            new Pair("1", 0),
            new Pair("2", 1),
            new Pair("3", 2),
            new Pair("4", 3),
    };

    /**
     * Propriedades da borda da pizza.
     */
    private final Pair[] pizzaBorder = {
            new Pair("Com Borda", 1),
            new Pair("Sem Borda", 0),
    };

    /**
     * Retorna as propriedades de uma categoria específica, como bordas, tamanhos ou número de sabores.
     *
     * @param type Tipo da propriedade que se deseja recuperar (bordas, tamanhos ou número de sabores)
     * @return Um array de pares representando as opções disponíveis para o tipo solicitado
     */
    public Pair[] getProperties(final String type) {
        return switch (type) {
            case "border" -> pizzaBorder;
            case "sizes" -> sizes;
            case "number of flavours" -> numFlavors;
            default -> null;
        };
    }

    /**
     * Calcula o preço baseado no tipo e item selecionado.
     *
     * @param type Tipo da propriedade (bordas, tamanhos ou número de sabores)
     * @param item O item específico da propriedade (ex: "Com Borda", "Pequena (25 cm)", etc.)
     * @return O preço correspondente ao item selecionado
     */
    public double getPrice(final String type, final String item) {
        Pair[] properties = getProperties(type);

        if (properties == null) {
            throw new NullPointerException("No properties found for type " + type);
        }

        double price = 0;

        for (Pair property : properties) {
            if (property.getOption().equals(item)) {
                price = property.getPrice();
            }
        }

        return price;
    }
}
