import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();

//comment
       student1.name = "mohamed";
       student1.age = 18;
       student1.grade = 75;

        student1.displayStudentInfo();
        student1.calculateGrade();
    }

}
