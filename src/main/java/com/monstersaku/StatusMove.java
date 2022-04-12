package com.monstersaku;

public class StatusMove extends Move{
    private String target; //harusnya dari config ada
    private StatusCondition statcon;
    private int hpEffect;
    private int attackEffect;
    private int defenseEffect;
    private int spAttEffect;
    private int spDefEffect;
    private int speedEffect;


    public StatusMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, StatusCondition statcon, int hpEffect, int attackEffect, int defenseEffect, int spAttEffect, int spDefEffect, int speedEffect){
        super(id, name, elementType, accuracy, priority, ammunition);
        this.target = target;
        this.statcon = statcon;
        this.hpEffect = hpEffect;
        this.attackEffect = attackEffect;
        this.defenseEffect = defenseEffect;
        this.spAttEffect = spAttEffect;
        this.spDefEffect = spDefEffect;
        this.speedEffect = speedEffect;
    }

    public StatusMove(StatusMove statmove, int ammunition){
        super(statmove, ammunition);
        this.target = statmove.getTarget();
        this.statcon = statmove.getStatusCondition();
        this.hpEffect = statmove.getHPEffect();
        this.attackEffect = statmove.getAttackEffect();
        this.defenseEffect = statmove.getDefenseEffect();
        this.spAttEffect = statmove.getSpAttEffect();
        this.spDefEffect = statmove.getDefenseEffect();
        this.speedEffect = statmove.getSpeedEffect();
    }
    public String getTarget(){
        return this.target;
    }

    public int getHPEffect(){
        return this.hpEffect;
    }

    public int getAttackEffect(){
        return this.attackEffect;
    }

    public int getDefenseEffect(){
        return this.defenseEffect;
    }

    public int getSpAttEffect(){
        return this.spAttEffect;
    }

    public int getSpDefEffect(){
        return this.spDefEffect;
    }

    public int getSpeedEffect(){
        return speedEffect;
    }

    public StatusCondition getStatusCondition(){
        return statcon;
    }

    //buat debug
    // public void printMoveProperties(){
    //     System.out.printf("%d, %s, %s, %d, %d, %d, %s, %s, %d, %d, %d, %d, %d, %d\n", super.getid(), super.getName(), super.getElType(), super.getAccuracy(), super.getPriority(), super.getAmmunition(), target, statcon, hpEffect, attackEffect, defenseEffect, spAttEffect, spDefEffect, speedEffect);
    // }
}
