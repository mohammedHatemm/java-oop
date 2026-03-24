import java.util.Objects;

public class AdminStaff extends Staff {

    private String role;
    private boolean canAccessRecords;

    public AdminStaff(String name, int age, String id, String department, double salary,
                      String role, boolean canAccessRecords) {
        super(name, age, id, department, salary);
        this.role = role;
        this.canAccessRecords = canAccessRecords;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: " + role);
        System.out.println("Can Access Records: " + canAccessRecords);
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", " + role + " in " + department);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCanAccessRecords() {
        return canAccessRecords;
    }

    public void setCanAccessRecords(boolean canAccessRecords) {
        this.canAccessRecords = canAccessRecords;
    }

    @Override
    public String toString() {
        return "AdminStaff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", role='" + role + '\'' +
                ", canAccessRecords=" + canAccessRecords +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        AdminStaff that = (AdminStaff) obj;
        return canAccessRecords == that.canAccessRecords &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, canAccessRecords);
    }
}
