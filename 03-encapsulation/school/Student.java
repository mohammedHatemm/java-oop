public class Student {
    private String name;
    private int age;
    private double gpa;
    private String studentId;

    // Constructor - uses setters for validation
    public Student(String name, int age, String studentId) {
        setName(name);
        setAge(age);
        setStudentId(studentId);
        this.gpa = 0.0;
    }

    // Full constructor
    public Student(String name, int age, String studentId, double gpa) {
        setName(name);
        setAge(age);
        setStudentId(studentId);
        setGpa(gpa);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 5 && age <= 100) {
            this.age = age;
        }
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }

    public void setStudentId(String studentId) {
        if (studentId != null && studentId.startsWith("STU")) {
            this.studentId = studentId;
        }
    }

    // Display method
    public void display() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }
}
