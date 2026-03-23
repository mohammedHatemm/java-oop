public class Student  extends Person{
 private String studentId;
 public Student(String name, int age , String studentId){
     super(name , age);
     this.studentId = studentId;
 }
 @Override
    public void introduce(){
     System.out.println("I am a student and this is my name  " + this.name);
 }
 @Override
    public void displayInfo(){
     super.displayInfo();
     System.out.println("Student id is " + this.studentId);

    }
}
