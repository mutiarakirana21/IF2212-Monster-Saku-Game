package com.monstersaku;

import java.util.ArrayList;

public class Monster {
    private int idMons;
    private String name;
    private ArrayList<ElementType> elemenTypes;
    private Stats baseStats;
    private ArrayList<Move> moves;
    private StatusCondition statcon;

    public Monster(int idMons, String name, ArrayList<ElementType> elemenTypes, Stats baseStats, ArrayList<Move> moves){
        this.idMons = idMons;
        this.name = name;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.moves = moves;
        this.statcon = StatusCondition.NOTHING;
    }
    // yang set list belom fix
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
    public ArrayList<Move> getMoves(){
        return moves;
    }
    public StatusCondition getStatcon(){
        return this.statcon;
    }
    public void printMoves(){
        for(int i = 0; i < this.getMoves().size(); i++){
            Move movex = this.getMoves().get(i);
            String movetype = null;
            System.out.println("Move name, Move type, Ammuniton");
            if(movex instanceof NormalMove){
                movetype = "Normal";
            }else if(movex instanceof SpecialMove){
                movetype = "Special";
            }else if(movex instanceof StatusMove){
                movetype = "Status";
            }
            String movename = movex.getName();
            int ammunution = movex.getAmmunition();
            System.out.printf("%s, %s, %d\n", movename, movetype, ammunution);
        }
    }
    public boolean isMonsDead(){
        return (baseStats.getHealthPoint() == 0);
    }
}