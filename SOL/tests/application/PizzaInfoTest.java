package application;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PizzaInfoTest {

    @Test
    void getPropertiesForBorderSituation() {
        var pizzaInfo = new PizzaInfo();
        var result = pizzaInfo.getProperties("border");
        assertEquals("Com Borda "+ 1.0  + "Sem Borda "+ 0.0,
        result[0].getOption() + " " + result[0].getPrice()  + result[1].getOption() + " " + result[1].getPrice());
        }

    @Test
    void getPropertiesForSizesSituation() {
        var pizzaInfo = new PizzaInfo();
        var  result = pizzaInfo.getProperties("sizes");
        assertEquals("Pequena (25 cm) "+ 25.0 + " Média (30 cm) "+ 30.0 + " Grande (35 cm) "+ 35.0 + " Família (40 cm) "+ 40.0 ,
        result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice()
                + " " + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice());
    }

    @Test
    void getPropertiesForNumFlavoursSituation() {
        var pizzaInfo = new PizzaInfo();
        var  result = pizzaInfo.getProperties("number of flavours");
        assertEquals("1 "+ 0.0 + " 2 "+ 1.0 + " 3 "+ 2.0 + " 4 "+ 3.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice()
                        + " " + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice());
    }

    @Test
    void getPriceForBorderTest() {
        var pizzaInfo = new PizzaInfo();
        assertEquals(0.0, pizzaInfo.getPrice("border", "Sem Borda"));
        assertEquals(1.0, pizzaInfo.getPrice("border", "Com Borda"));
    }
    @Test
    void getPriceForSizesTest() {
        var pizzaInfo = new PizzaInfo();
        assertEquals(25, pizzaInfo.getPrice("sizes", "Pequena (25 cm)"));
        assertEquals(30, pizzaInfo.getPrice("sizes", "Média (30 cm)"));
        assertEquals(35, pizzaInfo.getPrice("sizes", "Grande (35 cm)"));
        assertEquals(40, pizzaInfo.getPrice("sizes", "Família (40 cm)"));
    }@Test
    void getPriceForNumFlavoursTest() {
        var pizzaInfo = new PizzaInfo();
        assertEquals(0, pizzaInfo.getPrice("number of flavours", "1"));
        assertEquals(1, pizzaInfo.getPrice("number of flavours", "2"));
        assertEquals(2, pizzaInfo.getPrice("number of flavours", "3"));
        assertEquals(3, pizzaInfo.getPrice("number of flavours", "4"));

    }
}