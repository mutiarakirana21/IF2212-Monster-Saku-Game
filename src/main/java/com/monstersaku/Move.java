package com.monstersaku;

public abstract class Move {
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;

    //konstruktor move
    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition){
        this.name = name; 
        this.elementType = elementType; 
        this.accuracy = accuracy; 
        this.priority = priority; 
        this.ammunition = ammunition;
    }
    
    //abstract class yang harus diimplementasikan di sub-class
    //public abstract void damageDone();

    //getter
}
