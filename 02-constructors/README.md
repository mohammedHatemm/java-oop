# 02 - Constructors

## What You'll Learn

- What is a Constructor?
- Default Constructor
- Parameterized Constructor
- The `this` keyword
- Constructor Overloading
- **Advanced Topics:**
  - Constructor Chaining (`this()`)
  - Copy Constructor
  - Private Constructor
  - Constructor vs Method
  - Initialization Order
  - Common Mistakes

---

## Part 1: Basics

### What is a Constructor?
A **constructor** is a special method that is called when an object is created. It initializes the object.

**Rules:**
- Same name as the class
- No return type (not even `void`)
- Called automatically when you use `new`

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

## Part 2: Advanced Topics

---

### 1. Constructor Chaining with `this()`

You can call one constructor from another using `this()`.

```java
public class Student {
    String name;
    int age;
    double gpa;
    String studentId;
    
    // Main constructor - all parameters
    public Student(String name, int age, double gpa, String studentId) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.studentId = studentId;
        System.out.println("Student created: " + name);
    }
    
    // Constructor 2 - calls the main constructor
    public Student(String name, int age) {
        this(name, age, 0.0, "N/A");  // calls the main constructor
    }
    
    // Constructor 3 - calls constructor 2
    public Student(String name) {
        this(name, 18);  // calls constructor 2
    }
    
    // Default constructor - calls constructor 3
    public Student() {
        this("Unknown");  // calls constructor 3
    }
}
```

#### How it works:
```
new Student()
    │
    ▼
this("Unknown")  →  Student(String name)
                        │
                        ▼
                    this(name, 18)  →  Student(String name, int age)
                                            │
                                            ▼
                                        this(name, age, 0.0, "N/A")  →  Main Constructor
```

#### Rules for `this()`:
| Rule | Example |
|------|---------|
| Must be the **first line** | `this()` before any other code |
| Can only call **one** constructor | Can't have two `this()` calls |
| Avoids code duplication | Write initialization logic once |

#### Why use it?
```java
// ❌ BAD - Code duplication
public Student() {
    this.name = "Unknown";
    this.age = 0;
    this.gpa = 0.0;
    this.studentId = "N/A";
    System.out.println("Student created");  // duplicated in every constructor
}

public Student(String name) {
    this.name = name;
    this.age = 0;
    this.gpa = 0.0;
    this.studentId = "N/A";
    System.out.println("Student created");  // duplicated!
}

// ✓ GOOD - Use this()
public Student() {
    this("Unknown");  // no duplication
}

public Student(String name) {
    this(name, 0, 0.0, "N/A");  // no duplication
}
```

---

### 2. Copy Constructor

A constructor that creates a copy of another object.

```java
public class Student {
    String name;
    int age;
    double gpa;
    
    // Normal constructor
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
        this.gpa = other.gpa;
    }
}
```

#### Usage:
```java
Student s1 = new Student("Ahmed", 20, 3.5);

// Create a copy
Student s2 = new Student(s1);  // s2 is a copy of s1

// They are different objects
s2.name = "Mohamed";

System.out.println(s1.name);  // Ahmed (unchanged)
System.out.println(s2.name);  // Mohamed
```

#### Why use it?
Remember the reference problem?
```java
Student s1 = new Student("Ahmed", 20, 3.5);
Student s2 = s1;  // NOT a copy! Same object!

s2.name = "Mohamed";
System.out.println(s1.name);  // Mohamed (changed!)
```

Copy constructor solves this:
```java
Student s1 = new Student("Ahmed", 20, 3.5);
Student s2 = new Student(s1);  // Real copy!

s2.name = "Mohamed";
System.out.println(s1.name);  // Ahmed (safe!)
```

---

### 3. Private Constructor

A constructor with `private` access - cannot be called from outside.

```java
public class Database {
    private static Database instance;
    
    // Private constructor
    private Database() {
        System.out.println("Database connection created");
    }
    
    // The only way to get an instance
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
```

#### Usage:
```java
// This won't work
// Database db = new Database();  // ❌ Error - constructor is private

// This works
Database db1 = Database.getInstance();
Database db2 = Database.getInstance();

// db1 and db2 are the SAME object (Singleton pattern)
System.out.println(db1 == db2);  // true
```

#### When to use?
| Use Case | Description |
|----------|-------------|
| Singleton Pattern | Only one instance of the class |
| Utility Classes | Classes with only static methods (like `Math`) |
| Factory Pattern | Control how objects are created |

---

### 4. Constructor vs Method

| Feature | Constructor | Method |
|---------|-------------|--------|
| Name | Same as class name | Any name |
| Return type | None (not even void) | Must have one |
| Called | Automatically with `new` | Manually |
| Purpose | Initialize object | Perform action |
| Inheritance | Not inherited | Inherited |
| `static` | Cannot be static | Can be static |

```java
public class Student {
    String name;
    
    // Constructor - no return type
    public Student(String name) {
        this.name = name;
    }
    
    // Method - has return type
    public void setName(String name) {
        this.name = name;
    }
    
    // This is a METHOD, not a constructor (has return type)
    public void Student() {  // ⚠️ This is confusing but valid
        System.out.println("This is a method!");
    }
}
```

---

### 5. Initialization Order

When you create an object, things happen in this order:

```java
public class Student {
    // Step 1: Instance variables get default values
    String name;           // null
    int age;               // 0
    double gpa = 3.0;      // 3.0 (explicit initialization)
    
    // Step 2: Instance initializer block (if any)
    {
        System.out.println("Initializer block runs");
        name = "Default";
    }
    
    // Step 3: Constructor runs
    public Student(String name) {
        System.out.println("Constructor runs");
        this.name = name;
    }
}
```

#### Order:
```
1. Default values (null, 0, false, etc.)
       ↓
2. Explicit initializations (gpa = 3.0)
       ↓
3. Instance initializer blocks { }
       ↓
4. Constructor
```

#### Example:
```java
Student s = new Student("Ahmed");

// Output:
// Initializer block runs
// Constructor runs

// Final value: name = "Ahmed" (constructor overwrote "Default")
```

---

### 6. Default Values

If you don't initialize variables, they get default values:

| Type | Default Value |
|------|---------------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (null character) |
| Objects (`String`, `Student`, etc.) | `null` |

```java
public class Student {
    String name;    // null
    int age;        // 0
    double gpa;     // 0.0
    boolean active; // false
    
    public void printDefaults() {
        System.out.println(name);    // null
        System.out.println(age);     // 0
        System.out.println(gpa);     // 0.0
        System.out.println(active);  // false
    }
}
```

---

### 7. Common Mistakes

#### Mistake 1: Adding return type to constructor
```java
// ❌ WRONG - This is a method, not a constructor!
public void Student(String name) {
    this.name = name;
}

// ✓ CORRECT
public Student(String name) {
    this.name = name;
}
```

#### Mistake 2: Forgetting `this` keyword
```java
// ❌ WRONG - parameter shadows the attribute
public Student(String name) {
    name = name;  // Does nothing!
}

// ✓ CORRECT
public Student(String name) {
    this.name = name;
}
```

#### Mistake 3: `this()` not on first line
```java
// ❌ WRONG
public Student(String name) {
    System.out.println("Hello");
    this(name, 0);  // Error! this() must be first
}

// ✓ CORRECT
public Student(String name) {
    this(name, 0);  // First line
    System.out.println("Hello");
}
```

#### Mistake 4: Expecting default constructor when you defined one
```java
public class Student {
    public Student(String name) {
        this.name = name;
    }
}

// ❌ WRONG - No default constructor exists!
Student s = new Student();  // Error!

// ✓ CORRECT
Student s = new Student("Ahmed");
```

#### Mistake 5: Infinite constructor loop
```java
// ❌ WRONG - Infinite loop!
public Student() {
    this("Unknown");
}

public Student(String name) {
    this();  // Calls Student() which calls Student(String)... forever!
}
```

---

## Part 3: Key Points Summary

| Concept | Description |
|---------|-------------|
| Constructor | Special method to initialize objects |
| Default Constructor | No parameters, sets default values |
| Parameterized Constructor | Takes parameters to set values |
| `this` | Reference to current object |
| `this()` | Call another constructor (must be first line) |
| Copy Constructor | Creates a copy of another object |
| Private Constructor | Cannot be called from outside (Singleton) |
| Overloading | Multiple constructors with different parameters |

---

## Part 4: Exercises

### Exercise 1: Basic Constructors
Create a `Student` class with:
- Default constructor (sets name to "Unknown", age to 0)
- Constructor with name only
- Constructor with all attributes

```java
Student s1 = new Student();
Student s2 = new Student("Ahmed");
Student s3 = new Student("Sara", 20, 3.8);
```

---

### Exercise 2: Constructor Chaining
Rewrite Exercise 1 using `this()` to avoid code duplication.

```java
public Student() {
    this("Unknown");
}

public Student(String name) {
    this(name, 0, 0.0);
}
// etc.
```

---

### Exercise 3: Copy Constructor
Add a copy constructor to your Student class:

```java
Student original = new Student("Ahmed", 20, 3.5);
Student copy = new Student(original);

copy.name = "Mohamed";
System.out.println(original.name);  // Should print "Ahmed"
```

---

### Exercise 4: Validation in Constructor
Create a constructor that validates the input:

```java
public Student(String name, int age, double gpa) {
    // Validate: age must be > 0 and < 100
    // Validate: gpa must be between 0.0 and 4.0
    // If invalid, set to default values
}
```

---

### Exercise 5: Counter
Create a class that counts how many objects have been created:

```java
public class Student {
    private static int count = 0;
    
    public Student() {
        count++;
    }
    
    public static int getCount() {
        return count;
    }
}

// Usage
Student s1 = new Student();
Student s2 = new Student();
Student s3 = new Student();
System.out.println(Student.getCount());  // 3
```

---

### Exercise 6: Builder Pattern (Advanced)
Create a `StudentBuilder` class for creating complex Student objects:

```java
Student s = new StudentBuilder()
    .setName("Ahmed")
    .setAge(20)
    .setGpa(3.5)
    .setStudentId("STU001")
    .build();
```

---

## Next Lesson

[03 - Encapsulation](../03-encapsulation) - Learn how to protect your data with private fields and getters/setters
