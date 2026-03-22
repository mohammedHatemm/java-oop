public class Main {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       CLASSES & OBJECTS DEMO           ");
        System.out.println("========================================\n");

        // ==================== 1. Creating Objects ====================
        System.out.println("--- 1. Creating Objects ---");
        Student s1 = new Student();
        s1.name = "Ahmed";
        s1.age = 20;
        s1.grade = 85;
        s1.studentId = "STU001";

        Student s2 = new Student();
        s2.name = "Sara";
        s2.age = 19;
        s2.grade = 92;
        s2.studentId = "STU002";

        s1.displayStudentInfo();
        System.out.println();

        // ==================== 2. Method Overloading ====================
        System.out.println("--- 2. Method Overloading ---");
        s1.study();                        // بدون parameters
        s1.study("Math");                  // parameter واحد
        s1.study("Physics", 3);            // 2 parameters
        System.out.println();

        // ==================== 3. Calculate Grade (Overloading) ====================
        System.out.println("--- 3. Calculate Grade ---");
        System.out.println(s1.name + " grade: " + s1.calculateGrade());
        System.out.println("Grade 70: " + s1.calculateGrade(70));
        System.out.println("Grade 70 + bonus 10: " + s1.calculateGrade(70, 10));
        System.out.println();

        // ==================== 4. Method Chaining with 'this' ====================
        System.out.println("--- 4. Method Chaining ---");
        Student s3 = new Student();
        s3.setName("Mohamed").setAge(21).setGrade(78).printSummary();
        System.out.println();

        // ==================== 5. Array of Objects ====================
        System.out.println("--- 5. Array of Objects ---");
        Student[] students = new Student[3];

        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        for (int i = 0; i < students.length; i++) {
            students[i].printSummary();
        }
        System.out.println();

        // ==================== 6. Passing Object to Method ====================
        System.out.println("--- 6. Passing Object to Method ---");
        printStudent(s1);
        System.out.println();

        // ==================== 7. Compare Students ====================
        System.out.println("--- 7. Compare Students ---");
        compareStudents(s1, s2);
        System.out.println();

        // ==================== 8. Find Best Student ====================
        System.out.println("--- 8. Find Best Student ---");
        Student best = findBestStudent(students);
        System.out.println("Best student: " + best.name + " with grade " + best.grade);
        System.out.println();

        // ==================== 9. Object References ====================
        System.out.println("--- 9. Object References ---");
        Student original = new Student();
        original.name = "Test";
        original.grade = 60;

        Student copy = original;  // Same reference!
        copy.grade = 100;

        System.out.println("original.grade = " + original.grade);  // 100!
        System.out.println("copy.grade = " + copy.grade);          // 100
        System.out.println("Are they same object? " + (original == copy));  // true
        System.out.println();

        // ==================== 10. Null Handling ====================
        System.out.println("--- 10. Null Handling ---");
        safeDisplay(s1);      // Works fine
        safeDisplay(null);    // Handles null safely
        System.out.println();

        // ==================== 11. Statistics ====================
        System.out.println("--- 11. Statistics ---");
        printStatistics(students);

        System.out.println("\n========================================");
        System.out.println("              END OF DEMO               ");
        System.out.println("========================================");
    }

    // ==================== Helper Methods ====================

    // طباعة معلومات الطالب
    static void printStudent(Student s) {
        System.out.println("Student: " + s.name + ", Grade: " + s.grade);
    }

    // مقارنة طالبين
    static void compareStudents(Student s1, Student s2) {
        if (s1.grade > s2.grade) {
            System.out.println(s1.name + " has higher grade (" + s1.grade + ")");
        } else if (s2.grade > s1.grade) {
            System.out.println(s2.name + " has higher grade (" + s2.grade + ")");
        } else {
            System.out.println("Both have same grade (" + s1.grade + ")");
        }
    }

    // إيجاد أفضل طالب
    static Student findBestStudent(Student[] students) {
        Student best = students[0];
        for (int i = 1; i < students.length; i++) {
            if (students[i].grade > best.grade) {
                best = students[i];
            }
        }
        return best;
    }

    // عرض آمن (يتعامل مع null)
    static void safeDisplay(Student s) {
        if (s != null) {
            System.out.println("Student: " + s.name);
        } else {
            System.out.println("No student found (null)");
        }
    }

    // إحصائيات
    static void printStatistics(Student[] students) {
        double total = 0;
        int passing = 0;
        int failing = 0;
        double highest = students[0].grade;
        double lowest = students[0].grade;

        for (int i = 0; i < students.length; i++) {
            total += students[i].grade;

            if (students[i].isPassing()) {
                passing++;
            } else {
                failing++;
            }

            if (students[i].grade > highest) {
                highest = students[i].grade;
            }
            if (students[i].grade < lowest) {
                lowest = students[i].grade;
            }
        }

        double average = total / students.length;

        System.out.println("Total students: " + students.length);
        System.out.println("Passing: " + passing);
        System.out.println("Failing: " + failing);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
        System.out.println("Average: " + average);
    }
}
