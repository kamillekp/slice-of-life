public class Cartao {
    private String nome;
    private String validade;
    private int numero;
    private int cvv;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // public void setValidade(Date validade) {
    //     this.validade = validade;
    // }

    // public Date getValidade() {
    //     return validade;
    // }    

    public void setNumero(int numero) {
        this.numero = numero;
    }   

    public int getNumero() {
        return numero;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
