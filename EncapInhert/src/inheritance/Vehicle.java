package inheritance;

/**
 * Vehicle - Base class for vehicle hierarchy
 * Demonstrates:
 * - Protected fields for subclass access
 * - Methods that can be overridden
 * - Foundation for multilevel and hierarchical inheritance
 */
public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected double price;

    // Constructor
    public Vehicle(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        System.out.println("Vehicle constructor called: " + brand + " " + model);
    }

    // Methods that can be overridden
    public void start() {
        System.out.println(brand + " " + model + " is starting...");
    }

    public void stop() {
        System.out.println(brand + " " + model + " is stopping...");
    }

    public void displayInfo() {
        System.out.println("\n=== Vehicle Information ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + String.format("%.2f", price));
    }

    // Getters
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return year + " " + brand + " " + model + " ($" + String.format("%.2f", price) + ")";
    }
}
