

import java.util.ArrayList;

public class Flavor {
    private String type;
    private ArrayList<String> ingredients;

    // Só para teste
    public Flavor(String type, ArrayList<String> ingredients) {
    	this.ingredients = ingredients;
    	this.type = type;
    }
    
    public Flavor() {
    	return;
    }
    
    
    public String getType() {
    	return this.type;
    }
    
    public ArrayList<String> getIngredients(){
    	return this.ingredients;
    }
}

