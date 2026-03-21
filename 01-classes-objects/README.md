# 01 - Classes and Objects

## What You'll Learn

- What is a Class?
- What is an Object?
- Attributes (Instance Variables)
- Methods (Behaviors)
- Creating and using objects

---

## Concepts

### Class
A **class** is a blueprint/template for creating objects.

```java
public class Student {
    // Attributes (what the object HAS)
    String name;
    int age;
    double gpa;
    
    // Methods (what the object DOES)
    void study() {
        System.out.println(name + " is studying");
    }
    
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }
}
```

### Object
An **object** is an instance of a class.

```java
public class Main {
    public static void main(String[] args) {
        // Creating objects
        Student student1 = new Student();
        Student student2 = new Student();
        
        // Setting attributes
        student1.name = "Ahmed";
        student1.age = 20;
        student1.gpa = 3.5;
        
        student2.name = "Sara";
        student2.age = 21;
        student2.gpa = 3.8;
        
        // Calling methods
        student1.displayInfo();
        student1.study();
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Class | Blueprint for objects |
| Object | Instance of a class |
| Attribute | Variable inside a class (state) |
| Method | Function inside a class (behavior) |
| `new` | Keyword to create an object |
| `.` | Dot operator to access attributes/methods |

---

## Project Task

Create a basic `Student` class in the `school/` folder:

```java
// school/Student.java
public class Student {
    String name;
    int age;
    String studentId;
    double gpa;
    
    void displayInfo() {
        // Print all student info
    }
    
    void study() {
        // Print studying message
    }
}
```

---

## Exercises

1. Create a `Student` object and set its attributes
2. Create multiple `Student` objects
3. Call the methods on each object
4. Add a new attribute `major` to the Student class
5. Add a new method `calculateGrade()` that returns "Pass" or "Fail" based on GPA

---

## Next Lesson

[02 - Constructors](../02-constructors) - Learn how to initialize objects properly
