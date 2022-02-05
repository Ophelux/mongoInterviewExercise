package com.xgen.interview;

import java.lang.reflect.Array;

/**
 * New class to implement a different display of the receipt
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

    /**
     * Function to display the header of the receipt, not in the printReceipt() method
     * since it's not necessary to print it on all receipts. Also, it simplifies the code of the tests.
     */
    public void printHeaderReceipt(){
        System.out.println("------ Your receipt ------");
        System.out.println("Thanks for shopping with us !");
        System.out.println("\nPrice - Item - Quantity\n");
    }

    //Main methods to test the implementation and display
    /*
    public static void main(String[] args){
        ShoppingCartOtherBranch sc = new ShoppingCartOtherBranch(new Pricer());
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.printHeaderReceipt();
        sc.printReceipt();
    }
     */

}
