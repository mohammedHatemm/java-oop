public class Student {

    // Attributes (الخصائص)
    String name;
    int age;
    double gpa;
    double grade;
    String studentId;

    // ==================== Basic Methods ====================

    void displayStudentInfo() {
        System.out.println("=== Student Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Grade: " + grade);
        System.out.println("Student ID: " + studentId);
    }

    // ==================== Method Overloading ====================
    // نفس الاسم، parameters مختلفة

    // Version 1: بدون parameters
    void study() {
        System.out.println(name + " is studying.");
    }

    // Version 2: parameter واحد
    void study(String subject) {
        System.out.println(name + " is studying " + subject + ".");
    }

    // Version 3: 2 parameters
    void study(String subject, int hours) {
        System.out.println(name + " is studying " + subject + " for " + hours + " hours.");
    }

    // ==================== Calculate Grade ====================

    // Version 1: بدون parameters - تستخدم this.grade
    String calculateGrade() {
        if (grade > 85) {
            return "Excellent";
        } else if (grade > 75) {
            return "Very Good";
        } else if (grade > 65) {
            return "Good";
        } else if (grade >= 50) {
            return "Pass";
        } else {
            return "Failed";
        }
    }

    // Version 2: تستقبل grade كـ parameter
    String calculateGrade(double grade) {
        if (grade > 85) {
            return "Excellent";
        } else if (grade > 75) {
            return "Very Good";
        } else if (grade > 65) {
            return "Good";
        } else if (grade >= 50) {
            return "Pass";
        } else {
            return "Failed";
        }
    }

    // Version 3: تستقبل grade و bonus
    String calculateGrade(double grade, double bonus) {
        double finalGrade = grade + bonus;
        if (finalGrade > 100) {
            finalGrade = 100;
        }
        if (finalGrade > 85) {
            return "Excellent";
        } else if (finalGrade > 75) {
            return "Very Good";
        } else if (finalGrade > 65) {
            return "Good";
        } else if (finalGrade >= 50) {
            return "Pass";
        } else {
            return "Failed";
        }
    }

    // ==================== Using 'this' Keyword ====================

    // this بيفرق بين الـ attribute والـ parameter
    void setInfo(String name, int age, double grade) {
        this.name = name;      // this.name = attribute
        this.age = age;        // name = parameter
        this.grade = grade;
    }

    // Methods ترجع this للـ Method Chaining
    Student setName(String name) {
        this.name = name;
        return this;
    }

    Student setAge(int age) {
        this.age = age;
        return this;
    }

    Student setGrade(double grade) {
        this.grade = grade;
        return this;
    }

    // ==================== Utility Methods ====================

    // هل الطالب ناجح؟
    boolean isPassing() {
        return grade >= 50;
    }

    // هل الدرجة ممتازة؟
    boolean isExcellent() {
        return grade > 85;
    }

    // طباعة ملخص
    void printSummary() {
        System.out.println(name + " - " + calculateGrade() + " (" + grade + ")");
    }
}
