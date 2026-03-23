public class Person {

    protected String name;
    protected int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void displayInfo(){
        System.out.println("name is :" + this.name);
        System.out.println("age is :" + this.age);
    }
    public void  introduce(){
        System.out.println("I am a " + this.name);
    }
}
