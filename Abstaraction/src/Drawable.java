/**
 * Drawable interface - defines a contract for objects that can be drawn
 *
 * This interface demonstrates:
 * 1. Pure abstraction - only method signatures, no implementation (before Java 8)
 * 2. A "can-do" relationship - anything that CAN be drawn implements this
 * 3. Multiple unrelated classes can implement this interface
 * 4. Default methods (Java 8+) to provide optional behavior
 *
 * Key Points:
 * - All methods are public and abstract by default (no need to specify)
 * - Cannot be instantiated
 * - Classes implementing this MUST provide implementation for draw()
 * - Can be implemented by any class, regardless of inheritance hierarchy
 */
public interface Drawable {

    /**
     * Draw the object on screen
     * This is an abstract method - implementing classes must define HOW to draw
     */
    void draw();

    /**
     * Erase the object from screen
     * This is an abstract method
     */
    void erase();

    /**
     * Default method (Java 8+) - provides a default implementation
     * Classes can use this as-is or override it
     */
    default void display() {
        System.out.println("Displaying the drawable object...");
        draw();
    }

    /**
     * Default method to show/hide the object
     */
    default void setVisible(boolean visible) {
        if (visible) {
            draw();
        } else {
            erase();
        }
    }

    /**
     * Static method (Java 8+) - utility method for the interface
     * Called using: Drawable.printInfo()
     */
    static void printInfo() {
        System.out.println("Drawable Interface: Defines contract for drawable objects");
    }
}
