public class Main {
    public static void main(String[] args) {
        
        // ============================================
        // Exercise 2: Constructor Chaining
        // ============================================
        System.out.println("=== Constructor Chaining ===");
        Student s1 = new Student();  // Default → calls chain
        s1.display();
        
        System.out.println("\n--- With name only ---");
        Student s2 = new Student("Ahmed");
        s2.display();
        
        System.out.println("\n--- With all params ---");
        Student s3 = new Student("Sara", 20, "Computer Science", 3.8);
        s3.display();
        
        // ============================================
        // Exercise 3: Copy Constructor
        // ============================================
        System.out.println("\n\n=== Copy Constructor ===");
        Student original = new Student("Mohamed", 22, "Engineering", 3.5);
        Student copy = new Student(original);
        
        System.out.println("Original:");
        original.display();
        System.out.println("\nCopy:");
        copy.display();
        
        // ============================================
        // Exercise 4: Validation in Constructor
        // ============================================
        System.out.println("\n\n=== Validation Constructor ===");
        System.out.println("Creating student with age=15, gpa=2.0:");
        Student s4 = new Student(15, "Math", 2.0);
        s4.display();
        
        // ============================================
        // Exercise 5: Counter
        // ============================================
        System.out.println("\n\n=== Student Counter ===");
        System.out.println("Total students created: " + Student.getCount());
        
        // ============================================
        // Exercise 6: Builder Pattern
        // ============================================
        System.out.println("\n\n=== Builder Pattern ===");
        
        // Full builder
        Student s5 = new Student.Builder()
            .name("Ali")
            .age(21)
            .major("Medicine")
            .gpa(3.9)
            .build();
        System.out.println("Student built with all fields:");
        s5.display();
        
        // Partial builder (using defaults)
        Student s6 = new Student.Builder()
            .name("Fatma")
            .major("Law")
            .build();
        System.out.println("\nStudent built with some fields (defaults for rest):");
        s6.display();
        
        // Empty builder (all defaults)
        Student s7 = new Student.Builder().build();
        System.out.println("\nStudent built with no fields (all defaults):");
        s7.display();
        
        System.out.println("\n\n=== Final Count ===");
        System.out.println("Total students created: " + Student.getCount());
    }
}
