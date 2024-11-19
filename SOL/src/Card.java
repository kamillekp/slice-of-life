public class Card {
    private String name;
    private String validity;
    private int number;
    private int cvv;

    public Card(String name, String validity, int number, int cvv) {
        this.name = name;
        this.validity = validity;
        this.number = number;
        this.cvv = cvv;
    }

    public String getName() {
            return name;
    } 

    public String getValidity() {
         return validity;
    }    

    public int getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }
}
