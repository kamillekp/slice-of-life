package application;

public class Card {
    private String name;
    private String validity;
    private String number;
    private String cvv;

    public Card(String name, String validity, String number, String cvv) {
        this.name = name;
        this.validity = validity;
        this.number = number;
        this.cvv = cvv;
    }

    public Card(){
        this.name = null;
        this.validity = null;
        this.number = null;
        this.cvv = null;
    }

    public String getName() {
            return name;
    } 

    public String getValidity() {
         return validity;
    }    

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Validity: " + validity);
        System.out.println("Number: " + number);
        System.out.println("CVV: " + cvv);
    }
}
