package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    //Changed HashMap to LinkedHashMap to keep the order in which the items are scanned
    LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        //if it's the first time the cashier scans the product
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();
        float total = 0;

        for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(price) / 100;
            String priceString = String.format("€%.2f", priceFloat);

            //add price to the total
            total += priceFloat;

            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }

        //Print the total to the user
        System.out.println("\n------ TOTAL : €"+total);
    }

    /**
     * Function to display the header of the receipt, not in the printReceipt() method
     * since it's not necessary to print it on all receipts. Also, it simplifies the code of the tests.
     */
    public void printHeaderReceipt(){
        System.out.println("------ Your receipt ------");
        System.out.println("Thanks for shopping with us !");
        System.out.println("\nItem - Quantity - Price\n");
    }

    //Main method to test the implementation and display
    /*
    public static void main(String[] args){
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.addItem("apple", 2);
        sc.addItem("mango", 1);
        sc.addItem("apple", 2);
        sc.addItem("grapefruit", 1);
        sc.printHeaderReceipt();
        sc.printReceipt();
    }
     */

}
