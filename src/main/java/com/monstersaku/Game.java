package com.monstersaku;

import java.util.Scanner;

public class Game {
    
    public void start(Scanner scn) {

    }

    public void lisCommand() {
        System.out.println("Pilih Comman yang ingin di jalankan");
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

    public void help() {
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
}