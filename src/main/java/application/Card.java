package application;

/**
 * Representa um cartão com detalhes como nome, validade, número e CVV.
 */
public class Card {
    /** Nome do cartão. */
    private String name;
    /** Data de validade do cartão (formato <dia>/<mes>/<ano>). */
    private String validity;
    /** Número do cartão. */
    private String number;
    /** cvv do cartão. */
    private String cvv;
    /**
     * Construtor para inicializar o cartão com nome, validade, número e CVV.
     *
     * @param nameParam o nome do titular do cartão
     * @param validityParam a validade do cartão
     * @param numberParam o número do cartão
     * @param cvvParam o CVV do cartão
     */
    public Card(final String nameParam, final String validityParam,
                final String numberParam, final String cvvParam) {
        this.name = nameParam;
        this.validity = validityParam;
        this.number = numberParam;
        this.cvv = cvvParam;
    }

    /**
     * Construtor para inicializar o cartão com todos os campos nulos.
     */
    public Card() {
        this.name = null;
        this.validity = null;
        this.number = null;
        this.cvv = null;
    }

    public String getName() {
        return this.name;
    }

    public String getValidity() {
        return this.validity;
    }

    public String getNumber() {
        return this.number;
    }

    public String getCvv() {
        return this.cvv;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public void setValidity(final String validityParam) {
        this.validity = validityParam;
    }

    public void setNumber(final String numberParam) {
        this.number = numberParam;
    }

    public void setCvv(final String cvvParam) {
        this.cvv = cvvParam;
    }

    /**
     * Exibe os detalhes do cartão.
     */
    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Validity: " + validity);
        System.out.println("Number: " + number);
        System.out.println("CVV: " + cvv);
    }

    /**
     * Exibe o nome do titular do cartão.
     */
    public void printName() {
        System.out.println("Titular: " + name);
    }
}
