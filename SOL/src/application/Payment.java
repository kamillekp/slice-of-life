package application;

public class Payment {
    private String type;
    private Card card;

    public Payment(String type, Card card) {
        this.type = type;
        this.card = card;
    }

    public Payment(String type) {
        this.type = type;
        this.card = null;
    }

    public String getType() {
        return type;
    }

    public Card getCard() {
        return card;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}