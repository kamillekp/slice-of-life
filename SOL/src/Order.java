import java.util.ArrayList;

public class Order {
    private State state;
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean completed;

    public Order() {

        this.completed = false;
    }

    public void processOrder(){

    }

    public void nextState(){
        
    }
}
