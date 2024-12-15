package application;

/**
 * Singleton class to manage shared control data across the application.
 */
public class SharedControl {
    private static SharedControl instance;
    private final Order order;
    private Pizza pizza;
    private boolean editingAddedPizza;
    private int flavoursCounter;

    /**
     * Constructor initializes the order and flavours counter.
     */
    public SharedControl() {
        order = new Order();
        flavoursCounter = 0;
        pizza = null;
        editingAddedPizza = false;
    }

    /**
     * Returns the singleton instance of SharedControl.
     */
    public static SharedControl getInstance() {
        if (instance == null) {
            instance = new SharedControl();
        }
        return instance;
    }


    public void initPizza() {
        this.pizza = new Pizza();
    }


    public Order getOrder() {
        return order;
    }

    public int getFlavorsCounter() {
        return flavoursCounter;
    }

    public void resetPizza() {
        this.pizza = new Pizza();
    }


    public void resetCounter(){
        flavoursCounter = 0;
    }


    public void incrementFlavorsCounter(){
        flavoursCounter++;
    }


    public void decrementFlavorsCounter(){
        flavoursCounter--;
    }


    public void resetInstance(){
        instance = new SharedControl();
    }


    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {this.pizza = pizza;}

    public boolean isEditingAddedPizza() {
        return editingAddedPizza;
    }

    public void setEditingAddedPizza(boolean editingAddedPizza) {
        this.editingAddedPizza = editingAddedPizza;
    }

}