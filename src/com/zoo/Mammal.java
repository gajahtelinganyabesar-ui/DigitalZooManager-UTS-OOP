package com.zoo; // Ganti jika nama package-mu berbeda

// Extends Animal
public class Mammal extends Animal {
    // Private Field
    private String furColor;

    // Constructor: Must call the parent constructor (super())
    public Mammal(String name, int age, String furColor) {
        super(name, age);
        this.furColor = furColor;
    }

    // Getter/Setter for furColor
    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    // Method Overriding: makeSound()
    @Override
    public String makeSound() {
        return "The mammal makes a sound.";
    }

    // Method Overriding: getInfo() (Include fur color)
    @Override
    public String getInfo() {
        return super.getInfo() + ", Fur Color: " + furColor;
    }
}