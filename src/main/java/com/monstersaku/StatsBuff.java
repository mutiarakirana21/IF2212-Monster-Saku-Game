package com.monstersaku;

public class StatsBuff {
    private int attackBuff;
    private int defenseBuff;
    private int spAttBuff; 
    private int spDefBuff; 
    private int speedBuff;

    //konstruktor
    public StatsBuff(){
        resetbuff();
    }

    //setter
    public void setAttackBuff(int ab){
        this.attackBuff = ab;
    }

    public void setDefenseBuff(int db){
        this.defenseBuff = db;
    }

    public void setSpAttBuff(int sab){
        this.spAttBuff = sab;
    }

    public void setSpDefBuff(int sdb){
        this.spDefBuff = sdb;
    }

    public void setSpeedBuff(int sb){
        this.speedBuff = sb;
    }


    //methods buff
    public double getAttackBuff(){
        int x = attackBuff;
        double buff = 1;
        if(x == -4){
            buff = 2/6;
        }else if(x == -3){
            buff = 2/5;
        }else if(x == -2){
            buff = 2/4;
        }else if(x == -1){
            buff = 2/3;
        }else if(x == 0){
            buff = 1;
        }else if(x == 1){
            buff = 3/2;
        }else if(x == 2){
            buff = 4/2;
        }else if(x == 3){
            buff = 5/2;
        }else if(x == 4){
            buff = 6/2;
        }
        return buff;
    }
 
    public double getDefenseBuff(){
        int x = defenseBuff;
        double buff = 1;
        if(x == -4){
            buff = 2/6;
        }else if(x == -3){
            buff = 2/5;
        }else if(x == -2){
            buff = 2/4;
        }else if(x == -1){
            buff = 2/3;
        }else if(x == 0){
            buff = 1;
        }else if(x == 1){
            buff = 3/2;
        }else if(x == 2){
            buff = 4/2;
        }else if(x == 3){
            buff = 5/2;
        }else if(x == 4){
            buff = 6/2;
        }
        return buff;
     }
 
    public double getSpecialAttackBuff(){
        int x = spAttBuff;
        double buff = 1;
        if(x == -4){
            buff = 2/6;
        }else if(x == -3){
            buff = 2/5;
        }else if(x == -2){
            buff = 2/4;
        }else if(x == -1){
            buff = 2/3;
        }else if(x == 0){
            buff = 1;
        }else if(x == 1){
            buff = 3/2;
        }else if(x == 2){
            buff = 4/2;
        }else if(x == 3){
            buff = 5/2;
        }else if(x == 4){
            buff = 6/2;
        }
        return buff;
    }
 
    public double getSpecialDefenseBuff(){
        int x = spDefBuff;
        double buff = 1;
        if(x == -4){
            buff = 2/6;
        }else if(x == -3){
            buff = 2/5;
        }else if(x == -2){
            buff = 2/4;
        }else if(x == -1){
            buff = 2/3;
        }else if(x == 0){
            buff = 1;
        }else if(x == 1){
            buff = 3/2;
        }else if(x == 2){
            buff = 4/2;
        }else if(x == 3){
            buff = 5/2;
        }else if(x == 4){
            buff = 6/2;
        }
        return buff;
     }
 
    public double getSpeedBuff(){
        int x = speedBuff;
        double buff = 1;
        if(x == -4){
            buff = 2/6;
        }else if(x == -3){
            buff = 2/5;
        }else if(x == -2){
            buff = 2/4;
        }else if(x == -1){
            buff = 2/3;
        }else if(x == 0){
            buff = 1;
        }else if(x == 1){
            buff = 3/2;
        }else if(x == 2){
            buff = 4/2;
        }else if(x == 3){
            buff = 5/2;
        }else if(x == 4){
            buff = 6/2;
        }
        return buff;
    }

    public void resetbuff(){
        this.attackBuff = 0;
        this.defenseBuff = 0;
        this.spAttBuff = 0;
        this.spDefBuff = 0;
        this.speedBuff = 0;
    }

    //print component statsbuff
    public void printStatsBuff(Monster mons){
        System.out.printf("Berikut adalah Stats Buff dari %s.\n", mons.getName());
        System.out.printf("Attack buff dari %s : %d.\n", mons.getName(), this.attackBuff);
        System.out.printf("Defense buff dari %s : %d.\n", mons.getName(), this.defenseBuff);
        System.out.printf("Special Attack buff dari %s : %d.\n", mons.getName(), this.spAttBuff);
        System.out.printf("Special Defense buff dari %s : %d.\n", mons.getName(), this.spDefBuff);
        System.out.printf("Speed buff dari %s : %d.\n", mons.getName(), this.speedBuff);
    }
}
