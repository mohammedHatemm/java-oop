import java.util.Objects;

public class Assistant extends Teacher {

    private String supervisingProfessor;
    private int hoursPerWeek;

    public Assistant(String name, int age, String id, String subject, double salary,
                     String supervisingProfessor, int hoursPerWeek) {
        super(name, age, id, subject, salary);
        this.supervisingProfessor = supervisingProfessor;
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Supervising Professor: " + supervisingProfessor);
        System.out.println("Hours/Week: " + hoursPerWeek);
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a teaching assistant for " + subject);
    }

    public String getSupervisingProfessor() {
        return supervisingProfessor;
    }

    public void setSupervisingProfessor(String supervisingProfessor) {
        this.supervisingProfessor = supervisingProfessor;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", salary=" + salary +
                ", supervisingProfessor='" + supervisingProfessor + '\'' +
                ", hoursPerWeek=" + hoursPerWeek +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Assistant assistant = (Assistant) obj;
        return hoursPerWeek == assistant.hoursPerWeek &&
                Objects.equals(supervisingProfessor, assistant.supervisingProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supervisingProfessor, hoursPerWeek);
    }
}
