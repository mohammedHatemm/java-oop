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

## Advanced Concepts

### 1. Pattern Matching for instanceof (Java 16+)
A cleaner way to use instanceof without manual casting.

```java
// Old way (Java 15 and before)
if (person instanceof Student) {
    Student s = (Student) person;
    System.out.println(s.getGpa());
}

// New way (Java 16+) - Pattern Matching
if (person instanceof Student s) {
    // s is available directly without casting
    System.out.println(s.getGpa());
}

// Example with else
if (person instanceof Student s) {
    System.out.println("Student GPA: " + s.getGpa());
} else if (person instanceof Teacher t) {
    System.out.println("Teacher Subject: " + t.getSubject());
}
```

### 2. Covariant Return Types
When overriding, you can return a more specific type (Subclass) than the Parent.

```java
public class Person {
    public Person copy() {
        return new Person(this.name, this.age, this.id);
    }
}

public class Student extends Person {
    @Override
    public Student copy() {  // Returning Student instead of Person - allowed!
        return new Student(this.name, this.age, this.id, this.major, this.gpa);
    }
}

// Benefit: No need for casting
Student s1 = new Student("Ahmed", 20, "S001", "CS", 3.5);
Student s2 = s1.copy();  // Returns Student directly
```

### 3. Dynamic Method Dispatch (How Runtime Polymorphism Works)
The JVM decides which method to call at runtime based on the actual object type, not the variable type.

```java
Person p = new Student("Ahmed", 20, "S001", "CS", 3.5);

// Variable p is of type Person
// But the actual object is Student
// So Student.introduce() is called, not Person.introduce()

p.introduce();  // "I am a student" - calls Student's version

// This is called Dynamic Method Dispatch or Late Binding
```

**How it works internally:**
```
┌─────────────────────────────────────────────────────┐
│  Compile Time                                       │
│  - Compiler verifies Person has introduce() method  │
│  - Doesn't know which version will be called        │
└─────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────┐
│  Runtime                                            │
│  - JVM looks at the actual object (Student)         │
│  - Calls Student.introduce()                        │
└─────────────────────────────────────────────────────┘
```

### 4. Method Hiding vs Overriding (Static Methods)
Static methods are not overridden, they are **hidden**.

```java
public class Person {
    public static void staticMethod() {
        System.out.println("Person static");
    }
    
    public void instanceMethod() {
        System.out.println("Person instance");
    }
}

public class Student extends Person {
    // This is HIDING, not OVERRIDING
    public static void staticMethod() {
        System.out.println("Student static");
    }
    
    @Override  // This is OVERRIDING
    public void instanceMethod() {
        System.out.println("Student instance");
    }
}

// The difference:
Person p = new Student();

p.staticMethod();    // "Person static"   - depends on variable type (Person)
p.instanceMethod();  // "Student instance" - depends on object type (Student)
```

| | Overriding | Hiding |
|---|---|---|
| **Works with** | Instance methods | Static methods |
| **Depends on** | Actual object type | Variable type |
| **@Override** | Required/Recommended | Not allowed |

### 5. The `final` Keyword - Preventing Override
Use `final` to prevent overriding a specific method.

```java
public class Person {
    // Cannot override this method
    public final String getId() {
        return this.id;
    }
    
    // Can override this one
    public void introduce() {
        System.out.println("I am a person");
    }
}

public class Student extends Person {
    // ❌ Error! Cannot override final method
    // @Override
    // public String getId() { ... }
    
    // ✅ This is allowed
    @Override
    public void introduce() {
        System.out.println("I am a student");
    }
}
```

**When to use final:**
- Security-sensitive methods
- Methods that must always work the same way
- Performance optimization (JVM can inline)

### 6. ClassCastException - Downcasting Error
Occurs when trying to cast an object to an incorrect type.

```java
Person p = new Teacher("Sara", 35, "T001", "Math", 10000);

// ❌ Error! p is Teacher, not Student
Student s = (Student) p;  // ClassCastException at runtime!

// ✅ Correct way - check first
if (p instanceof Student) {
    Student s = (Student) p;
    // ...
}

// ✅ Or using Pattern Matching (Java 16+)
if (p instanceof Student s) {
    // Use s safely
}
```

### 7. Polymorphism with Method Parameters
You can accept Parent type and pass any Child.

```java
public class SchoolSystem {
    
    // Accepts any Person (Student, Teacher, Staff, etc.)
    public void register(Person person) {
        System.out.println("Registering: " + person.getName());
        person.displayInfo();
    }
    
    // Accepts array of any Person
    public void printAll(Person[] people) {
        for (Person p : people) {
            p.introduce();
        }
    }
}

// Usage
SchoolSystem system = new SchoolSystem();
system.register(new Student("Ahmed", 20, "S001", "CS", 3.5));  // ✅
system.register(new Teacher("Sara", 35, "T001", "Math", 10000));  // ✅
system.register(new AdminStaff(...));  // ✅
```

### 8. Polymorphism with Return Types
You can return any Child from a method that returns Parent.

```java
public class PersonFactory {
    
    public Person create(String type, String name) {
        if (type.equals("student")) {
            return new Student(name, 20, "S001", "CS", 3.0);
        } else if (type.equals("teacher")) {
            return new Teacher(name, 35, "T001", "Math", 10000);
        }
        return null;
    }
}

// Usage
PersonFactory factory = new PersonFactory();
Person p1 = factory.create("student", "Ahmed");  // Returns Student
Person p2 = factory.create("teacher", "Sara");   // Returns Teacher
```

---

## Common Mistakes to Avoid

### 1. Forgetting @Override
```java
public class Student extends Person {
    // ❌ Typo - won't override
    public void introudce() {  // typo: introudce
        System.out.println("I am a student");
    }
    
    // ✅ Use @Override so compiler catches the error
    @Override
    public void introduce() {
        System.out.println("I am a student");
    }
}
```

### 2. Downcasting without instanceof
```java
// ❌ Dangerous - may cause ClassCastException
public void process(Person p) {
    Student s = (Student) p;  // What if it's a Teacher?
}

// ✅ Safe
public void process(Person p) {
    if (p instanceof Student s) {
        // Use s
    }
}
```

### 3. Calling Child-specific method on Parent variable
```java
Person p = new Student("Ahmed", 20, "S001", "CS", 3.5);

// ❌ Error - Person doesn't have getGpa()
// p.getGpa();

// ✅ Correct - after checking and casting
if (p instanceof Student s) {
    System.out.println(s.getGpa());
}
```

---

## Exercises

1. Create a `Person[]` array with mixed `Student` and `Teacher` objects
2. Loop through and call `introduce()` on each
3. Use `instanceof` to count students vs teachers
4. Add a method `getDetails()` that returns different info for each type
5. Create a method `printReport(Person p)` that handles any person type
6. **Advanced:** Implement a `PersonFactory` class that creates different Person types
7. **Advanced:** Use Pattern Matching (Java 16+) to refactor instanceof checks
8. **Advanced:** Create a `copy()` method with Covariant Return Types

---

## Next Lesson

[06 - Abstraction](../06-abstraction) - Learn about abstract classes
