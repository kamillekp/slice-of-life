import java.util.ArrayList;

public class Order {
    private State currentState;
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean completed;
   
    public Order() {
        this.currentState = new InitialState(this);
        this.completed = false;
    }

    public void processOrder(){
        this.currentState.processOrder();
    }

    public void setState(State state){
        this.currentState = state;
    }
}
