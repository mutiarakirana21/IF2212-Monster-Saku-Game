package com.monstersaku;

public class Stats{
    
    // Attribute
    private double maxHP;
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;
    private StatsBuff statBuff;

    // Constructor
    public Stats(Double healthPoint, Double attack, Double defense, Double specialAttack, Double specialDefense, Double speed) {
        this.maxHP = healthPoint;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.statBuff = new StatsBuff();
    }

    // Setter
    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }
    public void setHealthPoint(double healthPoint) {
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
    public double getmaxHP(){
        return this.maxHP;
    }
    public double getHealthPoint() {
        return healthPoint;
    }
    public double getAttack() {
        return attack * statBuff.getAttackBuff();
    }
    public double getDefense() {
        return defense * statBuff.getDefenseBuff();
    }
    public double getSpecialAttack() {
        return specialAttack * statBuff.getSpecialAttackBuff();
    }
    public double getSpecialDefense() {
        return specialDefense * statBuff.getSpecialDefenseBuff();
    }
    public double getSpeed() {
        return speed * statBuff.getSpeedBuff();
    }

    public StatsBuff getStatsBuff(){
        return statBuff;
    }

    // Check if dead
    public boolean isHPZero() {
        return (healthPoint == 0);
    }
}