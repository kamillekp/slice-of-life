package application;


public class Client {
    private String name;
    private String surname;
    private boolean register;
    private Address address;
    private Payment payment;

    public Client() {
		this.name = null;
		this.surname = null;
		this.register = false;
	}

    public void initAddress(String street, String number, String city, String zipCode, String complement){
        if(complement != null){
            this.address = new Address(street, number, city, zipCode, complement);
        }
        else{
            this.address = new Address(street, number, city, zipCode);
        }
    }

    public void initPayment(String type, Card card){
        if(card != null){
            this.payment = new Payment(type, card);
        }
        else{
            this.payment = new Payment(type);
        }
    }

    public String getName() {
            return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isRegister() {      // verificar
        return register;
    }  
    
    public Address getAddress() {
        return address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void changeRegister(){
        this.register = !this.register;
    }

    public void print(){
        System.out.println("Name: " + this.name);
        System.out.println("Surname: " + this.surname);
        this.payment.print();
        this.address.print();
    }
}
