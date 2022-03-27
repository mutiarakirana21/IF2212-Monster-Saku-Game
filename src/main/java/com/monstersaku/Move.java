package com.monstersaku;

import java.util.Random;

public abstract class Move {
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    private int statBuff;

    //konstruktor move
    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition, int statBuff){
        this.name = name; 
        this.elementType = elementType; 
        this.accuracy = accuracy; 
        this.priority = priority; 
        this.ammunition = ammunition;
        this.statBuff = statBuff;
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

    public int getStatBuff(){
        return this.statBuff;
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
            if(mon1.getStat().getSpeed() > mon2.getStat().getSpeed()){
                exfirst = this;

            }else if(mon1.getStat().getSpeed() == mon2.getStat().getSpeed()){
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

    //methods buff
    Stats s = new Stats();

    public void setAttackBuff(double attack){
        int x = this.getStatBuff();
        if(x==-4){
            attack = attack * 2/6;
        }else if(x==-3){
            attack = attack * 2/5;
        }else if(x==-2){
            attack = attack * 2/4;
        }else if(x==-1){
            attack = attack * 2/3;
        }else if(x==0){
            attack = attack * 1;
        }else if(x==1){
            attack = attack * 3/2;
        }else if(x==2){
            attack = attack * 4/2;
        }else if(x==3){
            attack = attack * 5/2;
        }else if(x==4){
            attack = attack * 6/2;
        }
        s.setAttack(attack);
    }

    public void setDefenseBuff(int defense){
        int x = this.getStatBuff();
        if(x==-4){
         defense = defense * 2/6;
        }else if(x==-3){
         defense = defense * 2/5;
        }else if(x==-2){
         defense = defense * 2/4;
        }else if(x==-1){
         defense = defense * 2/3;
        }else if(x==0){
         defense = defense * 1;
        }else if(x==1){
         defense = defense * 3/2;
        }else if(x==2){
         defense = defense * 4/2;
        }else if(x==3){
         defense = defense * 5/2;
        }else if(x==4){
         defense = defense * 6/2;
        }
        s.setDefense(defense);
    }

    public void setSpecialAttackBuff(int specialAttack){
        int x = this.getStatBuff();
        if(x==-4){
         specialAttack = specialAttack * 2/6;
        }else if(x==-3){
         specialAttack = specialAttack * 2/5;
        }else if(x==-2){
         specialAttack = specialAttack * 2/4;
        }else if(x==-1){
         specialAttack = specialAttack * 2/3;
        }else if(x==0){
         specialAttack = specialAttack * 1;
        }else if(x==1){
         specialAttack = specialAttack * 3/2;
        }else if(x==2){
         specialAttack = specialAttack * 4/2;
        }else if(x==3){
         specialAttack = specialAttack * 5/2;
        }else if(x==4){
         specialAttack = specialAttack * 6/2;
        }
        s.setSpecialAttack(specialAttack);
    }

    public void setSpecialDefenseBuff(int specialDefense){
        int x = this.getStatBuff();
        if(x==-4){
         specialDefense = specialDefense * 2/6;
        }else if(x==-3){
         specialDefense = specialDefense * 2/5;
        }else if(x==-2){
         specialDefense = specialDefense * 2/4;
        }else if(x==-1){
         specialDefense = specialDefense * 2/3;
        }else if(x==0){
         specialDefense = specialDefense * 1;
        }else if(x==1){
         specialDefense = specialDefense * 3/2;
        }else if(x==2){
         specialDefense = specialDefense * 4/2;
        }else if(x==3){
         specialDefense = specialDefense * 5/2;
        }else if(x==4){
         specialDefense = specialDefense * 6/2;
        }
        s.setSpecialDefense(specialDefense);
    }

    public void setSpecialSpeed(int speed){
        int x = this.getStatBuff();
        if(x==-4){
         speed = speed * 2/6;
        }else if(x==-3){
         speed = speed * 2/5;
        }else if(x==-2){
         speed = speed * 2/4;
        }else if(x==-1){
         speed = speed * 2/3;
        }else if(x==0){
         speed = speed * 1;
        }else if(x==1){
         speed = speed * 3/2;
        }else if(x==2){
         speed = speed * 4/2;
        }else if(x==3){
         speed = speed * 5/2;
        }else if(x==4){
         speed = speed * 6/2;
        }
        s.setSpeed(speed);
    }

}
