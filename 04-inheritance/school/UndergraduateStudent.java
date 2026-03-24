import java.util.Objects;

public class UndergraduateStudent extends Student {

    private int year;

    public UndergraduateStudent(String name, int age, String id, String major, double gpa, int year) {
        super(name, age, id, major, gpa);
        this.year = year;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Year: " + year);
        System.out.println("Level: Undergraduate");
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a year " + year + " undergraduate student");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "UndergraduateStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        UndergraduateStudent that = (UndergraduateStudent) obj;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year);
    }
}
