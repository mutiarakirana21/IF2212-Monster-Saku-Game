package com.monstersaku;

public class DefaultMove extends Move{
    private int basePower;

    public DefaultMove(String name, ElementType elementType){
        super(name, elementType, 100, 0, 999);
        this.basePower = 50;
    }

    public int getBasePower() {
        return basePower;
    }
}
