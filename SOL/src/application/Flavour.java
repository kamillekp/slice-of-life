package application;

import java.util.ArrayList;

public class Flavour {
    private String type;
    private ArrayList<String> ingredients;

    public Flavour(String type, ArrayList<String> ingredients) {
    	this.ingredients = ingredients;
    	this.type = type;
    }

    public Flavour(String type){
        this.ingredients = new ArrayList<>();
        this.type = type;
    }

    public String getType() {
    	return this.type;
    }
    
    public ArrayList<String> getIngredients(){
    	return this.ingredients;
    }


}

