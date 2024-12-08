package application;

public class SharedControl {
    private static SharedControl instance;
    private Order order;

    public SharedControl() {
        order = new Order();
    }

    public static SharedControl getInstance() {
        if (instance == null) {
            instance = new SharedControl();
        }
        return instance;
    }

    public Order getOrder() {
        return order;
    }
}