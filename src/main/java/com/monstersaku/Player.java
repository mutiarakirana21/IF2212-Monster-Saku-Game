package com.monstersaku;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;
    private ArrayList<Monster> listmon;
    private Monster currentmons;

    public Player(String name, ArrayList<Monster> listmon){
        this.name = name;
        this.listmon = listmon;
    }

    public void addmon(ArrayList<Monster> listmon, Monster mon){
        listmon.add(mon);
    }

    //getter
    public String getName(){
        return this.name;
    }

    public ArrayList<Monster> getListMon(){
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

    public void printMonsters(){
        for(int i  = 0; i < 6; i++){
            Monster monsterx = this.getListMon().get(i);
            String monstername = monsterx.getName();
            String status;
            if(monsterx.isMonsDead()){
                status = "Dead";
            }else{
                status = "Alive";
            }
            System.out.printf("%s, %s", monstername, status);
        }
    }
    
}
