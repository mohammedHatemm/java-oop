import java.util.Objects;

public class Professor extends Teacher {

    private String rank;
    private int publications;

    public Professor(String name, int age, String id, String subject, double salary,
                     String rank, int publications) {
        super(name, age, id, subject, salary);
        this.rank = rank;
        this.publications = publications;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Rank: " + rank);
        System.out.println("Publications: " + publications);
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm " + rank + " " + name + ", I teach " + subject);
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPublications() {
        return publications;
    }

    public void setPublications(int publications) {
        this.publications = publications;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", salary=" + salary +
                ", rank='" + rank + '\'' +
                ", publications=" + publications +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Professor professor = (Professor) obj;
        return publications == professor.publications &&
                Objects.equals(rank, professor.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank, publications);
    }
}
