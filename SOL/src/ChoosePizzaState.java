public class ChoosePizzaState extends State{
    
    public ChoosePizzaState(Order order){
        super(order);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
        this.getOrder().setState(new ChooseFlavorState(this.getOrder()));
    }
}
