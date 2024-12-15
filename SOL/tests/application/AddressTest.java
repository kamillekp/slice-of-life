package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printWithoutComplement() {
        var address = new Address("R. Tal","186","Porto Alegre", "90043245");
        address.print();
        assertEquals("City: Porto Alegre" +System.lineSeparator() +
                "Street: R. Tal" + System.lineSeparator() +
                "Number: 186" + System.lineSeparator() +
                "Zip Code: 90043245" + System.lineSeparator() +
                "Complement: null",outputStreamCaptor.toString().trim());
    }
    @Test
    void printWithComplement() {
        var address = new Address("R. Tal","186","Porto Alegre", "90043245", "301");
        address.print();
        assertEquals("City: Porto Alegre" + System.lineSeparator() +
                "Street: R. Tal" + System.lineSeparator() +
                "Number: 186" + System.lineSeparator() +
                "Zip Code: 90043245" + System.lineSeparator() +
                "Complement: 301",outputStreamCaptor.toString().trim());
    }


}