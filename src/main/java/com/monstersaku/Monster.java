package com.monstersaku;

import java.util.List;
import java.util.Random;

public class Monster {
    private String name;
    private List<ElementType> elemenTypes;
    private Stats baseStats;
    private List<Move> moves;

    public Monster(String name, List<ElementType> elemenTypes, Stats baseStats, List<Move> moves){
        this.name = name;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.moves = moves;
    }
    // yang set list belom fix
    public void setName(String name){
        this.name = name;
    }
    public void setelmTypes(List<ElementType> elemenTypes){
        this.elemenTypes = elemenTypes;
    }
    public void setBS(Stats baseStats){
        this.baseStats = baseStats;
    }
    public void setMoves(List<Move> moves){
        this.moves = moves;
    }
    public String getName(){
        return name;
    }
    public List<ElementType> getelemenTypes(){
        return elemenTypes;
    }
    public Stats getbaseStats(){
        return baseStats;
    }
    public List<Move> getmoves(){
        return moves;
    }
    public double calculateDamage(Monster mon){
        
    }
    public void changeHP(Monster mon){
        double healthPoint = baseStats.getHealthPoint() - calculateDamage(mon);
        baseStats.setHealtPoint(healthPoint);
    }
    public boolean isMonsDead(){
        return (baseStats.getHealthPoint() == 0);
    }
}