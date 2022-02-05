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

    /*
    public static void main(String[] args){
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.printReceipt();
    }
     */
}
