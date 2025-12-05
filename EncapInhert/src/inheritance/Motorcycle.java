package inheritance;

/**
 * Motorcycle - Child class of Vehicle
 * Demonstrates:
 * - HIERARCHICAL INHERITANCE (Vehicle has multiple children: Car, Motorcycle)
 * - Both Car and Motorcycle extend Vehicle independently
 */
public class Motorcycle extends Vehicle {
    private String type;  // "Sport", "Cruiser", "Touring", "Off-road"
    private boolean hasSidecar;
    private int engineCC;  // Engine displacement in cubic centimeters

    // Constructor
    public Motorcycle(String brand, String model, int year, double price,
                      String type, int engineCC, boolean hasSidecar) {
        super(brand, model, year, price);
        this.type = type;
        this.engineCC = engineCC;
        this.hasSidecar = hasSidecar;
        System.out.println("Motorcycle constructor called");
    }

    // Override start method
    @Override
    public void start() {
        System.out.println("Motorcycle " + brand + " " + model + " kick-starting...");
        System.out.println("Engine roaring: " + engineCC + "cc!");
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: " + type);
        System.out.println("Engine: " + engineCC + "cc");
        System.out.println("Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }

    // Motorcycle-specific methods
    public void wheelie() {
        if (type.equals("Sport")) {
            System.out.println("Performing an epic wheelie! Front wheel up!");
        } else {
            System.out.println("This " + type + " motorcycle is not ideal for wheelies.");
        }
    }

    public void revEngine() {
        System.out.println("VROOM VROOM! " + engineCC + "cc engine revving loud!");
    }

    public void leanIntoTurn() {
        System.out.println("Leaning into the turn like a pro!");
    }

    // Getters
    public String getType() {
        return type;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    @Override
    public String toString() {
        return year + " " + brand + " " + model + " (" + type + ", " + engineCC + "cc)";
    }
}
