public class ReviewState extends State {
    public ReviewState(Order order) {
        super(order);
    }

    public void processOrder() {
        this.nextState();
    }

    public void nextState() {
        this.getOrder().setState(new FinalState(this.getOrder()));
    }
    
}
