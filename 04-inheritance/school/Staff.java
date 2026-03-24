import java.util.Objects;

public class Staff extends Person {

    protected String department;
    protected double salary;

    public Staff(String name, int age, String id, String department, double salary) {
        super(name, age, id);
        this.department = department;
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Staff Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + id);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + salary);
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", I work in " + department);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Staff staff = (Staff) obj;
        return Double.compare(staff.salary, salary) == 0 &&
                Objects.equals(department, staff.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, salary);
    }
}
