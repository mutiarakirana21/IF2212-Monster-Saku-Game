package com.monstersaku;

import java.util.Scanner;
import java.io.IOException;

public class Game {
    private static int turn;

    // Kata Pembuka
    public void start(Scanner sc) {
        System.out.println("");
        System.out.println("********SELAMAT DATANG DI MONSTER SAKU GAME********");
        System.out.println("");
        System.out.println("_________TEKAN 1 untuk melanjutkan ke game_________");
        System.out.println("");
        
        String start = sc.next();
        while (!start.equals("1")) {
            System.out.println("Ketik 1 untuk memulai!");
            start = sc.next();
        }
        System.out.println("");
    }

    // Command dalam battle
    public void listCommand() {
        System.out.println("Pilih Command yang ingin di jalankan");
        System.out.println("[1] Move");
        System.out.println("[2] Switch");
        System.out.println("[3] View Monster Info");
        System.out.println("[4] View Game Info");
        System.out.println("[5] Help");
        System.out.println("[6] Exit");
    }

    // Print Spasi diakhiran turn atau ganti layar
    public void space() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");
    }

    // Print help
    public static void help() {
        System.out.println("Cara memainkan game monster saku");
        System.out.println("Pertama-tama setiap pemain diberi 6 monster");
        System.out.println("Lalu pemain dapat memilih command setiap turnnya");
        System.out.println("Ada Move untuk memilih tipe serangan atau gerakan");
        System.out.println("Ada Switch untuk mengubah monster yang ditarungkan");
        System.out.println("Ada juga View Monster Info untuk melihat informasi monster");
        System.out.println("Ada juga View Game Info untuk melihat informasi turn dan informasi monster lainnya");
        System.out.println("Help dapat digunakan untuk melihat bantuan ini kembali");
        System.out.println("Exit dapat digunakan untuk keluar dari permainan");
        System.out.println("");
        System.out.println("Semoga Membantu Yaaa!!!");
    }

    // Print Info monster yang sedang bertarung
    public void viewMonsterInfo() {
        // Info Monster Player 1
        System.out.println("Player 1");
        System.out.println("Monster      : ");
        System.out.println("Element Type : ");
        System.out.println("");
        System.out.println("Max HP           : ");
        System.out.println("HP               : ");
        System.out.println("Attack           : ");
        System.out.println("Defense          : ");
        System.out.println("Special Attack   : ");
        System.out.println("Special Defense  : ");
        System.out.println("Speed            : ");
        System.out.println("Status Condition : ");
        System.out.println("");
        System.out.println("");
        // Info Monster Player 2
        System.out.println("Player 2");
        System.out.println("Monster      : ");
        System.out.println("Element Type : ");
        System.out.println("");
        System.out.println("Max HP           : ");
        System.out.println("HP               : ");
        System.out.println("Attack           : ");
        System.out.println("Defense          : ");
        System.out.println("Special Attack   : ");
        System.out.println("Special Defense  : ");
        System.out.println("Speed            : ");
        System.out.println("Status Condition : ");
        System.out.println("");
        System.out.println("");
    }

    // Print Info turn, info monster yang sedang bertarung dan yang tidak
    // khusus monster pada player yang menginput
    public void viewGameInfo() {
        System.out.println("Sekarang Turn Player ");
        System.out.println("");
        viewMonsterInfo();
        System.out.println("");
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

