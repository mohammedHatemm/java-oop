# 03 - Encapsulation

## What You'll Learn

- What is Encapsulation?
- Access Modifiers (public, private, protected)
- Getters and Setters
- Data Validation
- Why Encapsulation matters

---

## Concepts

### What is Encapsulation?
**Encapsulation** is hiding the internal data and only allowing access through methods. It's like a capsule that protects the data inside.

### The Problem Without Encapsulation
```java
public class Student {
    String name;
    int age;
    double gpa;
}

// Anyone can set invalid values!
Student s = new Student();
s.age = -5;      // Invalid! Age can't be negative
s.gpa = 10.0;    // Invalid! GPA max is 4.0
```

### Access Modifiers

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| `public` | Yes | Yes | Yes | Yes |
| `protected` | Yes | Yes | Yes | No |
| (default) | Yes | Yes | No | No |
| `private` | Yes | No | No | No |

### Getters and Setters
```java
public class Student {
    // Private attributes - hidden from outside
    private String name;
    private int age;
    private double gpa;
    
    // Getter - read access
    public String getName() {
        return name;
    }
    
    // Setter - write access with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age > 0 && age < 150) {  // Validation
            this.age = age;
        }
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {  // Validation
            this.gpa = gpa;
        }
    }
}
```

### Using Encapsulated Class
```java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        
        // s.name = "Ahmed";  // ERROR! name is private
        
        s.setName("Ahmed");   // OK - uses setter
        s.setAge(20);         // OK
        s.setAge(-5);         // Ignored - validation fails
        
        System.out.println(s.getName());  // Uses getter
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Encapsulation | Hiding data, exposing through methods |
| `private` | Only accessible within the class |
| `public` | Accessible from anywhere |
| Getter | Method to read a private attribute |
| Setter | Method to write a private attribute |
| Validation | Checking data before setting |

---

## Project Task

Update `Student` and create `Teacher` with encapsulation:

```java
// school/Student.java
public class Student {
    private String name;
    private int age;
    private String studentId;
    private double gpa;
    
    // Constructor
    public Student(String name, int age, String studentId) {
        setName(name);
        setAge(age);
        setStudentId(studentId);
        this.gpa = 0.0;
    }
    
    // Getters and Setters with validation
    // ...
}

// school/Teacher.java
public class Teacher {
    private String name;
    private int age;
    private String employeeId;
    private String subject;
    private double salary;
    
    // Constructor, Getters, Setters with validation
}
```

---

## Exercises

1. Make all attributes in `Student` private
2. Create getters for all attributes
3. Create setters with validation:
   - `name`: not null or empty
   - `age`: between 5 and 100
   - `gpa`: between 0.0 and 4.0
   - `studentId`: must start with "STU"
4. Create a `Teacher` class with encapsulation
5. Add validation: salary must be positive

---

## Next Lesson

[04 - Inheritance](../04-inheritance) - Learn how to create class hierarchies
