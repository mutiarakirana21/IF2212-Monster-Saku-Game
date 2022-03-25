package com.monstersaku;

public class Stats {
    
    // Attribute
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    // Constructor
    public Stats() {}

    // Setter
    public void setHealtPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }
    public void setAttack(double attack) {
        this.attack = attack;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public void setSpecialAttack(double specialAttack) {
        this.specialAttack = specialAttack;
    }
    public void setSpecialDefense(double specialDefense) {
        this.specialDefense = specialDefense;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Getter
    public double getHealthPoint() {
        return healthPoint;
    }
    public double getAttack() {
        return attack;
    }
    public double getDefense() {
        return defense;
    }
    public double getSpecialAttack() {
        return specialAttack;
    }
    public double getSpecialDefense() {
        return specialDefense;
    }
    public double getSpeed() {
        return speed;
    }

    // Check if dead
    public boolean isHPZero() {
        return (healthPoint == 0);
    }
}