package com.monstersaku;

public enum ElementType {
    NORMAL ("NORMAL"),
    FIRE ("FIRE"),
    WATER ("WATER"),
    GRASS ("GRASS");

    private String ElType;

    ElementType(String ElType){
        this.ElType = ElType;
    }
    public String getElType(){
        return this.ElType;
    }
}