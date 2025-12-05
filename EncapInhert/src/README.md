# Complete Master Guide to Encapsulation & Inheritance in Java
## From Zero to Master  2025 Edition

> **A deep dive into two fundamental OOP pillars with complete memory model explanations**

---

## Table of Contents

### Day 2: Encapsulation
1. [What is Encapsulation?](#1-what-is-encapsulation)
2. [Access Modifiers Deep Dive](#2-access-modifiers-deep-dive)
3. [Getters and Setters](#3-getters-and-setters)
4. [Data Hiding and Validation](#4-data-hiding-and-validation)
5. [Why Encapsulation Matters](#5-why-encapsulation-matters)
6. [Encapsulation and Memory Model](#6-encapsulation-and-memory-model)

### Day 3: Inheritance
7. [What is Inheritance?](#7-what-is-inheritance)
8. [The extends Keyword](#8-the-extends-keyword)
9. [Parent and Child Classes](#9-parent-and-child-classes)
10. [The super Keyword](#10-the-super-keyword)
11. [Constructor Chaining](#11-constructor-chaining)
12. [Types of Inheritance](#12-types-of-inheritance)
13. [protected Access Modifier in Inheritance](#13-protected-access-modifier-in-inheritance)
14. [Inheritance and Memory Model](#14-inheritance-and-memory-model)
15. [Master Diagrams Collection](#15-master-diagrams-collection)
16. [Best Practices & Common Pitfalls](#16-best-practices--common-pitfalls)

---

# Day 2: Encapsulation

## 1. What is Encapsulation?

**Encapsulation** is the bundling of data (fields) and methods that operate on that data within a single unit (class), while restricting direct access to some of the object's components.

### The Three Pillars of Encapsulation

**1. DATA HIDING**
   - Hide internal state using private fields

**2. CONTROLLED ACCESS**
   - Provide public getters/setters for access

**3. VALIDATION**
   - Validate data before allowing changes

### Real-World Analogy

**Car (Object)**

**Internal Components (PRIVATE):**
- Engine
- Fuel tank
- Transmission

**Public Interface (PUBLIC):**
- Accelerator (increases speed)
- Brake (decreases speed)
- Steering wheel (changes direction)

**Key Point:** You don't directly access the engine! You use the accelerator (method) to control it.

### Without Encapsulation vs With Encapsulation

**Without Encapsulation (BAD):**
```java
public class Student {
    public String name;    // Anyone can modify
    public int age;        // No validation possible
    public double gpa;     // Can be set to invalid values
}

// Usage:
Student s = new Student();
s.age = -5;        //  Invalid! But allowed
s.gpa = 10.5;      //  Invalid! But allowed
s.name = "";       //  Invalid! But allowed
```

**With Encapsulation (GOOD):**
```java
public class Student {
    private String name;   // Hidden from outside
    private int age;       // Protected
    private double gpa;    // Controlled access

    // Controlled access with validation
    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Invalid age");
        }
    }

    public int getAge() {
        return age;
    }
}

// Usage:
Student s = new Student();
s.setAge(-5);      //  Throws exception - data protected!
```

**Example Files:** `Student.java`, `EncapsulationDemo.java`

---

## 2. Access Modifiers Deep Dive

Java has 4 access levels that control visibility:

### Access Modifiers Table

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| private | ✓ | ✗ | ✗ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| protected | ✓ | ✓ | ✓ | ✗ |
| public | ✓ | ✓ | ✓ | ✓ |

### 1. private (Most Restrictive)

- **Accessible only within the same class**
- Use for: Internal implementation details, sensitive data
- Example: `balance` in `BankAccount`

```java
public class BankAccount {
    private double balance;           // Only accessible within this class
    private String accountNumber;     // Hidden from outside

    public void deposit(double amount) {
        balance += amount;  //  OK - same class
    }
}

// Usage:
BankAccount account = new BankAccount();
account.balance = 1000000;  //  COMPILE ERROR! private field
account.deposit(1000);      //  OK - public method
```

**Memory View:**

**Heap:**
- BankAccount @0x1234
  - Object Header
  - Class Ptr → BankAccount.class
  - **PRIVATE FIELDS:**
    - balance = 1000.0 (Only accessible via methods)
    - accountNumber = "12345"

**Stack:**
- account = 0x1234

**Note:** Direct access `account.balance` is BLOCKED by JVM at compile time!
Method access `account.getBalance()` is ALLOWED.

### 2. default (Package-Private)

- **No modifier keyword**
- Accessible within the same package only
- Use for: Package-level utilities, internal classes

```java
// File: com/example/Student.java
package com.example;

class Student {          // default (no modifier)
    String name;         // default access
    int age;

    void study() {       // default access
        System.out.println("Studying...");
    }
}

// File: com/example/Main.java (SAME package)
package com.example;

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "Ali";         //  OK - same package
        s.study();              //  OK - same package
    }
}

// File: com/other/Test.java (DIFFERENT package)
package com.other;
import com.example.Student;

public class Test {
    public static void main(String[] args) {
        Student s = new Student();  //  COMPILE ERROR! default class
    }
}
```

### 3. protected (Package + Subclasses)

- Accessible within same package + subclasses (even in different packages)
- Use for: Methods/fields that subclasses need to access

```java
// File: com/example/Person.java
package com.example;

public class Person {
    protected String name;      // protected field
    protected int age;

    protected void displayInfo() {
        System.out.println(name + " - " + age);
    }
}

// File: com/example/Student.java (SAME package)
package com.example;

public class Student {
    public void test() {
        Person p = new Person();
        p.name = "Ali";         //  OK - same package
        p.displayInfo();        //  OK - same package
    }
}

// File: com/other/Employee.java (DIFFERENT package, but SUBCLASS)
package com.other;
import com.example.Person;

public class Employee extends Person {
    public void test() {
        this.name = "Sara";     //  OK - inherited protected member
        this.displayInfo();     //  OK - inherited protected method

        // But:
        Person p = new Person();
        p.name = "John";        //  COMPILE ERROR! Different package, not inherited
    }
}
```

### 4. public (Least Restrictive)

- Accessible from anywhere
- Use for: Public API, methods/fields that should be universally accessible

```java
public class Student {
    public String name;         // Accessible from anywhere
    public int age;

    public void study() {       // Accessible from anywhere
        System.out.println("Studying...");
    }
}

// From ANY package, ANY class:
Student s = new Student();
s.name = "Ali";                 //  OK - public
s.study();                      //  OK - public
```

### Memory Perspective of Access Modifiers

**IMPORTANT:** Access modifiers are enforced at COMPILE TIME, not at runtime!

**Metaspace:**
- Student.class
  - Field Metadata:
    - name: String (access_flags: 0x0001 = public)
    - age: int (access_flags: 0x0002 = private)
  - Method Metadata:
    - getAge() (access_flags: 0x0001 = public)
    - setAge() (access_flags: 0x0001 = public)

**Compiler checks these flags at COMPILE TIME:**
- If you try to access private field from outside → COMPILE ERROR
- At RUNTIME, there's no access checking (already verified)

---

## 3. Getters and Setters

### Basic Pattern

Getters and setters provide controlled access to private fields:

```java
public class Person {
    // Private fields (data hiding)
    private String name;
    private int age;

    // Getter for name (read access)
    public String getName() {
        return name;
    }

    // Setter for name (write access)
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age (with validation)
    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 1 and 119");
        }
    }
}
```

### Advanced Patterns

#### 1. Read-Only Property (Getter only)

Used for computed values or properties that shouldn't be modified externally:

```java
public class Circle {
    private double radius;

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }

    public double getRadius() {
        return radius;
    }

    // Read-only properties: area and circumference (NO setters!)
    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }
}
```

**Example:** `Circle.java`

#### 2. Write-Only Property (Setter only) - Rare

Used for sensitive data like passwords:

```java
public class User {
    private String passwordHash;

    // Setter only - you can set password but never read it back
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    // NO getter for password! (Security)

    public boolean verifyPassword(String password) {
        return hashPassword(password).equals(passwordHash);
    }
}
```

#### 3. Lazy Initialization in Getter

Compute expensive values only when needed:

```java
public class Report {
    private String data;
    private String formattedData;  // Expensive to compute

    public Report(String data) {
        this.data = data;
    }

    // Lazy initialization: compute only when needed
    public String getFormattedData() {
        if (formattedData == null) {
            formattedData = expensiveFormatting(data);
            System.out.println("Formatting computed!");
        }
        return formattedData;
    }
}
```

#### 4. Defensive Copying (Mutable Objects)

Protect internal state from external modifications:

```java
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Student {
    private String name;
    private Date birthDate;        // Mutable!
    private List<String> courses;  // Mutable!

    public Student(String name, Date birthDate) {
        this.name = name;
        // Defensive copy on input
        this.birthDate = new Date(birthDate.getTime());
        this.courses = new ArrayList<>();
    }

    // Defensive copy on output
    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    // Defensive copy on input
    public void setBirthDate(Date birthDate) {
        this.birthDate = new Date(birthDate.getTime());
    }

    // Defensive copy on output
    public List<String> getCourses() {
        return new ArrayList<>(courses);
    }

    // Provide methods instead of exposing list
    public void addCourse(String course) {
        courses.add(course);
    }
}
```

---

## 4. Data Hiding and Validation

### Why Validate in Setters?

**Without Validation:**
```java
Student s = new Student();
s.age = -5;              // Invalid data!
s.gpa = 10.5;            // Out of range!
s.name = "";             // Empty name!
// Result: GARBAGE DATA in your objects!
```

**With Validation:**
```java
Student s = new Student();
s.setAge(-5);            // Throws exception
s.setGPA(10.5);          // Throws exception
s.setName("");           // Throws exception
// Result: ALWAYS VALID DATA in your objects!
```

### Validation Patterns

#### 1. Range Validation

```java
public class Student {
    private int age;
    private double gpa;

    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException(
                "Age must be between 1 and 120, got: " + age
            );
        }
        this.age = age;
    }

    public void setGPA(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException(
                "GPA must be between 0.0 and 4.0, got: " + gpa
            );
        }
        this.gpa = gpa;
    }
}
```

#### 2. Null Validation

```java
public class Person {
    private String name;

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }
}
```

#### 3. Regex Validation

```java
import java.util.regex.Pattern;

public class User {
    private String username;
    private static final Pattern USERNAME_PATTERN =
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");

    public void setUsername(String username) {
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException(
                "Username must be 3-20 alphanumeric characters or underscore"
            );
        }
        this.username = username;
    }
}
```

#### 4. Business Logic Validation

```java
public class BankAccount {
    private double balance;
    private double dailyWithdrawalLimit = 1000.0;
    private double totalWithdrawnToday = 0.0;

    public void withdraw(double amount) {
        // Validation 1: Amount must be positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Validation 2: Sufficient balance
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Validation 3: Daily limit
        if (totalWithdrawnToday + amount > dailyWithdrawalLimit) {
            throw new IllegalArgumentException("Daily withdrawal limit exceeded");
        }

        // All validations passed
        balance -= amount;
        totalWithdrawnToday += amount;
    }
}
```

**Example Files:** `BankAccount.java`, `Car.java`

---

## 5. Why Encapsulation Matters

### Benefits of Encapsulation

**1. DATA INTEGRITY**
- Ensures objects are always in valid state
- Prevents garbage data

**2. MAINTAINABILITY**
- Change internal implementation without breaking users
- Internal fields can be refactored freely

**3. FLEXIBILITY**
- Add logging, caching, validation transparently
- Computed properties (getArea(), getFullName())

**4. SECURITY**
- Sensitive data cannot be accessed directly
- Control who can read/write

**5. DEBUGGING**
- Set breakpoints in setters to catch invalid changes
- Log all modifications

### Real Example: Refactoring Internal Implementation

```java
// Version 1: Store temperature in Celsius
public class Thermostat {
    private double temperatureCelsius;

    public double getTemperature() {
        return temperatureCelsius;
    }

    public void setTemperature(double temp) {
        this.temperatureCelsius = temp;
    }
}

// Version 2: Change to store in Kelvin (internal change)
// BUT: Public API remains the same!
public class Thermostat {
    private double temperatureKelvin;  // Changed internal representation!

    // Public API unchanged - users don't need to know!
    public double getTemperature() {
        return temperatureKelvin - 273.15;  // Convert to Celsius
    }

    public void setTemperature(double tempCelsius) {
        this.temperatureKelvin = tempCelsius + 273.15;  // Convert
    }

    // New method using Kelvin directly
    public double getTemperatureKelvin() {
        return temperatureKelvin;
    }
}

// User code doesn't need to change at all!
Thermostat t = new Thermostat();
t.setTemperature(25.0);  // Still works! (25°C)
```

---

## 6. Encapsulation and Memory Model

### Memory Layout with Encapsulation

**Metaspace:**
- BankAccount.class
  - Field Metadata:
    - balance: double (private, 0x0002)
    - accountNumber: String (private, 0x0002)
  - Method Metadata:
    - deposit(double): void (public, 0x0001)
    - withdraw(double): void (public, 0x0001)
    - getBalance(): double (public, 0x0001)
    - setBalance(double): void (private, 0x0002)

**Heap:**
- BankAccount @0x1234
  - Object Header (12 bytes)
    - Mark Word (8 bytes)
    - Class Pointer (4 bytes) → BankAccount.class
  - **ENCAPSULATED DATA (Private):**
    - balance: 1000.50
    - accountNumber: "ACC-12345"

**Note:** Data is physically in Heap, but logically PROTECTED by access modifiers enforced at COMPILE TIME

**Stack (Thread):**
- main() Frame
  - account = 0x1234
  - amount = 500.0

- deposit() Frame (when called)
  - this = 0x1234 (Points to object in Heap)
  - amount = 500.0
  - Inside deposit():
    - `this.balance += amount;`
    - Accesses 0x1234.balance → OK - same class

**KEY POINTS:**
1. Private fields exist in Heap (physically accessible)
2. Access control enforced at COMPILE TIME by javac
3. At runtime, JVM doesn't check access (already verified)
4. 'this' inside methods points to object in Heap
5. Methods can access private fields via 'this'

---

# Day 3: Inheritance

## 7. What is Inheritance?

**Inheritance** is a mechanism where a new class (child/subclass) derives properties and behaviors from an existing class (parent/superclass).

### The Core Concept

**"IS-A" Relationship**

- Dog IS-A Animal
- Car IS-A Vehicle
- Student IS-A Person

**Benefits:**
- Code Reuse
- Polymorphism
- Hierarchical Classification

### Real-World Hierarchy

```
Animal (Parent/Superclass)
├── name
├── age
├── eat()
└── sleep()
    │
    ├─── Dog
    │    ├── breed
    │    ├── bark()
    │    └── wagTail()
    │
    ├─── Cat
    │    ├── color
    │    ├── meow()
    │    └── purr()
    │
    └─── Bird
         ├── wingspan
         ├── fly()
         └── chirp()
```

**Each child:**
- Inherits all parent members (name, age, eat(), sleep())
- Adds its own unique members
- Can override parent methods

**Example Files:** `Animal.java`, `Dog.java`, `Cat.java`, `Bird.java`

---

## 8. The extends Keyword

The `extends` keyword creates an inheritance relationship between classes.

### Basic Syntax

```java
// Parent class (Superclass)
public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Child class (Subclass)
public class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor
        this.breed = breed;
    }

    // Child's own method
    public void bark() {
        System.out.println(name + " says: Woof!");
    }

    // Override parent method
    @Override
    public void eat() {
        System.out.println(name + " the dog is eating dog food");
    }
}
```

### What extends Does

**1. Class Loading:**
- When Dog.class is loaded, JVM also loads Animal.class
- Dog.class contains reference to its parent

**2. Object Creation:**
- Dog object contains ALL Animal fields + Dog fields
- Memory allocated for entire hierarchy

**3. Method Resolution:**
- Dog's vtable extends Animal's vtable
- Overridden methods point to Dog's implementation
- Inherited methods point to Animal's implementation

**4. Access Control:**
- Dog can access Animal's protected and public members
- Dog cannot access Animal's private members directly

---

## 9. Parent and Child Classes

### Understanding IS-A Relationship

```java
// Create objects
Car car = new Car("Toyota", "Camry", 2023, 28000, 4, "Gasoline");
Motorcycle bike = new Motorcycle("Harley", "Sportster", 2023, 15000, "Cruiser", false);

// IS-A relationship:
Car car = ...;              // car IS-A Car
Vehicle v1 = car;           // car IS-A Vehicle
Object o1 = car;            // car IS-A Object

Motorcycle bike = ...;      // bike IS-A Motorcycle
Vehicle v2 = bike;          // bike IS-A Vehicle
Object o2 = bike;           // bike IS-A Object

// Type checking:
System.out.println(car instanceof Car);        // true
System.out.println(car instanceof Vehicle);    // true
System.out.println(car instanceof Object);     // true
System.out.println(car instanceof Motorcycle); // false

// Polymorphism:
Vehicle[] vehicles = new Vehicle[2];
vehicles[0] = car;   //  OK - Car IS-A Vehicle
vehicles[1] = bike;  //  OK - Motorcycle IS-A Vehicle

for (Vehicle v : vehicles) {
    v.start();  // Calls appropriate overridden method
}
```

**Example Files:** `Vehicle.java`, `Car.java`, `Motorcycle.java`

---

## 10. The super Keyword

The `super` keyword has three main uses:

### 1. Calling Parent Constructor

```java
public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Animal constructor");
    }
}

public class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        // MUST be first statement!
        super(name, age);  // Calls Animal(String, int)

        this.breed = breed;
        System.out.println("Dog constructor");
    }
}

// Output when creating: new Dog("Buddy", 3, "Labrador");
// Animal constructor
// Dog constructor
```

### 2. Calling Parent Method

```java
public class Animal {
    public void makeSound() {
        System.out.println("Some animal sound");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    @Override
    public void eat() {
        super.eat();  // Call parent's eat()
        System.out.println("Dog is eating bones");
    }

    public void test() {
        makeSound();        // Calls Dog's makeSound() → "Woof!"
        super.makeSound();  // Calls Animal's makeSound() → "Some animal sound"
    }
}
```

### 3. Accessing Parent Field (When Hidden)

```java
public class Parent {
    protected int x = 10;
}

public class Child extends Parent {
    private int x = 20;  // Hides parent's x

    public void display() {
        System.out.println(x);        // 20 (Child's x)
        System.out.println(this.x);   // 20 (Child's x)
        System.out.println(super.x);  // 10 (Parent's x)
    }
}
```

### super vs this

| super | this |
|-------|------|
| References parent class | References current class |
| Accesses parent constructor | Accesses current constructor |
| Accesses parent methods | Accesses current methods |
| Accesses parent fields | Accesses current fields |
| super() must be first | this() must be first |
| Cannot use both in same constructor | Cannot use both in same constructor |

---

## 11. Constructor Chaining

Constructor chaining is the process of calling constructors in a hierarchy, from child to parent.

### Implicit Constructor Chaining

```java
public class A {
    public A() {
        System.out.println("A constructor");
    }
}

public class B extends A {
    public B() {
        // implicit: super();
        System.out.println("B constructor");
    }
}

public class C extends B {
    public C() {
        // implicit: super();
        System.out.println("C constructor");
    }
}

// Create object:
C c = new C();

// Output:
// A constructor
// B constructor
// C constructor
```

### Explicit Constructor Chaining

Shows how constructors call each other in a specific order:

```java
public class Vehicle {
    protected String brand;
    protected String model;

    public Vehicle() {
        this("Unknown", "Unknown");
        System.out.println("Vehicle() called");
    }

    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Vehicle(String, String) called");
    }
}

public class Car extends Vehicle {
    private int numDoors;

    public Car() {
        this("Unknown", "Unknown", 4);
        System.out.println("Car() called");
    }

    public Car(String brand, String model, int numDoors) {
        super(brand, model);
        this.numDoors = numDoors;
        System.out.println("Car(String, String, int) called");
    }
}
```

### Constructor Chaining Flow

```
new Car()
    ↓
Car() constructor
    ↓
Calls: this("Unknown", "Unknown", 4)
    ↓
Car(String, String, int) constructor
    ↓
Calls: super("Unknown", "Unknown")
    ↓
Vehicle(String, String) constructor
    ↓
Initializes Vehicle fields
    ↓
Returns to Car constructor
    ↓
Initializes Car fields
    ↓
Returns to Car() constructor
    ↓
Object fully constructed!
```

**Example File:** `ElectricCar.java` (demonstrates 3-level chaining)

---

## 12. Types of Inheritance

Java supports several types of inheritance:

### 1. Single Inheritance

One class extends one parent class

```
Animal (Parent)
    ↓
   extends
    ↓
  Dog (Child)
```

**Example:** `Animal` → `Dog`

### 2. Multilevel Inheritance

Chain of inheritance: A → B → C

```
Animal (Grandparent)
    ↓
   extends
    ↓
  Dog (Parent)
    ↓
   extends
    ↓
Labrador (Child)
```

**Example:** `Vehicle` → `Car` → `ElectricCar`

### 3. Hierarchical Inheritance

Multiple classes extend the same parent

```
    Animal (Parent)
        ↓
    ┌───┼───┐
    ↓   ↓   ↓
  Dog  Cat  Bird
```

**Example:** `Animal` is extended by `Dog`, `Cat`, and `Bird`

### 4. Multiple Inheritance (NOT SUPPORTED in Java)

Java does NOT support multiple inheritance with classes

```
Animal        Machine
    ↓             ↓
    └─────┬───────┘
          ↓
        Robot
    NOT ALLOWED in Java!
```

**Reason:** The Diamond Problem
**Solution:** Use interfaces instead of multiple class inheritance

---

## 13. protected Access Modifier in Inheritance

### Understanding protected in Inheritance Context

```java
// File: com/example/Person.java
package com.example;

public class Person {
    private String ssn;           // Only Person class
    protected String name;        // Person + subclasses + same package
    String address;               // Person + same package (default)
    public int age;               // Everyone

    protected void displayInfo() {
        System.out.println("Name: " + name);
    }
}

// File: com/other/Student.java (DIFFERENT package, SUBCLASS)
package com.other;
import com.example.Person;

public class Student extends Person {
    public Student(String name, int age) {
        // Accessing inherited protected members
        this.name = name;              //  OK: inherited protected field
        this.age = age;                //  OK: inherited public field
        // this.address = "...";       //  Error: default, different package
    }

    public void display() {
        displayInfo();                 //  OK: inherited protected method
    }

    public void testOtherPerson() {
        Person other = new Person();
        // other.name = "Sara";        //  Error: protected, not inherited
        other.age = 25;                //  OK: public
    }
}
```

### protected Access Rules Summary

**Can access protected member when:**
- ✓ Same class
- ✓ Same package (any class)
- ✓ Subclass (even in different package)
  - BUT only through inherited reference (this)

**Cannot access protected member when:**
- ✗ Different package AND not a subclass
- ✗ Through external object reference (even in subclass)

---

## 14. Inheritance and Memory Model

### Memory Layout with Inheritance

**INHERITANCE MEMORY MODEL**

**Metaspace:**
- Animal.class
  - Superclass: Object
  - Fields: name (String), age (int)
  - Methods: eat(), sleep()
  - vtable: eat() → 0x1000, sleep() → 0x2000

  ↓ Extends

- Dog.class
  - Superclass: Animal
  - Fields (own): breed (String)
  - Methods (own): bark()
  - vtable: eat() → 0x4000 (overridden), sleep() → 0x2000, bark() → 0x5000 (new)

**Heap:**
- Dog object @0x7000
  - Object Header (12 bytes)
    - Class Pointer → Dog.class
  - **ANIMAL PART (inherited fields):**
    - name: "Buddy"
    - age: 3
  - **DOG PART (own fields):**
    - breed: "Labrador"

**Note:** Single object contains ALL fields from entire hierarchy!

**Stack:**
- dog: Dog = 0x7000
- animal: Animal = 0x7000 (Same obj!)

**KEY POINTS:**
1. Single object in Heap contains fields from ENTIRE hierarchy
2. Each class has its own metadata in Metaspace
3. Object's Class Pointer always points to ACTUAL type (Dog)
4. Field layout: Object Header → Animal fields → Dog fields

### Multilevel Inheritance Memory

```java
class Animal { protected String name; }
class Dog extends Animal { private String breed; }
class Labrador extends Dog { private String color; }

Labrador lab = new Labrador();
```

**Memory:**

**Heap:**
- Labrador object @0x8000
  - Object Header → Labrador.class
  - **ANIMAL PART:** name
  - **DOG PART:** breed
  - **LABRADOR PART:** color

**Note:** Object contains ALL fields from entire hierarchy!

### Virtual Method Dispatch

```java
Animal animal = new Dog("Buddy", 3, "Labrador");
animal.eat();  // Which eat()?
```

**Resolution:**
1. animal variable points to object @0x7000
2. Object has Class Pointer → Dog.class
3. Look up eat() in Dog's vtable → address 0x4000
4. Call Dog.eat() (not Animal.eat())
5. This is DYNAMIC DISPATCH (runtime polymorphism)

---

## 15. Master Diagrams Collection

### Complete Inheritance Hierarchy

**COMPLETE INHERITANCE MEMORY MODEL**

**Class Hierarchy in Metaspace:**

```
Object.class
├── hashCode()
├── equals()
└── toString()
    ↓ extends
Animal.class
├── name: String
├── age: int
├── eat()
└── makeSound()
    ↓ extends
Dog.class
├── breed: String
├── makeSound() (Overridden)
└── bark()
```

**Object in Heap:**
- Dog object @0xA000
  - Object Header → Dog.class
  - **INHERITED FROM ANIMAL:**
    - name: "Buddy"
    - age: 3
  - **DOG'S OWN FIELDS:**
    - breed: "Labrador"

### Polymorphism in Action

```java
Animal animal = new Dog("Buddy", 3, "Labrador");
animal.makeSound();  // Outputs: "Buddy says: Woof! Woof!"
```

**WHY Dog.makeSound() and not Animal.makeSound()?**
- ✓ Compile time: animal has type Animal
- ✓ Runtime: actual object is Dog
- ✓ JVM uses ACTUAL object's class (via Class Pointer)
- ✓ This is called DYNAMIC METHOD DISPATCH

---

## 16. Best Practices & Common Pitfalls

### Encapsulation Best Practices

- ✓ Always make fields private
- ✓ Provide public getters/setters only when needed
- ✓ Validate in setters
- ✓ Use defensive copying for mutable objects
- ✓ Make classes immutable when possible
- ✓ Don't expose internal collections directly
- ✓ Use meaningful names (getAge(), not getA())

### Inheritance Best Practices

- ✓ Use inheritance for IS-A relationships only
- ✓ Favor composition over inheritance when appropriate
- ✓ Make base class methods final if shouldn't be overridden
- ✓ Use @Override annotation always
- ✓ Call super() explicitly in constructors
- ✓ Don't call overridable methods in constructors
- ✓ Design for inheritance or prohibit it (final class)
- ✓ Document what can be overridden

### Common Pitfalls

#### 1. Breaking Encapsulation

```java
//  BAD: Exposing internal mutable object
public List<String> getCourses() {
    return courses;  // Direct reference!
}

//  GOOD: Defensive copy
public List<String> getCourses() {
    return new ArrayList<>(courses);
}
```

#### 2. Calling Overridable Methods in Constructor

```java
//  BAD: Dangerous!
public class Parent {
    public Parent() {
        init();  // Can be overridden - child not initialized yet!
    }
    public void init() { }
}

//  GOOD: Use final
public class Parent {
    public Parent() {
        init();
    }
    private final void init() { }  // Cannot be overridden
}
```

#### 3. Not Using @Override

```java
//  BAD: Typo won't be caught!
public void makesound() {  // Typo! Doesn't override
    System.out.println("Woof");
}

//  GOOD: Compiler catches error
@Override
public void makesound() {  // Compile error if not overriding
    System.out.println("Woof");
}
```

---

## Example Files Reference

### Encapsulation Examples:
- `Student.java` - Basic encapsulation with validation
- `BankAccount.java` - Business logic validation
- `Car.java` - Year and mileage validation
- `Circle.java` - Read-only computed properties
- `EncapsulationDemo.java` - Tests all encapsulation examples

### Inheritance Examples:
- `Animal.java` - Base class
- `Dog.java`, `Cat.java`, `Bird.java` - Single inheritance
- `Vehicle.java` - Base class for vehicle hierarchy
- `Car.java` (vehicle package) - Extends Vehicle
- `ElectricCar.java` - Multilevel inheritance (Vehicle → Car → ElectricCar)
- `Motorcycle.java` - Hierarchical inheritance
- `InheritanceDemo.java` - Tests all inheritance examples

---

## Conclusion

You now understand:

### Encapsulation:
- ✓ Data hiding with private fields
- ✓ All four access modifiers
- ✓ Getters/setters with validation
- ✓ Why encapsulation is critical
- ✓ Memory model with encapsulation

### Inheritance:
- ✓ IS-A relationships
- ✓ extends keyword
- ✓ super keyword (3 uses)
- ✓ Constructor chaining
- ✓ Types of inheritance
- ✓ protected in inheritance
- ✓ Memory model with inheritance
- ✓ Polymorphism basics

---

**Generated: 2025 Edition**
**Topics: Encapsulation & Inheritance**
**Level: Zero to Master**
