package encapsulation;

/**
 * Circle class demonstrating read-only computed properties
 * - radius can be modified
 * - area and circumference are computed (no setters!)
 */
public class Circle {
    private double radius;

    // Constructor
    public Circle(double radius) {
        setRadius(radius);
    }

    // ==================== GETTER/SETTER for radius ====================

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive, got: " + radius);
        }
        this.radius = radius;
    }

    // ==================== READ-ONLY COMPUTED PROPERTIES ====================

    /**
     * Area is computed from radius - NO setter!
     * Always up-to-date with current radius
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Circumference is computed from radius - NO setter!
     * Always up-to-date with current radius
     */
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    /**
     * Diameter is computed from radius - NO setter!
     */
    public double getDiameter() {
        return 2 * radius;
    }

    // ==================== UTILITY METHODS ====================

    public void displayInfo() {
        System.out.println("\n=== Circle Information ===");
        System.out.println("Radius: " + String.format("%.2f", radius));
        System.out.println("Diameter: " + String.format("%.2f", getDiameter()));
        System.out.println("Circumference: " + String.format("%.2f", getCircumference()));
        System.out.println("Area: " + String.format("%.2f", getArea()));
        System.out.println("=========================");
    }

    @Override
    public String toString() {
        return String.format("Circle{radius=%.2f, area=%.2f, circumference=%.2f}",
                             radius, getArea(), getCircumference());
    }
}
