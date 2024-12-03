package application;

import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean completed;
    private State state;
    
    public Order() {
        this.completed = false;
    }

    
    public void setPizzas(ArrayList<Pizza> pizzas) {
    	this.pizzas = pizzas;
    }
    public ArrayList<Pizza> getPizzas() {
    	return this.pizzas;
    }

    
    

	public void processOrder() {
		// TODO Auto-generated method stub
		return;
	}


	public void setState(State state) {
		this.state = state;
		
	}
}
