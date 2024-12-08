package application;

public class SharedControl {
    private static SharedControl instance;
    private Order order;
    private Pizza pizza;
    private boolean finishedPizzaInfo;

    public SharedControl() {
        order = new Order();
    }

    public static SharedControl getInstance() {
        if (instance == null) {
            instance = new SharedControl();
        }
        return instance;
    }

    public void InitPizza() {
        this.pizza = new Pizza();
    }

    public Order getOrder() {
        return order;
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