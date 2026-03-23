public class Teacher extends Person{

    private String employeeId;
    private String subjct;

    public Teacher( String name,int age , String employeeId, String subject){
        super(name,age);
        this.employeeId = employeeId;
        this.subjct = subject;

    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("the employee id is " + this.employeeId);
        System.out.println("the subjct is " + this.subjct);

    }
    @Override
    public void introduce(){
        System.out.println("I am a teacher and this is my name  " + this.name);
    }

}
