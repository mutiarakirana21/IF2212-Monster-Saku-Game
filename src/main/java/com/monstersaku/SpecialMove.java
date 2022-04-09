package com.monstersaku;

public class SpecialMove extends Move{
    private int basePower;

    public SpecialMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(id, name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }
    public SpecialMove (SpecialMove specialMove, int ammunition) {
        super(specialMove, ammunition);
        this.basePower = specialMove.getBasePower();
    }
    //getter
    public int getBasePower(){
        return this.basePower;
    }
    
}
