package application;

import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas;
    private Client client;
    private boolean completed;
    private double totalPrice;

    public Order() {
        this.completed = false;
        this.client = new Client();
        this.pizzas = new ArrayList<Pizza>();
        this.totalPrice = 0;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Client getClient() {
        return client;
    }

    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    public double getPrice() {
        return totalPrice;
    }
}
