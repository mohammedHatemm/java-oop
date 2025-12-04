/**
 * MASTER EXAMPLE: Complete E-Commerce System
 *
 * This comprehensive example demonstrates ALL concepts covered:
 * - Classes and Objects
 * - Constructors (default, parameterized, chaining)
 * - 'this' keyword
 * - Method overloading
 * - Static and instance members
 * - Composition (has-a relationship)
 * - Object comparison (equals and hashCode)
 * - Method chaining (fluent interface)
 * - toString() override
 * - Encapsulation (private fields, public methods)
 * - Static utility classes
 * - Object counters
 * - Builder pattern
 * - Business logic implementation
 *
 * Real-world scenario: Online Shopping System
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// ==================== PRODUCT CLASS ====================
class Product {
    private static int productCounter = 0;

    private final String productId;
    private String name;
    private double price;
    private String category;
    private int stockQuantity;

    // Constructor chaining
    public Product(String name, double price) {
        this(name, price, "General", 0);
    }

    public Product(String name, double price, String category) {
        this(name, price, category, 0);
    }

    public Product(String name, double price, String category, int stockQuantity) {
        this.productId = "PROD" + (++productCounter);
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    // Static method
    public static int getTotalProducts() {
        return productCounter;
    }

    // Method chaining (fluent interface)
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public Product setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
        return this;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public boolean reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
            return true;
        }
        return false;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    // Override toString
    @Override
    public String toString() {
        return String.format("%s: %s - $%.2f [Stock: %d]",
                productId, name, price, stockQuantity);
    }
}

// ==================== ADDRESS CLASS (Composition) ====================
class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s %s", street, city, state, zipCode);
    }
}

// ==================== CUSTOMER CLASS ====================
class Customer {
    private static int customerCounter = 0;

    private final String customerId;
    private String name;
    private String email;
    private Address address;  // Composition
    private ShoppingCart cart;  // Composition

    public Customer(String name, String email) {
        this.customerId = "CUST" + (++customerCounter);
        this.name = name;
        this.email = email;
        this.cart = new ShoppingCart(this);
    }

    public Customer(String name, String email, Address address) {
        this(name, email);
        this.address = address;
    }

    public static int getTotalCustomers() {
        return customerCounter;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Address getAddress() { return address; }
    public ShoppingCart getCart() { return cart; }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", customerId, name, email);
    }
}

// ==================== CART ITEM CLASS ====================
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x %d = $%.2f",
                product.getName(), quantity, getSubtotal());
    }
}

// ==================== SHOPPING CART CLASS ====================
class ShoppingCart {
    private Customer customer;
    private List<CartItem> items;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    // Method overloading
    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, int quantity) {
        // Check if product already in cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                System.out.println("✓ Updated quantity for: " + product.getName());
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        System.out.println("✓ Added to cart: " + product.getName());
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
        System.out.println("✓ Removed from cart: " + product.getName());
    }

    public void clear() {
        items.clear();
        System.out.println("✓ Cart cleared");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n--- Shopping Cart for " + customer.getName() + " ---");
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total: $%.2f%n", getTotal());
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);  // Defensive copy
    }
}

// ==================== ORDER CLASS ====================
class Order {
    private static int orderCounter = 0;

    private final String orderId;
    private Customer customer;
    private List<CartItem> items;
    private double totalAmount;
    private String status;

    private Order(Builder builder) {
        this.orderId = "ORD" + (++orderCounter);
        this.customer = builder.customer;
        this.items = builder.items;
        this.totalAmount = builder.totalAmount;
        this.status = "Pending";
    }

    public static int getTotalOrders() {
        return orderCounter;
    }

    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("\n========== ORDER DETAILS ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Status: " + status);
        System.out.println("\nItems:");
        for (CartItem item : items) {
            System.out.println("  " + item);
        }
        System.out.printf("Total: $%.2f%n", totalAmount);
        System.out.println("==================================");
    }

    // Builder Pattern
    public static class Builder {
        private Customer customer;
        private List<CartItem> items;
        private double totalAmount;

        public Builder(Customer customer) {
            this.customer = customer;
            this.items = new ArrayList<>();
        }

        public Builder items(List<CartItem> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public Builder totalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}

// ==================== UTILITY CLASS ====================
class PriceCalculator {
    // Constants
    public static final double TAX_RATE = 0.08;
    public static final double FREE_SHIPPING_THRESHOLD = 100.0;
    public static final double SHIPPING_COST = 10.0;

    // Prevent instantiation
    private PriceCalculator() {}

    // Method overloading
    public static double calculateTotal(double subtotal) {
        return calculateTotal(subtotal, TAX_RATE);
    }

    public static double calculateTotal(double subtotal, double taxRate) {
        return subtotal * (1 + taxRate);
    }

    public static double calculateShipping(double subtotal) {
        return subtotal >= FREE_SHIPPING_THRESHOLD ? 0 : SHIPPING_COST;
    }

    public static double calculateFinalTotal(double subtotal) {
        double tax = subtotal * TAX_RATE;
        double shipping = calculateShipping(subtotal);
        return subtotal + tax + shipping;
    }

    public static void displayBreakdown(double subtotal) {
        System.out.println("\n--- Price Breakdown ---");
        System.out.printf("Subtotal:        $%.2f%n", subtotal);
        System.out.printf("Tax (%.0f%%):      $%.2f%n", TAX_RATE * 100, subtotal * TAX_RATE);
        double shipping = calculateShipping(subtotal);
        if (shipping == 0) {
            System.out.println("Shipping:        FREE");
        } else {
            System.out.printf("Shipping:        $%.2f%n", shipping);
        }
        System.out.printf("Total:           $%.2f%n", calculateFinalTotal(subtotal));
    }
}

// ==================== MAIN CLASS ====================
public class MasterExample {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("  MASTER EXAMPLE: E-COMMERCE");
        System.out.println("=================================\n");

        // Creating products with method chaining
        System.out.println("--- Creating Products ---");
        Product laptop = new Product("Laptop", 999.99, "Electronics", 10);
        Product mouse = new Product("Wireless Mouse", 29.99)
                .setCategory("Electronics")
                .setStockQuantity(50);
        Product keyboard = new Product("Mechanical Keyboard", 79.99)
                .setCategory("Electronics")
                .setStockQuantity(30);
        Product book = new Product("Java Programming", 49.99, "Books", 100);

        System.out.println(laptop);
        System.out.println(mouse);
        System.out.println(keyboard);
        System.out.println(book);
        System.out.println("\nTotal products in catalog: " + Product.getTotalProducts());

        // Creating customers with composition (Address)
        System.out.println("\n--- Creating Customers ---");
        Address address1 = new Address("123 Main St", "New York", "NY", "10001");
        Customer customer1 = new Customer("John Doe", "john@example.com", address1);

        Customer customer2 = new Customer("Jane Smith", "jane@example.com");
        customer2.setAddress(new Address("456 Oak Ave", "Los Angeles", "CA", "90001"));

        System.out.println(customer1);
        System.out.println("Address: " + customer1.getAddress());
        System.out.println("\n" + customer2);
        System.out.println("Address: " + customer2.getAddress());
        System.out.println("\nTotal customers: " + Customer.getTotalCustomers());

        // Shopping Cart operations
        System.out.println("\n--- Shopping Cart Operations ---");
        ShoppingCart cart1 = customer1.getCart();

        cart1.addProduct(laptop);
        cart1.addProduct(mouse, 2);  // Method overloading
        cart1.addProduct(keyboard);
        cart1.addProduct(mouse);  // Adds to existing quantity

        cart1.displayCart();

        // Price calculations using utility class
        double subtotal = cart1.getTotal();
        PriceCalculator.displayBreakdown(subtotal);

        // Creating an order using Builder Pattern
        System.out.println("\n--- Creating Order ---");
        Order order1 = new Order.Builder(customer1)
                .items(cart1.getItems())
                .totalAmount(PriceCalculator.calculateFinalTotal(subtotal))
                .build();

        order1.displayOrder();

        // Clearing cart after order
        System.out.println("\n--- After Checkout ---");
        cart1.clear();
        cart1.displayCart();

        // Second customer shopping
        System.out.println("\n--- Second Customer Shopping ---");
        ShoppingCart cart2 = customer2.getCart();
        cart2.addProduct(book, 2);
        cart2.addProduct(mouse);
        cart2.displayCart();

        double subtotal2 = cart2.getTotal();
        PriceCalculator.displayBreakdown(subtotal2);

        // Object comparison
        System.out.println("\n--- Object Comparison ---");
        Product testProduct1 = laptop;
        Product testProduct2 = new Product("Laptop", 999.99, "Electronics", 10);

        System.out.println("testProduct1 == laptop: " + (testProduct1 == laptop));
        System.out.println("testProduct2 == laptop: " + (testProduct2 == laptop));
        System.out.println("testProduct2.equals(laptop): " + testProduct2.equals(laptop));

        // Statistics
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.println("Total Products Created: " + Product.getTotalProducts());
        System.out.println("Total Customers: " + Customer.getTotalCustomers());
        System.out.println("Total Orders: " + Order.getTotalOrders());
        System.out.println("Tax Rate: " + (PriceCalculator.TAX_RATE * 100) + "%");
        System.out.println("Free Shipping Threshold: $" + PriceCalculator.FREE_SHIPPING_THRESHOLD);

        System.out.println("\n=== CONCEPTS DEMONSTRATED ===");
        System.out.println("✓ Classes and Objects");
        System.out.println("✓ Constructors (default, parameterized, chaining)");
        System.out.println("✓ 'this' keyword");
        System.out.println("✓ Method overloading");
        System.out.println("✓ Static and instance members");
        System.out.println("✓ Static counters and utility classes");
        System.out.println("✓ Composition (has-a relationships)");
        System.out.println("✓ equals() and hashCode() override");
        System.out.println("✓ toString() override");
        System.out.println("✓ Method chaining (fluent interface)");
        System.out.println("✓ Builder pattern");
        System.out.println("✓ Encapsulation (private fields, public methods)");
        System.out.println("✓ ArrayList and Collections");
        System.out.println("✓ Real-world business logic");

        System.out.println("\n=================================");
        System.out.println("  MASTER EXAMPLE COMPLETE!");
        System.out.println("=================================");
    }
}
