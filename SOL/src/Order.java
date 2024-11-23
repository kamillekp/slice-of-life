import java.util.ArrayList;

public class Order {
    private State Initialstate;
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean completed;
   
    public Order() {
        this.Initialstate = new InitialState(this);
        this.completed = false;
    }

    public void processOrder(){
        this.Initialstate.processOrder();
    }

    public void setState(State state){
        this.Initialstate = state;
    }
}
