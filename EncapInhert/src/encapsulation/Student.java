package encapsulation;

/**
 * Student class demonstrating basic encapsulation principles
 * - Private fields for data hiding
 * - Public getters and setters for controlled access
 * - Validation in setters to ensure data integrity
 */
public class Student {
    // Private fields (data hiding)
    private String name;
    private int age;
    private double gpa;
    private String studentId;

    // Constructor
    public Student(String name, int age, double gpa, String studentId) {
        setName(name);           // Use setters for validation
        setAge(age);
        setGPA(gpa);
        setStudentId(studentId);
    }

    // ==================== GETTERS ====================

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    // ==================== SETTERS (with validation) ====================

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Age must be between 16 and 100, got: " + age);
        }
        this.age = age;
    }

    public void setGPA(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0, got: " + gpa);
        }
        this.gpa = gpa;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || !studentId.matches("^STU\\d{6}$")) {
            throw new IllegalArgumentException(
                "Student ID must be in format: STU followed by 6 digits (e.g., STU123456)"
            );
        }
        this.studentId = studentId;
    }

    // ==================== UTILITY METHODS ====================

    public void displayInfo() {
        System.out.println("=== Student Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Student ID: " + studentId);
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", gpa=" + gpa + ", id='" + studentId + "'}";
    }
}
