package com.monstersaku;

public class Print<T>{
    T t;

    public Print(T t){
        this.t = t;
    }

    public void printEnter(){
        System.out.println(t);
    }
    public void noEnterPrint(){
        System.out.print(t);
    }
}