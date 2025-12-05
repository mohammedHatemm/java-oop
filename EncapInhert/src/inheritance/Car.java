package inheritance;

/**
 * Car - Child class of Vehicle
 * Demonstrates:
 * - Single inheritance from Vehicle
 * - Will be extended by ElectricCar (multilevel inheritance)
 * - Overriding parent methods
 * - Adding car-specific functionality
 */
public class Car extends Vehicle {
    // Car-specific fields
    protected int numDoors;
    protected String fuelType;
    protected double fuelCapacity;  // in liters
    protected double currentFuel;

    // Constructor
    public Car(String brand, String model, int year, double price,
               int numDoors, String fuelType, double fuelCapacity) {
        // Call parent constructor
        super(brand, model, year, price);

        // Initialize Car-specific fields
        this.numDoors = numDoors;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.currentFuel = 0.0;
        System.out.println("Car constructor called");
    }

    // Override start method
    @Override
    public void start() {
        if (currentFuel > 0) {
            System.out.println("Car engine starting...");
            System.out.println("Fuel level: " + String.format("%.1f", currentFuel) + "L / " + fuelCapacity + "L");
        } else {
            System.out.println("Cannot start! No fuel in the tank.");
        }
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's displayInfo
        System.out.println("Doors: " + numDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Fuel Capacity: " + fuelCapacity + "L");
        System.out.println("Current Fuel: " + String.format("%.1f", currentFuel) + "L");
    }

    // Car-specific methods
    public void refuel(double liters) {
        if (currentFuel + liters > fuelCapacity) {
            currentFuel = fuelCapacity;
            System.out.println("Tank full! Capacity: " + fuelCapacity + "L");
        } else {
            currentFuel += liters;
            System.out.println("Refueled " + liters + "L. Current fuel: " +
                               String.format("%.1f", currentFuel) + "L");
        }
    }

    public void drive(double km) {
        double fuelNeeded = km * 0.08;  // 8L per 100km
        if (fuelNeeded <= currentFuel) {
            currentFuel -= fuelNeeded;
            System.out.println("Drove " + km + "km. Fuel remaining: " +
                               String.format("%.1f", currentFuel) + "L");
        } else {
            System.out.println("Not enough fuel to drive " + km + "km. " +
                               "Needed: " + String.format("%.1f", fuelNeeded) + "L, " +
                               "Available: " + String.format("%.1f", currentFuel) + "L");
        }
    }

    public void honk() {
        System.out.println("Beep beep!");
    }

    // Getters
    public int getNumDoors() {
        return numDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    @Override
    public String toString() {
        return year + " " + brand + " " + model + " (" + numDoors + "-door, " + fuelType + ")";
    }
}
