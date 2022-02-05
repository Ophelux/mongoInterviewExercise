# MongoDB Interview Exercise : ShoppingCart
### by Ophélie Amarine (Ophelux on GitHub)

I chose to implement the ShoppingCart with Java. 
The writing of the code took about 1h30.
The main issue I encountered was in the writing of the tests, because the end-of-line characters (LF or CRLF) disturbed the test instructions. All of the tests passed on my computerhope you won't have any issue while running them. 

## The work I have done 
- Added 'Total' line to the receipt. 
- Make the receipt print items in the order that they were scanned by changing the Hashmap to a LinkedHashmap
- In some other branches, customers want to show the price first on each line.
We could have added a boolean to the method printReceipt() to check if yes or no we are in one of these branches but it doesn't respect the Open-Closed Principle so I chose to create a new java class that extends ShoppingCart and implement printReceipt() in a different way. This solution support the new feature and it allows future changes to be implemented without changing the current behavior of the application.
- Update of the tests to match the new display and adding tests to check the new class behavior. 
- I added a method to display a header for the receipt (**printHeaderReceipt()**). It's not included in the **printReceipt()** method because I thought it coudld be a nice addition without being mandatory on each receipt. It can easily be added to the **printReceipt()** method but the tests for this function would become less easy to read. 
