public class App {
    public static void main(String[] args) throws Exception {
        Client c =  new Client  ("John", "Doe", 
                                    new Address("Cidade 1", "Rua 1", "123", "1234567"), 
                                    new Payment("Card", new Card("John Doe", "01/23", 123456789, 123))
                                );

        System.out.println(c.getAddress().getStreet());
        System.out.println(c.getPayment().getCard().getName());
    }
}
