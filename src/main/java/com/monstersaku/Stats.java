package com.monstersaku;
import java.util.Random;

public class Stats implements Condition {
    
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


    //implementasi Interface Condition
    public void burn(){
        healthPoint = 0.875 * healthPoint;
        attack = 0.5 * attack;
    }
    // catatan: aing bingung dapet max hpnya dari mana wkwk
    public void poison(){
        healthPoint = 0.9375 * healthPoint;
    }
    // idem
    public int sleep(){
        Random dice = new Random();
        int number;
        number = 1+dice.nextInt(7);
        return number;
    }
    // number yang dihasilkan sleep menunjukan berapa ronde monster akan sleep
    public int paralyze(){
        speed = 0.5 * speed;
        Random dice = new Random();
        int number;
        number = 1+dice.nextInt(4);
        return number;
    }
    //number yang dihasilkan menunjukan kemungkinan monster skip ronde (co: number 1 monster skip, 234 tidak skip)

}