package inheritance;

/**
 * Animal - Base class (Superclass/Parent)
 * Demonstrates:
 * - Protected fields accessible to subclasses
 * - Methods that can be inherited and overridden
 * - Constructor that subclasses must call via super()
 */
public class Animal {
    // Protected fields (accessible to subclasses)
    protected String name;
    protected int age;
    protected String species;

    // Constructor
    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
        System.out.println("Animal constructor called for: " + name);
    }

    // Common behaviors (can be inherited or overridden)
    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping (zzz...)");
    }

    public void makeSound() {
        System.out.println(name + " makes a generic animal sound");
    }

    public void displayInfo() {
        System.out.println("\n=== Animal Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
        System.out.println("Species: " + species);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return species + "{name='" + name + "', age=" + age + "}";
    }
}
