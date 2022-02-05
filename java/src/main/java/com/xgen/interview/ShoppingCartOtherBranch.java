package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;

/**
 * In some other branches, customers want to show the price forst on each line.
 *
 * We could have added a boolean to the method printReceipt() to check if yes or no we are in one of these branches
 * but it doesn't respect the Open-Closed Principle so I chose to create a new java class that extends ShoppingCart
 * and implement printReceipt() in a different way.
 */
public class ShoppingCartOtherBranch extends ShoppingCart{

    public ShoppingCartOtherBranch(Pricer pricer) {
        super(pricer);
    }

    @Override
    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();
        float total = 0;

        for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(price) / 100;
            String priceString = String.format("€%.2f", priceFloat);

            //add price to the total
            total += priceFloat;

            System.out.println(priceString + " - " + keys[i] + " - " + contents.get(keys[i]));
        }

        //Print the total to the user
        System.out.println("\n------ TOTAL : €"+total);
    }

    /*
    public static void main(String[] args){

        ShoppingCartOtherBranch sc = new ShoppingCartOtherBranch(new Pricer());
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.printReceipt();
    }
     */
}
