public class Student {
    private String name;
    private int age;
    private String major;
    private double gpa;
public static int count = 0;
    // ============================================
    // Constructor Chaining Example
    // ============================================

    // Constructor 1: Full constructor (الأساسي)
    public Student(String name, int age, String major, double gpa) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
        count++;  // ← كل student جديد هيتحسب
        System.out.println("Full constructor called");
    }

    // Constructor 2: بدون GPA → بينادي Constructor 1
    public Student(String name, int age, String major) {
        this(name, age, major, 0.0);  // ← Constructor Chaining
        System.out.println("3-param constructor called");
    }

    // Constructor 3: الاسم والعمر بس → بينادي Constructor 2
    public Student(String name, int age) {
        this(name, age, "Undeclared");  // ← Constructor Chaining
        System.out.println("2-param constructor called");
    }

    // Constructor 4: الاسم بس → بينادي Constructor 3
    public Student(String name) {
        this(name, 18);  // ← Constructor Chaining
        System.out.println("1-param constructor called");
    }

    // Constructor 5 moved to Exercise 5 section

    // Method لعرض البيانات
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Major: " + major);
        System.out.println("GPA: " + gpa);
    }
//### Exercise 3: Copy Constructor
    public Student(Student other){
        this.name = other.name;
        this.age = other.age;
        this.major = other.major;
        this.gpa = other.gpa;
        count++;
    }

    // ### Exercise 4: Validation in Constructor
    public Student(int age, String major , double gpa) {
        this.age = age;
        this.major = major;
        this.gpa= gpa;
        count++;
        if(age < 18){
            System.out.println("Age: " + age + " is less than 18");
            this.age = 18;
        }
        if(gpa <4.8){
            System.out.println("GPA: " + gpa + " is less than 4.8");
            this.gpa = 4.8;
        }
    }


    //### Exercise 5: Counter

    public Student(){
        this("Unknown");
        // count++ removed - already in Full constructor
        System.out.println("Default constructor called");
    }
    public static int getCount() {
        return count;
    }

    // ============================================
    // Exercise 6: Builder Pattern
    // ============================================
    
    // Private constructor - يستخدم من الـ Builder بس
    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.major = builder.major;
        this.gpa = builder.gpa;
        count++;
    }

    // Static Inner Class
    public static class Builder {
        // نفس الـ fields + default values
        private String name = "Unknown";
        private int age = 18;
        private String major = "Undeclared";
        private double gpa = 0.0;

        // كل method بتحدد قيمة وبترجع this
        public Builder name(String name) {
            this.name = name;
            return this;  // ← علشان الـ chaining
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }

        // الـ method اللي بتبني الـ Student
        public Student build() {
            return new Student(this);
        }
    }


}
