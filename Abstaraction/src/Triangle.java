/**
 * Triangle class - extends the abstract Shape class
 *
 * This is a concrete class that provides specific implementation
 * for the abstract methods defined in Shape.
 *
 * Demonstrates:
 * - Implementation of abstract methods with triangle-specific logic
 * - Additional properties (base, height, side1, side2, side3)
 * - Additional methods (getTriangleType)
 * - More complex calculations
 */
public class Triangle extends Shape {
    // Triangle-specific properties
    private double side1;
    private double side2;
    private double side3;
    private double base;
    private double height;

    // Constructor for triangle with base and height (for area calculation)
    public Triangle(String color, double base, double height, double side1, double side2, double side3) {
        super(color);
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Simplified constructor (assumes equilateral triangle)
    public Triangle(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
        // For simplified calculations
        this.side1 = base;
        this.side2 = base;
        this.side3 = base;
    }

    // Implementation of abstract method from Shape
    // Formula: Area = (base * height) / 2
    @Override
    public double calculateArea() {
        return (base * height) / 2.0;
    }

    // Implementation of abstract method from Shape
    // Formula: Perimeter = side1 + side2 + side3
    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    // Triangle-specific method - determine triangle type
    public String getTriangleType() {
        if (side1 == side2 && side2 == side3) {
            return "Equilateral";
        } else if (side1 == side2 || side2 == side3 || side1 == side3) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    // Check if triangle is valid (triangle inequality theorem)
    public boolean isValidTriangle() {
        return (side1 + side2 > side3) &&
               (side2 + side3 > side1) &&
               (side1 + side3 > side2);
    }

    // Getters
    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // Setters
    public void setBase(double base) {
        if (base > 0) {
            this.base = base;
        } else {
            System.out.println("Base must be positive!");
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("Height must be positive!");
        }
    }

    // Override displayInfo to add triangle-specific information
    @Override
    public void displayInfo() {
        System.out.println("Triangle Information:");
        System.out.println("  Color: " + getColor());
        System.out.println("  Base: " + String.format("%.2f", base));
        System.out.println("  Height: " + String.format("%.2f", height));
        System.out.println("  Sides: " + String.format("%.2f", side1) + ", " +
                          String.format("%.2f", side2) + ", " +
                          String.format("%.2f", side3));
        System.out.println("  Type: " + getTriangleType());
        System.out.println("  Valid: " + (isValidTriangle() ? "Yes" : "No"));
        System.out.println("  Area: " + String.format("%.2f", calculateArea()));
        System.out.println("  Perimeter: " + String.format("%.2f", calculatePerimeter()));
    }
}
