import java.util.Arrays;
import java.util.Objects;

public class TechnicalStaff extends Staff {

    private String specialization;
    private String[] certifications;

    public TechnicalStaff(String name, int age, String id, String department, double salary,
                          String specialization, String[] certifications) {
        super(name, age, id, department, salary);
        this.specialization = specialization;
        this.certifications = certifications;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Certifications: " + String.join(", ", certifications));
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a " + specialization + " specialist");
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String[] getCertifications() {
        return certifications;
    }

    public void setCertifications(String[] certifications) {
        this.certifications = certifications;
    }

    @Override
    public String toString() {
        return "TechnicalStaff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", specialization='" + specialization + '\'' +
                ", certifications=" + Arrays.toString(certifications) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        TechnicalStaff that = (TechnicalStaff) obj;
        return Objects.equals(specialization, that.specialization) &&
                Arrays.equals(certifications, that.certifications);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), specialization);
        result = 31 * result + Arrays.hashCode(certifications);
        return result;
    }
}
