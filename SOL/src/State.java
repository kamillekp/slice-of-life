public abstract class State {
    private Order order;

    public State(Order order) {
        this.order = order;
    }
    
    public abstract void processOrder();
    public abstract void nextState();

    public Order getOrder(){
        return this.order;
    }
}
