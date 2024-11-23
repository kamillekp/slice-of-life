import java.util.ArrayList;

public class Pizza {
    private boolean border;
    private int numFlavor;
    private int size;
    private ArrayList<Flavor> flavors;

    public void setBorder(boolean borda) {
        this.border = border;
    }

    public boolean getBorder() {
        return border;
    }

    public void setNumFlavor(int numFlavor) {
        this.numFlavor = numFlavor;
    }

    public int getNumFlavor() {
        return numFlavor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setFlavors(ArrayList<Flavor> flavors) {
        this.flavors = flavors;
    }

    public ArrayList<Flavor> getFlavors() {
        return flavors;
    }
}
