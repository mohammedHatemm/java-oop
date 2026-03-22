# 01 - Classes and Objects

## What You'll Learn

- What is a Class?
- What is an Object?
- Attributes (Instance Variables)
- Methods (Behaviors)
- Creating and using objects
- **Advanced Topics:**
  - Multiple Objects
  - Passing Objects to Methods
  - Returning Objects from Methods
  - Array of Objects
  - Object References
  - `==` vs `.equals()`
  - `null` Reference
  - `this` Keyword
  - Method Overloading
  - Object Memory (Stack vs Heap)

---

## Part 1: Basics

### Class
A **Class** is a blueprint or template for creating objects.

```java
public class Student {
    // Attributes - what the object HAS
    String name;
    int age;
    double grade;
    String studentId;
    
    // Methods - what the object DOES
    void study() {
        System.out.println(name + " is studying");
    }
    
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}
```

### Object
An **Object** is a real instance of a Class.

```java
public class Main {
    public static void main(String[] args) {
        // Creating an object
        Student student1 = new Student();
        
        // Setting attributes
        student1.name = "Ahmed";
        student1.age = 20;
        student1.grade = 85.5;
        
        // Calling methods
        student1.displayInfo();
        student1.study();
    }
}
```

---

## Part 2: Advanced Topics

---

### 1. Multiple Objects

Each object is independent - changing one does not affect the other.

```java
public class Main {
    public static void main(String[] args) {
        // Creating 3 objects from the same class
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        
        // Each object has different values
        s1.name = "Ahmed";
        s1.age = 20;
        s1.grade = 85;
        
        s2.name = "Mohamed";
        s2.age = 22;
        s2.grade = 90;
        
        s3.name = "Sara";
        s3.age = 19;
        s3.grade = 95;
        
        // Each object is independent
        System.out.println(s1.name);  // Ahmed
        System.out.println(s2.name);  // Mohamed
        System.out.println(s3.name);  // Sara
    }
}
```

#### Explanation:
- Each `new Student()` creates a new object in memory
- `s1`, `s2`, `s3` are all students but each one is different
- Think of it like making 3 copies of a form - each has different data

---

### 2. Passing Objects to Methods

You can pass an object as a parameter to any method.

```java
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ahmed";
        s1.age = 20;
        s1.grade = 85;
        
        // Passing the object to a method
        printStudentInfo(s1);
        
        // Modifying the object in a method
        updateGrade(s1, 95);
        System.out.println("New Grade: " + s1.grade);  // 95
    }
    
    // Method that receives a Student object
    static void printStudentInfo(Student student) {
        System.out.println("=== Student Info ===");
        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("Grade: " + student.grade);
    }
    
    // Method that modifies the object
    static void updateGrade(Student student, double newGrade) {
        student.grade = newGrade;
    }
}
```

#### Explanation:
- When you pass an object to a method, you're passing a **reference**, not a copy
- If you modify the object inside the method, the change appears outside too
- `updateGrade(s1, 95)` changed the original grade

---

### 3. Returning Objects from Methods

A method can return an object.

```java
public class Main {
    public static void main(String[] args) {
        // The method returns a Student object
        Student newStudent = createStudent("Ahmed", 20, 85);
        
        newStudent.displayInfo();
    }
    
    // Method that returns a Student object
    static Student createStudent(String name, int age, double grade) {
        Student s = new Student();
        s.name = name;
        s.age = age;
        s.grade = grade;
        return s;
    }
}
```

#### Explanation:
- The method's return type is `Student`, not `void`
- It creates a new object, fills it with data, and returns it
- Useful when you need to create many objects the same way

---

### 4. Array of Objects

You can create an array that contains multiple objects.

```java
public class Main {
    public static void main(String[] args) {
        // Creating an array of Students
        Student[] students = new Student[3];
        
        // Creating each object separately
        students[0] = new Student();
        students[0].name = "Ahmed";
        students[0].age = 20;
        students[0].grade = 85;
        
        students[1] = new Student();
        students[1].name = "Mohamed";
        students[1].age = 22;
        students[1].grade = 90;
        
        students[2] = new Student();
        students[2].name = "Sara";
        students[2].age = 19;
        students[2].grade = 95;
        
        // Loop through all students
        System.out.println("=== All Students ===");
        for (int i = 0; i < students.length; i++) {
            System.out.println("Student " + (i + 1) + ": " + students[i].name);
            System.out.println("Grade: " + students[i].grade);
            System.out.println("---");
        }
        
        // Calculate average
        double total = 0;
        for (int i = 0; i < students.length; i++) {
            total += students[i].grade;
        }
        double average = total / students.length;
        System.out.println("Average Grade: " + average);
    }
}
```

#### Explanation:
- `Student[] students = new Student[3]` creates an array that holds 3 students
- **Important:** The array is still empty! You must do `new Student()` for each element
- You can loop through the array and work with each student

---

### 5. Object References

When you assign one object to another, they both point to the same location in memory.

```java
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ahmed";
        s1.grade = 80;
        
        // s2 points to the same object
        Student s2 = s1;
        
        // Change through s2
        s2.name = "Mohamed";
        s2.grade = 95;
        
        // s1 changed too!
        System.out.println(s1.name);   // Mohamed (not Ahmed!)
        System.out.println(s1.grade);  // 95 (not 80!)
        
        // Because s1 and s2 are the same object
        System.out.println(s1 == s2);  // true
    }
}
```

#### Diagram:
```
Before s2 = s1:
┌─────┐         ┌──────────────┐
│ s1  │────────►│ name: Ahmed  │
└─────┘         │ grade: 80    │
                └──────────────┘

After s2 = s1:
┌─────┐         ┌──────────────┐
│ s1  │────────►│ name: Ahmed  │
└─────┘    ┌───►│ grade: 80    │
           │    └──────────────┘
┌─────┐    │
│ s2  │────┘  (same object!)
└─────┘
```

#### Explanation:
- `s2 = s1` does **NOT** create a new copy
- `s2` points to the same object that `s1` points to
- Any change from `s2` will appear in `s1` and vice versa

---

### 6. Comparing Objects: `==` vs `.equals()`

```java
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ahmed";
        
        Student s2 = new Student();
        s2.name = "Ahmed";
        
        Student s3 = s1;
        
        // == compares references (are they the same object?)
        System.out.println(s1 == s2);   // false (different objects)
        System.out.println(s1 == s3);   // true (same object)
        
        // For Strings, use .equals()
        System.out.println(s1.name.equals(s2.name));  // true (same value)
    }
}
```

#### Difference:
| Operator | Function |
|----------|----------|
| `==` | Are they the **same object** in memory? |
| `.equals()` | Is the **value** equal? |

#### Explanation:
- `s1` and `s2` have the same `name` but they are different objects
- `==` says `false` because they are in different memory locations
- `s3 = s1` so `s1 == s3` is `true`

---

### 7. The `null` Reference

`null` means "no object".

```java
public class Main {
    public static void main(String[] args) {
        Student s1 = null;  // no object
        
        // Error! NullPointerException
        // System.out.println(s1.name);
        
        // The correct way: check first
        if (s1 != null) {
            System.out.println(s1.name);
        } else {
            System.out.println("Student is null!");
        }
        
        // Create the object
        s1 = new Student();
        s1.name = "Ahmed";
        
        // Now it works
        System.out.println(s1.name);  // Ahmed
    }
}
```

#### Explanation:
- `null` = the variable doesn't point to any object
- If you try to use `null.name` you get a **NullPointerException**
- Always check with `if (obj != null)` before using

#### Practical Example:
```java
Student[] students = new Student[5];  // all elements are null!

// This is wrong
// System.out.println(students[0].name);  // NullPointerException

// Correct way
students[0] = new Student();  // first create the object
students[0].name = "Ahmed";   // then use it
```

---

### 8. The `this` Keyword

`this` refers to the current object.

```java
public class Student {
    String name;
    int age;
    double grade;
    
    // When parameter name is same as attribute name
    void setInfo(String name, int age, double grade) {
        this.name = name;    // this.name = the attribute
        this.age = age;      // name = the parameter
        this.grade = grade;
    }
    
    void displayInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
    }
    
    // this to return the object itself
    Student setName(String name) {
        this.name = name;
        return this;  // returns the object itself
    }
    
    Student setAge(int age) {
        this.age = age;
        return this;
    }
    
    // Method Chaining
    void printDetails() {
        System.out.println(this.name + " - " + this.age);
    }
}

// Usage with Method Chaining
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        
        // Method Chaining - calling methods one after another
        s1.setName("Ahmed").setAge(20).printDetails();
        // Output: Ahmed - 20
    }
}
```

#### Explanation:
- `this.name` = the class attribute
- `name` (without this) = the parameter coming from outside
- `return this` = return the object itself (useful for chaining)

---

### 9. Method Overloading (Multiple Methods with Same Name)

You can have methods with the same name but different parameters.

```java
public class Student {
    String name;
    int age;
    double grade;
    
    // Method without parameters
    void study() {
        System.out.println(name + " is studying.");
    }
    
    // Method with one parameter
    void study(String subject) {
        System.out.println(name + " is studying " + subject + ".");
    }
    
    // Method with 2 parameters
    void study(String subject, int hours) {
        System.out.println(name + " is studying " + subject + " for " + hours + " hours.");
    }
    
    // Method with different parameter type
    void study(int chapters) {
        System.out.println(name + " is studying " + chapters + " chapters.");
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ahmed";
        
        s1.study();                    // Ahmed is studying.
        s1.study("Math");              // Ahmed is studying Math.
        s1.study("Math", 3);           // Ahmed is studying Math for 3 hours.
        s1.study(5);                   // Ahmed is studying 5 chapters.
    }
}
```

#### Rules:
| They must differ in | Example |
|---------------------|---------|
| Number of parameters | `study()` vs `study(String s)` |
| Type of parameters | `study(String s)` vs `study(int n)` |
| Order of parameters | `study(String s, int n)` vs `study(int n, String s)` |

#### Explanation:
- Java chooses the correct method based on the parameters you pass
- This makes the code easier to use

---

### 10. Object Memory: Stack vs Heap

```
┌─────────────────────────────────────────────────────────────┐
│                         MEMORY                               │
├──────────────────────┬──────────────────────────────────────┤
│       STACK          │              HEAP                     │
│   (Local Variables)  │           (Objects)                   │
├──────────────────────┼──────────────────────────────────────┤
│                      │                                       │
│  ┌────────────┐      │      ┌─────────────────────┐         │
│  │ s1 = 0x100 │──────┼─────►│ Student Object      │         │
│  └────────────┘      │      │ name: "Ahmed"       │         │
│                      │      │ age: 20             │         │
│  ┌────────────┐      │      │ grade: 85           │         │
│  │ s2 = 0x200 │──────┼──┐   └─────────────────────┘         │
│  └────────────┘      │  │                                    │
│                      │  │   ┌─────────────────────┐         │
│                      │  └──►│ Student Object      │         │
│                      │      │ name: "Sara"        │         │
│                      │      │ age: 19             │         │
│                      │      │ grade: 95           │         │
│                      │      └─────────────────────┘         │
└──────────────────────┴──────────────────────────────────────┘
```

#### Explanation:
| Location | What it contains | When is it cleared |
|----------|------------------|-------------------|
| **Stack** | Variables and references (`s1`, `s2`) | When the method ends |
| **Heap** | The actual Objects | When no reference points to them (Garbage Collection) |

---

## Part 3: Practical Exercises

### Exercise 1: Multiple Objects
Create 5 different students and print their information.

```java
// In Main.java
Student s1 = new Student();
Student s2 = new Student();
// Continue...
```

---

### Exercise 2: Array of Students
Create an array of 3 students and calculate the average grade.

```java
// In Main.java
Student[] students = new Student[3];
// Continue...
// Print the average
```

---

### Exercise 3: Method Overloading
Add 3 versions of `calculateGrade()` method:

```java
// In Student.java
// Version 1: without parameters - uses this.grade
String calculateGrade() {
    // Continue...
}

// Version 2: receives grade as parameter
String calculateGrade(double grade) {
    // Continue...
}

// Version 3: receives grade and bonus
String calculateGrade(double grade, double bonus) {
    // Continue...
}
```

---

### Exercise 4: Passing Objects
Write a method `compareStudents` that receives 2 students and prints who has the higher grade.

```java
// In Main.java
static void compareStudents(Student s1, Student s2) {
    // Continue...
}
```

---

### Exercise 5: Object References
Try this code and understand the output:

```java
Student original = new Student();
original.name = "Ahmed";
original.grade = 80;

Student copy = original;
copy.grade = 100;

System.out.println(original.grade);  // What's the output? Why?
```

---

### Exercise 6: Null Handling
Write a method `safeDisplay` that prints student info only if not null.

```java
static void safeDisplay(Student s) {
    // Continue...
}

// Test
safeDisplay(null);  // Should print "No student found" not an error
```

---

### Exercise 7: Method with Return Object
Write a method `findBestStudent` that receives an array of students and returns the one with the highest grade.

```java
static Student findBestStudent(Student[] students) {
    // Continue...
}
```

---

### Exercise 8: this Keyword
Add methods to the Student class that use `this` and return the object:

```java
Student setName(String name) {
    // Continue...
}

Student setGrade(double grade) {
    // Continue...
}

// Usage (Method Chaining):
Student s = new Student();
s.setName("Ahmed").setGrade(90).displayInfo();
```

---

### Exercise 9: Complete Student Class
Write a complete Student class containing:
- Attributes: name, age, grade, studentId
- Methods:
  - `displayInfo()` - prints all information
  - `study()` - 3 versions (overloading)
  - `calculateGrade()` - returns "Excellent", "Very Good", "Good", "Failed"
  - `setInfo(String name, int age, double grade)` - uses this
  - `isPassing()` - returns true if grade >= 50

---

### Exercise 10: Student Management
Write a complete program in Main.java:
1. Create an array of 5 students
2. Fill in the data
3. Print all students
4. Print the highest grade
5. Print the lowest grade
6. Print the number of passing and failing students
7. Print the average

---

## Key Points Summary

| Concept | Explanation |
|---------|-------------|
| Multiple Objects | Each `new` creates a new independent object |
| Pass Object | Passing a reference, not a copy |
| Array of Objects | `Student[] arr = new Student[n]` + `arr[i] = new Student()` |
| References | `s2 = s1` = same object |
| `==` | Compare references |
| `.equals()` | Compare values |
| `null` | No object |
| `this` | The current object |
| Overloading | Same name, different parameters |

---

## Next Lesson

[02 - Constructors](../02-constructors) - Learn how to initialize objects properly with constructors
