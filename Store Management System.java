//importing java libraries
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Item class represents a product in the store with all required attributes
 */
class Item {
    // Private fields for encapsulation
    private String name;
    private String vendor;
    private double price;
    private double cost;
    private double weight;
    private boolean taxable;

    /**
     * Constructor that accepts name, cost, and price
     */
    public Item(String name, double cost, double price) {
        this.name = name;
        this.cost = cost;
        this.price = price;
        // Default values for other attributes
        this.vendor = "Unknown";
        this.weight = 0.0;
        this.taxable = false;
    }

    // Getter and setter methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    /**
     * Calculates the profit margin (price - cost)
     * @return Profit margin amount
     */
    public double calculateProfitMargin() {
        return price - cost;
    }
}

/**
 * ShoppingCart class manages a collection of items using aggregation
 */
class ShoppingCart {
    // Using ArrayList to store items (aggregation)
    private ArrayList<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart
     * @param item Item to be added
     */
    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " added to cart.");
    }

    /**
     * Removes an item from the shopping cart by name
     * @param itemName Name of item to remove
     */
    public void removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                System.out.println(itemName + " removed from cart.");
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    /**
     * Calculates total number of items in cart
     * @return Total item count
     */
    public int getTotalItems() {
        return items.size();
    }

    /**
     * Calculates total cost including tax where applicable
     * @return Total cost with tax
     */
    public double getTotalCost() {
        double total = 0;
        for (Item item : items) {
            if (item.isTaxable()) {
                total += item.getPrice() * 1.10; // Add 10% tax
            } else {
                total += item.getPrice();
            }
        }
        return total;
    }

    /**
     * Calculates total weight of all items
     * @return Total weight
     */
    public double getTotalWeight() {
        double total = 0;
        for (Item item : items) {
            total += item.getWeight();
        }
        return total;
    }

    /**
     * Prints a receipt showing all items with details
     */
    public void printReceipt() {
        System.out.println("\n=== STORE RECEIPT ===");
        System.out.println("ITEM\t\tPRICE\tTAX");
        System.out.println("----------------------------");
        
        for (Item item : items) {
            System.out.printf("%-10s\t$%.2f\t%s\n", 
                item.getName(), 
                item.getPrice(), 
                item.isTaxable() ? "Yes (10%)" : "No");
        }
        
        System.out.println("----------------------------");
        System.out.printf("TOTAL ITEMS: %d\n", getTotalItems());
        System.out.printf("TOTAL WEIGHT: %.2f kg\n", getTotalWeight());
        System.out.printf("TOTAL COST: $%.2f\n", getTotalCost());
        System.out.println("============================");
    }
}

/**
 * Main class to demonstrate the Store Management System
 */
// ... (previous Item and ShoppingCart classes remain the same)

public class StoreManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        
        System.out.println("=== STORE MANAGEMENT SYSTEM ===");
        
        // Create some sample items
        Item item1 = new Item("Laptop", 800, 1000);
        item1.setWeight(2.5);
        item1.setTaxable(true);
        
        Item item2 = new Item("Mouse", 10, 15);
        item2.setWeight(0.2);
        
        Item item3 = new Item("Notebook", 2, 5);
        item3.setWeight(0.3);
        item3.setTaxable(true);
        
        // Simple menu system with error handling
        while (true) {
            System.out.println("\n1. Add sample items to cart");
            System.out.println("2. View receipt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

          //Exception handing with Try catch blocks
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                switch (choice) {
                    case 1:
                        cart.addItem(item1);
                        cart.addItem(item2);
                        cart.addItem(item3);
                        break;
                    case 2:
                        cart.printReceipt();
                        break;
                    case 3:
                        System.out.println("Thank you for using the system!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (java.util.InputMismatchException e) { //Input mismatch exception checks datatype erorr on user input
                System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
                scanner.nextLine(); // Clear the invalid input
            }

              //runs the print statement whether the condition is true or false
            finally {
                System.out.println("Thank you for purshasing these items....");
            }
}
}
}
