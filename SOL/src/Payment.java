public class Payment {
    private String type;
    private boolean filled;     // verificar
    private Card card;

    public Payment(String type, Card card) {
        this.type = type;
        this.filled = true;
        this.card = card;
    }

    public Payment(String type) {
        this.type = type;
        this.filled = true;
    }

    public void changeFilled() {        // verificar
        this.filled = !this.filled;
    }

    public String getType() {
        return type;
    }

    public boolean getFilled() {        // verificar
        return filled;
    }

    public Card getCard() {
        return card;
    }
}