import java.util.Objects;

public class Student extends Person {

    protected String major;
    protected double gpa;

    public Student(String name, int age, String id, String major, double gpa) {
        super(name, age, id);
        this.major = major;
        this.gpa = gpa;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Student Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + id);
        System.out.println("Major: " + major);
        System.out.println("GPA: " + gpa);
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a student majoring in " + major);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Student student = (Student) obj;
        return Double.compare(student.gpa, gpa) == 0 &&
                Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), major, gpa);
    }
}
