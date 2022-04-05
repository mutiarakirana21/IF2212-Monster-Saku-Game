package com.monstersaku;

public interface StatusCondition {
    StatusCondition NOTHING = null;
    public void burn();
    public void poison();
    public int sleep();
    public int paralyze();
}

/*
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
*/