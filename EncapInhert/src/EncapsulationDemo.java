import encapsulation.*;

/**
 * EncapsulationDemo - Testing all encapsulation examples
 * Demonstrates:
 * - Data validation in setters
 * - Private field protection
 * - Business logic validation
 * - Read-only computed properties
 */
public class EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("           ENCAPSULATION DEMONSTRATION");
        System.out.println("=".repeat(70));

        testStudent();
        testBankAccount();
        testCar();
        testCircle();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("           ALL ENCAPSULATION TESTS COMPLETED");
        System.out.println("=".repeat(70));
    }

    private static void testStudent() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 1: STUDENT CLASS (Basic Encapsulation with Validation)");
        System.out.println("=".repeat(70));

        try {
            // Create valid student
            System.out.println("\n--- Creating valid student ---");
            Student s1 = new Student("Ali Hassan", 20, 3.8, "STU123456");
            s1.displayInfo();

            // Test valid modifications
            System.out.println("\n--- Testing valid modifications ---");
            s1.setAge(21);
            s1.setGPA(3.9);
            System.out.println("✓ Age updated to: " + s1.getAge());
            System.out.println("✓ GPA updated to: " + s1.getGPA());

            // Test invalid age
            System.out.println("\n--- Testing invalid age (should fail) ---");
            try {
                s1.setAge(-5);
                System.out.println("✗ ERROR: Invalid age was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test invalid GPA
            System.out.println("\n--- Testing invalid GPA (should fail) ---");
            try {
                s1.setGPA(5.0);
                System.out.println("✗ ERROR: Invalid GPA was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test invalid student ID
            System.out.println("\n--- Testing invalid student ID (should fail) ---");
            try {
                s1.setStudentId("INVALID123");
                System.out.println("✗ ERROR: Invalid ID was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test empty name
            System.out.println("\n--- Testing empty name (should fail) ---");
            try {
                s1.setName("");
                System.out.println("✗ ERROR: Empty name was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testBankAccount() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 2: BANK ACCOUNT (Business Logic Validation)");
        System.out.println("=".repeat(70));

        try {
            // Create accounts
            System.out.println("\n--- Creating bank accounts ---");
            BankAccount checking = new BankAccount("ACC001", "John Doe", "CHECKING");
            BankAccount savings = new BankAccount("ACC002", "Jane Smith", "SAVINGS");

            // Test deposits
            System.out.println("\n--- Testing deposits ---");
            checking.deposit(1000);
            savings.deposit(5000);

            // Test valid withdrawal
            System.out.println("\n--- Testing valid withdrawal ---");
            checking.withdraw(200);

            // Test excessive withdrawal (should fail)
            System.out.println("\n--- Testing excessive withdrawal (should fail) ---");
            try {
                checking.withdraw(10000);
                System.out.println("✗ ERROR: Excessive withdrawal was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test withdrawal exceeding balance (should fail)
            System.out.println("\n--- Testing withdrawal exceeding balance (should fail) ---");
            try {
                checking.withdraw(5000);
                System.out.println("✗ ERROR: Insufficient balance withdrawal was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test transfer
            System.out.println("\n--- Testing transfer ---");
            checking.deposit(4000);
            checking.transfer(savings, 1000);

            // Display final account states
            System.out.println("\n--- Final account states ---");
            checking.displayAccountInfo();
            savings.displayAccountInfo();

            // Test savings minimum balance
            System.out.println("\n--- Testing savings minimum balance (should fail) ---");
            try {
                savings.withdraw(5900);  // Would leave less than $100
                System.out.println("✗ ERROR: Minimum balance violation was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testCar() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 3: CAR CLASS (Year & Mileage Validation)");
        System.out.println("=".repeat(70));

        try {
            // Create valid car
            System.out.println("\n--- Creating valid car ---");
            Car car = new Car("Toyota", "Camry", 2023, 28000);
            car.setColor("Blue");
            car.displayInfo();

            // Test driving
            System.out.println("\n--- Testing driving (increases mileage) ---");
            car.drive(100);
            car.drive(50);
            System.out.println("Current mileage: " + car.getMileage());

            // Test setting mileage forward (valid)
            System.out.println("\n--- Setting mileage forward (valid) ---");
            car.setMileage(200);
            System.out.println("✓ Mileage set to: " + car.getMileage());

            // Test setting mileage backwards (should fail)
            System.out.println("\n--- Testing mileage backwards (should fail) ---");
            try {
                car.setMileage(50);
                System.out.println("✗ ERROR: Backward mileage was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test invalid year
            System.out.println("\n--- Testing invalid year (should fail) ---");
            try {
                Car car2 = new Car("Honda", "Accord", 1800, 20000);
                System.out.println("✗ ERROR: Invalid year was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test computed property (age)
            System.out.println("\n--- Testing computed property (age) ---");
            System.out.println("Car age: " + car.getAge() + " years (computed from year)");

            // Display final state
            car.displayInfo();

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testCircle() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST 4: CIRCLE CLASS (Read-Only Computed Properties)");
        System.out.println("=".repeat(70));

        try {
            // Create circle
            System.out.println("\n--- Creating circle with radius 5.0 ---");
            Circle circle = new Circle(5.0);
            circle.displayInfo();

            System.out.println("\n--- Note: Area and Circumference have NO setters! ---");
            System.out.println("They are COMPUTED from radius automatically.");

            // Change radius
            System.out.println("\n--- Changing radius to 10.0 ---");
            circle.setRadius(10.0);
            circle.displayInfo();

            System.out.println("\n--- Area and circumference updated automatically! ---");

            // Test invalid radius
            System.out.println("\n--- Testing invalid radius (should fail) ---");
            try {
                circle.setRadius(-5);
                System.out.println("✗ ERROR: Negative radius was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

            // Test zero radius
            try {
                circle.setRadius(0);
                System.out.println("✗ ERROR: Zero radius was accepted!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly rejected: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
