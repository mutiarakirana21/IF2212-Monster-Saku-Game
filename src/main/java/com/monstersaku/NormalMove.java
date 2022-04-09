package com.monstersaku;

public class NormalMove extends Move {
    private int basePower;

    public NormalMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(id, name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }
    public NormalMove (NormalMove normalMove, int ammunition) {
        super(normalMove, ammunition);
        this.basePower = normalMove.getBasePower();
    }
    //getter
    public int getBasePower(){
        return this.basePower;
    }
    
}
