/**
 * SmartRectangle - Demonstrates Multiple Interface Implementation
 *
 * This class shows the power of abstraction by:
 * 1. Extending an abstract class (Shape) - "is-a" relationship
 * 2. Implementing multiple interfaces (Drawable, Resizable) - "can-do" relationships
 *
 * This demonstrates:
 * - A SmartRectangle IS-A Shape (inheritance)
 * - A SmartRectangle CAN-BE Drawn (Drawable interface)
 * - A SmartRectangle CAN-BE Resized (Resizable interface)
 *
 * Java allows:
 * - Single inheritance (extends one class)
 * - Multiple interface implementation (implements many interfaces)
 */
public class SmartRectangle extends Shape implements Drawable, Resizable {

    // SmartRectangle-specific properties
    private double width;
    private double height;
    private double originalWidth;
    private double originalHeight;

    // Constructor
    public SmartRectangle(String color, double width, double height) {
        super(color);  // Call Shape constructor
        this.width = width;
        this.height = height;
        // Store original dimensions for resetting
        this.originalWidth = width;
        this.originalHeight = height;
    }

    // ========================================
    // Implementation of Shape abstract methods
    // ========================================

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // ========================================
    // Implementation of Drawable interface
    // ========================================

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " rectangle:");
        System.out.println("  Width: " + String.format("%.2f", width));
        System.out.println("  Height: " + String.format("%.2f", height));
        // Simple ASCII representation
        System.out.println("  +----------+");
        System.out.println("  |          |");
        System.out.println("  |          |");
        System.out.println("  +----------+");
    }

    @Override
    public void erase() {
        System.out.println("Erasing the " + getColor() + " rectangle from screen");
    }

    // Can use default methods from Drawable (display, setVisible) as-is
    // or override them if needed

    // ========================================
    // Implementation of Resizable interface
    // ========================================

    @Override
    public void resize(double percent) {
        System.out.println("Resizing rectangle to " + percent + "% of current size");
        width = width * (percent / 100.0);
        height = height * (percent / 100.0);
        System.out.println("New dimensions: " + String.format("%.2f", width) +
                          " x " + String.format("%.2f", height));
    }

    @Override
    public void resizeTo(double width, double height) {
        System.out.println("Resizing rectangle to specific dimensions");
        this.width = width;
        this.height = height;
        System.out.println("New dimensions: " + String.format("%.2f", width) +
                          " x " + String.format("%.2f", height));
    }

    // Override default method from Resizable to provide custom behavior
    @Override
    public void resetSize() {
        System.out.println("Resetting rectangle to original size");
        this.width = originalWidth;
        this.height = originalHeight;
        System.out.println("Dimensions reset to: " + String.format("%.2f", width) +
                          " x " + String.format("%.2f", height));
    }

    // Can use other default methods from Resizable (doubleSize, halveSize) as-is

    // ========================================
    // SmartRectangle-specific methods
    // ========================================

    public boolean isSquare() {
        return Math.abs(width - height) < 0.001; // Account for floating-point precision
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void displayInfo() {
        System.out.println("SmartRectangle Information:");
        System.out.println("  Color: " + getColor());
        System.out.println("  Current Width: " + String.format("%.2f", width));
        System.out.println("  Current Height: " + String.format("%.2f", height));
        System.out.println("  Original Width: " + String.format("%.2f", originalWidth));
        System.out.println("  Original Height: " + String.format("%.2f", originalHeight));
        System.out.println("  Is Square: " + (isSquare() ? "Yes" : "No"));
        System.out.println("  Area: " + String.format("%.2f", calculateArea()));
        System.out.println("  Perimeter: " + String.format("%.2f", calculatePerimeter()));
    }
}
