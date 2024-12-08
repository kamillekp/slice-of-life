package application;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private boolean border;
    private int numFlavor;
    private String size;
    private ArrayList<Flavor> flavors;

    public Pizza(boolean border, int numFlavor, String size) {
        this.border = border;
        this.numFlavor = numFlavor;
        this.size = size;
        this.flavors = new ArrayList<>();
    }

    public boolean isBorder() {
        return border;
    }

    public int getNumFlavor() {
        return numFlavor;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<Flavor> getFlavors() {
        return flavors;
    }

    public void changeBorder() {
        this.border = !border;
    }

    public void setNumFlavor(int numFlavor) {
        this.numFlavor = numFlavor;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void addFlavor(Flavor flavor) {
        this.flavors.add(flavor);
    }

    public void setBorder(boolean b) {this.border = b;}

    public List<Flavor> getSaboresDoces() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("doce")).toList();
    }

    public List<Flavor> getSaboresSalgados() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("salgado")).toList();
    }
}
