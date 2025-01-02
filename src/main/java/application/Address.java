package application;

/**
 * Pacote contendo classes relacionadas a endereços e
 * manipulação de dados de endereço.
 */
public final class Address {
    /** Cidade do endereço. */
    private String city;
    /** Rua do endereço. */
    private String street;
    /** Número do endereço. */
    private String number;
    /** Código postal do endereço. */
    private String zipCode;
    /** Complemento do endereço. */
    private String complement;

    /**
     * Construtor para criar um endereço com todos os campos.
     *
     * @param streetParam Rua do endereço.
     * @param numberParam Número do endereço.
     * @param cityParam Cidade do endereço.
     * @param zipCodeParam Código postal do endereço.
     * @param complementParam Complemento do endereço.
     */
    public Address(final String streetParam, final String numberParam, final String cityParam,
                   final String zipCodeParam, final String complementParam) {
        this.city = cityParam;
        this.street = streetParam;
        this.number = numberParam;
        this.zipCode = zipCodeParam;
        this.complement = complementParam;
    }

    /**
     * Construtor para criar um endereço sem complemento.
     *
     * @param streetParam Rua do endereço.
     * @param numberParam Número do endereço.
     * @param cityParam Cidade do endereço.
     * @param zipCodeParam Código postal do endereço.
     */
    public Address(final String streetParam, final String numberParam, final String cityParam,
                   final String zipCodeParam) {
        this(streetParam, numberParam, cityParam, zipCodeParam, null);
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getNumber() {
        return this.number;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setCity(final String cityParam) {
        this.city = cityParam;
    }

    public void setStreet(final String streetParam) {
        this.street = streetParam;
    }

    public void setNumber(final String numberParam) {
        this.number = numberParam;
    }

    public void setZipCode(final String zipCodeParam) {
        this.zipCode = zipCodeParam;
    }

    public void setComplement(final String complementParam) {
        this.complement = complementParam;
    }
}
