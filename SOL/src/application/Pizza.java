package application;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private boolean border;
    private int numFlavor;
    private String size;
    private final boolean finished;
    private final ArrayList<Flavour> flavours;
    private double price;

    public Pizza() {
        this.flavours = new ArrayList<>();
        this.finished = false;
        this.border = false;
        this.price = 0;
        this.numFlavor = 0;
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

    public ArrayList<Flavour> getFlavors() {
        return flavours;
    }

    public void setNumFlavor(int numFlavor) {
        this.numFlavor = numFlavor;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {return this.price;}

    public void setPrice(double price) {this.price = price;}

    public void setBorder(boolean b) {this.border = b;}

    public List<Flavour> getSugaryFlavours() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("doce")).toList();
    }


    public List<Flavour> getSaltyFlavours() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("salgado")).toList();
    }
}
