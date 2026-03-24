import java.util.Objects;

public class Teacher extends Person {

    protected String subject;
    protected double salary;

    public Teacher(String name, int age, String id, String subject, double salary) {
        super(name, age, id);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Teacher Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + id);
        System.out.println("Subject: " + subject);
        System.out.println("Salary: $" + salary);
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm " + name + ", I teach " + subject);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Teacher teacher = (Teacher) obj;
        return Double.compare(teacher.salary, salary) == 0 &&
                Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject, salary);
    }
}
