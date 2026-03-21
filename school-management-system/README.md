# School Management System

A complete school management system demonstrating all OOP concepts.

---

## Project Overview

This project combines everything learned in the OOP lessons:

| Concept | Implementation |
|---------|----------------|
| Classes & Objects | Student, Teacher, Course, etc. |
| Constructors | Multiple constructors for each class |
| Encapsulation | Private fields, getters/setters with validation |
| Inheritance | Person -> Student, Teacher |
| Polymorphism | Person array, method overriding |
| Abstraction | Abstract Person class |
| Interfaces | Gradable, Payable, Printable |
| Advanced | Enums, Singleton, Factory |

---

## Project Structure

```
school-management-system/
└── src/
    ├── Main.java                    # Entry point
    │
    ├── models/
    │   ├── Person.java              # Abstract base class
    │   ├── Student.java             # Student class
    │   ├── Teacher.java             # Teacher class
    │   ├── Course.java              # Course class
    │   └── Enrollment.java          # Student-Course relationship
    │
    ├── services/
    │   ├── SchoolSystem.java        # Singleton - main system
    │   ├── EnrollmentService.java   # Manage enrollments
    │   ├── GradeService.java        # Manage grades
    │   └── PersonFactory.java       # Factory pattern
    │
    ├── interfaces/
    │   ├── Gradable.java            # Grade-related methods
    │   ├── Payable.java             # Payment-related methods
    │   └── Printable.java           # Print reports
    │
    └── enums/
        ├── Grade.java               # A, B, C, D, F
        └── Department.java          # CS, MATH, PHYSICS, etc.
```

---

## Features

### 1. Person Management
- Add students and teachers
- Update personal information
- Remove persons from system

### 2. Course Management
- Create courses
- Assign teachers to courses
- Set course capacity

### 3. Enrollment
- Enroll students in courses
- Check prerequisites
- Handle capacity limits

### 4. Grading
- Assign grades to students
- Calculate GPA
- Generate transcripts

### 5. Reports
- List all students
- List all teachers
- Course reports
- Grade reports

---

## Usage Example

```java
public class Main {
    public static void main(String[] args) {
        // Get the school system instance (Singleton)
        SchoolSystem school = SchoolSystem.getInstance();
        
        // Create persons using Factory
        Student s1 = (Student) PersonFactory.create("student", "Ahmed", 20);
        Student s2 = (Student) PersonFactory.create("student", "Sara", 21);
        Teacher t1 = (Teacher) PersonFactory.create("teacher", "Dr. Omar", 45);
        
        // Add to system
        school.addStudent(s1);
        school.addStudent(s2);
        school.addTeacher(t1);
        
        // Create course
        Course javaCourse = new Course("CS101", "Java Programming", t1);
        school.addCourse(javaCourse);
        
        // Enroll students
        school.enrollStudent(s1, javaCourse);
        school.enrollStudent(s2, javaCourse);
        
        // Assign grades
        GradeService gradeService = new GradeService();
        gradeService.assignGrade(s1, javaCourse, Grade.A);
        gradeService.assignGrade(s2, javaCourse, Grade.B_PLUS);
        
        // Print reports
        school.printAllStudents();
        school.printAllCourses();
        s1.printTranscript();
    }
}
```

---

## Class Diagram

```
                    +---------------+
                    |  <<interface>>|
                    |   Gradable    |
                    +-------+-------+
                            |
+---------------+   +-------+-------+   +---------------+
| <<interface>> |   |  <<abstract>> |   | <<interface>> |
|   Printable   |<--|    Person     |-->|    Payable    |
+---------------+   +-------+-------+   +---------------+
                            |
            +---------------+---------------+
            |               |               |
    +-------+-------+ +-----+-----+ +-------+-------+
    |    Student    | |  Teacher  | |     Staff     |
    +---------------+ +-----------+ +---------------+
            |               |
            |       +-------+-------+
            |       |    Course     |
            |       +---------------+
            |               |
    +-------+---------------+-------+
    |          Enrollment           |
    +-------------------------------+
```

---

## OOP Concepts Checklist

- [ ] Abstract `Person` class
- [ ] `Student` extends `Person`
- [ ] `Teacher` extends `Person`
- [ ] `Gradable` interface
- [ ] `Payable` interface
- [ ] `Printable` interface
- [ ] `Grade` enum
- [ ] `Department` enum
- [ ] `SchoolSystem` Singleton
- [ ] `PersonFactory` Factory pattern
- [ ] Encapsulation with validation
- [ ] Method overriding
- [ ] Polymorphism with Person array
- [ ] Static counters for IDs

---

## How to Run

```bash
cd school-management-system/src
javac Main.java models/*.java services/*.java interfaces/*.java enums/*.java
java Main
```

---

## Extensions (Optional)

1. Add `Staff` class (extends Person)
2. Add prerequisites for courses
3. Implement course scheduling
4. Add payment processing for teachers
5. Create a simple menu-driven interface
6. Save/load data to/from files
