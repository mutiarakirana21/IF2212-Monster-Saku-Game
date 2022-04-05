package com.monstersaku;

import java.util.*;

public class Effectivity{
    private HashMap<String, Double> ListEffectivity;

    public Effectivity(){
        this.ListEffectivity = new HashMap<String,Double>();
    }

    public void add(ElementType source, ElementType target, double effectivity) {
        String key = source.getElType() + "," + target.getElType();
        this.ListEffectivity.put(key, effectivity);
    }

    public double getEffectivity (ElementType source, ElementType target) {
        String temp = source.getElType() + "," + target.getElType();
        double effectivity = this.ListEffectivity.get(temp);
        return effectivity;
    }

}