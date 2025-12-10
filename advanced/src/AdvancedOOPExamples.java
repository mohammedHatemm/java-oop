// To demonstrate package organization, this file must be in a folder structure
// that matches the package name. For example, if the package is 'com.example.advanced',
// this file should be located at '.../com/example/advanced/AdvancedOOPExamples.java'.
// For this example, we'll use a simple package name 'advanced'.
package advanced;

import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is a master example file demonstrating advanced OOP concepts in Java.
 * Run the main method to see all the demonstrations.
 */
public class AdvancedOOPExamples {

    public static void main(String[] args) {
        System.out.println("--- Day 6: Advanced OOP Concepts Examples ---");

        // 1. Static Members and Blocks
        System.out.println("\n--- 1. Static Members and Blocks ---");
        // The static block in Car is executed when the Car class is first loaded.
        // We don't even need to create an object yet.
        System.out.println("Calling static method without creating an instance.");
        Car.printDefaultColor();

        System.out.println("Creating Car instances...");
        Car car1 = new Car("Tesla", "Model S");
        Car car2 = new Car("Ford", "Mustang");
        System.out.println("Car 1 ID: " + car1.getId());
        System.out.println("Car 2 ID: " + car2.getId());
        // The static counter is shared across all instances.
        System.out.println("Total cars created (from static counter): " + Car.getCarCount());

        // 2. Final Keyword
        System.out.println("\n--- 2. Final Keyword ---");
        final double PI = 3.14159;
        // PI = 3.14; // This would cause a compilation error.
        System.out.println("Value of final variable PI: " + PI);
        System.out.println("A 'final' class like 'String' cannot be extended.");
        System.out.println("A 'final' method in the 'Vehicle' class cannot be overridden by 'Car'.");


        // 3. Inner and Static Nested Classes
        System.out.println("\n--- 3. Inner and Static Nested Classes ---");
        // Create an instance of the non-static inner class (Engine)
        // This requires an instance of the outer class (car1)
        Car.Engine engine = car1.new Engine(3.5);
        engine.start();

        // Create an instance of the static nested class (Tire)
        // This does not require an outer class instance.
        Car.Tire tire = new Car.Tire("Summer", 19);
        System.out.println("Tire type: " + tire.getType());


        // 4. Anonymous Classes
        System.out.println("\n--- 4. Anonymous Classes ---");
        // Often used for simple, one-off implementations of an interface.
        Runnable myTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is running inside an anonymous class that implements Runnable.");
            }
        };
        Thread thread = new Thread(myTask);
        thread.start(); // Executes the run() method above.


        // 5. The Object Class: toString(), equals(), and hashCode()
        System.out.println("\n--- 5. The Object Class Methods ---");
        System.out.println("--- Overriding toString() ---");
        // Without overriding toString(), this would print something like 'advanced.Car@1a2b3c4d'
        System.out.println("car1.toString(): " + car1.toString());
        System.out.println("car2.toString(): " + car2.toString());

        System.out.println("\n--- Overriding equals() and hashCode() ---");
        // Create two 'Book' objects that are logically equal but are different instances in memory.
        Book book1 = new Book("978-0321356680", "Effective Java");
        Book book2 = new Book("978-0321356680", "Effective Java");
        Book book3 = new Book("978-0132350884", "Clean Code");

        System.out.println("book1 == book2 (reference equality): " + (book1 == book2)); // false
        System.out.println("book1.equals(book2) (logical equality): " + book1.equals(book2)); // true, thanks to override

        System.out.println("\n--- The hashCode() Contract ---");
        System.out.println("If two objects are equal, their hash codes MUST be the same:");
        System.out.println("book1.hashCode(): " + book1.hashCode());
        System.out.println("book2.hashCode(): " + book2.hashCode());

        // Using a HashSet, which relies on equals() and hashCode()
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);
        bookSet.add(book2); // This will not be added because book1.equals(book2) is true
        bookSet.add(book3);

        System.out.println("\nSize of bookSet: " + bookSet.size()); // Will be 2, not 3
        System.out.println("Contents of bookSet:");
        for (Book b : bookSet) {
            System.out.println("- " + b);
        }
    }
}


// --- Supporting Classes for the Examples ---

/**
 * A base class to demonstrate the 'final' keyword on methods.
 */
class Vehicle {
    public final void displayInfo() {
        System.out.println("This is a vehicle.");
    }
}

/**
 * A class demonstrating static members, final, inner classes, and toString().
 */
class Car extends Vehicle {
    // 1a. Static Variable (Class Variable)
    // This counter is shared by all instances of the Car class.
    private static int carCount = 0;
    private static final String DEFAULT_COLOR;

    // 1b. Static Block
    // This block is executed once when the class is loaded into memory.
    // Used for initializing static variables.
    static {
        DEFAULT_COLOR = "Black";
        System.out.println("Car class loaded. Static block executed. Default color set to " + DEFAULT_COLOR);
    }

    private final int id; // 2a. Final instance variable
    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        // Increment the static counter and assign the final id for this instance.
        this.id = ++carCount;
    }

    // 1c. Static Method
    public static int getCarCount() {
        // Note: A static method can only access static members directly.
        // System.out.println(this.make); // This would cause a compilation error.
        return carCount;
    }
    
    public static void printDefaultColor() {
        System.out.println("Default car color is: " + DEFAULT_COLOR);
    }

    public int getId() {
        return id;
    }

    // This method cannot be overridden by a subclass of Car because it's final in Vehicle.
    // @Override
    // public final void displayInfo() { ... } // This would be a compile error.

    // 5a. Overriding toString()
    @Override
    public String toString() {
        return "Car #" + id + " | Make: " + make + ", Model: " + model;
    }

    // 3a. Inner Class (Non-static)
    // An Engine is tightly coupled with a specific Car instance.
    public class Engine {
        private double displacement;

        public Engine(double displacement) {
            this.displacement = displacement;
        }

        public void start() {
            // The inner class can access members of the outer class directly.
            System.out.println("Starting the engine of the " + make + " " + model + ".");
        }
    }

    // 3b. Static Nested Class
    // A Tire can be conceptualized independently of a specific car.
    public static class Tire {
        private String type;
        private int size;

        public Tire(String type, int size) {
            this.type = type;
            this.size = size;
        }

        public String getType() {
            // Cannot access non-static members of Car, e.g., 'make' or 'model'.
            // System.out.println(make); // Compile error.
            return type;
        }
    }
}

/**
 * A class demonstrating the correct way to override equals() and hashCode().
 */
class Book {
    private final String isbn; // A good candidate for equality checking
    private String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book[ISBN=" + isbn + ", Title=" + title + "]";
    }

    // 5b. Overriding equals()
    @Override
    public boolean equals(Object o) {
        // 1. Self check
        if (this == o) return true;
        // 2. Null check and class check
        if (o == null || getClass() != o.getClass()) return false;
        // 3. Cast the object
        Book book = (Book) o;
        // 4. Compare significant fields (in this case, the unique ISBN)
        return Objects.equals(isbn, book.isbn);
    }

    // 5c. Overriding hashCode()
    // This is the required contract: if two objects are equal, they MUST have the same hash code.
    @Override
    public int hashCode() {
        // Use Objects.hash() to generate a hash code based on the same fields used in equals().
        return Objects.hash(isbn);
    }
}
