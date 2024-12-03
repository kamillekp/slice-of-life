package application;


public abstract class State {
    private Client client;

    public State(Client client) {
        this.client = client;
    }
    
    public abstract void processOrder();
    public abstract void nextState();

    public Client getClient(){
        return this.client;
    }
}
