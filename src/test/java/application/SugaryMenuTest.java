package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SugaryMenuTest {

    @Test
    void getIngredientsByToppingTest() {
        var sugaryMenu = new SugaryMenu();
        var result = sugaryMenu.getIngredientsByType("topping");
        assertEquals("Ao leite "+ 1.0  + " Meio Amargo "+ 2.0 + " Branco " + 3.0 + " Amargo " + 4.0 + " Pistache " + 5.0 + " Avelã " + 6.0
                        + " Doce de Leite " + 7.0 + " Sorvete " + 8.0 + " Brigadeiro " + 9.0 + " Pasta de Amendoim " + 10.0 + " Beijinho " + 11.0,
            result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice() + " "
                + result[6].getOption() + " " + result[6].getPrice() + " " + result[7].getOption() + " " + result[7].getPrice() + " " + result[8].getOption() + " "
                + result[8].getPrice() + " " + result[9].getOption() + " " + result[9].getPrice() + " " + result[10].getOption() + " " + result[10].getPrice());
    }


    @Test
    void getIngredientsByFruitTest() {
        var sugaryMenu = new SugaryMenu();
        var result = sugaryMenu.getIngredientsByType("fruit");
        assertEquals("Morango "+ 1.0  + " Framboesa "+ 2.0 + " Abacaxi " + 3.0 + " Uva " + 4.0 + " Kiwi " + 5.0 + " Tâmara " + 6.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice());
    }
    @Test
    void getIngredientsByCondimentTest() {
        var sugaryMenu = new SugaryMenu();
        var result = sugaryMenu.getIngredientsByType("condiment");
        assertEquals("M&M's "+ 1.0  + " Amêndoas "+ 2.0 + " Coco ralado " + 3.0 + " Nozes " + 4.0 + " Paçoca " + 5.0 + " Castanhas " + 6.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice());
    }


    @Test
    void findToppingTypeTest() {
        var sugaryMenu = new SugaryMenu();
        assertEquals("topping", sugaryMenu.findType("Meio Amargo"));
        assertNotEquals("topping", sugaryMenu.findType("Morango"));
    }

    @Test
    void findFruitTypeTest() {
        var sugaryMenu = new SugaryMenu();
        assertEquals("fruit", sugaryMenu.findType("Abacaxi"));
        assertNotEquals("fruit", sugaryMenu.findType("Beijinho"));
    }
    @Test
    void findCondimentTypeTest() {
        var sugaryMenu = new SugaryMenu();
        assertEquals("condiment", sugaryMenu.findType("Coco ralado"));
        assertNotEquals("condiment", sugaryMenu.findType("Kiwi"));
    }
}