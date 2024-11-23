public class ChooseFlavorState extends State{

    public ChooseFlavorState(Order order){
        super(order);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
        this.getOrder().setState(new PaymentState(this.getOrder()));
    }
}
