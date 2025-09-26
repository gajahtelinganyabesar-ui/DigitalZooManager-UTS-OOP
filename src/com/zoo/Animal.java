package com.zoo; // Ganti jika nama package-mu berbeda

public class Animal {
    // Private Fields (Encapsulation)
    private String name;
    private int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Methods
    public String makeSound() {
        return "The animal makes a sound.";
    }

    public String getInfo() {
        return "Name: " + name + ", Age: " + age;
    }
}