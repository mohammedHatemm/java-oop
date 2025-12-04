/**
 * Example 05: Method Overloading
 *
 * This example demonstrates:
 * - Method overloading by number of parameters
 * - Method overloading by type of parameters
 * - Method overloading by order of parameters
 * - Variable arguments (varargs)
 * - Type promotion in overloading
 */

class Calculator {
    // Overloading by number of parameters

    public int add(int a, int b) {
        System.out.println("add(int, int) called");
        return a + b;
    }

    public int add(int a, int b, int c) {
        System.out.println("add(int, int, int) called");
        return a + b + c;
    }

    public int add(int a, int b, int c, int d) {
        System.out.println("add(int, int, int, int) called");
        return a + b + c + d;
    }

    // Overloading by type of parameters

    public double add(double a, double b) {
        System.out.println("add(double, double) called");
        return a + b;
    }

    public String add(String a, String b) {
        System.out.println("add(String, String) called");
        return a + b;
    }

    // Overloading by order of parameters

    public void display(int number, String text) {
        System.out.println("display(int, String): " + number + " - " + text);
    }

    public void display(String text, int number) {
        System.out.println("display(String, int): " + text + " - " + number);
    }

    // Variable arguments (varargs)

    public int add(int... numbers) {
        System.out.println("add(int... varargs) called with " + numbers.length + " arguments");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Combining regular parameters with varargs

    public double calculateTotal(double taxRate, double... prices) {
        System.out.println("calculateTotal with " + prices.length + " prices");
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        return subtotal * (1 + taxRate);
    }
}

class Printer {
    // Overloaded print methods for different types

    public void print(int value) {
        System.out.println("Integer: " + value);
    }

    public void print(double value) {
        System.out.println("Double: " + value);
    }

    public void print(String value) {
        System.out.println("String: " + value);
    }

    public void print(boolean value) {
        System.out.println("Boolean: " + value);
    }

    public void print(int[] array) {
        System.out.print("Integer Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Print with formatting options

    public void print(String value, boolean uppercase) {
        if (uppercase) {
            System.out.println("String (uppercase): " + value.toUpperCase());
        } else {
            System.out.println("String (lowercase): " + value.toLowerCase());
        }
    }
}

class StringFormatter {
    // Multiple ways to format strings

    public String format(String text) {
        return text.trim();
    }

    public String format(String text, boolean uppercase) {
        text = text.trim();
        return uppercase ? text.toUpperCase() : text.toLowerCase();
    }

    public String format(String text, int maxLength) {
        text = text.trim();
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "...";
        }
        return text;
    }

    public String format(String text, String prefix, String suffix) {
        return prefix + text.trim() + suffix;
    }
}

public class Example05_MethodOverloading {
    public static void main(String[] args) {
        System.out.println("=== Example 05: Method Overloading ===\n");

        Calculator calc = new Calculator();

        // Overloading by number of parameters
        System.out.println("--- Overloading by Number of Parameters ---");
        System.out.println("Result: " + calc.add(5, 10));
        System.out.println("Result: " + calc.add(5, 10, 15));
        System.out.println("Result: " + calc.add(5, 10, 15, 20));

        // Overloading by type of parameters
        System.out.println("\n--- Overloading by Type of Parameters ---");
        System.out.println("Result: " + calc.add(5, 10));           // int
        System.out.println("Result: " + calc.add(5.5, 10.5));       // double
        System.out.println("Result: " + calc.add("Hello", "World")); // String

        // Overloading by order of parameters
        System.out.println("\n--- Overloading by Order of Parameters ---");
        calc.display(100, "items");
        calc.display("Count:", 100);

        // Variable arguments
        System.out.println("\n--- Variable Arguments (Varargs) ---");
        System.out.println("Result: " + calc.add(1, 2, 3, 4, 5));
        System.out.println("Result: " + calc.add(10, 20));
        System.out.println("Result: " + calc.add(5, 10, 15, 20, 25, 30));

        // Varargs with regular parameters
        System.out.println("\n--- Varargs with Regular Parameters ---");
        double total1 = calc.calculateTotal(0.08, 29.99, 49.99, 19.99);
        System.out.printf("Total with 8%% tax: $%.2f%n", total1);

        double total2 = calc.calculateTotal(0.10, 100.00, 200.00);
        System.out.printf("Total with 10%% tax: $%.2f%n", total2);

        // Type-specific overloading
        System.out.println("\n--- Type-Specific Overloading ---");
        Printer printer = new Printer();
        printer.print(42);
        printer.print(3.14159);
        printer.print("Hello, Java!");
        printer.print(true);
        printer.print(new int[]{1, 2, 3, 4, 5});

        // Overloading with additional parameters
        System.out.println("\n--- Overloading with Additional Parameters ---");
        printer.print("Java Programming", true);
        printer.print("Java Programming", false);

        // String formatter example
        System.out.println("\n--- String Formatter Example ---");
        StringFormatter formatter = new StringFormatter();

        String text = "  Hello World  ";
        System.out.println("Original: '" + text + "'");
        System.out.println("Trimmed: '" + formatter.format(text) + "'");
        System.out.println("Uppercase: '" + formatter.format(text, true) + "'");
        System.out.println("Lowercase: '" + formatter.format(text, false) + "'");
        System.out.println("Max length 5: '" + formatter.format(text, 5) + "'");
        System.out.println("With prefix/suffix: '" + formatter.format(text, "[", "]") + "'");

        // Type promotion
        System.out.println("\n--- Type Promotion in Overloading ---");
        Calculator calc2 = new Calculator();
        byte b1 = 10;
        byte b2 = 20;
        System.out.println("Adding two bytes:");
        int result = calc2.add(b1, b2);  // bytes promoted to int
        System.out.println("Result: " + result);

        System.out.println("\n=== Key Points ===");
        System.out.println("✓ Method overloading = same name, different parameters");
        System.out.println("✓ Parameters must differ in: number, type, or order");
        System.out.println("✓ Return type alone is NOT sufficient for overloading");
        System.out.println("✓ Varargs must be the last parameter");
        System.out.println("✓ Compiler determines which method to call (compile-time polymorphism)");
        System.out.println("✓ Type promotion happens automatically (byte -> int, int -> double, etc.)");
    }
}
