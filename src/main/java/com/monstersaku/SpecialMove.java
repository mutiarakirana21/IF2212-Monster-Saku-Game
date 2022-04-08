package com.monstersaku;

public class SpecialMove extends Move{
    private int basePower;

    public SpecialMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(id, name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }
    
    //getter
    public int getBasePower(){
        return this.basePower;
    }
    
}
