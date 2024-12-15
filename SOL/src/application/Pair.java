package application;

public class Pair {
    private final String option;
    private final double price;

    Pair(String option, double price) {
        this.option = option;
        this.price = price;
    }

    public String getOption() {
        return option;
    }

    public double getPrice() {
        return price;
    }
}
