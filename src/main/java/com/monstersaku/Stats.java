package com.monstersaku;
import java.util.Random;

public class Stats implements StatusCondition {
    
    // Attribute
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;
    private int statBuff;

    // Constructor
    public Stats() {}

    // Setter
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

    public int getStatBuff() {
        return statBuff;
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

     //methods buff

     public void setAttackBuff(double attack){
         int x = getStatBuff();
         if(x==-4){
             attack = attack * 2/6;
         }else if(x==-3){
             attack = attack * 2/5;
         }else if(x==-2){
             attack = attack * 2/4;
         }else if(x==-1){
             attack = attack * 2/3;
         }else if(x==0){
             attack = attack * 1;
         }else if(x==1){
             attack = attack * 3/2;
         }else if(x==2){
             attack = attack * 4/2;
         }else if(x==3){
             attack = attack * 5/2;
         }else if(x==4){
             attack = attack * 6/2;
         }
         setAttack(attack);
     }
 
     public void setDefenseBuff(int defense){
         int x = getStatBuff();
         if(x==-4){
          defense = defense * 2/6;
         }else if(x==-3){
          defense = defense * 2/5;
         }else if(x==-2){
          defense = defense * 2/4;
         }else if(x==-1){
          defense = defense * 2/3;
         }else if(x==0){
          defense = defense * 1;
         }else if(x==1){
          defense = defense * 3/2;
         }else if(x==2){
          defense = defense * 4/2;
         }else if(x==3){
          defense = defense * 5/2;
         }else if(x==4){
          defense = defense * 6/2;
         }
         setDefense(defense);
     }
 
     public void setSpecialAttackBuff(int specialAttack){
         int x = getStatBuff();
         if(x==-4){
          specialAttack = specialAttack * 2/6;
         }else if(x==-3){
          specialAttack = specialAttack * 2/5;
         }else if(x==-2){
          specialAttack = specialAttack * 2/4;
         }else if(x==-1){
          specialAttack = specialAttack * 2/3;
         }else if(x==0){
          specialAttack = specialAttack * 1;
         }else if(x==1){
          specialAttack = specialAttack * 3/2;
         }else if(x==2){
          specialAttack = specialAttack * 4/2;
         }else if(x==3){
          specialAttack = specialAttack * 5/2;
         }else if(x==4){
          specialAttack = specialAttack * 6/2;
         }
         setSpecialAttack(specialAttack);
     }
 
     public void setSpecialDefenseBuff(int specialDefense){
         int x = getStatBuff();
         if(x==-4){
          specialDefense = specialDefense * 2/6;
         }else if(x==-3){
          specialDefense = specialDefense * 2/5;
         }else if(x==-2){
          specialDefense = specialDefense * 2/4;
         }else if(x==-1){
          specialDefense = specialDefense * 2/3;
         }else if(x==0){
          specialDefense = specialDefense * 1;
         }else if(x==1){
          specialDefense = specialDefense * 3/2;
         }else if(x==2){
          specialDefense = specialDefense * 4/2;
         }else if(x==3){
          specialDefense = specialDefense * 5/2;
         }else if(x==4){
          specialDefense = specialDefense * 6/2;
         }
         setSpecialDefense(specialDefense);
     }
 
     public void setSpecialSpeed(int speed){
         int x = getStatBuff();
         if(x==-4){
          speed = speed * 2/6;
         }else if(x==-3){
          speed = speed * 2/5;
         }else if(x==-2){
          speed = speed * 2/4;
         }else if(x==-1){
          speed = speed * 2/3;
         }else if(x==0){
          speed = speed * 1;
         }else if(x==1){
          speed = speed * 3/2;
         }else if(x==2){
          speed = speed * 4/2;
         }else if(x==3){
          speed = speed * 5/2;
         }else if(x==4){
          speed = speed * 6/2;
         }
         setSpeed(speed);
     }

}