public class FinalState extends State {
    public FinalState(Order order) {
        super(order);
    }

    public void processOrder() {
        this.nextState();
    }

    public void nextState() {
        this.getOrder().setState(new InitialState(this.getOrder()));
    }
    
}
