package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        //change final output and added trim() to resolve end of line conflicts
        assertEquals(String.format("apple - 1 - €1,00%n" +
                "\n------ TOTAL : €1.0"), myOut.toString().trim());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2,00%n" +
                "\n------ TOTAL : €2.0"), myOut.toString().trim());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        assertEquals(String.format("apple - 2 - €2,00%n" +
                "banana - 1 - €2,00%n" +
                "\n------ TOTAL : €4.0"), myOut.toString().trim());
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0,00%n" +
                "\n------ TOTAL : €0.0"), myOut.toString().trim());
    }

    @Test
    public void canAddItemToShoppingCartOtherBranch(){
        ShoppingCartOtherBranch sc = new ShoppingCartOtherBranch(new Pricer());
        sc.addItem("apple", 1);
        String value = sc.contents.keySet().toString();
        String number = sc.contents.values().toString();
        assertTrue(value.equals("[apple]") && number.equals("[1]"));
    }

    @Test
    public void canAddDifferentItemsInShoppingCartOtherBranch() {
        ShoppingCartOtherBranch sc = new ShoppingCartOtherBranch(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        assertEquals(String.format("€2,00 - apple - 2%n" +
                "€2,00 - banana - 1%n" +
                "\n------ TOTAL : €4.0"), myOut.toString().trim());
    }
}


