package application;


public class ChooseFlavorState extends State{

    public ChooseFlavorState(Client client){
        super(client);
    }

    public void processOrder(){
        this.nextState();
    }

    public void nextState(){
        this.getClient().setState(new PaymentState(this.getClient()));
    }
}
