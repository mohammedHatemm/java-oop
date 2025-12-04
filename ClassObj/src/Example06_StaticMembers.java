/**
 * Example 06: Static Members
 *
 * This example demonstrates:
 * - Static variables (class variables)
 * - Static methods
 * - Static vs instance members
 * - Static blocks
 * - Static utility classes
 * - Object counters using static
 */

class Counter {
    private static int totalObjects = 0;  // Static variable (shared)
    private int instanceId;                // Instance variable (unique per object)

    public Counter() {
        totalObjects++;              // Increment shared counter
        this.instanceId = totalObjects;  // Assign unique ID
        System.out.println("Counter object #" + instanceId + " created");
    }

    // Static method - can access only static members
    public static int getTotalObjects() {
        // Cannot access instanceId here (non-static)
        return totalObjects;
    }

    // Instance method - can access both static and instance members
    public void displayInfo() {
        System.out.println("Instance ID: " + instanceId);
        System.out.println("Total objects created: " + totalObjects);
    }

    // Static method to reset counter
    public static void resetCounter() {
        totalObjects = 0;
        System.out.println("Counter reset to 0");
    }
}

class MathUtils {
    // Constants (static final)
    public static final double PI = 3.14159265359;
    public static final double E = 2.71828182846;

    // Static utility methods
    public static int square(int number) {
        return number * number;
    }

    public static double circleArea(double radius) {
        return PI * radius * radius;
    }

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    // Prevent instantiation
    private MathUtils() {
        throw new AssertionError("Utility class cannot be instantiated");
    }
}

class Database {
    private static String connectionString;
    private static boolean initialized = false;

    // Static initialization block
    static {
        System.out.println("Static block 1: Initializing database...");
        connectionString = "jdbc:mysql://localhost:3306/mydb";
        initialized = true;
    }

    // Second static block (executed in order)
    static {
        System.out.println("Static block 2: Database configuration complete");
    }

    private String sessionId;
    private int queryCount;

    // Instance initialization block
    {
        System.out.println("Instance block: Creating database session");
        sessionId = "SESSION_" + System.currentTimeMillis();
        queryCount = 0;
    }

    public Database() {
        System.out.println("Constructor: Database object created with " + sessionId);
    }

    public static String getConnectionString() {
        return connectionString;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public void executeQuery(String query) {
        queryCount++;
        System.out.println("Executing query: " + query);
        System.out.println("Session: " + sessionId + ", Query count: " + queryCount);
    }
}

class StudentWithStatic {
    private static String university = "MIT";  // Shared among all students
    private static int totalStudents = 0;

    private String name;
    private int rollNumber;

    public StudentWithStatic(String name) {
        this.name = name;
        this.rollNumber = ++totalStudents;
    }

    // Static method
    public static void setUniversity(String newUniversity) {
        university = newUniversity;
        System.out.println("University changed to: " + university);
    }

    public static String getUniversity() {
        return university;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    // Instance method
    public void displayInfo() {
        System.out.printf("Student: %s, Roll: %d, University: %s%n",
                name, rollNumber, university);
    }
}

public class Example06_StaticMembers {
    public static void main(String[] args) {
        System.out.println("=== Example 06: Static Members ===\n");

        // Static variables shared among all instances
        System.out.println("--- Static Variables (Object Counter) ---");
        System.out.println("Total objects before creating any: " + Counter.getTotalObjects());

        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        System.out.println("\nTotal objects after creating 3: " + Counter.getTotalObjects());

        c1.displayInfo();
        System.out.println();
        c2.displayInfo();

        // Static utility class
        System.out.println("\n--- Static Utility Class ---");
        System.out.println("PI = " + MathUtils.PI);
        System.out.println("E = " + MathUtils.E);
        System.out.println("Square of 5 = " + MathUtils.square(5));
        System.out.println("Area of circle (radius=3) = " + MathUtils.circleArea(3));
        System.out.println("Max(10, 20) = " + MathUtils.max(10, 20));
        System.out.println("Min(10, 20) = " + MathUtils.min(10, 20));

        // Static blocks
        System.out.println("\n--- Static and Instance Blocks ---");
        System.out.println("Before creating any Database object:");
        System.out.println("Connection: " + Database.getConnectionString());
        System.out.println("Initialized: " + Database.isInitialized());

        System.out.println("\nCreating first Database object:");
        Database db1 = new Database();
        db1.executeQuery("SELECT * FROM users");

        System.out.println("\nCreating second Database object:");
        Database db2 = new Database();
        db2.executeQuery("SELECT * FROM products");
        db2.executeQuery("INSERT INTO orders VALUES (...)");

        // Static vs Instance demonstration
        System.out.println("\n--- Static vs Instance Members ---");
        StudentWithStatic.setUniversity("Stanford");
        System.out.println("University (via class): " + StudentWithStatic.getUniversity());

        StudentWithStatic student1 = new StudentWithStatic("Alice");
        StudentWithStatic student2 = new StudentWithStatic("Bob");
        StudentWithStatic student3 = new StudentWithStatic("Charlie");

        System.out.println("\nAll students share the same university:");
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();

        System.out.println("\nChanging university...");
        StudentWithStatic.setUniversity("Harvard");

        System.out.println("\nUniversity changed for ALL students:");
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();

        System.out.println("\nTotal students: " + StudentWithStatic.getTotalStudents());

        // Accessing static members
        System.out.println("\n--- Accessing Static Members ---");
        System.out.println("Via class name (preferred): " + Counter.getTotalObjects());
        System.out.println("Via object reference (not recommended): " + c1.getTotalObjects());
        System.out.println("✓ Both access the same static variable");

        // Reset counter
        System.out.println("\n--- Resetting Counter ---");
        Counter.resetCounter();
        System.out.println("Total objects after reset: " + Counter.getTotalObjects());

        Counter c4 = new Counter();
        System.out.println("Total objects after creating new: " + Counter.getTotalObjects());

        System.out.println("\n=== Key Points ===");
        System.out.println("✓ Static members belong to the class, not instances");
        System.out.println("✓ Only one copy of static variable exists (shared)");
        System.out.println("✓ Static methods can access only static members");
        System.out.println("✓ Instance methods can access both static and instance members");
        System.out.println("✓ Access static members via class name (ClassName.member)");
        System.out.println("✓ Static blocks initialize static variables when class is loaded");
        System.out.println("✓ Static blocks execute once, before any object is created");
        System.out.println("✓ Use static for: utilities, constants, counters, shared data");
    }
}
