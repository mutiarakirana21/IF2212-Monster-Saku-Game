package com.monstersaku;

public class StatusMove extends Move{
    private Monster target;
    private int hpEffect;
    private int attackEffect;
    private int defenseEffect;
    private int spAttEffect;
    private int spDefEffect;
    private int speedEffect;


    public StatusMove(String name, ElementType elementType, int accuracy, int priority, int ammunition, Monster target, int hpEffect, int attackEffect, int defenseEffect, int spAttEffect, int spDefEffect, int speedEffect){
        super(name, elementType, accuracy, priority, ammunition);
        this.target = target;
        this.hpEffect = hpEffect;
        this.attackEffect = attackEffect;
        this.defenseEffect = defenseEffect;
        this.spAttEffect = spAttEffect;
        this.spDefEffect = spDefEffect;
        this.speedEffect = speedEffect;
    }
}
