import java.util.ArrayList;

public abstract class Sabor {
    //private TipoSabor tipo;
    private String[] ingredientes;

    public Sabor(TipoSabor tipo) {
        this.tipo = tipo;
        
        if(tipo == TipoSabor.DOCE) {
            this.ingredientes = new Doce;
        } else {
            this.ingredientes = null;
        }
    }
}
