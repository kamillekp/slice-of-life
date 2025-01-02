package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma pizza, que pode ter diferentes sabores, borda e tamanho.
 * Contém informações sobre os sabores da pizza e o preço.
 */
public final class Pizza {

    /**
     * A borda da pizza (com ou sem borda).
     */
    private boolean border;

    /**
     * Número de sabores na pizza.
     */
    private int numFlavor;

    /**
     * O tamanho da pizza (ex: pequena, média, grande).
     */
    private String size;

    /**
     * Lista de sabores da pizza.
     */
    private final ArrayList<Flavour> flavours;

    /**
     * Preço da pizza.
     */
    private double price;

    /**
     * Construtor da classe Pizza.
     * Inicializa os atributos da pizza com valores padrão.
     */
    public Pizza() {
        this.flavours = new ArrayList<>();
        this.border = false;
        this.price = 0;
        this.numFlavor = 0;
    }

    /**
     * Retorna se a pizza tem borda.
     *
     * @return true se a pizza tem borda, false caso contrário
     */
    public boolean isBorder() {
        return this.border;
    }

    /**
     * Retorna o número de sabores da pizza.
     *
     * @return O número de sabores na pizza
     */
    public int getNumFlavor() {
        return this.numFlavor;
    }

    /**
     * Retorna o tamanho da pizza.
     *
     * @return O tamanho da pizza (ex: pequena, média, grande)
     */
    public String getSize() {
        return this.size;
    }

    /**
     * Retorna a lista de sabores da pizza.
     *
     * @return Lista de sabores da pizza
     */
    public ArrayList<Flavour> getFlavors() {
        return this.flavours;
    }

    /**
     * Define o número de sabores da pizza.
     *
     * @param numFlavorParam O número de sabores a ser definido
     */
    public void setNumFlavor(final int numFlavorParam) {
        this.numFlavor = numFlavorParam;
    }

    /**
     * Define o tamanho da pizza.
     *
     * @param sizeParam O tamanho a ser definido (ex: pequena, média, grande)
     */
    public void setSize(final String sizeParam) {
        this.size = sizeParam;
    }

    /**
     * Retorna o preço da pizza.
     *
     * @return O preço da pizza
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Define o preço da pizza.
     *
     * @param priceParam O preço a ser definido
     */
    public void setPrice(final double priceParam) {
        this.price = priceParam;
    }

    /**
     * Define se a pizza tem borda.
     *
     * @param borderParam true para pizza com borda, false para sem borda
     */
    public void setBorder(final boolean borderParam) {
        this.border = borderParam;
    }

    /**
     * Retorna os sabores doces da pizza.
     *
     * @return Lista de sabores doces
     */
    public List<Flavour> getSugaryFlavours() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("doce")).toList();
    }

    /**
     * Retorna os sabores salgados da pizza.
     *
     * @return Lista de sabores salgados
     */
    public List<Flavour> getSaltyFlavours() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("salgado")).toList();
    }
    
    public void print_num_flavours(){
        System.out.println(this.numFlavor);


    }
}
