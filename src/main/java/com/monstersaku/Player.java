package com.monstersaku;

import java.util.ArrayList;

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

    //setter monster awal
    public void setCurrMonster(Monster monster){
        this.currentmons = monster;
    }
    
    //switch current monster
    public void switchCurrMonster(Monster monster){
        this.currentmons.getbaseStats().getStatsBuff().resetbuff();
        System.out.printf("%s mengganti monster %s menjadi %s.\n", this.name, this.currentmons.getName(), monster.getName());
        setCurrMonster(monster);
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

    public void printAvailableMonsters(){
        System.out.println("Monster, HP");
        for(int i  = 0; i < 6; i++){
            Monster monsterx = this.getListMon().get(i);
            if(!monsterx.isMonsDead()){
                //nampilin monster yang masih hidup aja
                String monstername = monsterx.getName();
                Double HP = monsterx.getbaseStats().getHealthPoint();
                System.out.printf("%s, %.2f\n", monstername, HP);
            }
        }
    }
    
    public void printMonstersNotUsed(){
        System.out.println("Nama monster, status");
        for(int i = 0; i < 6; i++){
            Monster monsterx = this.getListMon().get(i);
            String status = "Alive";
            if(monsterx.isMonsDead()){
                status = "Dead";
            }
            if(!monsterx.equals(this.getCurrentMonster())){
                System.out.printf("%s, %s\n",monsterx.getName(), status);
            }
        }
    }

    public boolean isNoMoreMonsterAvailable(){
        //return true kalo current monster adalah satu-satunya monster yang hidup
        boolean nomore = true;
        for(Monster monsterx : this.getListMon()){
            if(monsterx != this.getCurrentMonster() && !monsterx.isMonsDead()){
                //bukan monster yang sekarang dipake dan monsternya ga mati berarti ada yg lain
                nomore = false;
            }
        }
        return nomore;
    }
}
