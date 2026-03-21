# 07 - Interfaces

## What You'll Learn

- What is an Interface?
- `interface` and `implements` keywords
- Multiple Inheritance with Interfaces
- Interface vs Abstract Class
- Default and Static methods in interfaces

---

## Concepts

### What is an Interface?
An **interface** is a contract that defines what a class must do, but not how. It's 100% abstract (before Java 8).

### Defining an Interface
```java
public interface Gradable {
    // All methods are public abstract by default
    void assignGrade(double grade);
    double getGrade();
    String getLetterGrade();
}

public interface Payable {
    double calculatePayment();
    void processPayment();
}
```

### Implementing an Interface
```java
public class Student extends Person implements Gradable {
    private double gpa;
    
    @Override
    public void assignGrade(double grade) {
        if (grade >= 0 && grade <= 4.0) {
            this.gpa = grade;
        }
    }
    
    @Override
    public double getGrade() {
        return gpa;
    }
    
    @Override
    public String getLetterGrade() {
        if (gpa >= 3.7) return "A";
        if (gpa >= 3.3) return "A-";
        if (gpa >= 3.0) return "B+";
        if (gpa >= 2.7) return "B";
        if (gpa >= 2.0) return "C";
        return "F";
    }
}
```

### Multiple Interfaces
A class can implement multiple interfaces (Java's way of multiple inheritance).

```java
public class Teacher extends Person implements Payable, Gradable {
    private double salary;
    private double performanceScore;
    
    // Must implement ALL methods from BOTH interfaces
    
    @Override
    public double calculatePayment() {
        return salary;
    }
    
    @Override
    public void processPayment() {
        System.out.println("Processing payment of $" + salary);
    }
    
    @Override
    public void assignGrade(double grade) {
        this.performanceScore = grade;
    }
    
    @Override
    public double getGrade() {
        return performanceScore;
    }
    
    @Override
    public String getLetterGrade() {
        // Teacher performance grade
        if (performanceScore >= 90) return "Excellent";
        if (performanceScore >= 80) return "Good";
        return "Needs Improvement";
    }
}
```

### Interface as Type
```java
public class Main {
    public static void main(String[] args) {
        // Interface as reference type
        Gradable[] gradables = new Gradable[2];
        gradables[0] = new Student("Ahmed", 20, "STU001");
        gradables[1] = new Teacher("Sara", 35, "EMP001", "Math", 8000);
        
        for (Gradable g : gradables) {
            g.assignGrade(3.5);
            System.out.println("Letter Grade: " + g.getLetterGrade());
        }
        
        // Payable reference
        Payable[] payables = new Payable[1];
        payables[0] = new Teacher("Sara", 35, "EMP001", "Math", 8000);
        
        for (Payable p : payables) {
            System.out.println("Payment: " + p.calculatePayment());
        }
    }
}
```

### Default Methods (Java 8+)
```java
public interface Printable {
    void print();
    
    // Default method - has implementation
    default void printHeader() {
        System.out.println("=== Report ===");
    }
}
```

---

## Key Points

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Instantiate | No | No |
| Constructor | Yes | No |
| Instance variables | Yes | No (only constants) |
| Method implementation | Yes | Yes (default methods) |
| Multiple inheritance | No | Yes |
| Access modifiers | Any | public only |

### When to Use What?

- **Abstract Class:** When classes share common code (attributes, methods)
- **Interface:** When classes share common behavior (contract)

---

## Project Task

Create interfaces for the school system:

```java
// school/Gradable.java
public interface Gradable {
    void assignGrade(double grade);
    double getGrade();
    String getLetterGrade();
}

// school/Payable.java
public interface Payable {
    double calculatePayment();
    void processPayment();
}

// school/Printable.java
public interface Printable {
    void printReport();
    
    default void printHeader() {
        System.out.println("========== REPORT ==========");
    }
}

// Update Student
public class Student extends Person implements Gradable, Printable {
    // Implement all interface methods
}

// Update Teacher
public class Teacher extends Person implements Payable, Gradable, Printable {
    // Implement all interface methods
}
```

---

## Exercises

1. Create `Gradable` interface with grade-related methods
2. Create `Payable` interface with payment-related methods
3. Create `Printable` interface with a default `printHeader()` method
4. Make `Student` implement `Gradable` and `Printable`
5. Make `Teacher` implement all three interfaces
6. Create an array of `Gradable` objects and process them

---

## Next Lesson

[08 - Advanced](../08-advanced) - Learn about static, final, enums, and design patterns
