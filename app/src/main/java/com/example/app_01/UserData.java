package com.example.app_01;

public class UserData {
    private final int age;
    private final int feet;
    private final int inches;
    private final int centimeters;
    private final double weight;

    public UserData(int age, int feet, int inches, int centimeters, double weight) {
        this.age = age;
        this.feet = feet;
        this.inches = inches;
        this.centimeters = centimeters;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public int getFeet() {
        return feet;
    }

    public int getInches() {
        return inches;
    }
    public int getCentimeters() {
        return centimeters;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "age=" + age +
                ", feet=" + feet +
                ", inches=" + inches +
                ", centimeters=" + centimeters +
                ", weight=" + weight +
                '}';
    }
}