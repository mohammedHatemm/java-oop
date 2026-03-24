import java.util.Objects;

public class GraduateStudent extends Student {

    private String thesisTopic;
    private String supervisor;

    public GraduateStudent(String name, int age, String id, String major, double gpa,
                           String thesisTopic, String supervisor) {
        super(name, age, id, major, gpa);
        this.thesisTopic = thesisTopic;
        this.supervisor = supervisor;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Level: Graduate");
        System.out.println("Thesis: " + thesisTopic);
        System.out.println("Supervisor: " + supervisor);
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a graduate student researching " + thesisTopic);
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                ", thesisTopic='" + thesisTopic + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        GraduateStudent that = (GraduateStudent) obj;
        return Objects.equals(thesisTopic, that.thesisTopic) &&
                Objects.equals(supervisor, that.supervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), thesisTopic, supervisor);
    }
}
