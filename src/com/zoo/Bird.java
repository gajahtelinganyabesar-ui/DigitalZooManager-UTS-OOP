package com.zoo; // Ganti jika nama package-mu berbeda

// Extends Animal
public class Bird extends Animal {
    // Private Field
    private boolean canFly;

    // Constructor: Must call the parent constructor
    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    // Getter/Setter for canFly
    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    // Method Overriding: makeSound()
    @Override
    public String makeSound() {
        return "The bird chirps.";
    }

    // Method Overriding: getInfo() (Include whether it can fly)
    @Override
    public String getInfo() {
        String flyStatus = canFly ? "Can Fly: Yes" : "Can Fly: No";
        return super.getInfo() + ", " + flyStatus;
    }
}