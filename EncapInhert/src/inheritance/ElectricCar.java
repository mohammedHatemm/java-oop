package inheritance;

/**
 * ElectricCar - Grandchild class (extends Car, which extends Vehicle)
 * Demonstrates:
 * - MULTILEVEL INHERITANCE: Vehicle → Car → ElectricCar
 * - Constructor chaining through multiple levels
 * - Overriding methods from both parent and grandparent
 * - Adapting inherited methods to new context (electric vs gas)
 */
public class ElectricCar extends Car {
    // ElectricCar-specific fields
    private int batteryCapacity;  // in kWh
    private int currentCharge;    // in kWh
    private int range;            // in km

    // Constructor
    public ElectricCar(String brand, String model, int year, double price,
                       int numDoors, int batteryCapacity, int range) {
        // Call Car constructor (which calls Vehicle constructor)
        // Electric cars don't use traditional fuel, so pass 0 for fuelCapacity
        super(brand, model, year, price, numDoors, "Electric", 0);

        this.batteryCapacity = batteryCapacity;
        this.currentCharge = 0;
        this.range = range;
        System.out.println("ElectricCar constructor called");
    }

    // Override start method (from Vehicle, overridden by Car, now by ElectricCar)
    @Override
    public void start() {
        if (currentCharge > 0) {
            System.out.println("Electric motor starting silently...");
            System.out.println("Battery level: " + currentCharge + "kWh / " + batteryCapacity + "kWh");
            System.out.println("Estimated range: " + getEstimatedRange() + "km");
        } else {
            System.out.println("Cannot start! Battery is empty.");
        }
    }

    // Override displayInfo (from Vehicle, overridden by Car, now by ElectricCar)
    @Override
    public void displayInfo() {
        // Customize the display instead of calling super
        System.out.println("\n=== Electric Vehicle Information ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("Doors: " + numDoors);
        System.out.println("Power: Electric");
        System.out.println("Battery Capacity: " + batteryCapacity + "kWh");
        System.out.println("Current Charge: " + currentCharge + "kWh");
        System.out.println("Max Range: " + range + "km");
        System.out.println("Estimated Range: " + getEstimatedRange() + "km");
    }

    // Override refuel → charge (electric cars don't refuel, they charge)
    @Override
    public void refuel(double amount) {
        charge((int)amount);
    }

    // ElectricCar-specific: charge battery
    public void charge(int kWh) {
        if (currentCharge + kWh > batteryCapacity) {
            currentCharge = batteryCapacity;
            System.out.println("Battery full! Capacity: " + batteryCapacity + "kWh");
        } else {
            currentCharge += kWh;
            System.out.println("Charged " + kWh + "kWh. Current charge: " + currentCharge + "kWh");
        }
    }

    // Override drive (electric cars consume battery, not fuel)
    @Override
    public void drive(double km) {
        int chargeNeeded = (int)(km / range * batteryCapacity);
        if (chargeNeeded <= currentCharge) {
            currentCharge -= chargeNeeded;
            System.out.println("Drove " + km + "km silently and efficiently. " +
                               "Charge remaining: " + currentCharge + "kWh");
        } else {
            System.out.println("Not enough charge to drive " + km + "km. " +
                               "Needed: " + chargeNeeded + "kWh, " +
                               "Available: " + currentCharge + "kWh");
        }
    }

    // ElectricCar-specific methods
    public int getEstimatedRange() {
        if (batteryCapacity == 0) return 0;
        return (int)((double)currentCharge / batteryCapacity * range);
    }

    public void enableAutopilot() {
        System.out.println("Autopilot enabled on " + brand + " " + model + "! Hands-free driving engaged.");
    }

    public void regenerativeBraking() {
        int recovered = 2;  // Recover 2 kWh
        if (currentCharge + recovered <= batteryCapacity) {
            currentCharge += recovered;
            System.out.println("Regenerative braking: Recovered " + recovered + "kWh of energy!");
        }
    }

    // Getters
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getCurrentCharge() {
        return currentCharge;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String toString() {
        return year + " " + brand + " " + model + " (Electric, " + batteryCapacity + "kWh, " + range + "km range)";
    }
}
