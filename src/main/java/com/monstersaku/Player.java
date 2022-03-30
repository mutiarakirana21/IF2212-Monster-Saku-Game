package com.monstersaku;

import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private List<Monster> listmon;
    private Monster currentmons;

    public Player(String name, Monster mon1, Monster mon2, Monster mon3, Monster mon4, Monster mon5, Monster mon6){
        this.name = name;
        addmon(listmon, mon1);
        addmon(listmon, mon2);
        addmon(listmon, mon3);
        addmon(listmon, mon4);
        addmon(listmon, mon5);
        addmon(listmon, mon6);
        Random rand = new Random();
        int first = rand.nextInt(6);
        if(first == 0){
            this.currentmons = mon1;
        }else if(first == 1){
            this.currentmons = mon2;
        }else if(first == 2){
            this.currentmons = mon3;
        }else if(first == 3){
            this.currentmons = mon4;
        }else if(first == 4){
            this.currentmons = mon5;
        }else if(first == 5){
            this.currentmons = mon6;
        }
    }

    public void addmon(List<Monster> listmon, Monster mon){
        listmon.add(mon);
    }

    //getter
    public String getName(){
        return this.name;
    }

    public List<Monster> getListMon(){
        return this.listmon;
    }

    public Monster getCurrentMonster(){
        return this.currentmons;
    }

    //switch current monster
    public void switchCurrMonster(Monster mon){
        this.currentmons = mon;
    }

    //mengembalikan true jika semua monster sudah mati
    public boolean isAllDead(){
        boolean isdead = true;
        for(Monster mons : this.listmon){
            if(!mons.isMonsDead()){
                isdead = false;
            }
        }
        return isdead;
    }   
}
