public class Cliente extends Pedido{
    private String nome;
    private String sobrenome;
    private boolean cadastro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    public boolean getCadastro() {
        return cadastro;
    }

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

    public class Endereco {
        private String cidade;
        private String rua;
        private String numero;
        private String cep;
        private String complemento;

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getRua() {
            return rua;
        }

        public void setRua(String rua) {
            this.rua = rua;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }   

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }
    } 
}
