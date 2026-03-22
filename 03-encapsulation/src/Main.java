public class Main {
    public static void main(String[] args) {
        
        // ============================================
        // Testing Student Class
        // ============================================
        System.out.println("=== Testing Student ===\n");
        
        // Valid student
        Student s1 = new Student("Ahmed", 20, "STU001", 3.5);
        System.out.println("Student 1 (valid data):");
        s1.display();
        
        // Test validation - invalid age
        System.out.println("\n--- Testing invalid age (3) ---");
        s1.setAge(3);  // Should be ignored (min is 5)
        System.out.println("Age after setAge(3): " + s1.getAge());  // Still 20
        
        // Test validation - invalid GPA
        System.out.println("\n--- Testing invalid GPA (5.0) ---");
        s1.setGpa(5.0);  // Should be ignored (max is 4.0)
        System.out.println("GPA after setGpa(5.0): " + s1.getGpa());  // Still 3.5
        
        // Test validation - invalid studentId
        System.out.println("\n--- Testing invalid studentId ---");
        Student s2 = new Student("Sara", 18, "ABC123", 3.0);  // Invalid ID
        System.out.println("Student 2 (invalid studentId 'ABC123'):");
        s2.display();  // studentId will be null
        
        // Fix the studentId
        s2.setStudentId("STU002");
        System.out.println("\nAfter fixing studentId:");
        s2.display();
        
        // ============================================
        // Testing Teacher Class
        // ============================================
        System.out.println("\n\n=== Testing Teacher ===\n");
        
        // Valid teacher
        Teacher t1 = new Teacher("Dr. Mohamed", 45, "EMP001", "Mathematics", 5000.0);
        System.out.println("Teacher 1 (valid data):");
        t1.display();
        
        // Test validation - invalid salary
        System.out.println("\n--- Testing invalid salary (-1000) ---");
        t1.setSalary(-1000);  // Should be ignored (must be positive)
        System.out.println("Salary after setSalary(-1000): $" + t1.getSalary());  // Still 5000
        
        // Test validation - invalid age
        System.out.println("\n--- Testing invalid age (19) ---");
        t1.setAge(19);  // Should be ignored (min is 21)
        System.out.println("Age after setAge(19): " + t1.getAge());  // Still 45
        
        // Valid update
        System.out.println("\n--- Testing valid updates ---");
        t1.setSalary(6000);
        t1.setSubject("Physics");
        System.out.println("After valid updates:");
        t1.display();
        
        // ============================================
        // Demonstrating Encapsulation Benefits
        // ============================================
        System.out.println("\n\n=== Encapsulation Benefits ===");
        System.out.println("1. Cannot access private fields directly:");
        System.out.println("   // s1.name = \"test\";  // ERROR! name is private");
        System.out.println("2. All changes go through validation");
        System.out.println("3. Invalid data is rejected automatically");
    }
}
