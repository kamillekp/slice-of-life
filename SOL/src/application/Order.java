package application;

import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas;
    private Client client;
    private boolean completed;

    public Order() {
        this.completed = false;
        this.client = new Client();
        this.pizzas = new ArrayList<>();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Client getClient() {
        return client;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void changeCompleted() {
        this.completed = !completed;
    }
}
