package com.monstersaku;

public class SpecialMove extends Move{
    private int basePower;

    public SpecialMove(String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }
    
}
