# 04 - Inheritance (الوراثة)

## What You'll Learn

- What is Inheritance?
- `extends` keyword
- `super` keyword
- Method Overriding (`@Override`)
- Types of Inheritance
- Diamond Problem
- Method Hiding vs Overriding
- Covariant Return Types
- `final` with Inheritance
- Object Class
- `instanceof` Operator
- Upcasting & Downcasting
- Composition vs Inheritance
- SOLID Principles (Liskov Substitution)
- Constructor Chain in Inheritance
- Best Practices

---

## 1. What is Inheritance?

**Inheritance** allows a class to inherit attributes and methods from another class. It's an **"IS-A"** relationship.

```
Person (Parent/Superclass)
   |-- Student (Child/Subclass)  → Student IS-A Person
   |-- Teacher (Child/Subclass)  → Teacher IS-A Person
```

### Benefits of Inheritance:
1. **Code Reusability** - لا تكرار للكود
2. **Extensibility** - سهولة إضافة features جديدة
3. **Polymorphism** - التعامل مع objects مختلفة بنفس الطريقة
4. **Maintainability** - تعديل واحد يأثر على كل الـ subclasses

---

## 2. The `extends` Keyword

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

---

## 3. The `super` Keyword

`super` is used to:

| Usage | Example | Description |
|-------|---------|-------------|
| Call parent constructor | `super(args)` | لازم يكون أول سطر |
| Call parent method | `super.methodName()` | لما تعمل override وعايز تنادي الأصلية |
| Access parent attribute | `super.attributeName` | لما يكون في attribute بنفس الاسم |

```java
public class Student extends Person {
    private String studentId;
    
    public Student(String name, int age, String studentId) {
        super(name, age);  // MUST be first line
        this.studentId = studentId;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method first
        System.out.println("Student ID: " + studentId);
    }
}
```

### super() vs this()

| Keyword | Purpose |
|---------|---------|
| `this()` | Calls another constructor in **same** class |
| `super()` | Calls constructor in **parent** class |

```java
public class Student extends Person {
    private String studentId;
    private double gpa;
    
    public Student(String name, int age, String studentId) {
        super(name, age);  // Call parent
        this.studentId = studentId;
        this.gpa = 0.0;
    }
    
    public Student(String name, int age, String studentId, double gpa) {
        this(name, age, studentId);  // Call sibling constructor
        this.gpa = gpa;
    }
}
```

---

## 4. Method Overriding

Child class provides its own implementation of a parent method.

### Rules for Overriding:

| Rule | Description |
|------|-------------|
| Same signature | نفس الاسم + نفس الـ parameters |
| Same or covariant return type | نفس الـ return type أو subtype منه |
| Same or less restrictive access | `protected` → `public` ✅ / `public` → `private` ❌ |
| Cannot override `final` methods | ❌ |
| Cannot override `static` methods | بيعمل hiding مش overriding |
| Cannot override `private` methods | مش visible أصلاً |

```java
public class Person {
    public void introduce() {
        System.out.println("I am " + name);
    }
}

public class Student extends Person {
    @Override  // Annotation - good practice (يكشف الأخطاء)
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

### Why use @Override annotation?

```java
// بدون @Override - لو غلطت في الاسم، مش هيقولك
public void introudce() { }  // Typo! ده method جديد مش override

// مع @Override - هيطلع error لو مفيش method بالاسم ده في الـ parent
@Override
public void introudce() { }  // ❌ Compile error: method does not override
```

---

## 5. Types of Inheritance

### 5.1 Single Inheritance ✅
```
    A
    ↓
    B
```
```java
class Animal { }
class Dog extends Animal { }
```

### 5.2 Multilevel Inheritance ✅
```
    A
    ↓
    B
    ↓
    C
```
```java
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }
```

### 5.3 Hierarchical Inheritance ✅
```
       A
      / \
     B   C
```
```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
```

### 5.4 Multiple Inheritance ❌ (NOT supported for classes)
```
    A     B
     \   /
       C
```
```java
// ❌ NOT ALLOWED in Java
class C extends A, B { }  // Compile Error!

// ✅ Use interfaces instead
interface A { }
interface B { }
class C implements A, B { }
```

### 5.5 Hybrid Inheritance ❌ (NOT supported)
Combination of multiple types that includes Multiple Inheritance.

---

## 6. The Diamond Problem 💎

### Why Java doesn't support Multiple Inheritance for classes:

```
         Animal
        /      \
     Dog        Cat
        \      /
         Hybrid
```

```java
class Animal {
    void speak() { System.out.println("Animal speaks"); }
}

class Dog extends Animal {
    @Override
    void speak() { System.out.println("Bark!"); }
}

class Cat extends Animal {
    @Override
    void speak() { System.out.println("Meow!"); }
}

// ❌ If this was allowed:
class Hybrid extends Dog, Cat { }

Hybrid h = new Hybrid();
h.speak();  // ??? Bark or Meow? AMBIGUOUS!
```

### Solution: Use Interfaces

```java
interface Swimmer {
    default void swim() { System.out.println("Swimming"); }
}

interface Flyer {
    default void fly() { System.out.println("Flying"); }
}

class Duck implements Swimmer, Flyer {
    // If both interfaces have same default method:
    @Override
    public void move() {
        Swimmer.super.move();  // Explicitly choose
        // or provide own implementation
    }
}
```

---

## 7. Method Hiding vs Method Overriding

### Key Difference:

| Aspect | Method Overriding | Method Hiding |
|--------|-------------------|---------------|
| Method type | Instance methods | Static methods |
| Binding | Runtime (Dynamic) | Compile-time (Static) |
| Behavior | Based on **object** type | Based on **reference** type |

```java
class Parent {
    // Instance method - can be overridden
    void instanceMethod() {
        System.out.println("Parent instance");
    }
    
    // Static method - can only be hidden
    static void staticMethod() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    @Override
    void instanceMethod() {
        System.out.println("Child instance");
    }
    
    // This is HIDING, not overriding (no @Override allowed)
    static void staticMethod() {
        System.out.println("Child static");
    }
}

// Test:
Parent p = new Child();

p.instanceMethod();  // "Child instance" ← Runtime polymorphism (object type)
p.staticMethod();    // "Parent static"  ← Compile-time binding (reference type)

Child c = new Child();
c.staticMethod();    // "Child static"
```

### Visual Explanation:

```
Parent p = new Child();
       ↑           ↑
  Reference     Object
    Type         Type

Instance methods → look at Object type (Child)
Static methods   → look at Reference type (Parent)
```

---

## 8. Covariant Return Types

Since Java 5, overriding method can return a **subtype** of the original return type.

```java
class Animal {
    Animal create() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    Dog create() {  // ✅ Dog is subtype of Animal
        return new Dog();
    }
}

// Usage:
Dog d = new Dog();
Dog newDog = d.create();  // No casting needed!
```

### More Examples:

```java
class Factory {
    Object produce() { return new Object(); }
}

class CarFactory extends Factory {
    @Override
    Car produce() { return new Car(); }  // ✅ Car extends Object
}

class NumberFactory {
    Number getNumber() { return 42; }
}

class IntegerFactory extends NumberFactory {
    @Override
    Integer getNumber() { return 42; }  // ✅ Integer extends Number
}
```

---

## 9. `final` with Inheritance

### 9.1 Final Class - Cannot be extended

```java
final class Constants {
    public static final double PI = 3.14159;
}

// ❌ Compile Error
class MyConstants extends Constants { }
```

**Real Examples:**
- `String` class is final
- `Integer`, `Double`, etc. are final
- Security-sensitive classes

### 9.2 Final Method - Cannot be overridden

```java
class BankAccount {
    private double balance;
    
    // Critical method - prevent tampering
    public final void processTransaction(double amount) {
        // Secure implementation
        this.balance += amount;
        logTransaction(amount);
    }
    
    // Can be customized
    protected void logTransaction(double amount) {
        System.out.println("Transaction: " + amount);
    }
}

class SavingsAccount extends BankAccount {
    // ❌ Cannot override
    // public void processTransaction(double amount) { }
    
    // ✅ Can override
    @Override
    protected void logTransaction(double amount) {
        System.out.println("Savings Transaction: " + amount);
    }
}
```

### 9.3 Final Variable - Cannot be reassigned

```java
class Circle {
    final double PI = 3.14159;  // Must be initialized
    
    void test() {
        // PI = 3.14;  // ❌ Cannot reassign
    }
}
```

---

## 10. The Object Class - Root of All Classes

Every class in Java implicitly extends `Object`.

```java
class Student { }

// Is actually:
class Student extends Object { }
```

### Methods inherited from Object:

| Method | Description | Should Override? |
|--------|-------------|------------------|
| `toString()` | String representation | ✅ Yes |
| `equals(Object obj)` | Equality check | ✅ Yes |
| `hashCode()` | Hash code for collections | ✅ Yes (with equals) |
| `getClass()` | Runtime class info | ❌ No (final) |
| `clone()` | Create copy | ⚠️ Sometimes |
| `finalize()` | Before garbage collection | ❌ Deprecated |
| `wait()`, `notify()`, `notifyAll()` | Threading | ❌ Rarely |

### Overriding toString():

```java
class Student {
    private String name;
    private int age;
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

Student s = new Student("Ahmed", 20);
System.out.println(s);  // Student{name='Ahmed', age=20}
```

### Overriding equals() and hashCode():

```java
class Student {
    private String id;
    private String name;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

---

## 11. `instanceof` Operator

Check if an object is an instance of a specific class or interface.

```java
Person p = new Student("Ahmed", 20, "S001");

// Basic usage
if (p instanceof Student) {
    Student s = (Student) p;  // Safe to cast
    System.out.println(s.getStudentId());
}

// Check interface
if (p instanceof Serializable) {
    // p implements Serializable
}

// null check
Person nullPerson = null;
System.out.println(nullPerson instanceof Person);  // false (not NullPointerException)
```

### Pattern Matching (Java 16+):

```java
// Old way
if (obj instanceof Student) {
    Student s = (Student) obj;
    s.study();
}

// New way - Pattern matching
if (obj instanceof Student s) {
    s.study();  // s is automatically cast and available
}

// With logical operators
if (obj instanceof Student s && s.getGpa() > 3.5) {
    System.out.println("Honor student: " + s.getName());
}
```

### instanceof with Inheritance Hierarchy:

```java
class Animal { }
class Dog extends Animal { }
class Labrador extends Dog { }

Labrador lab = new Labrador();

System.out.println(lab instanceof Labrador);  // true
System.out.println(lab instanceof Dog);       // true
System.out.println(lab instanceof Animal);    // true
System.out.println(lab instanceof Object);    // true
```

---

## 12. Upcasting & Downcasting

### Upcasting (Implicit - Always Safe) ✅

Converting subclass reference to superclass reference.

```java
Student student = new Student("Ahmed", 20, "S001");
Person person = student;  // ✅ Automatic (implicit)

// What's accessible?
person.getName();        // ✅ Person method
person.displayInfo();    // ✅ Person method (may be overridden)
// person.getStudentId(); // ❌ Not visible - Person reference
```

### Downcasting (Explicit - Dangerous) ⚠️

Converting superclass reference to subclass reference.

```java
Person person = new Student("Ahmed", 20, "S001");  // Actually a Student

// Must be explicit
Student student = (Student) person;  // ✅ Works - person IS a Student
student.getStudentId();  // ✅ Now accessible

// DANGER!
Person person2 = new Person("Ali", 30);  // Actually a Person
Student student2 = (Student) person2;    // ❌ ClassCastException at runtime!
```

### Safe Downcasting Pattern:

```java
public void process(Person person) {
    if (person instanceof Student) {
        Student student = (Student) person;
        student.enrollInCourse("Java 101");
    } else if (person instanceof Teacher) {
        Teacher teacher = (Teacher) person;
        teacher.assignGrade();
    }
}

// Java 16+ Pattern Matching
public void processModern(Person person) {
    if (person instanceof Student student) {
        student.enrollInCourse("Java 101");
    } else if (person instanceof Teacher teacher) {
        teacher.assignGrade();
    }
}
```

### Why Upcasting is Useful:

```java
// Polymorphism - treat all as Person
List<Person> people = new ArrayList<>();
people.add(new Student("Ahmed", 20, "S001"));
people.add(new Teacher("Dr. Sara", 35, "CS101"));
people.add(new Staff("Ali", 28, "Admin"));

for (Person p : people) {
    p.displayInfo();  // Each calls its own overridden version
}
```

---

## 13. Composition vs Inheritance 🔥

### Inheritance: "IS-A" Relationship

```java
class Car extends Vehicle { }  // Car IS-A Vehicle
```

### Composition: "HAS-A" Relationship

```java
class Car {
    private Engine engine;      // Car HAS-A Engine
    private Wheel[] wheels;     // Car HAS-A Wheels
    private SteeringWheel steering;
    
    public Car() {
        this.engine = new Engine();
        this.wheels = new Wheel[4];
        this.steering = new SteeringWheel();
    }
    
    public void start() {
        engine.ignite();
    }
}
```

### When to Use Which?

| Use Inheritance When | Use Composition When |
|---------------------|---------------------|
| True "IS-A" relationship | "HAS-A" relationship |
| Want polymorphism | Need flexibility |
| Subclass is a specialization | Component can change at runtime |
| Behavior is fixed | Behavior varies |

### The Problem with Inheritance:

```java
// ❌ Fragile Base Class Problem
class Stack extends ArrayList {
    public void push(Object item) {
        add(item);
    }
    
    public Object pop() {
        return remove(size() - 1);
    }
}

Stack stack = new Stack();
stack.push("A");
stack.add("B");      // ⚠️ Exposes ArrayList method - breaks encapsulation!
stack.add(0, "C");   // ⚠️ Can insert anywhere!
```

### Better with Composition:

```java
// ✅ Composition - hide implementation details
class Stack<T> {
    private List<T> items = new ArrayList<>();  // HAS-A
    
    public void push(T item) {
        items.add(item);
    }
    
    public T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // Only expose what's needed - no add(), remove(index), etc.
}
```

### Favor Composition Over Inheritance

> "Prefer composition over inheritance" - Effective Java

```java
// ❌ Inheritance - tightly coupled
class LoggingList extends ArrayList<String> {
    @Override
    public boolean add(String s) {
        log("Adding: " + s);
        return super.add(s);
    }
}

// ✅ Composition - loosely coupled, more flexible
class LoggingList<T> {
    private List<T> list;  // Can be ArrayList, LinkedList, etc.
    private Logger logger;
    
    public LoggingList(List<T> list, Logger logger) {
        this.list = list;
        this.logger = logger;
    }
    
    public void add(T item) {
        logger.log("Adding: " + item);
        list.add(item);
    }
}
```

---

## 14. SOLID: Liskov Substitution Principle (LSP)

> "Objects of a superclass should be replaceable with objects of its subclasses without breaking the application."

### ❌ Violation Example:

```java
class Rectangle {
    protected int width;
    protected int height;
    
    public void setWidth(int w) { width = w; }
    public void setHeight(int h) { height = h; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        width = w;
        height = w;  // ⚠️ Unexpected side effect!
    }
    
    @Override
    public void setHeight(int h) {
        width = h;   // ⚠️ Unexpected side effect!
        height = h;
    }
}

// This breaks!
void testRectangle(Rectangle r) {
    r.setWidth(5);
    r.setHeight(4);
    assert r.getArea() == 20;  // ❌ Fails for Square! (returns 16)
}
```

### ✅ Correct Design:

```java
interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    private final int width;
    private final int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public int getArea() { return width * height; }
}

class Square implements Shape {
    private final int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    @Override
    public int getArea() { return side * side; }
}
```

### LSP Rules:

1. **Preconditions cannot be strengthened** in subclass
2. **Postconditions cannot be weakened** in subclass
3. **Invariants must be preserved** in subclass
4. **No new exceptions** (except subtypes of parent's exceptions)

---

## 15. Constructor Chain in Inheritance

When you create a subclass object, constructors are called **from top to bottom**.

```java
class Animal {
    Animal() {
        System.out.println("1. Animal constructor");
    }
}

class Mammal extends Animal {
    Mammal() {
        // super(); ← Implicit call to Animal()
        System.out.println("2. Mammal constructor");
    }
}

class Dog extends Mammal {
    Dog() {
        // super(); ← Implicit call to Mammal()
        System.out.println("3. Dog constructor");
    }
}

// Test:
Dog d = new Dog();

// Output:
// 1. Animal constructor
// 2. Mammal constructor
// 3. Dog constructor
```

### With Parameters:

```java
class Person {
    protected String name;
    
    Person(String name) {
        System.out.println("Person(" + name + ")");
        this.name = name;
    }
}

class Student extends Person {
    private String id;
    
    Student(String name, String id) {
        super(name);  // MUST be first line - no implicit super() here!
        System.out.println("Student(" + id + ")");
        this.id = id;
    }
}

Student s = new Student("Ahmed", "S001");
// Output:
// Person(Ahmed)
// Student(S001)
```

### Why Parent Constructor First?

```java
class Parent {
    int value = 10;
    
    Parent() {
        System.out.println("Parent: value = " + value);
        init();  // ⚠️ Dangerous - child not initialized yet!
    }
    
    void init() {
        System.out.println("Parent init");
    }
}

class Child extends Parent {
    int childValue = 20;
    
    Child() {
        System.out.println("Child: childValue = " + childValue);
    }
    
    @Override
    void init() {
        System.out.println("Child init: childValue = " + childValue);  // ⚠️ Still 0!
    }
}

new Child();
// Output:
// Parent: value = 10
// Child init: childValue = 0  ← NOT 20! Child fields not initialized yet!
// Child: childValue = 20
```

**Rule:** Never call overridable methods in constructor!

---

## 16. Access Modifiers in Inheritance

| Modifier | Same Class | Same Package | Subclass | Other |
|----------|------------|--------------|----------|-------|
| `private` | ✅ | ❌ | ❌ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

```java
class Parent {
    private int a;      // Only in Parent
    int b;              // Parent + same package
    protected int c;    // Parent + same package + subclasses
    public int d;       // Everyone
}

class Child extends Parent {
    void test() {
        // System.out.println(a);  // ❌ private
        System.out.println(b);     // ✅ if same package
        System.out.println(c);     // ✅ protected
        System.out.println(d);     // ✅ public
    }
}
```

---

## 17. Sealed Classes (Java 17+)

Control which classes can extend your class.

```java
// Only these classes can extend Shape
public sealed class Shape permits Circle, Rectangle, Triangle {
    // ...
}

// Must be final, sealed, or non-sealed
public final class Circle extends Shape { }

public sealed class Rectangle extends Shape permits Square { }

public non-sealed class Triangle extends Shape { }
// Anyone can extend Triangle
```

---

## 18. Best Practices Summary

### ✅ DO:

1. **Use inheritance for "IS-A"** relationships
2. **Favor composition** over inheritance when possible
3. **Use @Override** annotation always
4. **Make classes final** if not designed for inheritance
5. **Call super()** explicitly for clarity
6. **Override toString(), equals(), hashCode()** together
7. **Use protected** for members subclasses need
8. **Document inheritance behavior** in parent class

### ❌ DON'T:

1. **Don't use inheritance for code reuse only** - use composition
2. **Don't call overridable methods** in constructors
3. **Don't break LSP** - subclass should be substitutable
4. **Don't expose implementation** through inheritance
5. **Don't create deep hierarchies** - prefer shallow (max 2-3 levels)
6. **Don't override equals without hashCode**

---

## Key Points Summary

| Concept | Description |
|---------|-------------|
| `extends` | Inherit from a class |
| `super` | Reference to parent class |
| `@Override` | Annotation for overridden methods |
| `final class` | Cannot be extended |
| `final method` | Cannot be overridden |
| Upcasting | Subclass → Superclass (automatic) |
| Downcasting | Superclass → Subclass (manual, dangerous) |
| `instanceof` | Check object type at runtime |
| Diamond Problem | Why multiple inheritance isn't allowed |
| Method Hiding | Static methods in subclass |
| Covariant Return | Return subtype in override |
| LSP | Subclass must be substitutable |

---

## Project Task

Create a complete hierarchy:

```
Person (abstract)
├── Student
│   ├── UndergraduateStudent
│   └── GraduateStudent
├── Teacher
│   ├── Professor
│   └── Assistant
└── Staff
    ├── AdminStaff
    └── TechnicalStaff
```

Implement:
1. Proper use of `super` and constructors
2. Method overriding with `@Override`
3. `toString()`, `equals()`, `hashCode()`
4. `instanceof` checks
5. Demonstrate upcasting/downcasting

---

## Exercises

1. ✅ Create `Person` class with common attributes
2. ✅ Make `Student` extend `Person`
3. ✅ Make `Teacher` extend `Person`
4. ✅ Override `displayInfo()` in both subclasses
5. ✅ Override `introduce()` in both subclasses
6. ⬜ Create a `Staff` class that extends `Person`
7. ⬜ Implement `toString()`, `equals()`, `hashCode()` in all classes
8. ⬜ Create method that uses `instanceof` for type-specific behavior
9. ⬜ Demonstrate diamond problem solution with interfaces
10. ⬜ Refactor one inheritance to composition

---

## Next Lesson

[05 - Polymorphism](../05-polymorphism) - Learn how to treat different objects uniformly
