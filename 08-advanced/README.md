# 08 - Advanced OOP Concepts

## What You'll Learn

- `static` keyword
- `final` keyword
- Enums
- Inner Classes
- Design Patterns (Singleton, Factory)

---

## Concepts

### 1. The `static` Keyword

Static members belong to the **class**, not to instances.

```java
public class Student {
    // Instance variable - each object has its own
    private String name;
    
    // Static variable - shared by ALL objects
    private static int totalStudents = 0;
    
    // Static constant
    public static final String SCHOOL_NAME = "Java Academy";
    
    public Student(String name) {
        this.name = name;
        totalStudents++;  // Increment for each new student
    }
    
    // Static method - can be called without object
    public static int getTotalStudents() {
        return totalStudents;
    }
}

// Usage
System.out.println(Student.SCHOOL_NAME);        // No object needed
System.out.println(Student.getTotalStudents()); // No object needed
```

### 2. The `final` Keyword

```java
// Final variable - cannot be changed
final int MAX_STUDENTS = 100;

// Final method - cannot be overridden
public final void displayId() {
    System.out.println(studentId);
}

// Final class - cannot be extended
public final class Constants {
    public static final double PI = 3.14159;
}
```

### 3. Enums

Enums are special classes that represent a fixed set of constants.

```java
public enum Grade {
    A_PLUS(4.0, "Excellent"),
    A(4.0, "Excellent"),
    A_MINUS(3.7, "Very Good"),
    B_PLUS(3.3, "Good"),
    B(3.0, "Good"),
    B_MINUS(2.7, "Above Average"),
    C(2.0, "Average"),
    F(0.0, "Fail");
    
    private final double points;
    private final String description;
    
    Grade(double points, String description) {
        this.points = points;
        this.description = description;
    }
    
    public double getPoints() {
        return points;
    }
    
    public String getDescription() {
        return description;
    }
}

// Usage
Grade studentGrade = Grade.A;
System.out.println(studentGrade.getPoints());      // 4.0
System.out.println(studentGrade.getDescription()); // Excellent

// Switch with enum
switch (studentGrade) {
    case A_PLUS:
    case A:
        System.out.println("Dean's List!");
        break;
    case F:
        System.out.println("Must retake course");
        break;
}
```

### 4. Inner Classes

```java
public class School {
    private String name;
    
    // Inner class
    public class Department {
        private String deptName;
        
        public void display() {
            // Can access outer class members
            System.out.println(name + " - " + deptName);
        }
    }
    
    // Static inner class
    public static class Address {
        private String city;
        private String street;
    }
}

// Usage
School school = new School();
School.Department dept = school.new Department();  // Need outer instance

School.Address address = new School.Address();     // No outer instance needed
```

### 5. Singleton Pattern

Ensures only ONE instance of a class exists.

```java
public class SchoolSystem {
    // Private static instance
    private static SchoolSystem instance;
    
    // Private constructor - prevent external instantiation
    private SchoolSystem() {
        System.out.println("School System initialized");
    }
    
    // Public static method to get instance
    public static SchoolSystem getInstance() {
        if (instance == null) {
            instance = new SchoolSystem();
        }
        return instance;
    }
    
    public void addStudent(Student s) {
        // ...
    }
}

// Usage
SchoolSystem system1 = SchoolSystem.getInstance();
SchoolSystem system2 = SchoolSystem.getInstance();
// system1 == system2 (same object!)
```

### 6. Factory Pattern

Creates objects without exposing creation logic.

```java
public class PersonFactory {
    public static Person createPerson(String type, String name, int age) {
        switch (type.toLowerCase()) {
            case "student":
                return new Student(name, age, generateStudentId());
            case "teacher":
                return new Teacher(name, age, generateEmployeeId());
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
    
    private static String generateStudentId() {
        return "STU" + System.currentTimeMillis();
    }
    
    private static String generateEmployeeId() {
        return "EMP" + System.currentTimeMillis();
    }
}

// Usage
Person student = PersonFactory.createPerson("student", "Ahmed", 20);
Person teacher = PersonFactory.createPerson("teacher", "Sara", 35);
```

---

## Key Points

| Concept | Description |
|---------|-------------|
| `static` | Belongs to class, not instance |
| `final` | Cannot be changed/overridden/extended |
| Enum | Fixed set of constants with behavior |
| Singleton | Only one instance of a class |
| Factory | Creates objects without exposing logic |

---

## Project Task

Add advanced features to the school system:

```java
// school/Grade.java
public enum Grade {
    A_PLUS, A, A_MINUS, B_PLUS, B, B_MINUS, C, F;
    // Add points and description
}

// school/SchoolSystem.java
public class SchoolSystem {
    private static SchoolSystem instance;
    private List<Student> students;
    private List<Teacher> teachers;
    
    private SchoolSystem() { }
    
    public static SchoolSystem getInstance() { }
    
    public void addStudent(Student s) { }
    public void addTeacher(Teacher t) { }
    public void printAllPeople() { }
}

// school/PersonFactory.java
public class PersonFactory {
    public static Person create(String type, String name, int age) { }
}
```

---

## Exercises

1. Add static counter to count total students created
2. Create `Grade` enum with points and descriptions
3. Implement `SchoolSystem` as Singleton
4. Create `PersonFactory` to create Student/Teacher
5. Add `final` to methods that shouldn't be overridden
6. Use enum in Student for grade assignment

---

## Final Project

[School Management System](../school-management-system) - Complete project combining all concepts
