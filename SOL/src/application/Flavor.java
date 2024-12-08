package application;

import java.util.ArrayList;

public class Flavor {
    private String type;
    private ArrayList<String> ingredients;

    public Flavor(String type, ArrayList<String> ingredients) {
    	this.ingredients = ingredients;
    	this.type = type;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public ArrayList<String> getIngredients(){
    	return this.ingredients;
    }


}

