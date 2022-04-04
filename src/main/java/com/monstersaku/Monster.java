package com.monstersaku;

import java.util.ArrayList;
import java.util.Random;

public class Monster {
    private int idMons;
    private String name;
    private ArrayList<ElementType> elemenTypes;
    private Stats baseStats;
    private ArrayList<Move> moves;

    public Monster(int idMons, String name, ArrayList<ElementType> elemenTypes, Stats baseStats, ArrayList<Move> moves){
        this.idMons = idMons;
        this.name = name;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.moves = moves;
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
    public double calculateDamage(Monster mon){
        double damage = moves.getBasePower();
    }

    public void printMoves(){
        this.getMoves().forEach((n) -> System.out.println(n));
    }

    //calculate damage dari move
    /*
        public int damageCalc(Monster attacker, Monster defense){
        int damage;
        double min = 0.85;
        double max = 1.0;
        Random r = new Random();
        double coef = min + (max - min) * r.nextDouble();
        damage = basePower * ((attacker.getStat().getAttack()*)/(defense.getStat().getDefense()*)) * coef * elEff * burn;
    }
    */

    public void changeHP(Monster mon){
        double healthPoint = baseStats.getHealthPoint() - calculateDamage(mon);
        baseStats.setHealtPoint(healthPoint);
    }
    
    public boolean isMonsDead(){
        return (baseStats.getHealthPoint() == 0);
    }
}