package com.monstersaku;

import java.util.List;
import java.util.Random;

public class Monster {
    private int idMons;
    private String name;
    private List<ElementType> elemenTypes;
    private Stats baseStats;
    private List<Move> moves;

    public Monster(int idMons, String name, List<ElementType> elemenTypes, Stats baseStats, List<Move> moves){
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
    public void setelmTypes(List<ElementType> elemenTypes){
        this.elemenTypes = elemenTypes;
    }
    public void setBS(Stats baseStats){
        this.baseStats = baseStats;
    }
    public void setMoves(List<Move> moves){
        this.moves = moves;
    }
    public int getId(){
        return idMons;
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
        double damage = moves.getBasePower();
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