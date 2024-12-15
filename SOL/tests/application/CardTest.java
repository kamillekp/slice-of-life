package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printShouldWork() {
        var card = new Card("Pedro","10/25","83923782","837");
        card.print();
        assertEquals("Name: Pedro" + System.lineSeparator() +
                "Validity: 10/25" + System.lineSeparator() +
                "Number: 83923782" + System.lineSeparator() +
                "CVV: 837", outputStreamCaptor.toString().trim());
    }
}