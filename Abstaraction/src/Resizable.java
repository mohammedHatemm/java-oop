/**
 * Resizable interface - defines a contract for objects that can be resized
 *
 * This interface demonstrates:
 * 1. Another "can-do" relationship - anything that CAN be resized implements this
 * 2. Can be combined with other interfaces (multiple interface implementation)
 * 3. Provides different resizing options (by percentage, by factor, to specific size)
 * 4. Default methods for convenience
 *
 * Key Points:
 * - A class can implement both Drawable AND Resizable
 * - This achieves multiple inheritance of behavior
 * - Each implementing class defines HOW to resize based on its properties
 */
public interface Resizable {

    /**
     * Resize the object by a given percentage
     * @param percent - percentage to resize (100 = same size, 200 = double, 50 = half)
     */
    void resize(double percent);

    /**
     * Resize the object to specific dimensions
     * @param width - new width
     * @param height - new height
     */
    void resizeTo(double width, double height);

    /**
     * Default method - double the size
     * Uses the resize method with 200%
     */
    default void doubleSize() {
        System.out.println("Doubling the size...");
        resize(200);
    }

    /**
     * Default method - halve the size
     * Uses the resize method with 50%
     */
    default void halveSize() {
        System.out.println("Halving the size...");
        resize(50);
    }

    /**
     * Default method - reset to original size
     * Uses the resize method with 100%
     */
    default void resetSize() {
        System.out.println("Resetting to original size...");
        resize(100);
    }

    /**
     * Static utility method - calculate new dimension based on percentage
     */
    static double calculateNewDimension(double original, double percent) {
        return original * (percent / 100.0);
    }
}
