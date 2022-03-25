package com.monstersaku;

public interface Condition {
    public void burn();
    public void poison();
    public int sleep();
    public int paralyze();
}

/*
public class Condition extends Stats implements ConditionInterface{

    public void burn(){
        healthPoint = 0.875 * healthPoint;
        attack = 0.5 * attack;
    }

    public void poison(){
        healthPoint = 0.9375 * healthPoint;
    }

    public int sleep(){
        Random dice = new Random();
        int number;
        number = 1+dice.nextInt(7);
        return number;
    }

    public void paralyze(){
        speed = 0.5 * speed;
    }
}
*/