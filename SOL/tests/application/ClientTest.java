package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printShouldWorkTest() {
        var client = new Client();
        var card = new Card("Pedro","10/25","83923782","837");
        client.setName("John Doe");
        client.setSurname("Smith");
        client.initPayment("45344234", card);
        client.initAddress("R. Tal","186","Porto Alegre", "90043245", null);
        client.print();
        assertEquals("Name: John Doe" + System.lineSeparator() +
                "Surname: Smith" + System.lineSeparator() +
                "Type: 45344234" + System.lineSeparator() +
                "Name: Pedro" + System.lineSeparator() +
                "Validity: 10/25" + System.lineSeparator() +
                "Number: 83923782" + System.lineSeparator() +
                "CVV: 837" + System.lineSeparator() +
                "City: Porto Alegre" + System.lineSeparator() +
                "Street: R. Tal" + System.lineSeparator() +
                "Number: 186" + System.lineSeparator() +
                "Zip Code: 90043245" + System.lineSeparator() +
                "Complement: null" + System.lineSeparator() , outputStreamCaptor.toString());
    }

    @Test
    void addressShouldntHaveComplementTest() {
        var client = new Client();
        client.initAddress("R. Tal","186","Porto Alegre", "90043245", null);
        assertNull(client.getAddress().getComplement());
    }
    @Test
    void addressShouldHaveComplementTest() {
        var client = new Client();
        client.initAddress("R. Tal","186","Porto Alegre", "90043245", "323");
        assertNotNull(client.getAddress().getComplement());
    }

    @Test
    void paymentShouldHaveCardTest() {
        var client = new Client();
        var card = new Card("Pedro","10/25","83923782","837");
        client.initPayment("45344234", card);
        assertNotNull(client.getPayment().getCard());
    }
    @Test
    void paymentShouldntHaveCardTest() {
        var client = new Client();
        var card = new Card("Pedro","10/25","83923782","837");
        client.initPayment("45344234",null);
        assertNull(client.getPayment().getCard());
    }


}