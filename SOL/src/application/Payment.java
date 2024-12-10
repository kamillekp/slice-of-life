package application;

public class Payment {
    private String type;
    private Card card;
    private float value;

    public Payment(String type, Card card) {
        this.type = type;
        this.card = card;
    }

    public Payment(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Card getCard() {
        return card;
    }

    public float getValue() {
        return value;
    }
}