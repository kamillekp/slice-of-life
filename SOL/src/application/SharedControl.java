package application;

/**
 * Singleton class to manage shared control data across the application.
 */
public class SharedControl {
    private static SharedControl instance; // Singleton instance
    private Order order; // Order object to store order details
    private Pizza pizza; // Pizza object to store pizza details
    private boolean finishedPizzaInfo; // Flag to indicate if pizza info is finished
    private int flavoursCounter; // Counter for the number of flavours

    /**
     * Constructor initializes the order and flavours counter.
     */
    public SharedControl() {
        // Static instance of the order to be shared across the application
        order = new Order();
        // Static counter for the number of flavours of the current pizza (?)
        flavoursCounter = 0;
        this.finishedPizzaInfo = false;
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


    public boolean isFinishedPizzaInfo() {
        return finishedPizzaInfo;
    }


    public void changeFinishedPizzaInfo() {
        finishedPizzaInfo = !finishedPizzaInfo;
    }
}