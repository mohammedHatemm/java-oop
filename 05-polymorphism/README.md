# 05 - Polymorphism

## What You'll Learn

- What is Polymorphism?
- Compile-time Polymorphism (Method Overloading)
- Runtime Polymorphism (Method Overriding)
- Upcasting and Downcasting
- `instanceof` operator

---

## Concepts

### What is Polymorphism?
**Polymorphism** means "many forms". The same method can behave differently based on the object.

### Compile-time Polymorphism (Method Overloading)
Same method name, different parameters. Decided at **compile time**.

```java
public class Calculator {
    // Same name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

### Runtime Polymorphism (Method Overriding)
Same method in parent and child, different implementation. Decided at **runtime**.

```java
public class Person {
    public void introduce() {
        System.out.println("I am a person");
    }
}

public class Student extends Person {
    @Override
    public void introduce() {
        System.out.println("I am a student");
    }
}

public class Teacher extends Person {
    @Override
    public void introduce() {
        System.out.println("I am a teacher");
    }
}
```

### The Power of Polymorphism
```java
public class Main {
    public static void main(String[] args) {
        // Parent reference can hold child objects
        Person p1 = new Student("Ahmed", 20, "STU001");
        Person p2 = new Teacher("Sara", 35, "EMP001");
        
        p1.introduce();  // "I am a student"
        p2.introduce();  // "I am a teacher"
        
        // Array of different types
        Person[] people = new Person[3];
        people[0] = new Student("Ahmed", 20, "STU001");
        people[1] = new Teacher("Sara", 35, "EMP001");
        people[2] = new Student("Ali", 21, "STU002");
        
        // Same method call, different behavior
        for (Person p : people) {
            p.introduce();  // Each calls its own version
        }
    }
}
```

### Upcasting and Downcasting
```java
// Upcasting (automatic) - Child to Parent
Student student = new Student("Ahmed", 20, "STU001");
Person person = student;  // OK - automatic

// Downcasting (manual) - Parent to Child
Person person = new Student("Ahmed", 20, "STU001");
Student student = (Student) person;  // Need explicit cast

// Safe downcasting with instanceof
if (person instanceof Student) {
    Student s = (Student) person;
    s.getGpa();  // Now we can access Student methods
}
```

### The `instanceof` Operator
```java
public void process(Person p) {
    if (p instanceof Student) {
        Student s = (Student) p;
        System.out.println("GPA: " + s.getGpa());
    } else if (p instanceof Teacher) {
        Teacher t = (Teacher) p;
        System.out.println("Subject: " + t.getSubject());
    }
}
```

---

## Key Points

| Term | Description |
|------|-------------|
| Polymorphism | Same method, different behaviors |
| Overloading | Same name, different parameters (compile-time) |
| Overriding | Same signature, different implementation (runtime) |
| Upcasting | Child to Parent (automatic) |
| Downcasting | Parent to Child (explicit cast) |
| `instanceof` | Check object's actual type |

---

## Project Task

Apply polymorphism in the school system:

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        // Create array of Person
        Person[] people = new Person[4];
        people[0] = new Student("Ahmed", 20, "STU001");
        people[1] = new Teacher("Sara", 35, "EMP001");
        people[2] = new Student("Ali", 21, "STU002");
        people[3] = new Teacher("Omar", 40, "EMP002");
        
        // Process all with polymorphism
        for (Person p : people) {
            p.introduce();
            p.displayInfo();
            System.out.println("---");
        }
        
        // Count students and teachers
        int studentCount = 0;
        int teacherCount = 0;
        
        for (Person p : people) {
            if (p instanceof Student) {
                studentCount++;
            } else if (p instanceof Teacher) {
                teacherCount++;
            }
        }
    }
}
```

---

## Exercises

1. Create a `Person[]` array with mixed `Student` and `Teacher` objects
2. Loop through and call `introduce()` on each
3. Use `instanceof` to count students vs teachers
4. Add a method `getDetails()` that returns different info for each type
5. Create a method `printReport(Person p)` that handles any person type

---

## Next Lesson

[06 - Abstraction](../06-abstraction) - Learn about abstract classes
