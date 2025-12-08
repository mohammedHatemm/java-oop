/**
 * Rectangle class - extends the abstract Shape class
 *
 * This is a concrete class that provides specific implementation
 * for the abstract methods defined in Shape.
 *
 * Demonstrates:
 * - Implementation of abstract methods with rectangle-specific logic
 * - Additional properties (width, height)
 * - Additional methods (isSquare)
 */
public class Rectangle extends Shape {
    // Rectangle-specific properties
    private double width;
    private double height;

    // Constructor
    public Rectangle(String color, double width, double height) {
        super(color);  // Call Shape constructor
        this.width = width;
        this.height = height;
    }

    // Implementation of abstract method from Shape
    // Formula: Area = width * height
    @Override
    public double calculateArea() {
        return width * height;
    }

    // Implementation of abstract method from Shape
    // Formula: Perimeter = 2 * (width + height)
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // Rectangle-specific method - check if it's a square
    public boolean isSquare() {
        return width == height;
    }

    // Getters
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Setters with validation
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("Width must be positive!");
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("Height must be positive!");
        }
    }

    // Override displayInfo to add rectangle-specific information
    @Override
    public void displayInfo() {
        System.out.println("Rectangle Information:");
        System.out.println("  Color: " + getColor());
        System.out.println("  Width: " + String.format("%.2f", width));
        System.out.println("  Height: " + String.format("%.2f", height));
        System.out.println("  Is Square: " + (isSquare() ? "Yes" : "No"));
        System.out.println("  Area: " + String.format("%.2f", calculateArea()));
        System.out.println("  Perimeter: " + String.format("%.2f", calculatePerimeter()));
    }
}
