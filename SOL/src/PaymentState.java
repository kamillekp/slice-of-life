public class PaymentState extends State {
    public PaymentState(Order order) {
        super(order);
    }

    public void processOrder() {
        this.nextState();
    }

    public void nextState() {
        this.getOrder().setState(new InitialState(this.getOrder()));
    }
}
