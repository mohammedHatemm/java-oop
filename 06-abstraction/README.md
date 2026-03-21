# 06 - Abstraction

## What You'll Learn

- What is Abstraction?
- Abstract Classes
- Abstract Methods
- When to use Abstract Classes
- Abstract vs Concrete Classes

---

## Concepts

### What is Abstraction?
**Abstraction** is hiding complex implementation details and showing only the essential features. It's about WHAT an object does, not HOW it does it.

### The Problem
```java
public class Person {
    public void introduce() {
        // What should this print?
        // A Person is too generic!
    }
}
```

A `Person` object doesn't make sense on its own - we always have specific types like `Student` or `Teacher`.

### Abstract Classes
An **abstract class** cannot be instantiated. It's a blueprint for other classes.

```java
// Abstract class - cannot create objects directly
public abstract class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Concrete method - has implementation
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
    
    // Abstract method - NO implementation
    // Subclasses MUST implement this
    public abstract void introduce();
    
    public abstract double calculatePayment();
}
```

### Implementing Abstract Methods
```java
public class Student extends Person {
    private String studentId;
    private double gpa;
    
    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }
    
    @Override
    public void introduce() {
        System.out.println("I am student " + name + ", ID: " + studentId);
    }
    
    @Override
    public double calculatePayment() {
        // Students pay tuition
        return 5000.0;  // tuition fee
    }
}

public class Teacher extends Person {
    private String subject;
    private double salary;
    
    public Teacher(String name, int age, String subject, double salary) {
        super(name, age);
        this.subject = subject;
        this.salary = salary;
    }
    
    @Override
    public void introduce() {
        System.out.println("I am teacher " + name + ", I teach " + subject);
    }
    
    @Override
    public double calculatePayment() {
        // Teachers receive salary
        return salary;
    }
}
```

### Using Abstract Classes
```java
public class Main {
    public static void main(String[] args) {
        // Person p = new Person();  // ERROR! Cannot instantiate abstract class
        
        Person student = new Student("Ahmed", 20, "STU001");
        Person teacher = new Teacher("Sara", 35, "Math", 8000);
        
        student.introduce();         // Student's implementation
        teacher.introduce();         // Teacher's implementation
        
        System.out.println(student.calculatePayment());  // 5000.0
        System.out.println(teacher.calculatePayment());  // 8000.0
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Abstract Class | Cannot be instantiated, may have abstract methods |
| Abstract Method | No body, must be implemented by subclass |
| Concrete Method | Has implementation (body) |
| `abstract` | Keyword for abstract classes/methods |

### Rules for Abstract Classes

1. Cannot create objects of abstract class
2. Can have constructors (called by subclasses)
3. Can have both abstract and concrete methods
4. Can have attributes
5. If a class has abstract method, class must be abstract
6. Subclass must implement ALL abstract methods (or be abstract too)

---

## Project Task

Make `Person` abstract:

```java
// school/Person.java
public abstract class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Concrete method
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
    
    // Abstract methods - subclasses must implement
    public abstract void introduce();
    public abstract String getRole();
    public abstract double calculatePayment();
}
```

Update `Student` and `Teacher` to implement abstract methods.

---

## Exercises

1. Make `Person` class abstract
2. Add abstract method `introduce()` to Person
3. Add abstract method `getRole()` that returns "Student" or "Teacher"
4. Add abstract method `calculatePayment()` 
5. Implement all abstract methods in Student and Teacher
6. Try to create a Person object - observe the error

---

## Next Lesson

[07 - Interfaces](../07-interfaces) - Learn about interfaces and multiple inheritance
