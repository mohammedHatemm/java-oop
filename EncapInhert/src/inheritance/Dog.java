package inheritance;

/**
 * Dog - Child class (Subclass) of Animal
 * Demonstrates:
 * - Single inheritance (extends Animal)
 * - Calling parent constructor with super()
 * - Overriding parent methods (@Override)
 * - Adding new fields and methods specific to Dog
 */
public class Dog extends Animal {
    // Dog-specific fields
    private String breed;
    private boolean isTrained;

    // Constructor
    public Dog(String name, int age, String breed) {
        // MUST call parent constructor first!
        super(name, age, "Dog");  // Calls Animal constructor

        // Initialize Dog-specific fields
        this.breed = breed;
        this.isTrained = false;
        System.out.println("Dog constructor called for: " + name);
    }

    // Override parent method (polymorphism)
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }

    // Override displayInfo to add Dog-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's displayInfo first
        System.out.println("Breed: " + breed);
        System.out.println("Trained: " + (isTrained ? "Yes" : "No"));
    }

    // Dog-specific methods
    public void bark() {
        System.out.println(name + " is barking loudly!");
    }

    public void wagTail() {
        System.out.println(name + " is wagging tail happily!");
    }

    public void train() {
        isTrained = true;
        System.out.println(name + " has been trained successfully!");
    }

    public void fetch() {
        if (isTrained) {
            System.out.println(name + " is fetching the ball!");
        } else {
            System.out.println(name + " doesn't know how to fetch yet. Train first!");
        }
    }

    // Getter for breed
    public String getBreed() {
        return breed;
    }

    public boolean isTrained() {
        return isTrained;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', age=" + age + ", breed='" + breed + "', trained=" + isTrained + "}";
    }
}
