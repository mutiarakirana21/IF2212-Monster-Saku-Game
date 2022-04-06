package com.monstersaku;

import java.util.Scanner;

public class Game {
    private static int turn;

    public void start(Scanner scn) {

    }

    public void listCommand() {
        System.out.println("Pilih Command yang dapat di jalankan");
        System.out.println("[1] Pilih Attack");
        System.out.println("[2] Ganti Monster");
        System.out.println("[3] Help");
        System.out.println("[4] Exit");
    }

    public void space() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");
    }

    // public void currentMonsterInfo(Player currentPlayer) {

    // }

    public static void help() {
        System.out.println("Cara memainkan game monster saku");
        System.out.println("Pertama pemain memiliki 6 monster");
        System.out.println("[1] Pilih Attack");
        System.out.println("[2] Ganti Monster");
        System.out.println("[3] Help");
        System.out.println("[4] Exit");
    }

    public static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void exit(){
        System.exit(0);
    }
    
    public static int getTurn(){
        return turn;
    }

    public static void incrTurn(){
        turn++;
    }
}