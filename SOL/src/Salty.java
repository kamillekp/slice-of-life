import java.util.ArrayList;

public class Salty extends Flavor {
    private String[] cheese = {"Gorgonzola", "Ricota", "Requeijão", "Cheddar", "Brie"};
    private String[] vegetable= {"Brócolis", "Tomate", "Pimentão", "Cebola", "Palmito", "Cogumelo"};
    private String[] protein = {"Filé", "Salmão", "Frango", "Lombo", "Bacon", "Camarão"};
    private String[] greenLeafy = {"Manjericão", "Salsa", "Rúcula", "Agrião", "Coentro", "Espinafre"};

    public Salty() {
        super();
    }

    public String[] getCheese() {
        return cheese;
    }

    public String[] getVegetable() {
        return vegetable;
    }

    public String[] getProtein() {
        return protein;
    }

    public String[] getGreenLeafy() {
        return greenLeafy;
    }
    
}
