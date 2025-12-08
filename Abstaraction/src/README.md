# Abstraction in Java - From Zero to Master Level

## Table of Contents
1. [What is Abstraction?](#what-is-abstraction)
2. [Why Do We Need Abstraction?](#why-do-we-need-abstraction)
3. [Abstract Classes](#abstract-classes)
4. [Abstract Methods](#abstract-methods)
5. [Interfaces](#interfaces)
6. [Abstract Class vs Interface](#abstract-class-vs-interface)
7. [Multiple Interface Implementation](#multiple-interface-implementation)
8. [Default and Static Methods in Interfaces (Java 8+)](#default-and-static-methods-in-interfaces-java-8)
9. [Advanced Concepts](#advanced-concepts)
10. [Best Practices](#best-practices)

---

## What is Abstraction?

**Abstraction** is one of the four fundamental principles of Object-Oriented Programming (OOP), alongside Encapsulation, Inheritance, and Polymorphism.

### Definition
Abstraction is the process of **hiding implementation details** and **showing only the essential features** of an object to the user. It focuses on **WHAT** an object does rather than **HOW** it does it.

### Real-World Analogy
Think of a **TV remote control**:
- You know WHAT it does: change channels, adjust volume, turn on/off
- You DON'T need to know HOW it works internally: circuit boards, infrared signals, etc.

Another example is a **car**:
- You know WHAT to do: press accelerator to go faster, brake to slow down
- You DON'T need to know HOW the engine works internally

### In Programming
Abstraction allows you to:
- Create a **blueprint** or **template** for objects
- Define **what methods** an object must have without specifying how they work
- Hide complex implementation details from the user
- Focus on the **interface** rather than the **implementation**

---

## Why Do We Need Abstraction?

### 1. Reduces Complexity
By hiding unnecessary details, abstraction makes the system easier to understand and use.

### 2. Enhances Code Reusability
Abstract classes and interfaces can be reused across different parts of your application.

### 3. Improves Maintainability
Changes to implementation details don't affect code that uses the abstraction.

### 4. Provides a Clear Contract
Abstraction defines a clear contract that all implementations must follow.

### 5. Supports Polymorphism
Abstraction enables polymorphic behavior, allowing different implementations to be used interchangeably.

---

## Abstract Classes

An **abstract class** is a class that **cannot be instantiated** (you cannot create objects of it directly). It serves as a **blueprint** for other classes.

### Key Characteristics

1. **Declared with `abstract` keyword**
2. **Cannot be instantiated directly** (no `new AbstractClass()`)
3. **Can have both abstract and concrete methods**
4. **Can have constructors** (called when subclass is instantiated)
5. **Can have instance variables** (fields)
6. **Can have static methods and variables**
7. **Supports single inheritance** (a class can extend only one abstract class)

### Syntax
```java
abstract class ClassName {
    // Abstract methods (no body)
    abstract returnType methodName();

    // Concrete methods (with body)
    returnType concreteMethod() {
        // implementation
    }

    // Fields
    private int field;

    // Constructor
    public ClassName() {
        // initialization
    }
}
```

### Example
```java
abstract class Animal {
    private String name;

    // Constructor
    public Animal(String name) {
        this.name = name;
    }

    // Abstract method - must be implemented by subclasses
    abstract void makeSound();

    // Concrete method - inherited by all subclasses
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    // Must implement abstract method
    @Override
    void makeSound() {
        System.out.println(getName() + " says: Woof! Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(getName() + " says: Meow! Meow!");
    }
}

// Usage
Dog dog = new Dog("Buddy");
dog.makeSound();  // Output: Buddy says: Woof! Woof!
dog.sleep();      // Output: Buddy is sleeping...

Cat cat = new Cat("Whiskers");
cat.makeSound();  // Output: Whiskers says: Meow! Meow!
cat.sleep();      // Output: Whiskers is sleeping...

// Animal animal = new Animal("Generic"); // ERROR! Cannot instantiate abstract class
```

---

## Abstract Methods

An **abstract method** is a method that is **declared without an implementation** (no body). It must be implemented by the first concrete (non-abstract) subclass.

### Key Characteristics

1. **Declared with `abstract` keyword**
2. **Has no body** (ends with semicolon)
3. **Must be overridden** by concrete subclasses
4. **Can only exist in abstract classes or interfaces**
5. **Cannot be private** (must be accessible to subclasses)
6. **Cannot be final** (must be overridable)
7. **Cannot be static** (belongs to instance, not class)

### Syntax
```java
abstract returnType methodName(parameters);
```

### Example
```java
abstract class Vehicle {
    // Abstract methods - no implementation
    abstract void start();
    abstract void stop();
    abstract int getMaxSpeed();

    // Concrete method
    public void displayInfo() {
        System.out.println("This is a vehicle");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car: Turn the key to start the engine");
    }

    @Override
    void stop() {
        System.out.println("Car: Press the brake pedal");
    }

    @Override
    int getMaxSpeed() {
        return 200; // km/h
    }
}

class Bicycle extends Vehicle {
    @Override
    void start() {
        System.out.println("Bicycle: Start pedaling");
    }

    @Override
    void stop() {
        System.out.println("Bicycle: Use hand brakes");
    }

    @Override
    int getMaxSpeed() {
        return 40; // km/h
    }
}
```

---

## Interfaces

An **interface** is a completely abstract reference type that contains only **abstract methods** (until Java 8). It represents a **contract** that classes must fulfill.

### Key Characteristics

1. **Declared with `interface` keyword**
2. **All methods are public and abstract by default** (before Java 8)
3. **All variables are public, static, and final by default** (constants)
4. **Cannot be instantiated**
5. **Cannot have constructors**
6. **Cannot have instance variables** (only constants)
7. **Supports multiple inheritance** (a class can implement multiple interfaces)
8. **Since Java 8**: Can have default and static methods
9. **Since Java 9**: Can have private methods

### Syntax
```java
interface InterfaceName {
    // Constant (public static final by default)
    int CONSTANT = 100;

    // Abstract method (public abstract by default)
    void method1();

    // You can explicitly write modifiers, but they're redundant
    public abstract void method2();
}
```

### Example
```java
interface Flyable {
    int MAX_ALTITUDE = 50000; // public static final by default

    void fly();      // public abstract by default
    void land();     // public abstract by default
}

class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Airplane is flying using jet engines");
    }

    @Override
    public void land() {
        System.out.println("Airplane is landing on runway");
    }
}

class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird is flying by flapping wings");
    }

    @Override
    public void land() {
        System.out.println("Bird is landing on a tree branch");
    }
}

// Usage
Flyable airplane = new Airplane();
airplane.fly();   // Output: Airplane is flying using jet engines

Flyable bird = new Bird();
bird.fly();       // Output: Bird is flying by flapping wings

System.out.println("Max altitude: " + Flyable.MAX_ALTITUDE);
```

### Functional Interfaces (Java 8+)
An interface with exactly **one abstract method** is called a **functional interface**. It can be used with lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));      // Output: 8
System.out.println(multiply.calculate(5, 3)); // Output: 15
```

---

## Abstract Class vs Interface

This is one of the most important concepts to master. Here's a comprehensive comparison:

### When to Use Abstract Class

Use an **abstract class** when:

1. **You want to share code among related classes**
   - Example: All shapes have a color property and getColor() method

2. **You need to declare non-static or non-final fields**
   - Example: Animal class with 'age' and 'name' fields that can be modified

3. **You need constructors**
   - Example: Initialize common properties when creating subclass objects

4. **You want to have methods with different access modifiers**
   - Example: public, protected, or private methods

5. **You have a clear "is-a" relationship**
   - Example: Dog IS-A Animal, Car IS-A Vehicle

6. **You want to provide default implementation that can be inherited**
   - Example: sleep() method works the same for all animals

### When to Use Interface

Use an **interface** when:

1. **You expect unrelated classes to implement your interface**
   - Example: Comparable can be implemented by String, Integer, Person, etc.

2. **You want to specify behavior without implementation**
   - Example: Flyable behavior can be implemented by Bird, Airplane, Drone

3. **You want to achieve multiple inheritance**
   - Example: A Duck can implement both Swimmable and Flyable

4. **You want to define a contract**
   - Example: All payment methods must have processPayment()

5. **You have a "can-do" relationship**
   - Example: Bird CAN Fly, Fish CAN Swim

6. **You want to achieve loose coupling**
   - Example: Code depends on interface, not concrete implementation

### Comparison Table

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| **Keyword** | `abstract class` | `interface` |
| **Inheritance** | Single (extends one) | Multiple (implements many) |
| **Methods** | Can have abstract and concrete | All abstract (before Java 8) |
| **Fields** | Can have instance variables | Only public static final constants |
| **Constructors** | Yes | No |
| **Access Modifiers** | public, protected, private | public only (methods) |
| **Default Implementation** | Yes | Yes (Java 8+ with default) |
| **Static Methods** | Yes | Yes (Java 8+) |
| **Instantiation** | Cannot instantiate | Cannot instantiate |
| **Use Case** | "is-a" relationship | "can-do" relationship |

### Example Demonstrating Both

```java
// Abstract class for "is-a" relationship
abstract class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Concrete method
    public void displayInfo() {
        System.out.println("Name: " + name + ", Salary: $" + salary);
    }

    // Abstract method
    abstract void work();

    public String getName() {
        return name;
    }
}

// Interfaces for "can-do" relationships
interface Programmer {
    void writeCode();
}

interface Manager {
    void managePeople();
}

// A class can extend one abstract class and implement multiple interfaces
class TechLead extends Employee implements Programmer, Manager {
    public TechLead(String name, double salary) {
        super(name, salary);
    }

    @Override
    void work() {
        System.out.println(getName() + " is working as a Tech Lead");
    }

    @Override
    public void writeCode() {
        System.out.println(getName() + " is writing code");
    }

    @Override
    public void managePeople() {
        System.out.println(getName() + " is managing the team");
    }
}
```

---

## Multiple Interface Implementation

Java supports **multiple inheritance through interfaces**. A class can implement multiple interfaces, allowing it to inherit behavior from multiple sources.

### Why Multiple Inheritance is Allowed with Interfaces

**The Diamond Problem** is avoided because:
- Interfaces only define method signatures (before Java 8)
- There's no ambiguity about which implementation to use
- With Java 8+ default methods, there are resolution rules

### Syntax
```java
class ClassName implements Interface1, Interface2, Interface3 {
    // Must implement all abstract methods from all interfaces
}
```

### Example
```java
interface Swimmable {
    void swim();
}

interface Flyable {
    void fly();
}

interface Walkable {
    void walk();
}

// Duck can do all three
class Duck implements Swimmable, Flyable, Walkable {
    @Override
    public void swim() {
        System.out.println("Duck is swimming in the pond");
    }

    @Override
    public void fly() {
        System.out.println("Duck is flying in the sky");
    }

    @Override
    public void walk() {
        System.out.println("Duck is walking on land");
    }
}

// Fish can only swim
class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Fish is swimming underwater");
    }
}

// Airplane can only fly
class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Airplane is flying with engines");
    }
}

// Usage
Duck duck = new Duck();
duck.swim();  // Duck is swimming in the pond
duck.fly();   // Duck is flying in the sky
duck.walk();  // Duck is walking on land
```

### Combining Abstract Class and Multiple Interfaces
```java
abstract class Vehicle {
    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    abstract void start();

    public String getBrand() {
        return brand;
    }
}

interface Electric {
    void chargeBattery();
}

interface GPS {
    void navigate(String destination);
}

class Tesla extends Vehicle implements Electric, GPS {
    public Tesla(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(getBrand() + " is starting silently...");
    }

    @Override
    public void chargeBattery() {
        System.out.println(getBrand() + " is charging battery");
    }

    @Override
    public void navigate(String destination) {
        System.out.println(getBrand() + " is navigating to " + destination);
    }
}
```

---

## Default and Static Methods in Interfaces (Java 8+)

Java 8 introduced **default** and **static** methods in interfaces to allow interfaces to evolve without breaking existing implementations.

### Default Methods

A **default method** is a method in an interface that has an implementation. Classes that implement the interface can:
- Use the default implementation
- Override it with their own implementation

#### Syntax
```java
interface InterfaceName {
    // Default method
    default returnType methodName() {
        // implementation
    }
}
```

#### Example
```java
interface Vehicle {
    void start(); // abstract method

    // Default method - provides default implementation
    default void honk() {
        System.out.println("Beep! Beep!");
    }

    default void displayInfo() {
        System.out.println("This is a vehicle");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    // Using default honk() method - no override needed

    // Optionally override default method
    @Override
    public void displayInfo() {
        System.out.println("This is a car");
    }
}

class Truck implements Vehicle {
    @Override
    public void start() {
        System.out.println("Truck is starting...");
    }

    // Override default method
    @Override
    public void honk() {
        System.out.println("HOOOOOONK!"); // Louder honk for truck
    }
}

// Usage
Car car = new Car();
car.honk();         // Output: Beep! Beep! (using default)
car.displayInfo();  // Output: This is a car (overridden)

Truck truck = new Truck();
truck.honk();       // Output: HOOOOOONK! (overridden)
```

#### Why Default Methods?

1. **Backward Compatibility**: Add new methods to interfaces without breaking existing implementations
2. **Optional Behavior**: Provide default behavior that can be optionally overridden
3. **Code Reuse**: Share common implementation across all implementing classes

### Static Methods

A **static method** in an interface belongs to the interface itself, not to instances. It's called using the interface name.

#### Syntax
```java
interface InterfaceName {
    static returnType methodName() {
        // implementation
    }
}
```

#### Example
```java
interface MathOperations {
    // Abstract method
    int calculate(int a, int b);

    // Static method - utility method
    static int add(int a, int b) {
        return a + b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }

    // Default method
    default void printResult(int result) {
        System.out.println("Result: " + result);
    }
}

class Calculator implements MathOperations {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}

// Usage
// Call static method using interface name
int sum = MathOperations.add(5, 3);
System.out.println("Sum: " + sum); // Output: Sum: 8

int product = MathOperations.multiply(5, 3);
System.out.println("Product: " + product); // Output: Product: 15

Calculator calc = new Calculator();
// calc.add(5, 3); // ERROR! Cannot call static method on instance
```

#### Why Static Methods?

1. **Utility Methods**: Provide helper methods related to the interface
2. **Factory Methods**: Create instances of classes that implement the interface
3. **Namespace**: Group related utility methods under the interface name

### Diamond Problem with Default Methods

When a class implements multiple interfaces with the same default method, you must resolve the conflict.

```java
interface Interface1 {
    default void display() {
        System.out.println("Interface1");
    }
}

interface Interface2 {
    default void display() {
        System.out.println("Interface2");
    }
}

// This will cause a compilation error
// class MyClass implements Interface1, Interface2 { }

// Solution: Override the method
class MyClass implements Interface1, Interface2 {
    @Override
    public void display() {
        // Option 1: Provide your own implementation
        System.out.println("MyClass");

        // Option 2: Call specific interface's default method
        // Interface1.super.display();
        // Interface2.super.display();
    }
}
```

### Private Methods in Interfaces (Java 9+)

Java 9 introduced **private methods** in interfaces to help organize default method code.

```java
interface Logger {
    // Private method - can be used by default methods
    private void log(String level, String message) {
        System.out.println("[" + level + "] " + message);
    }

    // Private static method
    private static String formatMessage(String message) {
        return message.toUpperCase();
    }

    // Default methods can use private methods
    default void info(String message) {
        log("INFO", formatMessage(message));
    }

    default void error(String message) {
        log("ERROR", formatMessage(message));
    }
}
```

---

## Advanced Concepts

### 1. Abstraction Levels

You can have multiple levels of abstraction:

```java
// Level 1: Most abstract
interface Drawable {
    void draw();
}

// Level 2: Abstract class implementing interface
abstract class Shape implements Drawable {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    abstract double calculateArea();

    public String getColor() {
        return color;
    }
}

// Level 3: Another abstract class extending first
abstract class Polygon extends Shape {
    private int sides;

    public Polygon(String color, int sides) {
        super(color);
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }
}

// Level 4: Concrete class
class Triangle extends Polygon {
    private double base;
    private double height;

    public Triangle(String color, double base, double height) {
        super(color, 3);
        this.base = base;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " triangle");
    }
}
```

### 2. Abstract Classes Can Implement Interfaces Partially

An abstract class doesn't need to implement all interface methods:

```java
interface FullyFeatured {
    void method1();
    void method2();
    void method3();
}

// Abstract class implements only some methods
abstract class PartialImplementation implements FullyFeatured {
    @Override
    public void method1() {
        System.out.println("Method 1 implemented");
    }

    // method2() and method3() are still abstract
    // Concrete subclass must implement them
}

class CompleteImplementation extends PartialImplementation {
    @Override
    public void method2() {
        System.out.println("Method 2 implemented");
    }

    @Override
    public void method3() {
        System.out.println("Method 3 implemented");
    }
}
```

### 3. Marker Interfaces

A **marker interface** is an empty interface used to mark or tag classes:

```java
// Marker interface - no methods
interface Serializable {
    // Empty - just marks classes as serializable
}

class Person implements Serializable {
    private String name;
    // This class can now be serialized
}

// Check at runtime
if (obj instanceof Serializable) {
    // Object can be serialized
}
```

### 4. Polymorphism with Abstraction

Abstraction enables powerful polymorphic behavior:

```java
// Using interface reference
List<Flyable> flyingThings = new ArrayList<>();
flyingThings.add(new Bird());
flyingThings.add(new Airplane());
flyingThings.add(new Drone());

// Polymorphic behavior
for (Flyable thing : flyingThings) {
    thing.fly(); // Each calls its own implementation
}

// Using abstract class reference
Shape[] shapes = {
    new Circle("red", 5),
    new Rectangle("blue", 4, 6),
    new Triangle("green", 3, 4)
};

for (Shape shape : shapes) {
    System.out.println("Area: " + shape.calculateArea());
    shape.draw();
}
```

### 5. Anonymous Classes with Abstraction

You can create anonymous implementations:

```java
// Anonymous class implementing interface
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task running...");
    }
};

// With lambda (for functional interfaces)
Runnable task2 = () -> System.out.println("Task running...");

// Anonymous class extending abstract class
abstract class Processor {
    abstract void process();
}

Processor processor = new Processor() {
    @Override
    void process() {
        System.out.println("Processing...");
    }
};
```

---

## Best Practices

### 1. Design Principles

**Program to an interface, not an implementation**
```java
// Good
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();

// Not recommended
ArrayList<String> list = new ArrayList<>();
HashMap<String, Integer> map = new HashMap<>();
```

### 2. Naming Conventions

- **Interfaces**: Use nouns or adjectives
  - Good: `Drawable`, `Comparable`, `Serializable`, `Runnable`
  - Avoid: `IDrawable` (Hungarian notation - not Java convention)

- **Abstract Classes**: Use nouns
  - Good: `Shape`, `Animal`, `Vehicle`

### 3. Keep Interfaces Focused

Follow the **Interface Segregation Principle** (ISP):
- Many small, focused interfaces are better than one large interface
- Clients shouldn't be forced to implement methods they don't use

```java
// Bad - one large interface
interface Worker {
    void work();
    void eat();
    void sleep();
}

// Good - segregated interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// Robot only implements what it needs
class Robot implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
}

// Human implements all
class Human implements Workable, Eatable, Sleepable {
    public void work() { }
    public void eat() { }
    public void sleep() { }
}
```

### 4. Use Abstract Classes for Common Implementation

If multiple classes share common code, use an abstract class:

```java
abstract class DatabaseConnection {
    private String url;

    public DatabaseConnection(String url) {
        this.url = url;
    }

    // Common implementation
    public void connect() {
        System.out.println("Connecting to " + url);
    }

    // Let subclasses define specific behavior
    abstract void executeQuery(String query);
}
```

### 5. Favor Composition Over Inheritance

Sometimes using interfaces with composition is better than abstract class inheritance:

```java
// Instead of inheritance hierarchy
interface Engine {
    void start();
}

interface GPS {
    void navigate(String destination);
}

class Car {
    private Engine engine;
    private GPS gps;

    public Car(Engine engine, GPS gps) {
        this.engine = engine;
        this.gps = gps;
    }

    public void start() {
        engine.start();
    }

    public void navigateTo(String destination) {
        gps.navigate(destination);
    }
}
```

### 6. Document Your Abstractions

Always document what the abstraction represents and how to use it:

```java
/**
 * Represents a payment processing strategy.
 * Implementations should handle specific payment methods (credit card, PayPal, etc.)
 */
interface PaymentProcessor {
    /**
     * Process a payment transaction.
     *
     * @param amount the amount to charge
     * @return true if payment successful, false otherwise
     */
    boolean processPayment(double amount);
}
```

### 7. Use @Override Annotation

Always use `@Override` when implementing abstract methods:

```java
abstract class Parent {
    abstract void display();
}

class Child extends Parent {
    @Override  // Always use this
    void display() {
        System.out.println("Child display");
    }
}
```

### 8. Consider Default Methods for Interface Evolution

When adding new methods to existing interfaces, use default methods to maintain backward compatibility:

```java
interface OldInterface {
    void existingMethod();

    // New method added in version 2.0
    default void newMethod() {
        // Default implementation so existing classes don't break
        System.out.println("Default implementation");
    }
}
```

---

## Summary

**Abstraction** is about hiding complexity and exposing only what's necessary. Master these concepts:

1. **Abstract Classes**: Use for "is-a" relationships, shared code, and when you need constructors/fields
2. **Interfaces**: Use for "can-do" relationships, contracts, and multiple inheritance
3. **Abstract Methods**: Define what must be implemented without specifying how
4. **Multiple Inheritance**: Achieved through interfaces to combine different capabilities
5. **Default Methods**: Provide default implementations in interfaces (Java 8+)
6. **Static Methods**: Add utility methods to interfaces (Java 8+)

**Remember**:
- Abstract classes = **partial abstraction** (can have both abstract and concrete methods)
- Interfaces = **complete abstraction** (traditionally all methods are abstract)
- Choose based on your design needs: inheritance structure vs. behavior contracts
- Program to interfaces for flexibility and loose coupling

Congratulations! You've now learned abstraction from zero to master level. Practice with the examples in this directory to solidify your understanding.
