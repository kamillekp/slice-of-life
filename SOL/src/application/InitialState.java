package application;


public class InitialState extends State{    
    public InitialState(Client client){
        super(client);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
        this.getClient().setState(new ChoosePizzaState(this.getClient()));
    }
}

