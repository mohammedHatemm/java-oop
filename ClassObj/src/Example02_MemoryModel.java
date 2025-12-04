/**
 * Example 02: Memory Model - Stack and Heap
 *
 * This example demonstrates:
 * - How references work
 * - Stack vs Heap memory
 * - Reference assignment
 * - null references
 * - Garbage collection eligibility
 */

class Car {
    String brand;
    String model;
    int year;

    void displayInfo() {
        System.out.println(year + " " + brand + " " + model);
    }
}

public class Example02_MemoryModel {
    public static void main(String[] args) {
        System.out.println("=== Example 02: Memory Model ===\n");

        // Creating an object
        // Stack: car1 (reference variable)
        // Heap: Car object
        System.out.println("--- Creating Object ---");
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.model = "Camry";
        car1.year = 2020;

        System.out.print("car1: ");
        car1.displayInfo();

        // Reference assignment
        // Both car1 and car2 point to the SAME object in heap
        System.out.println("\n--- Reference Assignment ---");
        Car car2 = car1;
        System.out.print("car2: ");
        car2.displayInfo();

        System.out.println("\nChanging car2.year to 2021...");
        car2.year = 2021;

        System.out.print("car1: ");
        car1.displayInfo(); // Shows 2021 (same object!)
        System.out.print("car2: ");
        car2.displayInfo(); // Shows 2021

        System.out.println("\n✓ car1 and car2 refer to the SAME object");

        // Creating independent objects
        System.out.println("\n--- Independent Objects ---");
        Car car3 = new Car();
        car3.brand = "Honda";
        car3.model = "Accord";
        car3.year = 2019;

        Car car4 = new Car();
        car4.brand = "Honda";
        car4.model = "Accord";
        car4.year = 2019;

        System.out.print("car3: ");
        car3.displayInfo();
        System.out.print("car4: ");
        car4.displayInfo();

        System.out.println("\nChanging car3.year to 2022...");
        car3.year = 2022;

        System.out.print("car3: ");
        car3.displayInfo(); // Shows 2022
        System.out.print("car4: ");
        car4.displayInfo(); // Still shows 2019

        System.out.println("\n✓ car3 and car4 are DIFFERENT objects");

        // null reference
        System.out.println("\n--- Null Reference ---");
        Car car5 = null;
        System.out.println("car5 = " + car5);

        try {
            car5.displayInfo(); // This will throw NullPointerException
        } catch (NullPointerException e) {
            System.out.println("✗ Cannot call method on null reference!");
        }

        // Making object eligible for garbage collection
        System.out.println("\n--- Garbage Collection Eligibility ---");
        Car car6 = new Car();
        car6.brand = "BMW";
        car6.model = "X5";
        car6.year = 2023;

        System.out.print("car6: ");
        car6.displayInfo();

        System.out.println("\nSetting car6 = null...");
        car6 = null;
        // The BMW object is now eligible for garbage collection
        System.out.println("The BMW object has no references and can be garbage collected");

        // Reassigning reference
        System.out.println("\n--- Reassigning Reference ---");
        Car car7 = new Car();
        car7.brand = "Ford";
        car7.model = "Mustang";
        car7.year = 2022;

        System.out.print("car7 initially: ");
        car7.displayInfo();

        car7 = new Car(); // Old Ford object becomes eligible for GC
        car7.brand = "Chevrolet";
        car7.model = "Corvette";
        car7.year = 2023;

        System.out.print("car7 after reassignment: ");
        car7.displayInfo();
        System.out.println("The Ford object is now eligible for GC");

        // Reference comparison
        System.out.println("\n--- Reference Comparison (==) ---");
        Car car8 = new Car();
        car8.brand = "Tesla";
        Car car9 = car8;
        Car car10 = new Car();
        car10.brand = "Tesla";

        System.out.println("car8 == car9: " + (car8 == car9) + " (same object)");
        System.out.println("car8 == car10: " + (car8 == car10) + " (different objects)");

        System.out.println("\n=== Memory Summary ===");
        System.out.println("Stack Memory: Stores reference variables");
        System.out.println("Heap Memory: Stores actual objects");
        System.out.println("Reference Variable: Holds memory address of object");
        System.out.println("null: Special value indicating no object");
        System.out.println("Garbage Collector: Automatically frees memory of unreachable objects");
    }
}
