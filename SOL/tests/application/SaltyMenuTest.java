package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaltyMenuTest {

    @Test
    void getIngredientsByCheeseTest() {
        var saltyMenu = new SaltyMenu();
        var result = saltyMenu.getIngredientsByType("cheese");
        assertEquals("Gorgonzola "+ 1.0  + " Ricota "+ 2.0 + " Requeijão " + 3.0 + " Cheddar " + 4.0 + " Brie " + 5.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() );
    }

    @Test
    void getIngredientsByVegetableTest() {
        var saltyMenu = new SaltyMenu();
        var result = saltyMenu.getIngredientsByType("vegetable");
        assertEquals("Brócolis "+ 1.0  + " Tomate "+ 2.0 + " Pimentão " + 3.0 + " Cebola " + 4.0 + " Palmito " + 5.0 + " Cogumelo " + 6.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice() );
    }

    @Test
    void getIngredientsByProteinTest() {
        var saltyMenu = new SaltyMenu();
        var result = saltyMenu.getIngredientsByType("protein");
        assertEquals("Filé "+ 1.0  + " Salmão "+ 2.0 + " Frango " + 3.0 + " Lombo " + 4.0 + " Bacon " + 5.0 + " Camarão " + 6.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice() );
    }


    @Test
    void getIngredientsByGreenLeafTest() {
        var saltyMenu = new SaltyMenu();
        var result = saltyMenu.getIngredientsByType("green leaf");
        assertEquals("Manjericão "+ 1.0  + " Salsa "+ 2.0 + " Rúcula " + 3.0 + " Agrião " + 4.0 + " Coentro " + 5.0 + " Espinafre " + 6.0,
                result[0].getOption() + " " + result[0].getPrice() + " " + result[1].getOption() + " " + result[1].getPrice() + " "
                        + result[2].getOption() + " " + result[2].getPrice() + " " + result[3].getOption() + " " + result[3].getPrice() + " "
                        + result[4].getOption() + " " + result[4].getPrice() + " " + result[5].getOption() + " " + result[5].getPrice() );
    }

    @Test
    void findCheeseTypeTest() {
        var saltyMenu = new SaltyMenu();
        assertEquals("cheese", saltyMenu.findType("Ricota"));
        assertNotEquals("cheese", saltyMenu.findType("Cogumelo"));
    }

    @Test
    void findVegetableTypeTest() {
        var saltyMenu = new SaltyMenu();
        assertEquals("vegetable", saltyMenu.findType("Tomate"));
        assertNotEquals("vegetable", saltyMenu.findType("Filé"));
    }

    @Test
    void findProteinTypeTest() {
        var saltyMenu = new SaltyMenu();
        assertEquals("protein", saltyMenu.findType("Salmão"));
        assertNotEquals("protein", saltyMenu.findType("Requeijão"));
    }

    @Test
    void findGreenLeafTypeTest() {
        var saltyMenu = new SaltyMenu();
        assertEquals("green leaf", saltyMenu.findType("Manjericão"));
        assertNotEquals("green leaf", saltyMenu.findType("Ricota"));
    }
}