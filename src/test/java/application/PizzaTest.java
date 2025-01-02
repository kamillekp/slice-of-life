package application;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTest {


    @Test
    void shouldReturnOnlySugary() {
        var sabor1 = new ArrayList<String>();
        sabor1.add("doce1");
        sabor1.add("doce2");
        var flavour_sugary1 = new Flavour("doce",sabor1);
        var sabor2 = new ArrayList<String>();
        sabor1.add("doce3");
        sabor1.add("doce4");
        var flavour_sugary2 = new Flavour("doce",sabor2);
        var sabor3 = new ArrayList<String>();
        sabor1.add("salgado1");
        sabor1.add("salgado2");
        var flavour_salty1 = new Flavour("salgado",sabor3);
        var sabor4 = new ArrayList<String>();
        sabor1.add("salgado3");
        sabor1.add("salgado4");
        var flavour_salty2 = new Flavour("salgado",sabor4);
        var pizza = new Pizza();
        pizza.getFlavors().add(flavour_sugary1);
        pizza.getFlavors().add(flavour_salty1);
        pizza.getFlavors().add(flavour_salty2);
        pizza.getFlavors().add(flavour_sugary2);
        var sugaryList = new ArrayList<String>();
        var expectedList = new ArrayList<String>();
        for (Flavour flavour : pizza.getSugaryFlavours()) {
            sugaryList.add(flavour.getType());
        }
        expectedList.add("doce");
        expectedList.add("doce");
        assertEquals(expectedList,sugaryList);

    }

    @Test
    void shouldReturnOnlySalty() {
        var sabor1 = new ArrayList<String>();
        sabor1.add("doce1");
        sabor1.add("doce2");
        var flavour_sugary1 = new Flavour("doce",sabor1);
        var sabor2 = new ArrayList<String>();
        sabor1.add("doce3");
        sabor1.add("doce4");
        var flavour_sugary2 = new Flavour("doce",sabor2);
        var sabor3 = new ArrayList<String>();
        sabor1.add("salgado1");
        sabor1.add("salgado2");
        var flavour_salty1 = new Flavour("salgado",sabor3);
        var sabor4 = new ArrayList<String>();
        sabor1.add("salgado3");
        sabor1.add("salgado4");
        var flavour_salty2 = new Flavour("salgado",sabor4);
        var pizza = new Pizza();
        pizza.getFlavors().add(flavour_sugary1);
        pizza.getFlavors().add(flavour_salty1);
        pizza.getFlavors().add(flavour_salty2);
        pizza.getFlavors().add(flavour_sugary2);
        var sugaryList = new ArrayList<String>();
        var expectedList = new ArrayList<String>();
        for (Flavour flavour : pizza.getSaltyFlavours()) {
            sugaryList.add(flavour.getType());
        }
        expectedList.add("salgado");
        expectedList.add("salgado");
        assertEquals(expectedList,sugaryList);
    }
}