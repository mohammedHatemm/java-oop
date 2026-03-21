public class Student {


    String name;
    int age;
    double gpa;
    double  grade;
    String studentId;

    void displayStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Grade: " + grade);

    }

    void study(){
        displayStudentInfo();
    }
    void calculateGrade() {
        if (grade > 75) {
            System.out.println("your grade is excellent");
        } else if (grade > 65) {
            System.out.println("your grade is very good");
        } else if (grade >= 50) {
            System.out.println("your grade is good");
        } else {
            System.out.println("you failed");
        }
    }

}
