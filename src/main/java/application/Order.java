package application;

import java.util.ArrayList;

/**
 * Representa um pedido de pizza, com o cliente associado e a lista de pizzas.
 * Contém métodos para acessar as pizzas, o cliente e o preço total do pedido.
 */
public class Order {

    /**
     * A lista de pizzas no pedido.
     */
    private final ArrayList<Pizza> pizzas;

    /**
     * O cliente associado ao pedido.
     */
    private final Client client;

    /**
     * O preço total do pedido.
     */
    private double totalPrice;

    /**
     * Construtor da classe Order.
     * Inicializa o cliente, a lista de pizzas e o preço total.
     */
    public Order() {
        this.client = new Client();
        this.pizzas = new ArrayList<Pizza>();
        this.totalPrice = 0;
    }

    /**
     * Retorna a lista de pizzas do pedido.
     *
     * @return A lista de pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * Retorna o cliente associado ao pedido.
     *
     * @return O cliente do pedido
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Define o preço total do pedido.
     *
     * @param totalPriceParam O preço total a ser definido
     */
    public void setTotalPrice(final double totalPriceParam) {
        this.totalPrice = totalPriceParam;
    }

    /**
     * Retorna o preço total do pedido.
     *
     * @return O preço total
     */
    public double getPrice() {
        return this.totalPrice;
    }
}
