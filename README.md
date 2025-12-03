# <¯ One Week OOP in Java - Study Plan

**Duration:** 7 Days
**Goal:** Master Object-Oriented Programming fundamentals in Java

---

## =Å Week Schedule

### **Day 1: Classes and Objects**
**Topics to Cover:**
- What are Classes and Objects?
- Creating your first class
- Instance variables (fields/attributes)
- Constructors (default and parameterized)
- Creating and using objects
- The `this` keyword
- Multiple constructors

**Practice Tasks:**
- [ ] Create a `Person` class with fields: name, age, email
- [ ] Create a `Car` class with fields: brand, model, year, color
- [ ] Implement multiple constructors for each class
- [ ] Create objects and print their details

**Resources Needed:**
- IntelliJ IDEA or preferred IDE
- JDK installed

---

### **Day 2: Encapsulation**
**Topics to Cover:**
- What is Encapsulation?
- Access modifiers: `private`, `public`, `protected`, default
- Getters and Setters
- Data hiding and validation
- Why encapsulation matters

**Practice Tasks:**
- [ ] Refactor Day 1 classes to use private fields
- [ ] Create getters and setters for all fields
- [ ] Add validation in setters (e.g., age > 0, year validation)
- [ ] Create a `BankAccount` class with private balance
- [ ] Implement deposit/withdraw methods with validation

---

### **Day 3: Inheritance**
**Topics to Cover:**
- What is Inheritance?
- The `extends` keyword
- Parent class (superclass) and Child class (subclass)
- The `super` keyword
- Constructor chaining
- Types of inheritance (Single, Multilevel, Hierarchical)
- `protected` access modifier in inheritance

**Practice Tasks:**
- [ ] Create an `Animal` superclass with basic properties
- [ ] Create `Dog`, `Cat`, `Bird` subclasses extending Animal
- [ ] Use `super()` to call parent constructor
- [ ] Create a `Vehicle` ’ `Car` ’ `ElectricCar` hierarchy
- [ ] Demonstrate constructor chaining

---

### **Day 4: Polymorphism**
**Topics to Cover:**
- What is Polymorphism?
- Method Overriding (Runtime Polymorphism)
- The `@Override` annotation
- Method Overloading (Compile-time Polymorphism)
- Dynamic method dispatch
- The `instanceof` operator
- Upcasting and Downcasting

**Practice Tasks:**
- [ ] Override methods in Day 3 subclasses
- [ ] Create a method that accepts parent class but works with child objects
- [ ] Implement method overloading with different parameters
- [ ] Use `instanceof` to check object types before casting
- [ ] Create an array of parent type holding different child objects

---

### **Day 5: Abstraction**
**Topics to Cover:**
- What is Abstraction?
- Abstract classes (`abstract` keyword)
- Abstract methods
- Interfaces (`interface` keyword)
- Implementing interfaces (`implements`)
- Multiple interface implementation
- Default and static methods in interfaces (Java 8+)
- When to use abstract class vs interface

**Practice Tasks:**
- [ ] Create an abstract `Shape` class with abstract `calculateArea()` method
- [ ] Implement `Circle`, `Rectangle`, `Triangle` classes
- [ ] Create a `Drawable` interface with `draw()` method
- [ ] Create a `Resizable` interface with `resize()` method
- [ ] Implement multiple interfaces in a class

---

### **Day 6: Advanced OOP Concepts**
**Topics to Cover:**
- Static members (variables and methods)
- Static blocks
- Final keyword (variables, methods, classes)
- Inner classes (nested classes)
- Anonymous classes
- The `Object` class and its methods (`toString()`, `equals()`, `hashCode()`)
- Package organization

**Practice Tasks:**
- [ ] Create a class with static counter for instances
- [ ] Override `toString()` method in your classes
- [ ] Override `equals()` method for object comparison
- [ ] Create a utility class with static methods
- [ ] Use final keyword to create constants
- [ ] Organize classes into packages

---

### **Day 7: Project Day + Review**
**Focus:**
- Apply all OOP concepts learned
- Build the final project
- Code review and refactoring
- Testing and debugging

**Tasks:**
- [ ] Complete the final project (see below)
- [ ] Review all code written during the week
- [ ] Refactor code to follow best practices
- [ ] Add comments and documentation
- [ ] Test all functionality

---

## =€ Final Project Ideas

### **Option 1: Library Management System** P (Recommended)
**Description:** Create a system to manage books, members, and borrowing operations.

**Classes to Implement:**
- `Book` (title, author, ISBN, isAvailable)
- `Member` (name, memberId, borrowedBooks list)
- `LibraryItem` (abstract parent for Book, Magazine, DVD)
- `Borrowable` (interface with borrow/return methods)
- `Library` (manages books and members)
- `Librarian` extends `Person` (can add/remove books)

**Features:**
- Add/remove books
- Register members
- Borrow/return books
- Search for books by title or author
- Display all available books
- Track borrowed books per member

**OOP Concepts Used:**
-  Classes and Objects
-  Encapsulation (private fields, getters/setters)
-  Inheritance (LibraryItem hierarchy)
-  Polymorphism (method overriding)
-  Abstraction (LibraryItem abstract class, Borrowable interface)
-  Static members (book counter, member counter)

---

### **Option 2: University Management System**
**Description:** Manage students, courses, and enrollments.

**Classes to Implement:**
- `Person` (abstract: name, id, age)
- `Student` extends Person (studentId, enrolledCourses list, GPA)
- `Professor` extends Person (employeeId, department, coursesTaught list)
- `Course` (courseCode, name, professor, enrolledStudents, maxCapacity)
- `Department` (name, courses list, professors list)
- `University` (manages all entities)
- `Enrollable` (interface for enrollment operations)

**Features:**
- Add students and professors
- Create courses with capacity limits
- Enroll/drop students from courses
- Assign professors to courses
- Calculate student GPA
- Display course roster
- Search functionality

---

### **Option 3: E-Commerce System**
**Description:** Simple online shopping system.

**Classes to Implement:**
- `Product` (name, price, stockQuantity, productId)
- `Electronics`, `Clothing`, `Food` extend Product
- `Customer` (name, email, shoppingCart, orderHistory)
- `ShoppingCart` (items list, total calculation)
- `Order` (orderId, customer, items, orderDate, status)
- `Payment` (interface: processPayment())
- `CreditCardPayment`, `PayPalPayment` implement Payment
- `Store` (manages products and orders)

**Features:**
- Browse products by category
- Add/remove items from cart
- Checkout and place order
- Apply discounts (inheritance for different discount types)
- Track order status
- View order history

---

### **Option 4: Zoo Management System**
**Description:** Manage animals, habitats, and zoo operations.

**Classes to Implement:**
- `Animal` (abstract: name, age, species, sound)
- `Mammal`, `Bird`, `Reptile` extend Animal
- `Feedable` (interface: feed())
- `Trainable` (interface: train())
- `Habitat` (type, capacity, animals list)
- `ZooKeeper` (name, assignedAnimals)
- `Zoo` (name, habitats, animals, zookeepers)

**Features:**
- Add animals to zoo
- Assign animals to habitats
- Feed animals (different feeding schedules)
- Animal sounds (polymorphism)
- Habitat capacity management
- Zoo keeper assignments

---

## =Ý Todo List System

### **How It Works:**
As you complete each day's tasks, update this README by checking off items with `[x]`. The system tracks your progress throughout the week.

### **Progress Tracker:**

#### Day 1 Progress:  0/4 tasks
- [ ] Person class created
- [ ] Car class created
- [ ] Multiple constructors implemented
- [ ] Objects created and tested

#### Day 2 Progress:  0/5 tasks
- [ ] Classes refactored with private fields
- [ ] Getters and setters created
- [ ] Validation added to setters
- [ ] BankAccount class created
- [ ] Deposit/withdraw methods implemented

#### Day 3 Progress:  0/5 tasks
- [ ] Animal superclass created
- [ ] Dog, Cat, Bird subclasses created
- [ ] super() keyword used correctly
- [ ] Vehicle hierarchy created
- [ ] Constructor chaining demonstrated

#### Day 4 Progress:  0/5 tasks
- [ ] Methods overridden in subclasses
- [ ] Polymorphic method created
- [ ] Method overloading implemented
- [ ] instanceof operator used
- [ ] Array of parent type with child objects

#### Day 5 Progress:  0/5 tasks
- [ ] Abstract Shape class created
- [ ] Circle, Rectangle, Triangle implemented
- [ ] Drawable interface created
- [ ] Resizable interface created
- [ ] Multiple interfaces implemented

#### Day 6 Progress:  0/6 tasks
- [ ] Static counter implemented
- [ ] toString() overridden
- [ ] equals() overridden
- [ ] Utility class with static methods
- [ ] Final keyword used for constants
- [ ] Classes organized into packages

#### Day 7 Progress:  0/4 tasks
- [ ] Final project started
- [ ] All classes implemented
- [ ] Code refactored and documented
- [ ] Project tested and completed

---

## <“ Learning Tips

1. **Code Every Day**: Practice is key to mastering OOP
2. **Draw Diagrams**: Use UML or simple diagrams to visualize class relationships
3. **Experiment**: Try breaking things to understand how they work
4. **Review Previous Days**: Each concept builds on the previous one
5. **Ask Questions**: If stuck, research or seek help
6. **Real-World Thinking**: Relate concepts to real-world objects

---

## =Ú Additional Resources

- Oracle Java Documentation: https://docs.oracle.com/javase/tutorial/java/concepts/
- Java OOP Concepts
- IntelliJ IDEA for Java development

---

## <Æ Week Completion Checklist

- [ ] All 7 days completed
- [ ] Final project chosen
- [ ] Final project completed
- [ ] Code reviewed and refactored
- [ ] All concepts understood
- [ ] Ready for advanced Java topics

---

**Good luck with your OOP journey! =€**

*Remember: OOP is about thinking in terms of objects and their relationships. Take your time to understand each concept deeply.*
