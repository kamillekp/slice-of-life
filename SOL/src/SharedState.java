
import java.util.ArrayList;

public class SharedState {;
	
	private static SharedState instance = null;
	private Client client;
	
	
	
	public SharedState(){
		this.client = new Client();		
	}
	
    public static SharedState getInstance() {
        if (instance == null) {
            instance = new SharedState();
        }
        return instance;
    }
	

    // Adiciona uma pizza ao pedido
    public void addPizza(Pizza pizza) {
        this.client.getPizzas().add(pizza);
        
    }

    // Retorna a lista de strings
    public ArrayList<Pizza> getPizzas() {
        return this.client.getPizzas();
    }
    
    
    public String getName() {
    	return this.client.getName();
    }
    
    public String getSurname() {
    	return this.client.getSurname();
    }
    
    public Payment getPayment(){
    	return this.client.getPayment();
    }
    
    public Address getAdress(){
    	return this.client.getAddress();
    }
    
    
}

