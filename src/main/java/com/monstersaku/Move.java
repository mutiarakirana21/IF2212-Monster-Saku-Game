package com.monstersaku;

import java.util.Random;

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
    public String getName(){
        return this.name;
    }

    public ElementType getElType(){
        return this.elementType;
    }

    public int getAccuracy(){
        return this.accuracy;
    }

    public int getPriority(){
        return this.priority;
    }

    public int getAmmunition(){
        return this.ammunition;
    }

    //setter
    public void setAmmunition(int ammunition){
        this.ammunition = ammunition;
    }

    public Move compareMove(Monster mon1, Move move1, Monster mon2, Move move2){
        if(move1.getPriority() > move2.getPriority()){
            return move1;

        }else if(move1.getPriority() == move2.getPriority()){
            if(mon1.getStat().getSpeed() > mon2.getStat().getSpeed()){
                return move1;

            }else if(mon1.getStat().getSpeed() == mon2.getStat().getSpeed()){
                Random rand = new Random();
                int first = rand.nextInt(2);
                if(first == 0){
                    return move1;
                }else{
                    return move2;
                }

            }else{
                return move2;
            }

        }else{
            return move2;
        }
    }

}
