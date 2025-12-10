# Day 6: Advanced OOP Concepts

This document provides a deep dive into advanced Object-Oriented Programming (OOP) concepts in Java. We'll explore each topic from the ground up, including how they work under the hood and their impact on memory.

---

## 1. Static Members (Variables and Methods)

### From Zero to Hero

**What are they?**
`static` members belong to the class itself, not to any individual instance (object) of the class. This means there is only one copy of a static member, regardless of how many objects of that class are created.

*   **Static Variables (Class Variables):** A single copy of the variable is shared among all instances of the class.
*   **Static Methods (Class Methods):** Can be called directly on the class without creating an instance. They can only access other static members of the class directly.

### Memory and Internals

*   **Memory Allocation:** Static members are allocated memory in a special area of the heap called the **Metaspace** (prior to Java 8, this was the PermGen space). This happens when the class is loaded by the Java Virtual Machine (JVM), even before any objects are created.
*   **Lifecycle:** They exist for the entire lifetime of the application, as long as the class is loaded in the JVM.
*   **Why can't static methods access instance members?** Instance members (variables and methods) are stored on the heap and are tied to a specific object. A static method doesn't know which specific instance it should work with because it's not associated with any. It would be like a general blueprint trying to know the color of a specific car that hasn't been built yet.

---

## 2. Static Blocks

### From Zero to Hero

**What is it?**
A `static` block is a block of code prefixed with the `static` keyword. It is used to initialize static variables.

**Execution:**
The JVM executes static blocks when the class is first loaded into memory, in the order they appear in the source code. This happens only once.

### Memory and Internals

*   The primary purpose of a static block is to perform complex initialization for static variables that can't be done in a single line. For example, you might need to read from a file or perform a calculation to set the value of a static variable.
*   Like static members, the code inside a static block is executed at class loading time, and it has access only to other static members.

---

## 3. The `final` Keyword

The `final` keyword is used to apply restrictions on classes, methods, and variables.

### a) `final` Variables (Constants)

*   **What it means:** The value of a `final` variable cannot be changed once it has been assigned. It creates a constant.
*   **Initialization:** A `final` variable must be initialized, either at the time of declaration or within the constructor. A `static final` variable must be initialized at declaration or in a static block.
*   **Memory Impact:** For primitive types, the value is stored directly. For object references, the reference itself is final (it can't be pointed to another object), but the internal state of the object it points to can still be changed.

### b) `final` Methods

*   **What it means:** A `final` method cannot be overridden by a subclass.
*   **Why use it?** To enforce a stable, unchangeable implementation of a method. This is crucial for methods that have a core, security-sensitive, or platform-dependent implementation.
*   **Performance:** In the early days of Java, `final` methods could sometimes be inlined by the compiler for a minor performance boost. However, modern JVMs are so good at optimization that this is no longer a significant reason to use `final`. The primary reason is for solid design.

### c) `final` Classes

*   **What it means:** A `final` class cannot be subclassed (inherited from).
*   **Why use it?** For security and immutability. For example, the `String` class is `final` so that its behavior cannot be altered by a malicious subclass. If you could extend `String`, you could potentially break all the code that relies on the predictable, immutable nature of strings.

---

## 4. Inner Classes (Nested Classes)

Java allows you to define a class within another class. These are called nested classes.

*   **Static Nested Class:** Behaves like a regular top-level class but is packaged inside another. It can only access static members of the outer class. It does not have a reference to an outer class instance.
*   **Inner Class (Non-static):** Each instance of an inner class is implicitly associated with an instance of the outer class. It can access all members (static and non-static) of the outer class.
*   **Memory Impact:** An instance of a non-static inner class holds a hidden reference to the instance of the outer class that created it. This can prevent the outer class instance from being garbage collected if the inner class instance is still alive, potentially leading to memory leaks if not managed carefully.

---

## 5. Anonymous Classes

### From Zero to Hero

**What are they?**
An anonymous class is an inner class without a name. It allows you to declare and instantiate a class at the same time. They are like a one-time-use class.

**Common Use Case:**
They are often used for implementing an interface or extending a class on the fly, typically for event handlers in GUIs or for creating `Runnable` objects for threading.

### Memory and Internals

*   Since an anonymous class is a type of inner class, it also holds an implicit reference to the instance of the enclosing class.
*   The compiler generates a regular `.class` file for each anonymous class, with a synthetic name like `OuterClassName$1.class`.
*   With the introduction of lambda expressions in Java 8, the use of anonymous classes has decreased for implementing simple functional interfaces, as lambdas provide a more concise syntax.

---

## 6. The `Object` Class and its Methods

Every class in Java implicitly inherits from the `Object` class. This means all objects have access to its methods.

### a) `toString()`

*   **Purpose:** To provide a string representation of an object.
*   **Default Behavior:** The default implementation in the `Object` class prints the class name followed by `@` and the object's hash code in hexadecimal (e.g., `MyClass@1a2b3c4d`).
*   **Best Practice:** You should always override `toString()` in your classes to provide meaningful, human-readable information about the object's state. This is invaluable for logging and debugging.

### b) `equals()`

*   **Purpose:** To check if two objects are "equal".
*   **Default Behavior:** The default implementation in the `Object` class checks for reference equality (i.e., it returns `true` only if two references point to the exact same object in memory, using `==`).
*   **Best Practice:** You should override `equals()` to define "logical equality." For example, two `Person` objects might be considered equal if they have the same ID number, even if they are different instances in memory.

### c) `hashCode()`

*   **Purpose:** To return a unique integer (hash code) for an object. This is used by data structures like `HashMap` and `HashSet` for efficient storage and retrieval.
*   **The Contract:**
    1.  If you override `equals()`, you **MUST** override `hashCode()`.
    2.  If `obj1.equals(obj2)` is true, then `obj1.hashCode()` **MUST** be equal to `obj2.hashCode()`.
    3.  If `obj1.equals(obj2)` is false, their hash codes do not have to be different, but it's highly recommended for performance.
*   **Why?** If two equal objects had different hash codes, you could never find the object in a `HashMap`. You would put an object in the map at a location based on its hash code, then try to retrieve it with an "equal" object that has a different hash code, and you'd be looking in the wrong place.

---

## 7. Package Organization

### From Zero to Hero

**What are they?**
Packages are namespaces used to organize related classes and interfaces. They prevent naming conflicts and control access.

**How it works:**
The `package` statement at the top of a source file places the class in that package. The directory structure of your project must match the package structure. For example, a class in the package `com.example.project` must be in a file located at `.../com/example/project/MyClass.java`.

### Memory and Internals

*   Packages are a compile-time and organizational feature. They don't have a direct impact on memory usage at runtime in the way objects or static variables do.
*   They are crucial for the JVM to locate and load the correct `.class` files. When your code refers to `com.example.project.MyClass`, the JVM's ClassLoader knows to search for it in the `com/example/project/` subdirectory of the classpath.
*   They also control access. Members with default (package-private) access are only visible to other classes within the same package.
