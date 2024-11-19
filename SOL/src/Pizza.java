import java.util.ArrayList;

public class Pizza {
    private boolean borda;
    private int quantidadeSabor;
    private int tamanho;
    private ArrayList<Sabor> sabores;

    public void setBorda(boolean borda) {
        this.borda = borda;
    }

    public boolean getBorda() {
        return borda;
    }

    public void setQuantidadeSabor(int quantidadeSabor) {
        this.quantidadeSabor = quantidadeSabor;
    }

    public int getQuantidadeSabor() {
        return quantidadeSabor;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setSabores(ArrayList<Sabor> sabores) {
        this.sabores = sabores;
    }

    public ArrayList<Sabor> getSabores() {
        return sabores;
    }


}
