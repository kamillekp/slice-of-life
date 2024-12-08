package application;

public class Client {
    private String name;
    private String surname;
    private boolean register;
    private Address address;
    private Payment payment;

    public Client() {
		this.name = "";
		this.surname = "";
		this.register = false;
		this.address = null;
		this.payment = null;
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

    public void ChangeRegister(){
        this.register = !this.register;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
