/**
 * Abstract Shape class demonstrating abstraction concepts
 *
 * This class serves as a blueprint for all shapes.
 * It defines what all shapes must have (calculateArea, calculatePerimeter)
 * but doesn't specify HOW to calculate them - that's up to concrete classes.
 *
 * Key Points:
 * 1. Cannot be instantiated directly (new Shape() won't work)
 * 2. Has both abstract methods (must be implemented) and concrete methods (inherited as-is)
 * 3. Has instance variables (color) that all shapes share
 * 4. Has a constructor to initialize common properties
 */
public abstract class Shape {
    // Instance variable - shared by all shapes
    private String color;

    // Constructor - called when creating concrete shape objects
    public Shape(String color) {
        this.color = color;
    }

    // Abstract method - must be implemented by all concrete shapes
    // Each shape calculates its area differently
    public abstract double calculateArea();

    // Abstract method - must be implemented by all concrete shapes
    // Each shape calculates its perimeter differently
    public abstract double calculatePerimeter();

    // Concrete method - inherited by all shapes as-is
    // This implementation works for all shapes
    public void displayInfo() {
        System.out.println("Shape Information:");
        System.out.println("  Color: " + color);
        System.out.println("  Area: " + String.format("%.2f", calculateArea()));
        System.out.println("  Perimeter: " + String.format("%.2f", calculatePerimeter()));
    }

    // Concrete method - getter for color
    public String getColor() {
        return color;
    }

    // Concrete method - setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Concrete method - inherited by all shapes
    public void fill() {
        System.out.println("Filling the shape with " + color + " color");
    }
}
