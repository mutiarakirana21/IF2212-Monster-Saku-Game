package com.monstersaku;

import java.util.Random;

public class NormalMove extends Move {
    private int basePower;

    public NormalMove(String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }

    //getter
    public int getBasePower(){
        return this.basePower;
    }
    
}
