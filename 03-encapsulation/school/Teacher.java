public class Teacher {
    private String name;
    private int age;
    private String employeeId;
    private String subject;
    private double salary;

    // Constructor
    public Teacher(String name, int age, String employeeId, String subject, double salary) {
        setName(name);
        setAge(age);
        setEmployeeId(employeeId);
        setSubject(subject);
        setSalary(salary);
    }

    // Getters
    public String getName() {
        return name;  // Fixed: was empty!
    }

    public int getAge() {
        return age;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getSubject() {
        return subject;
    }

    public double getSalary() {
        return salary;
    }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 21 && age <= 70) {  // Teachers: 21-70
            this.age = age;
        }
    }

    public void setEmployeeId(String employeeId) {
        if (employeeId != null && employeeId.startsWith("EMP")) {
            this.employeeId = employeeId;
        }
    }

    public void setSubject(String subject) {
        if (subject != null && !subject.isEmpty()) {
            this.subject = subject;
        }
    }

    public void setSalary(double salary) {
        if (salary > 0) {  // Must be positive
            this.salary = salary;
        }
    }

    // Display method
    public void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Subject: " + subject);
        System.out.println("Salary: $" + salary);
    }
}
