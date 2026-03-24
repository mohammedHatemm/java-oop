public class Main {
    public static void main(String[] args) {

        // === Creating Objects ===
        UndergraduateStudent undergrad = new UndergraduateStudent(
                "Ahmed", 20, "S001", "Computer Science", 3.5, 2
        );

        GraduateStudent grad = new GraduateStudent(
                "Sara", 25, "G001", "AI", 3.8, "Machine Learning", "Dr. Ali"
        );

        Professor prof = new Professor(
                "Dr. Mohamed", 45, "T001", "Data Science", 15000, "Full Professor", 50
        );

        Assistant ta = new Assistant(
                "Omar", 28, "T002", "Programming", 5000, "Dr. Mohamed", 20
        );

        AdminStaff admin = new AdminStaff(
                "Fatma", 35, "ST001", "Registrar", 8000, "Secretary", true
        );

        TechnicalStaff tech = new TechnicalStaff(
                "Hassan", 30, "ST002", "IT", 10000, "Network Admin",
                new String[]{"CCNA", "AWS", "Linux"}
        );

        // === Upcasting - Store all in Person array ===
        Person[] people = {undergrad, grad, prof, ta, admin, tech};

        System.out.println("=== All People Introductions ===\n");
        for (Person p : people) {
            p.introduce();
        }

        // === instanceof & Downcasting ===
        System.out.println("\n=== Students Only (using instanceof) ===\n");
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;  // Downcasting
                System.out.println(s.getName() + " - GPA: " + s.getGpa());
            }
        }

        // === Display Full Info ===
        System.out.println("\n=== Full Info for Professor ===\n");
        prof.displayInfo();

        // === equals() Test ===
        System.out.println("\n=== Equals Test ===");
        Student s1 = new Student("Test", 20, "X001", "CS", 3.0);
        Student s2 = new Student("Test", 20, "X001", "CS", 3.0);
        Student s3 = new Student("Test", 20, "X001", "CS", 3.5);

        System.out.println("s1.equals(s2): " + s1.equals(s2));  // true
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // false (different GPA)

        // === toString() Test ===
        System.out.println("\n=== toString Test ===");
        System.out.println(prof);
        System.out.println(tech);

        // === hashCode() Test ===
        System.out.println("\n=== hashCode Test ===");
        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s2.hashCode(): " + s2.hashCode());
        System.out.println("Same hashCode? " + (s1.hashCode() == s2.hashCode()));
    }
}
