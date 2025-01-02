package application;

/**
 * Representa um par de opção e preço.
 * Cada instância da classe contém uma opção (string) e um preço associado (double).
 */
public final class Pair {

    /**
     * A opção associada ao par.
     */
    private final String option;

    /**
     * O preço associado ao par.
     */
    private final double price;

    /**
     * Construtor da classe Pair.
     * Inicializa a opção e o preço do par.
     *
     * @param optionParam A opção associada ao par
     * @param priceParam O preço associado à opção
     */
    public Pair(final String optionParam, final double priceParam) {
        this.option = optionParam;
        this.price = priceParam;
    }

    /**
     * Retorna a opção associada a este par.
     *
     * @return A opção do par
     */
    public String getOption() {
        return this.option;
    }

    /**
     * Retorna o preço associado a este par.
     *
     * @return O preço do par
     */
    public double getPrice() {
        return this.price;
    }
}
