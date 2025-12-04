/**
 * Example 01: Basic Class and Object Creation
 *
 * This example demonstrates:
 * - How to define a simple class
 * - Creating objects from a class
 * - Accessing fields and methods
 * - Multiple objects from same class
 */

// Simple Person class
class Person {
    // Attributes (fields/instance variables)
    String name;
    int age;
    String email;

    // Method (behavior)
    void introduce() {
        System.out.println("Hi, I'm " + name + " and I'm " + age + " years old.");
    }

    void celebrateBirthday() {
        age++;
        System.out.println("Happy Birthday! Now I'm " + age + " years old.");
    }

    void updateEmail(String newEmail) {
        email = newEmail;
        System.out.println("Email updated to: " + email);
    }
}

public class Example01_BasicClass {
    public static void main(String[] args) {
        System.out.println("=== Example 01: Basic Class and Object ===\n");

        // Creating first object
        Person person1 = new Person();
        person1.name = "Alice";
        person1.age = 25;
        person1.email = "alice@example.com";

        System.out.println("Person 1:");
        person1.introduce();
        System.out.println("Email: " + person1.email);

        System.out.println("\n" + person1.name + " celebrates birthday:");
        person1.celebrateBirthday();

        // Creating second object
        System.out.println("\n---");
        Person person2 = new Person();
        person2.name = "Bob";
        person2.age = 30;
        person2.email = "bob@example.com";

        System.out.println("\nPerson 2:");
        person2.introduce();

        // Objects are independent
        System.out.println("\n--- Objects are Independent ---");
        System.out.println("Person 1 age: " + person1.age);
        System.out.println("Person 2 age: " + person2.age);

        person2.celebrateBirthday();
        System.out.println("\nAfter Person 2's birthday:");
        System.out.println("Person 1 age: " + person1.age + " (unchanged)");
        System.out.println("Person 2 age: " + person2.age + " (changed)");

        // Multiple objects can be created
        System.out.println("\n--- Creating Multiple Objects ---");
        Person person3 = new Person();
        person3.name = "Charlie";
        person3.age = 28;
        person3.introduce();

        Person person4 = new Person();
        person4.name = "Diana";
        person4.age = 32;
        person4.introduce();

        // Uninitialized fields have default values
        System.out.println("\n--- Default Values ---");
        Person person5 = new Person();
        System.out.println("Uninitialized Person:");
        System.out.println("Name: " + person5.name + " (null)");
        System.out.println("Age: " + person5.age + " (0)");
        System.out.println("Email: " + person5.email + " (null)");
    }
}
