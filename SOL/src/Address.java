

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
    }

    public String getcity() {
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
} 