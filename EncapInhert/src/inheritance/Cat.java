package inheritance;

/**
 * Cat - Child class (Subclass) of Animal
 * Demonstrates single inheritance and polymorphism
 */
public class Cat extends Animal {
    private String furColor;
    private boolean isIndoor;

    // Constructor
    public Cat(String name, int age, String furColor, boolean isIndoor) {
        super(name, age, "Cat");  // Call parent constructor
        this.furColor = furColor;
        this.isIndoor = isIndoor;
        System.out.println("Cat constructor called for: " + name);
    }

    // Override makeSound (polymorphism)
    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow! Meow!");
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Fur Color: " + furColor);
        System.out.println("Indoor: " + (isIndoor ? "Yes" : "No"));
    }

    // Cat-specific methods
    public void meow() {
        System.out.println(name + " is meowing softly");
    }

    public void purr() {
        System.out.println(name + " is purring contentedly");
    }

    public void scratch() {
        if (isIndoor) {
            System.out.println(name + " is scratching the furniture!");
        } else {
            System.out.println(name + " is scratching a tree");
        }
    }

    public void climb() {
        System.out.println(name + " is climbing up high!");
    }

    // Getters
    public String getFurColor() {
        return furColor;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "', age=" + age + ", furColor='" + furColor + "', indoor=" + isIndoor + "}";
    }
}
