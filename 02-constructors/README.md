# 02 - Constructors

## What You'll Learn

- What is a Constructor?
- Default Constructor
- Parameterized Constructor
- The `this` keyword
- Constructor Overloading

---

## Concepts

### What is a Constructor?
A **constructor** is a special method that is called when an object is created. It initializes the object.

### Default Constructor
```java
public class Student {
    String name;
    int age;
    
    // Default constructor (no parameters)
    public Student() {
        name = "Unknown";
        age = 0;
    }
}
```

### Parameterized Constructor
```java
public class Student {
    String name;
    int age;
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;  // 'this' refers to the current object
        this.age = age;
    }
}
```

### The `this` Keyword
`this` refers to the current object. Used when parameter names match attribute names.

```java
public Student(String name, int age) {
    this.name = name;  // this.name = object's attribute
    this.age = age;    // name = parameter
}
```

### Constructor Overloading
Multiple constructors with different parameters.

```java
public class Student {
    String name;
    int age;
    double gpa;
    
    // Constructor 1: No parameters
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.gpa = 0.0;
    }
    
    // Constructor 2: Name only
    public Student(String name) {
        this.name = name;
        this.age = 0;
        this.gpa = 0.0;
    }
    
    // Constructor 3: All parameters
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
}
```

### Using Constructors
```java
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();                      // Uses constructor 1
        Student s2 = new Student("Ahmed");               // Uses constructor 2
        Student s3 = new Student("Sara", 20, 3.8);       // Uses constructor 3
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Constructor | Special method to initialize objects |
| Default Constructor | No parameters, sets default values |
| Parameterized Constructor | Takes parameters to set values |
| `this` | Reference to current object |
| Overloading | Multiple constructors with different parameters |

---

## Project Task

Update `Student` class with constructors:

```java
// school/Student.java
public class Student {
    String name;
    int age;
    String studentId;
    double gpa;
    
    // Default constructor
    public Student() {
        // Set default values
    }
    
    // Parameterized constructor
    public Student(String name, int age, String studentId, double gpa) {
        // Initialize all attributes
    }
    
    // Constructor with name only
    public Student(String name) {
        // Initialize name, set defaults for others
    }
}
```

---

## Exercises

1. Create a `Student` using the default constructor
2. Create a `Student` using the parameterized constructor
3. Add a constructor that takes only `name` and `studentId`
4. Use `this` keyword correctly in all constructors
5. Create a `Teacher` class with similar constructors

---

## Next Lesson

[03 - Encapsulation](../03-encapsulation) - Learn how to protect your data
