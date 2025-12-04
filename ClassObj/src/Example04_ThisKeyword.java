/**
 * Example 04: The 'this' Keyword
 *
 * This example demonstrates:
 * - Using this to distinguish instance variables from parameters
 * - Using this() to call other constructors
 * - Using this to return current object (method chaining)
 * - Using this to pass current object as parameter
 */

class ProductItem {
    private String name;
    private double price;
    private int quantity;

    // Use 1: Distinguish instance variables from parameters
    public ProductItem(String name, double price, int quantity) {
        this.name = name;           // this.name = instance variable
        this.price = price;         // name = parameter
        this.quantity = quantity;
    }

    // Use 2: Constructor chaining with this()
    public ProductItem(String name) {
        this(name, 0.0, 0);  // Calls the main constructor
        System.out.println("Single-parameter constructor called");
    }

    public ProductItem(String name, double price) {
        this(name, price, 0);  // Calls the main constructor
        System.out.println("Two-parameter constructor called");
    }

    // Use 3: Method chaining - returning 'this'
    public ProductItem setName(String name) {
        this.name = name;
        return this;  // Returns current object
    }

    public ProductItem setPrice(double price) {
        this.price = price;
        return this;  // Returns current object
    }

    public ProductItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;  // Returns current object
    }

    public void displayInfo() {
        System.out.printf("Product: %s, Price: $%.2f, Quantity: %d%n",
                this.name, this.price, this.quantity);
    }

    // Use 4: Passing current object as parameter
    public void compareWith(ProductItem other) {
        System.out.println("\nComparing products:");
        System.out.println("This product: " + this.name);
        System.out.println("Other product: " + other.name);

        if (this.price > other.price) {
            System.out.println(this.name + " is more expensive");
        } else if (this.price < other.price) {
            System.out.println(other.name + " is more expensive");
        } else {
            System.out.println("Both products have the same price");
        }
    }

    // Method that prints object reference
    public void printReference() {
        System.out.println("Object reference (this): " + this);
    }
}

class BankAccount {
    private String accountNumber;
    private String owner;
    private double balance;

    public BankAccount(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    // Fluent interface using 'this'
    public BankAccount deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited $%.2f%n", amount);
        }
        return this;
    }

    public BankAccount withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.printf("Withdrew $%.2f%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
        return this;
    }

    public BankAccount displayBalance() {
        System.out.printf("Balance: $%.2f%n", this.balance);
        return this;
    }
}

public class Example04_ThisKeyword {
    public static void main(String[] args) {
        System.out.println("=== Example 04: The 'this' Keyword ===\n");

        // Use 1: Distinguishing instance variables from parameters
        System.out.println("--- Use 1: Parameter vs Field ---");
        ProductItem product1 = new ProductItem("Laptop", 999.99, 5);
        product1.displayInfo();

        // Use 2: Constructor chaining
        System.out.println("\n--- Use 2: Constructor Chaining ---");
        ProductItem product2 = new ProductItem("Mouse");
        product2.displayInfo();

        ProductItem product3 = new ProductItem("Keyboard", 79.99);
        product3.displayInfo();

        // Use 3: Method chaining (Fluent Interface)
        System.out.println("\n--- Use 3: Method Chaining ---");
        ProductItem product4 = new ProductItem("Phone");
        product4.setName("iPhone 14")
                .setPrice(899.99)
                .setQuantity(10);
        product4.displayInfo();

        System.out.println("\nChaining in one statement:");
        new ProductItem("Tablet")
                .setName("iPad Pro")
                .setPrice(1099.99)
                .setQuantity(3)
                .displayInfo();

        // Use 4: Passing current object as parameter
        System.out.println("\n--- Use 4: Passing 'this' as Parameter ---");
        ProductItem productA = new ProductItem("TV", 599.99, 2);
        ProductItem productB = new ProductItem("Monitor", 299.99, 5);

        productA.compareWith(productB);

        // Fluent interface with BankAccount
        System.out.println("\n--- Fluent Interface Example ---");
        BankAccount account = new BankAccount("ACC001", "John Doe");

        System.out.println("Performing multiple operations:");
        account.deposit(1000.00)
               .deposit(500.00)
               .withdraw(200.00)
               .displayBalance()
               .withdraw(100.00)
               .displayBalance();

        // Showing object reference
        System.out.println("\n--- Object Reference ---");
        ProductItem product5 = new ProductItem("Camera", 599.99, 1);
        System.out.println("Object variable: " + product5);
        product5.printReference();
        System.out.println("✓ Both refer to the same object");

        System.out.println("\n=== Key Points ===");
        System.out.println("✓ 'this' refers to the current object");
        System.out.println("✓ Use 'this.field' to distinguish from parameters");
        System.out.println("✓ Use 'this()' to call another constructor");
        System.out.println("✓ Return 'this' for method chaining (fluent interface)");
        System.out.println("✓ Pass 'this' to methods that need current object");
        System.out.println("✓ Cannot use 'this' in static context");
    }
}
