# 04 - Inheritance

## What You'll Learn

- What is Inheritance?
- `extends` keyword
- `super` keyword
- Method Overriding (`@Override`)
- Types of Inheritance

---

## Concepts

### What is Inheritance?
**Inheritance** allows a class to inherit attributes and methods from another class. It's an "IS-A" relationship.

```
Person (Parent/Superclass)
   |-- Student (Child/Subclass)  -> Student IS-A Person
   |-- Teacher (Child/Subclass)  -> Teacher IS-A Person
```

### The `extends` Keyword
```java
// Parent class (Superclass)
public class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Child class (Subclass)
public class Student extends Person {
    private String studentId;
    private double gpa;
    
    public Student(String name, int age, String studentId) {
        super(name, age);  // Call parent constructor
        this.studentId = studentId;
    }
}
```

### The `super` Keyword
`super` is used to:
1. Call parent constructor: `super(args)`
2. Call parent method: `super.methodName()`
3. Access parent attribute: `super.attributeName`

```java
public class Student extends Person {
    private String studentId;
    
    public Student(String name, int age, String studentId) {
        super(name, age);  // MUST be first line
        this.studentId = studentId;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Student ID: " + studentId);
    }
}
```

### Method Overriding
Child class can provide its own implementation of a parent method.

```java
public class Person {
    public void introduce() {
        System.out.println("I am " + name);
    }
}

public class Student extends Person {
    @Override  // Annotation - good practice
    public void introduce() {
        System.out.println("I am student " + name + ", ID: " + studentId);
    }
}

public class Teacher extends Person {
    @Override
    public void introduce() {
        System.out.println("I am teacher " + name + ", I teach " + subject);
    }
}
```

### Using Inheritance
```java
public class Main {
    public static void main(String[] args) {
        Student s = new Student("Ahmed", 20, "STU001");
        Teacher t = new Teacher("Dr. Sara", 35, "CS101");
        
        s.displayInfo();  // Inherited + overridden
        t.displayInfo();
        
        s.introduce();    // Student's version
        t.introduce();    // Teacher's version
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Inheritance | Child class inherits from parent class |
| `extends` | Keyword to inherit from a class |
| `super` | Reference to parent class |
| `@Override` | Annotation for overridden methods |
| `protected` | Accessible by subclasses |
| Parent/Superclass | The class being inherited from |
| Child/Subclass | The class that inherits |

---

## Project Task

Create `Person` as parent class:

```java
// school/Person.java
public class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() { }
    public void introduce() { }
}

// school/Student.java
public class Student extends Person {
    private String studentId;
    private double gpa;
    
    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }
    
    @Override
    public void displayInfo() { }
    
    @Override
    public void introduce() { }
}

// school/Teacher.java
public class Teacher extends Person {
    private String employeeId;
    private String subject;
    
    // Similar structure
}
```

---

## Exercises

1. Create `Person` class with common attributes
2. Make `Student` extend `Person`
3. Make `Teacher` extend `Person`
4. Override `displayInfo()` in both subclasses
5. Override `introduce()` in both subclasses
6. Create a `Staff` class that also extends `Person`

---

## Next Lesson

[05 - Polymorphism](../05-polymorphism) - Learn how to treat different objects uniformly
