

public class Client extends Order{
    private String name;
    private String surname;
    private boolean register; // verificar
    private Address address;
    private Payment payment;

    public Client(String name, String surname, Address address, Payment payment) {
        this.name = name;
        this.surname = surname;
        this.register = true;
        this.address = address;     
        this.payment = payment;
    }  
    

    
    public Client() {
		this.name = null;
		this.surname = null;
		this.register = false;
		this.address = null;
		this.payment = null;
	}



	public void ChangeRegister(){       // verificar
        this.register = !this.register;
    }

    public String getName() {
            return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean getRegister() {      // verificar
        return register;
    }  
    
    public Address getAddress() {
        return address;
    }

    public Payment getPayment() {
        return payment;
    }
}
