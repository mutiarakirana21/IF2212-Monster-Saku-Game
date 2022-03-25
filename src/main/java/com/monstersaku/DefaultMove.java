package com.monstersaku;

public class DefaultMove extends Move{
    private int basePower;

    public DefaultMove(String name){
        super(name, ElementType.NORMAL, 100, 0, 999);
        this.basePower = 50;
    }

    public int getBasePower() {
        return basePower;
    }
}
