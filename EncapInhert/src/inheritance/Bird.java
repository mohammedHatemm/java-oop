package inheritance;

/**
 * Bird - Child class (Subclass) of Animal
 * Demonstrates single inheritance and polymorphism
 */
public class Bird extends Animal {
    private double wingspan;  // in meters
    private boolean canFly;

    // Constructor
    public Bird(String name, int age, double wingspan, boolean canFly) {
        super(name, age, "Bird");
        this.wingspan = wingspan;
        this.canFly = canFly;
        System.out.println("Bird constructor called for: " + name);
    }

    // Override makeSound (polymorphism)
    @Override
    public void makeSound() {
        System.out.println(name + " says: Tweet! Tweet!");
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Wingspan: " + wingspan + " meters");
        System.out.println("Can Fly: " + (canFly ? "Yes" : "No"));
    }

    // Bird-specific methods
    public void chirp() {
        System.out.println(name + " is chirping melodiously");
    }

    public void fly() {
        if (canFly) {
            System.out.println(name + " is flying high in the sky with " + wingspan + "m wingspan!");
        } else {
            System.out.println(name + " cannot fly (flightless bird like penguin or ostrich)");
        }
    }

    public void buildNest() {
        System.out.println(name + " is building a cozy nest");
    }

    public void layEgg() {
        System.out.println(name + " has laid an egg!");
    }

    // Getters
    public double getWingspan() {
        return wingspan;
    }

    public boolean canFly() {
        return canFly;
    }

    @Override
    public String toString() {
        return "Bird{name='" + name + "', age=" + age + ", wingspan=" + wingspan + "m, canFly=" + canFly + "}";
    }
}
