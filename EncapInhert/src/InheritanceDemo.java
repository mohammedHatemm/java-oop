import inheritance.*;

/**
 * InheritanceDemo - Testing all inheritance examples
 * Demonstrates:
 * - Single inheritance (Animal → Dog/Cat/Bird)
 * - Multilevel inheritance (Vehicle → Car → ElectricCar)
 * - Hierarchical inheritance (Vehicle → Car/Motorcycle)
 * - Polymorphism
 * - Constructor chaining
 * - Method overriding
 */
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("           INHERITANCE DEMONSTRATION");
        System.out.println("=".repeat(70));

        testAnimalHierarchy();
        testVehicleHierarchy();
        testPolymorphism();
        testConstructorChaining();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("           ALL INHERITANCE TESTS COMPLETED");
        System.out.println("=".repeat(70));
    }

    private static void testAnimalHierarchy() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 1: ANIMAL HIERARCHY (Single Inheritance)");
        System.out.println("=".repeat(70));

        // Test Dog
        System.out.println("\n--- Creating Dog (extends Animal) ---");
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        System.out.println();
        dog.displayInfo();
        System.out.println("\n--- Dog behaviors ---");
        dog.makeSound();  // Overridden
        dog.eat();        // Inherited
        dog.bark();       // Dog-specific
        dog.wagTail();    // Dog-specific
        System.out.println("\n--- Testing training ---");
        dog.fetch();      // Not trained yet
        dog.train();
        dog.fetch();      // Now trained!

        // Test Cat
        System.out.println("\n--- Creating Cat (extends Animal) ---");
        Cat cat = new Cat("Whiskers", 2, "Orange", true);
        System.out.println();
        cat.displayInfo();
        System.out.println("\n--- Cat behaviors ---");
        cat.makeSound();  // Overridden
        cat.sleep();      // Inherited
        cat.meow();       // Cat-specific
        cat.purr();       // Cat-specific
        cat.scratch();    // Cat-specific

        // Test Bird
        System.out.println("\n--- Creating Bird (extends Animal) ---");
        Bird bird = new Bird("Tweety", 1, 0.3, true);
        System.out.println();
        bird.displayInfo();
        System.out.println("\n--- Bird behaviors ---");
        bird.makeSound();   // Overridden
        bird.chirp();       // Bird-specific
        bird.fly();         // Bird-specific
        bird.buildNest();   // Bird-specific

        // Test flightless bird
        System.out.println("\n--- Creating Flightless Bird (Penguin) ---");
        Bird penguin = new Bird("Pingu", 2, 0.8, false);
        penguin.fly();      // Cannot fly!
    }

    private static void testVehicleHierarchy() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 2: VEHICLE HIERARCHY (Multilevel & Hierarchical)");
        System.out.println("=".repeat(70));

        // Test Car (Vehicle → Car)
        System.out.println("\n--- Creating Car (extends Vehicle) ---");
        Car car = new Car("Toyota", "Camry", 2023, 28000, 4, "Gasoline", 50);
        System.out.println();
        car.displayInfo();
        System.out.println("\n--- Car operations ---");
        car.start();      // No fuel
        car.refuel(40);
        car.start();      // Has fuel now
        car.drive(100);
        car.honk();

        // Test ElectricCar (Vehicle → Car → ElectricCar) - MULTILEVEL
        System.out.println("\n--- Creating ElectricCar (extends Car, which extends Vehicle) ---");
        System.out.println("--- This demonstrates MULTILEVEL INHERITANCE (3 levels) ---");
        ElectricCar tesla = new ElectricCar("Tesla", "Model 3", 2024, 45000, 4, 75, 500);
        System.out.println();
        tesla.displayInfo();
        System.out.println("\n--- ElectricCar operations ---");
        tesla.start();           // No charge
        tesla.charge(75);        // Full charge
        tesla.start();           // Has charge now
        tesla.drive(200);
        tesla.regenerativeBraking();
        tesla.enableAutopilot();

        // Test Motorcycle (Vehicle → Motorcycle) - HIERARCHICAL
        System.out.println("\n--- Creating Motorcycle (extends Vehicle) ---");
        System.out.println("--- This demonstrates HIERARCHICAL INHERITANCE ---");
        System.out.println("--- (Both Car and Motorcycle extend Vehicle) ---");
        Motorcycle harley = new Motorcycle("Harley-Davidson", "Sportster", 2023, 15000,
                                            "Cruiser", 883, false);
        System.out.println();
        harley.displayInfo();
        System.out.println("\n--- Motorcycle operations ---");
        harley.start();
        harley.revEngine();
        harley.wheelie();
        harley.leanIntoTurn();
    }

    private static void testPolymorphism() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 3: POLYMORPHISM (Runtime Method Dispatch)");
        System.out.println("=".repeat(70));

        // Animal polymorphism
        System.out.println("\n--- Animal Polymorphism ---");
        System.out.println("Creating array of type Animal[], but storing Dog, Cat, Bird objects");

        Animal[] animals = new Animal[3];
        animals[0] = new Dog("Max", 5, "Labrador");
        animals[1] = new Cat("Luna", 3, "Gray", false);
        animals[2] = new Bird("Polly", 2, 0.5, true);

        System.out.println("\n--- Polymorphic method calls ---");
        System.out.println("Calling makeSound() on each animal:");
        System.out.println("(Each calls its own overridden version!)");

        for (Animal animal : animals) {
            System.out.println("\n" + animal.getName() + ":");
            animal.makeSound();  // Calls overridden method based on actual type
            animal.eat();        // Inherited method
        }

        // Vehicle polymorphism
        System.out.println("\n--- Vehicle Polymorphism ---");
        System.out.println("Creating array of type Vehicle[], but storing Car, ElectricCar, Motorcycle");

        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car("Honda", "Accord", 2023, 30000, 4, "Gasoline", 55);
        vehicles[1] = new ElectricCar("Nissan", "Leaf", 2024, 35000, 4, 60, 400);
        vehicles[2] = new Motorcycle("Yamaha", "R1", 2023, 18000, "Sport", 998, false);

        System.out.println("\n--- Polymorphic method calls ---");
        System.out.println("Calling start() on each vehicle:");
        System.out.println("(Each calls its own overridden version!)");

        for (Vehicle v : vehicles) {
            System.out.println();
            v.start();  // Calls appropriate overridden method
        }

        // Type checking with instanceof
        System.out.println("\n--- Type Checking with instanceof ---");
        Vehicle v = vehicles[1];  // ElectricCar
        System.out.println("v reference type: Vehicle");
        System.out.println("v actual type: " + v.getClass().getSimpleName());
        System.out.println("v instanceof Vehicle: " + (v instanceof Vehicle));
        System.out.println("v instanceof Car: " + (v instanceof Car));
        System.out.println("v instanceof ElectricCar: " + (v instanceof ElectricCar));
        System.out.println("v instanceof Motorcycle: " + (v instanceof Motorcycle));
    }

    private static void testConstructorChaining() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 4: CONSTRUCTOR CHAINING");
        System.out.println("=".repeat(70));

        System.out.println("\n--- Watch the constructor call order for ElectricCar ---");
        System.out.println("ElectricCar extends Car, which extends Vehicle");
        System.out.println("Expected order: Vehicle → Car → ElectricCar");
        System.out.println();

        ElectricCar ev = new ElectricCar("BMW", "i4", 2024, 55000, 4, 80, 480);

        System.out.println("\n--- Constructor chain completed! ---");
        System.out.println("The object now has:");
        System.out.println("- Fields from Vehicle (brand, model, year, price)");
        System.out.println("- Fields from Car (numDoors, fuelType, etc.)");
        System.out.println("- Fields from ElectricCar (batteryCapacity, range)");
        System.out.println();
        ev.displayInfo();
    }
}
