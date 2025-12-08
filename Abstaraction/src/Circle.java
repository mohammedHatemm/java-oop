/**
 * Circle class - extends the abstract Shape class
 *
 * This is a concrete class that provides specific implementation
 * for the abstract methods defined in Shape.
 *
 * Key Points:
 * 1. Must implement all abstract methods from Shape
 * 2. Inherits concrete methods from Shape (displayInfo, fill, getColor, setColor)
 * 3. Can have its own specific properties (radius)
 * 4. Can have its own specific methods (getDiameter)
 */
public class Circle extends Shape {
    // Circle-specific property
    private double radius;

    // Constructor - calls parent constructor to initialize color
    public Circle(String color, double radius) {
        super(color);  // Call Shape constructor
        this.radius = radius;
    }

    // Implementation of abstract method from Shape
    // Formula: Area = π * r²
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Implementation of abstract method from Shape
    // Formula: Perimeter (Circumference) = 2 * π * r
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    // Circle-specific method
    public double getDiameter() {
        return 2 * radius;
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }

    // Setter for radius
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Radius must be positive!");
        }
    }

    // Override displayInfo to add circle-specific information
    @Override
    public void displayInfo() {
        System.out.println("Circle Information:");
        System.out.println("  Color: " + getColor());
        System.out.println("  Radius: " + String.format("%.2f", radius));
        System.out.println("  Diameter: " + String.format("%.2f", getDiameter()));
        System.out.println("  Area: " + String.format("%.2f", calculateArea()));
        System.out.println("  Circumference: " + String.format("%.2f", calculatePerimeter()));
    }
}
