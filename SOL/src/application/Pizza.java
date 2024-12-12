package application;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private boolean border;
    private int numFlavor;
    private String size;
    private boolean finished;
    private ArrayList<Flavour> flavours;
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

    public boolean isFinished() {
        return finished;
    }

    public ArrayList<Flavour> getFlavors() {
        return flavours;
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

    public void changeFinished() {
        this.finished = !finished;
    }

    public void addFlavor(Flavour flavour) {
        this.flavours.add(flavour);
    }

    public double getPrice() {return this.price;}

    public void setPrice(double price) {this.price = price;}


    public void setBorder(boolean b) {this.border = b;}

    public List<Flavour> getSaboresDoces() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("doce")).toList();
    }

    public List<Flavour> getSaboresSalgados() {
        return this.getFlavors().stream().filter(f -> f.getType().equals("salgado")).toList();
    }
}
