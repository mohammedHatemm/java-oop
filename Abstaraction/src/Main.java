/**
 * Main class - Demonstrates all abstraction concepts
 *
 * This class shows practical examples of:
 * 1. Abstract classes and methods
 * 2. Interfaces
 * 3. Multiple interface implementation
 * 4. Polymorphism with abstraction
 * 5. Default and static methods in interfaces
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("       ABSTRACTION IN JAVA - COMPLETE DEMONSTRATION");
        System.out.println("=".repeat(70));

        // ========================================
        // 1. ABSTRACT CLASS DEMONSTRATION
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("1. ABSTRACT CLASS DEMONSTRATION");
        System.out.println("=".repeat(70));

        // Cannot instantiate abstract class
        // Shape shape = new Shape("red"); // ERROR! This won't compile

        // Create concrete shape objects
        Circle circle = new Circle("Red", 5.0);
        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);
        Triangle triangle = new Triangle("Green", 6.0, 4.0, 5.0, 5.0, 6.0);

        System.out.println("\nCircle:");
        circle.displayInfo();

        System.out.println("\nRectangle:");
        rectangle.displayInfo();

        System.out.println("\nTriangle:");
        triangle.displayInfo();

        // ========================================
        // 2. POLYMORPHISM WITH ABSTRACT CLASSES
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("2. POLYMORPHISM WITH ABSTRACT CLASSES");
        System.out.println("=".repeat(70));

        // Array of Shape references - polymorphism in action!
        Shape[] shapes = {
            new Circle("Yellow", 3.0),
            new Rectangle("Purple", 5.0, 5.0),
            new Triangle("Orange", 4.0, 3.0)
        };

        System.out.println("\nCalculating total area of all shapes:");
        double totalArea = 0;
        for (Shape shape : shapes) {
            double area = shape.calculateArea();
            System.out.println("  " + shape.getClass().getSimpleName() +
                             " (" + shape.getColor() + ") - Area: " +
                             String.format("%.2f", area));
            totalArea += area;
        }
        System.out.println("Total area: " + String.format("%.2f", totalArea));

        // ========================================
        // 3. INTERFACE DEMONSTRATION
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("3. INTERFACE DEMONSTRATION");
        System.out.println("=".repeat(70));

        // Call static method from interface
        System.out.println("\nCalling static method from Drawable interface:");
        Drawable.printInfo();

        // ========================================
        // 4. MULTIPLE INTERFACE IMPLEMENTATION
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("4. MULTIPLE INTERFACE IMPLEMENTATION");
        System.out.println("=".repeat(70));

        SmartRectangle smartRect = new SmartRectangle("Cyan", 10.0, 8.0);

        System.out.println("\nSmartRectangle implements both Drawable and Resizable:");
        smartRect.displayInfo();

        System.out.println("\n--- Testing Drawable interface methods ---");
        smartRect.draw();

        System.out.println("\nUsing default method from Drawable:");
        smartRect.display();

        System.out.println("\nUsing setVisible method:");
        smartRect.setVisible(false);

        System.out.println("\n--- Testing Resizable interface methods ---");

        System.out.println("\nResizing to 150%:");
        smartRect.resize(150);

        System.out.println("\nDoubling the size (using default method):");
        smartRect.doubleSize();

        System.out.println("\nHalving the size (using default method):");
        smartRect.halveSize();

        System.out.println("\nResizing to specific dimensions:");
        smartRect.resizeTo(15.0, 10.0);

        System.out.println("\nResetting to original size:");
        smartRect.resetSize();

        // ========================================
        // 5. POLYMORPHISM WITH INTERFACES
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("5. POLYMORPHISM WITH INTERFACES");
        System.out.println("=".repeat(70));

        // Using interface reference - polymorphism
        System.out.println("\nUsing Drawable reference:");
        Drawable drawable = smartRect;
        drawable.draw();
        // drawable.resize(50); // ERROR! Drawable doesn't have resize method

        System.out.println("\nUsing Resizable reference:");
        Resizable resizable = smartRect;
        resizable.resize(120);
        // resizable.draw(); // ERROR! Resizable doesn't have draw method

        System.out.println("\nUsing Shape reference:");
        Shape shape = smartRect;
        System.out.println("Area: " + String.format("%.2f", shape.calculateArea()));
        // shape.draw(); // ERROR! Shape doesn't have draw method

        // ========================================
        // 6. PRACTICAL EXAMPLE - SHAPE RENDERER
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("6. PRACTICAL EXAMPLE - SHAPE RENDERER");
        System.out.println("=".repeat(70));

        // Create multiple SmartRectangles
        SmartRectangle[] smartShapes = {
            new SmartRectangle("Red", 5.0, 5.0),
            new SmartRectangle("Green", 8.0, 4.0),
            new SmartRectangle("Blue", 6.0, 6.0)
        };

        System.out.println("\nDrawing all shapes:");
        for (SmartRectangle sr : smartShapes) {
            sr.draw();
            System.out.println();
        }

        System.out.println("Resizing all shapes to 50%:");
        for (SmartRectangle sr : smartShapes) {
            sr.resize(50);
        }

        // ========================================
        // 7. DEMONSTRATING ABSTRACT CLASS BENEFITS
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("7. DEMONSTRATING ABSTRACT CLASS BENEFITS");
        System.out.println("=".repeat(70));

        System.out.println("\nAll shapes inherit the fill() method from Shape:");
        circle.fill();
        rectangle.fill();
        triangle.fill();

        System.out.println("\nChanging colors (using inherited setter):");
        circle.setColor("Pink");
        rectangle.setColor("Violet");
        triangle.setColor("Lime");

        System.out.println("New colors: " + circle.getColor() + ", " +
                          rectangle.getColor() + ", " + triangle.getColor());

        // ========================================
        // 8. KEY CONCEPTS SUMMARY
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("8. KEY CONCEPTS DEMONSTRATED");
        System.out.println("=".repeat(70));

        System.out.println("\n✓ Abstract Classes:");
        System.out.println("  - Cannot be instantiated");
        System.out.println("  - Can have both abstract and concrete methods");
        System.out.println("  - Can have constructors, fields, and all access modifiers");
        System.out.println("  - Support single inheritance (extends one class)");

        System.out.println("\n✓ Interfaces:");
        System.out.println("  - Cannot be instantiated");
        System.out.println("  - Methods are public and abstract by default (before Java 8)");
        System.out.println("  - Can have default and static methods (Java 8+)");
        System.out.println("  - Support multiple inheritance (implements many interfaces)");

        System.out.println("\n✓ When to Use:");
        System.out.println("  - Abstract Class: 'is-a' relationship, shared code, need fields/constructors");
        System.out.println("  - Interface: 'can-do' relationship, contracts, multiple inheritance");

        System.out.println("\n✓ Polymorphism:");
        System.out.println("  - Different objects respond to same method call differently");
        System.out.println("  - Enables flexible and extensible code");

        System.out.println("\n✓ Multiple Interface Implementation:");
        System.out.println("  - A class can extend one abstract class AND implement many interfaces");
        System.out.println("  - Combines inheritance with multiple behaviors");

        // ========================================
        // 9. ADVANCED: DEFAULT METHOD BEHAVIOR
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("9. ADVANCED: DEFAULT METHOD BEHAVIOR");
        System.out.println("=".repeat(70));

        SmartRectangle advancedRect = new SmartRectangle("Gold", 12.0, 8.0);

        System.out.println("\nUsing inherited default methods:");
        System.out.println("From Drawable interface:");
        advancedRect.display(); // Uses default implementation

        System.out.println("\nFrom Resizable interface:");
        advancedRect.doubleSize(); // Uses default implementation
        System.out.println("Current area: " + String.format("%.2f", advancedRect.calculateArea()));

        System.out.println("\nBut resetSize() is overridden in SmartRectangle:");
        advancedRect.resetSize(); // Uses overridden implementation
        System.out.println("Area after reset: " + String.format("%.2f", advancedRect.calculateArea()));

        // ========================================
        // 10. STATIC METHOD FROM INTERFACE
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("10. STATIC METHODS IN INTERFACES");
        System.out.println("=".repeat(70));

        System.out.println("\nUsing static method from Resizable interface:");
        double newDim = Resizable.calculateNewDimension(100, 150);
        System.out.println("100 units at 150% = " + newDim + " units");

        // ========================================
        // CONCLUSION
        // ========================================
        System.out.println("\n" + "=".repeat(70));
        System.out.println("CONCLUSION");
        System.out.println("=".repeat(70));

        System.out.println("\nAbstraction is powerful because it:");
        System.out.println("  1. Hides complexity - focus on WHAT, not HOW");
        System.out.println("  2. Enables polymorphism - treat different objects uniformly");
        System.out.println("  3. Promotes code reuse - share common functionality");
        System.out.println("  4. Improves maintainability - changes don't break client code");
        System.out.println("  5. Provides clear contracts - defines expectations");

        System.out.println("\n" + "=".repeat(70));
        System.out.println("End of Abstraction Demonstration");
        System.out.println("=".repeat(70));
    }
}
