// Connor Peng and Arta Amini
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Create Products and Categories
        String[] categories = {"Technology", "Clothing", "Literature"};

        ArrayList<String> electronics = new ArrayList<>();
        electronics.add("RTX 4090");
        electronics.add("iPhone 16");
        electronics.add("AirPods");
        electronics.add("Laptop");

        ArrayList<String> clothing = new ArrayList<>();
        clothing.add("White Tees");
        clothing.add("Jeans");
        clothing.add("Jordan 4's");
        clothing.add("Baseball Hats");

        ArrayList<String> books = new ArrayList<>();
        books.add("The Catcher in the Rye");
        books.add("Coding for Dummies");
        books.add("Charlotte's Web");
        books.add("The Little Prince");

        ArrayList<String> productNames = new ArrayList<>();
        productNames.add("RTX 4090");
        productNames.add("iPhone 16");
        productNames.add("AirPods");
        productNames.add("Laptop");
        productNames.add("White Tees");
        productNames.add("Jeans");
        productNames.add("Jordan 4's");
        productNames.add("Baseball Hats");
        productNames.add("The Catcher in the Rye");
        productNames.add("Coding for Dummies");
        productNames.add("Charlotte's Web");
        productNames.add("The Little Prince");

        double[] productPrices = {899.99, 699.99, 199.99, 499.99, 29.99, 39.99, 229.99, 39.99, 14.99, 16.99, 12.99, 9.99};

        ArrayList<Integer> productStock = new ArrayList<>();
        productStock.add(5);
        productStock.add(3);
        productStock.add(10);
        productStock.add(12);
        productStock.add(10);
        productStock.add(8);
        productStock.add(7);
        productStock.add(4);
        productStock.add(5);
        productStock.add(3);
        productStock.add(1);
        productStock.add(7);

        ArrayList<String> cartItems = new ArrayList<>();
        ArrayList<Integer> cartQuantities = new ArrayList<>();
        ArrayList<String> totalItems = new ArrayList<>();
        ArrayList<Integer> totalQuantity = new ArrayList<>();
        int orderAmounts = 0;

        System.out.println("\nWelcome to the Nick-Nack Corner!");
        boolean run = true;
        while (run) {
            System.out.println("Enter \"Products\" to browse categories, \"View Cart\" to view your cart, \"Checkout\" to checkout, \"Admin\" if you are an administrator, or \"Exit\" to exit.");
            System.out.println("Please select an option: ");
            String request = sc.nextLine();
            //Handling the Products
            if (request.equals("Products")) {
                System.out.println("Select a category: \"1\" for Technology, \"2\" for Clothing, and \"3\" for Literature");
                int categoryChoice = sc.nextInt();
                sc.nextLine(); // Consume the newline

                ArrayList<String> temp = new ArrayList<>();
                if (categoryChoice == 1) {
                    temp = electronics;
                } else if (categoryChoice == 2) {
                    temp = clothing;
                } else if (categoryChoice == 3) {
                    temp = books;
                } else {
                    while (categoryChoice != 1 || categoryChoice != 2 || categoryChoice != 3) {
                        System.out.println("Invalid category. Please choose again, or enter \"0\" to return to the menu.");
                        categoryChoice = sc.nextInt();
                        if (categoryChoice == 0) {
                            sc.nextLine(); //Consume new line
                            break;
                        }
                    }
                    if (categoryChoice == 1) {
                        temp = electronics;
                    }
                    else if (categoryChoice == 2) {
                        temp = clothing;
                    }
                    else if (categoryChoice == 3) {
                        temp = books;
                    }
                }
                if (temp.size() != 0) {
                    System.out.println("Available products:");
                    for (String product : temp) {
                        int index = productNames.indexOf(product);
                        int stock = productStock.get(index);
                        double price = productPrices[index];
                        if (stock > 0) {
                            System.out.println(product + " - " + stock + " in stock ($" + price + ")");
                        } else {
                            System.out.println(product + " - Out of Stock");
                        }
                    }

                    System.out.println("Enter the name of the product you wish to purchase or \"Back\" to return:");
                    String productPurchased = sc.nextLine();

                    while (!temp.contains(productPurchased)) {
                        System.out.println("Invalid product, try again.");
                        productPurchased = sc.nextLine();
                        if (productPurchased.equals("Back")) {
                            System.out.println("Returning to main menu.");
                            break;
                        }
                    }

                    if (temp.contains(productPurchased)) {
                        int productIndex = productNames.indexOf(productPurchased);
                        int stock = productStock.get(productIndex);
                        if (stock > 0) {
                            System.out.println("Enter quantity:");
                            int quantity = sc.nextInt();
                            sc.nextLine();

                            while (quantity > stock || quantity <= 0) {
                                System.out.println("Invalid quantity. Available stock: " + stock);
                                System.out.println("Enter a valid quantity:");
                                quantity = sc.nextInt();
                                sc.nextLine();
                            }
                            cartItems.add(productPurchased);
                            cartQuantities.add(quantity);
                            productStock.set(productIndex, stock - quantity);
                            System.out.println(quantity + " " + productPurchased + " added to your cart.");
                            System.out.println("Your cart currently contains:" );
                            for (int i = 0; i < cartItems.size(); i++) {
                                String item = cartItems.get(i);
                                int amount = cartQuantities.get(i);
                                System.out.println(item + " - " + amount + " units");
                            }
                        } else {
                            System.out.println("Sorry, this product is out of stock.");
                        }
                    }
                }
            }
            // Handling the View Cart Feature
            else if (request.equals("View Cart")) {
                System.out.println("Enter \"Remove\" to remove an item, \"Update\" to update quantities, or \"Back\" to return:");
                String cartAction = sc.nextLine();
                if (cartItems.size() == 0) {
                    System.out.println("Your cart is empty, sending you back to the menu.");
                    break;
                }
                System.out.println("Your cart contains:");
                double total = 0;
                for (int i = 0; i < cartItems.size(); i++) {
                    String item = cartItems.get(i);
                    int quantity = cartQuantities.get(i);
                    double price = productPrices[productNames.indexOf(item)];
                    total += price * quantity;
                    System.out.println(item + " - " + quantity + " units ($" + price + " each)");
                }
                System.out.println("Current Total: $" + total);

                if (cartAction.equals("Remove")) {
                    System.out.println("Enter the name of the product to remove:");
                    String itemToRemove = sc.nextLine();
                    while (!cartItems.contains(itemToRemove)) {
                        System.out.println("That product does not exist in your cart, try again.");
                        itemToRemove = sc.nextLine();
                    }
                    if (cartItems.contains(itemToRemove)) {
                        int cartStock = cartQuantities.get(cartItems.indexOf(itemToRemove));
                        System.out.println("How many units of this item would you like to remove?");
                        int unitsRemoved = sc.nextInt();

                        while (unitsRemoved > cartStock) {
                            System.out.println("Unable to remove that many units, try again.");
                            unitsRemoved = sc.nextInt();
                        }
                        // Index of the item in the cart
                        int index = cartItems.indexOf(itemToRemove);
                        // Index of the item as a whole
                        int stockIndex = productNames.indexOf(itemToRemove);
                        //Set the new product stock after removing
                        productStock.set(stockIndex, productStock.get(stockIndex) + cartQuantities.get(index));
                        if (cartStock - unitsRemoved == 0) {
                            cartItems.remove(index);
                        }
                        cartQuantities.set(index, cartStock - unitsRemoved);

                        System.out.println(unitsRemoved + " " + itemToRemove + " removed from your cart.");
                        System.out.println("Your cart now currently contains:");
                        for (int i = 0; i < cartQuantities.size() ; i++) {
                            String item = cartItems.get(i);
                            int quantity = cartQuantities.get(i);
                            System.out.println(item + " - " + quantity + " units");
                        }
                        double itemCost = productPrices[productNames.indexOf(itemToRemove)];
                        double newTotal = total - (unitsRemoved * itemCost);
                        System.out.println("Your new total cost is now: $" + newTotal);
                    }
                    else {
                        System.out.println("Item not found in cart. Returning you back to the menu.");
                    }
                }  else if (cartAction.equals("Update")) {
                    System.out.println("Enter the name of the product to update:");
                    String itemToUpdate = sc.nextLine();
                    if (cartItems.contains(itemToUpdate)) {
                        //Find the index of the item in your cart
                        int index = cartItems.indexOf(itemToUpdate);
                        //Find the index of the product's stock
                        int stockIndex = productNames.indexOf(itemToUpdate);
                        System.out.println("Enter new quantity:");
                        int newQuantity = sc.nextInt();
                        sc.nextLine();
                        //Find the current stock of the product
                        int currentStock = productStock.get(stockIndex);
                        //Find the current stock in cart
                        int oldQuantity = cartQuantities.get(index);

                        while (newQuantity > currentStock + oldQuantity || newQuantity <= 0) {
                            System.out.println("Invalid quantity. Available total stock: " + (currentStock + oldQuantity));
                            System.out.println("Enter a new quantity.");
                            newQuantity = sc.nextInt();
                            sc.nextLine();
                        }
                        cartQuantities.set(index, newQuantity);
                        productStock.set(stockIndex, currentStock + oldQuantity - newQuantity);
                        System.out.println("The quantity for " + itemToUpdate + " has been updated.");
                        System.out.println("Your cart now currently contains:");
                        for (int i = 0; i < cartQuantities.size(); i++) {
                            String item = cartItems.get(i);
                            int quantity = cartQuantities.get(i);
                            System.out.println(item + " - " + quantity + " units");
                        }
                    } else {
                        System.out.println("Item not found in cart, sending you back to the main menu.");
                    }
                }
                else {
                    System.out.println("Sending you back to the main menu.");
                }
            }
            //Checkout Feature
            else if (request.equals("Checkout")) {
                if (cartItems.size() == 0) {
                    System.out.println("Your cart is empty, there is nothing to checkout.");
                    System.out.println("Returning to main menu.");
                } else {
                    System.out.println("Confirm your order? Enter \"Yes\" or \"No\"");
                    String orderConfirmation = sc.nextLine();
                    if (orderConfirmation.equals("Yes")) {
                        double total = 0;
                        System.out.println("Invoice:");
                        for (int i = 0; i < cartItems.size(); i++) {
                            String item = cartItems.get(i);
                            int quantity = cartQuantities.get(i);
                            double price = productPrices[productNames.indexOf(item)];
                            total += price * quantity;
                            System.out.println(item + " - " + quantity + " units @ $" + price + " each");
                            totalQuantity.add(quantity);
                            totalItems.add(item);
                        }
                        System.out.println("Total: $" + total);
                        System.out.println("Order Confirmed.\nThank you for shopping with us!");
                        orderAmounts++;
                        cartItems.clear();
                        cartQuantities.clear();
                    }
                    else {
                        System.out.println("Order confirmation rejected, returning you to the main menu.");
                    }
                }
            }
            //Admin Feature
            else if (request.equals("Admin")) {
                System.out.println("Enter the password to log in:");
                String userPassword = sc.nextLine();
                while (!userPassword.equals("Admin!234")) {
                    System.out.println("Invalid password, try again.");
                    userPassword = sc.nextLine();
                }
                if (userPassword.equals("Admin!234")) {
                    System.out.println("Here are the lists of all customer orders:");
                    System.out.println("Total orders: " + orderAmounts);
                    for (int index = 0; index < totalItems.size(); index++) {
                        String item = totalItems.get(index);
                        System.out.println(item + ", ");
                    }

                    System.out.println("Total item count for each order:");
                    for (int index = 0; index < totalQuantity.size(); index++) {
                        int quantity = totalQuantity.get(index);
                        System.out.println(quantity + ", ");
                    }

                    System.out.println("Would you like to mark the orders as shipped? \"Yes\" or \"No\".");
                    String adminResponse = sc.nextLine();
                    if (adminResponse.equals("Yes")) {
                        System.out.println("All orders have been marked for shipping! The admin interface will now be closing.");
                        totalItems.clear();
                        totalQuantity.clear();
                    }
                    else {
                        System.out.println("Order have not yet been marked for shipping. The admin interface will now be closing.");
                    }
                } else {
                    System.out.println("Invalid password.");
                }
            } else if (request.equals("Exit")) {
                System.out.println("Thank you for using the Nick-Nack corner for online shopping, bye!");
                run = false;
            } else {
                System.out.println("Invalid option. Bringing you back to the main menu.");
            }
        }
    }
}
