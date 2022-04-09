package com.monstersaku;

import java.util.ArrayList;
import java.util.Random;

public class Monster {
    private int idMons;
    private String name;
    private ArrayList<ElementType> elemenTypes;
    private Stats baseStats;
    private ArrayList<Integer> Idmoves;
    private ArrayList<Move> moves;
    private StatusCondition statcon;
    private boolean dead;
    private int sleep;

    public Monster(int idMons, String name, ArrayList<ElementType> elemenTypes, Stats baseStats){
        this.idMons = idMons;
        this.name = name;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.Idmoves = new ArrayList<Integer>();
        this.moves = new ArrayList<Move>();
        this.statcon = StatusCondition.NOTHING;
        this.dead = false;
        this.sleep = 0;
    }

    public Monster(Monster monster, Stats monstat){
        this.idMons = monster.getId();
        this.name = monster.getName();
        this.elemenTypes = monster.getelemenTypes();
        this.baseStats = monster.getbaseStats();
        this.Idmoves = monster.getIDmove();
        this.moves = new ArrayList<Move>();
        this.statcon = StatusCondition.NOTHING;
        this.dead = false;
        this.sleep = 0;
    }
    // setter
    public void setidMons(int idMons){
        this.idMons = idMons;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setelmTypes(ArrayList<ElementType> elemenTypes){
        this.elemenTypes = elemenTypes;
    }
    public void setBS(Stats baseStats){
        this.baseStats = baseStats;
    }
    public void setMoves(ArrayList<Move> moves){
        this.moves = moves;
    }
    public void setStatcon (StatusCondition statcon){
        this.statcon = statcon;
    }
    public void setnumsleep(int value){
        this.sleep = value;
    }

    public void monsterDie(){
        //set to true kalau monsternya mati
        this.dead = true;
        System.out.printf("Oh tidak! %s sudah mati.\n", this.name);
    }

    //getter
    public int getId(){
        return idMons;
    }
    public String getName(){
        return name;
    }
    public ArrayList<ElementType> getelemenTypes(){
        return elemenTypes;
    }
    public Stats getbaseStats(){
        return baseStats;
    }
    public ArrayList<Integer> getIDmove(){
        return this.Idmoves;
    }
    public ArrayList<Move> getMoves(){
        return moves;
    }
    public StatusCondition getStatcon(){
        return this.statcon;
    }
    public int getnumsleep(){
        return sleep;
    }

    public boolean isMonsDead(){
        //return true if monster is dead
        return dead;
    }
    
    public void printAvailableMoves(){
        System.out.println("Move name, Move type, Ammuniton");
        for(int i = 0; i < this.getMoves().size(); i++){
            Move movex = this.getMoves().get(i);
            String movetype = null;
            if(!movex.isAmmunitionZero()){
                //nampilin yg available aja
                if(movex instanceof NormalMove){
                    movetype = "Normal";
                }else if(movex instanceof SpecialMove){
                    movetype = "Special";
                }else if(movex instanceof StatusMove){
                    movetype = "Status";
                }else if(movex instanceof DefaultMove){
                    movetype = "Default";
                }
                String movename = movex.getName();
                int ammunution = movex.getAmmunition();
                System.out.printf("%s, %s, %d\n", movename, movetype, ammunution);
            }
        }
    }
  
    public boolean isAllMovesUnavailable(){
        //return true kalau masih ada move yang available
        boolean isunavailable = true;
        for(Move movex : moves){
            if(!movex.isAmmunitionZero()){
                isunavailable = false;
            }
        }
        return isunavailable;
    }

    //Status Condition Related Methods
    public void nothing(){
        statcon = StatusCondition.NOTHING;
    }
    public void burn(){
        statcon = StatusCondition.BURN;
    }
    
    public void poison(){
        statcon = StatusCondition.POISON;
    }
    
    public void sleep(){
        Random dice = new Random();
        int number = 1 + dice.nextInt(7);
        sleep = number;
        statcon = StatusCondition.SLEEP;
    }
    
    public void sleepdecr(){
        sleep--;
        if(sleep == 0){
            //udah di turn terakhir
            statcon = StatusCondition.NOTHING;
            System.out.printf("Monster %s sudah bebas dari condition sleep. Silakan gunakan!", this.getName());
        }
    }

    public void paralyze(){
        statcon = StatusCondition.PARALYZE;
        baseStats.setSpeed(baseStats.getSpeed() / 2);
    }
    //number yang dihasilkan menunjukan kemungkinan monster skip ronde (co: number 1 monster skip, 234 tidak skip)

}