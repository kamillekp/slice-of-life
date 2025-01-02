package application;

/**
 * Representa um pagamento, que pode ser feito através de diferentes tipos de métodos.
 * Pode incluir o uso de um cartão de crédito/débito associado ao pagamento.
 */
public final class Payment {

    /**
     * O tipo de pagamento (ex: cartão, dinheiro, pix).
     */
    private String type;

    /**
     * O cartão associado ao pagamento (se houver).
     */
    private Card card;

    /**
     * Construtor da classe Payment.
     * Inicializa o tipo de pagamento e o cartão associado.
     *
     * @param typeParam O tipo de pagamento (ex: cartão, dinheiro)
     * @param cardParam O cartão associado ao pagamento (pode ser nulo)
     */
    public Payment(final String typeParam, final Card cardParam) {
        this.type = typeParam;
        this.card = cardParam;
    }

    /**
     * Construtor da classe Payment.
     * Inicializa o tipo de pagamento sem um cartão associado.
     *
     * @param typeParam O tipo de pagamento (ex: cartão, dinheiro)
     */
    public Payment(final String typeParam) {
        this.type = typeParam;
        this.card = null;
    }

    /**
     * Retorna o tipo de pagamento.
     *
     * @return O tipo de pagamento (ex: cartão, dinheiro)
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retorna o cartão associado ao pagamento, se houver.
     *
     * @return O cartão associado ao pagamento ou null se não houver
     */
    public Card getCard() {
        return this.card;
    }

    /**
     * Define o tipo de pagamento.
     *
     * @param typeParam O tipo de pagamento a ser definido (ex: cartão, dinheiro)
     */
    public void setType(final String typeParam) {
        this.type = typeParam;
    }

    /**
     * Define o cartão associado ao pagamento.
     *
     * @param cardParam O cartão a ser associado ao pagamento
     */
    public void setCard(final Card cardParam) {
        this.card = cardParam;
    }
}
