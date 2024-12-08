package application;

public class SharedControl {
    private static SharedControl instance;
    private Order order;
    private Pizza pizza;

    public SharedControl() {
        order = new Order();
    }

    public static SharedControl getInstance() {
        if (instance == null) {
            instance = new SharedControl();
        }
        return instance;
    }

    public void InitPizza(boolean border, int numFlavor, String size) {
        this.pizza = new Pizza(border,numFlavor, size);
    }

    public Order getOrder() {
        return order;
    }

    public Pizza getPizza() {
        return pizza;
    }
}