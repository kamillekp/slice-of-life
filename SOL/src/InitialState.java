public class InitialState extends State{    
    public InitialState(Order order){
        super(order);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
        this.getOrder().setState(new ChoosePizzaState(this.getOrder()));
    }
}

