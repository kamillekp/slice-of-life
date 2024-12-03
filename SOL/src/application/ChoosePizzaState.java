package application;

public class ChoosePizzaState extends State{
    
    public ChoosePizzaState(Client client){
        super(client);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
    	this.getClient().setState(new ChooseFlavorState(this.getClient()));
    }
}
