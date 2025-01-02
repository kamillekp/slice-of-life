package application;

/**
 * Representa um cliente com dados pessoais, endereço e dados de pagamento.
 */
public class Client {

    /**
     * O nome do cliente.
     */
    private String name;

    /**
     * O sobrenome do cliente.
     */
    private String surname;

    /**
     * O estado de registro do cliente.
     * Se verdadeiro, o cliente está registrado; se falso, não está.
     */
    private boolean register;

    /**
     * O endereço do cliente.
     */
    private Address address;

    /**
     * Os dados de pagamento do cliente.
     */
    private Payment payment;

    /**
     * Constrói um cliente com valores padrão.
     */
    public Client() {
        this.name = null;
        this.surname = null;
        this.register = false;
    }

    /**
     * Inicializa o endereço do cliente com os dados fornecidos.
     *
     * @param street o nome da rua do endereço
     * @param number o número da casa ou apartamento
     * @param city a cidade do endereço
     * @param zipCode o código postal
     * @param complement informações adicionais do endereço, como complemento de apartamento
     */
    public void initAddress(final String street, final String number, final String city,
                            final String zipCode, final String complement) {
        if (complement != null) {
            this.address = new Address(street, number, city, zipCode, complement);
        } else {
            this.address = new Address(street, number, city, zipCode);
        }
    }

    /**
     * Inicializa os dados de pagamento do cliente com base no tipo e no cartão fornecido.
     *
     * @param type o tipo de pagamento (por exemplo, "cartão de crédito")
     * @param card o cartão de pagamento
     */
    public void initPayment(final String type, final Card card) {
        if (card != null) {
            this.payment = new Payment(type, card);
        } else {
            this.payment = new Payment(type);
        }
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return o nome do cliente
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retorna o sobrenome do cliente.
     *
     * @return o sobrenome do cliente
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Retorna o estado de registro do cliente.
     *
     * @return verdadeiro se o cliente estiver registrado, falso caso contrário
     */
    public boolean isRegister() {
        return this.register;
    }

    /**
     * Retorna o endereço do cliente.
     *
     * @return o endereço do cliente
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Retorna os dados de pagamento do cliente.
     *
     * @return os dados de pagamento do cliente
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Altera o nome do cliente.
     *
     * @param nameParam o novo nome do cliente
     */
    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    /**
     * Altera o sobrenome do cliente.
     *
     * @param surnameParam o novo sobrenome do cliente
     */
    public void setSurname(final String surnameParam) {
        this.surname = surnameParam;
    }

    /**
     * Altera o estado de registro do cliente.
     * Caso esteja registrado, passará a não registrado e vice-versa.
     */
    public void changeRegister() {
        this.register = !this.register;
    }
}
