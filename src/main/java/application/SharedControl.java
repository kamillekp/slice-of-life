package application;

/**
 * Classe Singleton para gerenciar os dados de controle compartilhados entre a aplicação.
 */
public class SharedControl {
    /**
     * Instância única de SharedControl.
     */
    private static SharedControl instance;

    /**
     * Pedido atual na aplicação.
     */
    private final Order order;

    /**
     * Pizza que está sendo manipulada.
     */
    private Pizza pizza;

    /**
     * Indicador de se a pizza está sendo editada (já foi criada uma vez).
     */
    private boolean editingAddedPizza;

    /**
     * Contador de sabores da pizza (qual sabor está sendo editado).
     */
    private int flavoursCounter;

    /**
     * Construtor que inicializa o pedido e o contador de sabores.
     */
    public SharedControl() {
        order = new Order();
        flavoursCounter = 0;
        pizza = null;
        editingAddedPizza = false;
    }

    /**
     * Retorna a instância singleton de SharedControl.
     *
     * @return a instância de SharedControl.
     */
    public static SharedControl getInstance() {
        if (instance == null) {
            instance = new SharedControl();
        }
        return instance;
    }

    /**
     * Inicializa uma nova pizza.
     */
    public void initPizza() {
        this.pizza = new Pizza();
    }

    /**
     * Retorna o pedido atual.
     *
     * @return o pedido atual.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Retorna o contador de sabores.
     *
     * @return o contador de sabores.
     */
    public int getFlavorsCounter() {
        return flavoursCounter;
    }

    /**
     * Reseta a pizza atual.
     */
    public void resetPizza() {
        this.pizza = new Pizza();
    }

    /**
     * Reseta o contador de sabores.
     */
    public void resetCounter() {
        flavoursCounter = 0;
    }

    /**
     * Incrementa o contador de sabores.
     */
    public void incrementFlavorsCounter() {
        flavoursCounter++;
    }

    /**
     * Decrementa o contador de sabores.
     */
    public void decrementFlavorsCounter() {
        flavoursCounter--;
    }

    /**
     * Reseta a instância singleton.
     */
    public void resetInstance() {
        instance = new SharedControl();
    }

    /**
     * Retorna a pizza atual.
     *
     * @return a pizza atual.
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * Define uma nova pizza.
     *
     * @param pizzaParam a nova pizza a ser definida.
     */
    public void setPizza(final Pizza pizzaParam) {
        this.pizza = pizzaParam;
    }

    /**
     * Verifica se a pizza está sendo editada.
     *
     * @return true se estiver editando a pizza, false caso contrário.
     */
    public boolean isEditingAddedPizza() {
        return editingAddedPizza;
    }

    /**
     * Define o estado de edição da pizza.
     *
     * @param editingAddedPizzaParam o novo estado de edição.
     */
    public void setEditingAddedPizza(final boolean editingAddedPizzaParam) {
        this.editingAddedPizza = editingAddedPizzaParam;
    }
}
