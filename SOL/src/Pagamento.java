public class Pagamento {
    private String tipo;
    private boolean preenchido;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPreenchido(boolean preenchido) {
        this.preenchido = preenchido;
    }

    public boolean getPreenchido() {
        return preenchido;
    }
}