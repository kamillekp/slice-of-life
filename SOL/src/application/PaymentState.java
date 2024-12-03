package application;

public class PaymentState extends State {
    public PaymentState(Client client) {
        super(client);
    }

    public void processOrder() {
        this.nextState();
    }

    public void nextState() {
        this.getClient().setState(new InitialState(this.getClient()));
    }
}
