/**
 * Example 03: Constructors
 *
 * This example demonstrates:
 * - Default constructor
 * - Parameterized constructor
 * - Constructor overloading
 * - Constructor chaining with this()
 * - Copy constructor pattern
 */

class Student {
    private String name;
    private int age;
    private String studentId;
    private String major;

    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 18;
        this.studentId = "N/A";
        this.major = "Undecided";
        System.out.println("Default constructor called");
    }

    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.studentId = "N/A";
        this.major = "Undecided";
        System.out.println("Two-parameter constructor called");
    }

    // Constructor with more parameters
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.major = "Undecided";
        System.out.println("Three-parameter constructor called");
    }

    // Full constructor
    public Student(String name, int age, String studentId, String major) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.major = major;
        System.out.println("Four-parameter constructor called");
    }

    // Copy constructor pattern
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
        this.studentId = other.studentId;
        this.major = other.major;
        System.out.println("Copy constructor called");
    }

    public void displayInfo() {
        System.out.printf("  Name: %s, Age: %d, ID: %s, Major: %s%n",
                name, age, studentId, major);
    }
}

// Example with constructor chaining
class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    // Constructor chaining - all constructors call the main constructor
    public Employee() {
        this("Unknown", 0, "Unassigned", 0.0);
        System.out.println("Default constructor");
    }

    public Employee(String name) {
        this(name, 0, "Unassigned", 0.0);
        System.out.println("Name-only constructor");
    }

    public Employee(String name, int age) {
        this(name, age, "Unassigned", 0.0);
        System.out.println("Name and age constructor");
    }

    public Employee(String name, int age, String department) {
        this(name, age, department, 0.0);
        System.out.println("Three-parameter constructor");
    }

    // Main constructor - does all the work
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
        System.out.println("Main constructor (4 parameters)");
    }

    public void displayInfo() {
        System.out.printf("  Name: %s, Age: %d, Dept: %s, Salary: $%.2f%n",
                name, age, department, salary);
    }
}

public class Example03_Constructors {
    public static void main(String[] args) {
        System.out.println("=== Example 03: Constructors ===\n");

        // Default constructor
        System.out.println("--- Default Constructor ---");
        Student student1 = new Student();
        student1.displayInfo();

        // Parameterized constructor
        System.out.println("\n--- Parameterized Constructor (2 params) ---");
        Student student2 = new Student("Alice", 20);
        student2.displayInfo();

        // Constructor with more parameters
        System.out.println("\n--- Parameterized Constructor (3 params) ---");
        Student student3 = new Student("Bob", 22, "S12345");
        student3.displayInfo();

        // Full constructor
        System.out.println("\n--- Full Constructor (4 params) ---");
        Student student4 = new Student("Charlie", 21, "S67890", "Computer Science");
        student4.displayInfo();

        // Copy constructor
        System.out.println("\n--- Copy Constructor ---");
        Student student5 = new Student(student4);
        student5.displayInfo();
        System.out.println("✓ Created a copy of student4");

        // Constructor Chaining Example
        System.out.println("\n=== Constructor Chaining ===\n");

        System.out.println("--- Creating employee with default constructor ---");
        Employee emp1 = new Employee();
        emp1.displayInfo();

        System.out.println("\n--- Creating employee with name only ---");
        Employee emp2 = new Employee("John Doe");
        emp2.displayInfo();

        System.out.println("\n--- Creating employee with name and age ---");
        Employee emp3 = new Employee("Jane Smith", 30);
        emp3.displayInfo();

        System.out.println("\n--- Creating employee with 3 parameters ---");
        Employee emp4 = new Employee("Mike Johnson", 35, "Engineering");
        emp4.displayInfo();

        System.out.println("\n--- Creating employee with all parameters ---");
        Employee emp5 = new Employee("Sarah Williams", 28, "Marketing", 75000.00);
        emp5.displayInfo();

        System.out.println("\n=== Key Points ===");
        System.out.println("✓ Constructor name must match class name");
        System.out.println("✓ No return type (not even void)");
        System.out.println("✓ Called automatically when object is created");
        System.out.println("✓ Can be overloaded (multiple constructors)");
        System.out.println("✓ Use this() for constructor chaining (must be first statement)");
        System.out.println("✓ Java provides default constructor only if you don't write any");
    }
}
