package encapsulation;

import java.time.Year;

/**
 * Car class demonstrating encapsulation with:
 * - Year validation (current year constraints)
 * - Mileage validation (cannot decrease)
 * - Computed properties (age)
 */
public class Car {
    private String make;
    private String model;
    private int year;
    private double price;
    private int mileage;
    private String color;

    // Constructor
    public Car(String make, String model, int year, double price) {
        setMake(make);
        setModel(model);
        setYear(year);
        setPrice(price);
        this.mileage = 0;
        this.color = "White";  // Default color
    }

    // ==================== GETTERS ====================

    public String getMake() {
        return make;
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

    public int getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    /**
     * Computed read-only property: car age
     * No setter - calculated from year
     */
    public int getAge() {
        return Year.now().getValue() - year;
    }

    // ==================== SETTERS (with validation) ====================

    public void setMake(String make) {
        if (make == null || make.trim().isEmpty()) {
            throw new IllegalArgumentException("Make cannot be null or empty");
        }
        this.make = make.trim();
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        this.model = model.trim();
    }

    /**
     * Year validation: between 1900 and next year
     */
    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        int minYear = 1900;
        int maxYear = currentYear + 1;  // Allow next year models

        if (year < minYear || year > maxYear) {
            throw new IllegalArgumentException(
                String.format("Year must be between %d and %d, got: %d",
                              minYear, maxYear, year)
            );
        }
        this.year = year;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    /**
     * Mileage validation: cannot decrease
     */
    public void setMileage(int mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
        if (mileage < this.mileage) {
            throw new IllegalArgumentException(
                String.format("New mileage (%d) cannot be less than current mileage (%d)",
                              mileage, this.mileage)
            );
        }
        this.mileage = mileage;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color.trim();
    }

    // ==================== BUSINESS METHODS ====================

    /**
     * Drive the car - increases mileage
     */
    public void drive(int miles) {
        if (miles <= 0) {
            throw new IllegalArgumentException("Miles must be positive");
        }
        mileage += miles;
        System.out.println("Drove " + miles + " miles. Total mileage: " + mileage);
    }

    /**
     * Display complete car information
     */
    public void displayInfo() {
        System.out.println("\n=== Car Information ===");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year + " (Age: " + getAge() + " years)");
        System.out.println("Color: " + color);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("Mileage: " + mileage + " miles");
        System.out.println("======================");
    }

    @Override
    public String toString() {
        return String.format("%d %s %s (%s) - %d miles - $%.2f",
                             year, make, model, color, mileage, price);
    }
}
