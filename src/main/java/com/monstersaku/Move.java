package com.monstersaku;

import java.util.Random;

public abstract class Move {
    private int id;
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;

    //konstruktor move
    public Move(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition){
        this.id = id;
        this.name = name; 
        this.elementType = elementType; 
        this.accuracy = accuracy; 
        this.priority = priority; 
        this.ammunition = ammunition;
    }
    
    //abstract class yang harus diimplementasikan di sub-class
    //public abstract void damageDone();

    //getter
    public int getid(){
        return this.id;
    }

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

    public Move compareMove(Monster mon1, Monster mon2, Move move2){
        Move exfirst;
        if(this.getPriority() > move2.getPriority()){
            exfirst = this;

        }else if(this.getPriority() == move2.getPriority()){
            if(mon1.getbaseStats().getSpeed() > mon2.getbaseStats().getSpeed()){
                exfirst = this;

            }else if(mon1.getbaseStats().getSpeed() == mon2.getbaseStats().getSpeed()){
                Random rand = new Random();
                int first = rand.nextInt(2);
                if(first == 0){
                    exfirst = this;
                }else{
                    exfirst = move2;
                }

            }else{
                exfirst = move2;
            }

        }else{
            exfirst = move2;
        }
        return exfirst;
    }

    public boolean isAmmunitionZero(){
        return(ammunition == 0);
    }
    
}
