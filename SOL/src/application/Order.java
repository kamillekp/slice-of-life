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
        this.pizzas = new ArrayList<>();
        this.totalPrice = 0;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Client getClient() {
        return client;
    }

    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    public boolean isCompleted() {
        return completed;
    }

    public double getPrice() {
        return totalPrice;
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

    public void updateTotalPrice(double price) {
        this.totalPrice = this.totalPrice + price;
    }
}
