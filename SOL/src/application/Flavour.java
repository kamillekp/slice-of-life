package application;

import java.util.ArrayList;

public class Flavour {
    private final String type;
    private final ArrayList<String> ingredients;

    public Flavour(String type, ArrayList<String> ingredients) {
    	this.ingredients = ingredients;
    	this.type = type;
    }

    public Flavour (String type) {
        this.type = type;
        this.ingredients = new ArrayList<>();
    }


    public String getType() {
    	return this.type;
    }


    public ArrayList<String> getIngredients(){
    	return this.ingredients;
    }

}

