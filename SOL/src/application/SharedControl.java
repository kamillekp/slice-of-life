package application;

public class SharedControl {
    private static SharedControl instance;
    private Order order;
    private Pizza pizza;
    private boolean finishedPizzaInfo;
    private int flavorsCounter;

    public SharedControl() {
        order = new Order();
        flavorsCounter = 0;
        this.finishedPizzaInfo = false;
    }

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
        return flavorsCounter;
    }

    public void incrementFlavorsCounter(){
        flavorsCounter++;
    }

    public void decrementFlavorsCounter(){
        flavorsCounter--;
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