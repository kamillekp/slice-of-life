package application;

public class Address {
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String complement;


    public Address(String street, String number, String city, String zipCode, String complement) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.complement = complement;
    }

    public Address(String street, String number, String city, String zipCode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.complement = null;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    } 

    public String getZipCode() {
        return zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void print(){
        System.out.println("City: " + city);
        System.out.println("Street: " + street);
        System.out.println("Number: " + number);
        System.out.println("Zip Code: " + zipCode);
        System.out.println("Complement: " + complement);
    }
}