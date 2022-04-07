package com.monstersaku;

import java.util.Scanner;
import java.io.IOException;

public class Game {
    private static int turn = 0;

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
    public static void listCommand() {
        System.out.println("Command yang dapat dijalankan");
        System.out.println("[1] Move");
        System.out.println("[2] Switch");
        System.out.println("[3] View Monster Info (khusus dalam game)");
        System.out.println("[4] View Game Info (khusus dalam game)");
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

    public static void useNormalMove (Monster source, Monster target, NormalMove move, Effectivity eff){
        double ElementEffectivity = 1;
        for (ElementType e : target.getelemenTypes()){
            ElementEffectivity = ElementEffectivity * eff.getEffectivity(move.getElType(), e);
        }
        double burn;
        if (source.getStatcon() == StatusCondition.BURN){
            burn = 0.5;
        }else{
            burn = 1;
        }
        float finaldamage = (float)Math.floor((move.getBasePower() * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
    }
    
    public static void useSpecialMove (Monster source, Monster target, SpecialMove move, Effectivity eff){
        double ElementEffectivity = 1;
        for (ElementType e : target.getelemenTypes()){
            ElementEffectivity = ElementEffectivity * eff.getEffectivity(move.getElType(), e);
        }
        double burn;
        if (source.getStatcon() == StatusCondition.BURN){
            burn = 0.5;
        }else{
            burn = 1;
        }
        float finaldamage = (float)Math.floor((move.getBasePower() * ((source.getbaseStats().getSpecialAttack()) / (target.getbaseStats().getSpecialDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
    }
    
    public static void useDefaultMove (Monster source, Monster target, Move move, Effectivity eff){
        double ElementEffectivity = 1;
        for (ElementType e : target.getelemenTypes()){
            ElementEffectivity = ElementEffectivity * eff.getEffectivity(move.getElType(), e);
        }
        double burn;
        if (source.getStatcon() == StatusCondition.BURN){
            burn = 0.5;
        }else{
            burn = 1;
        }
        float finaldamage = (float)Math.floor((50 * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
        Double HPSource = Math.floor(source.getbaseStats().getHealthPoint() - (1/4) * source.getbaseStats().getmaxHP());
        if (HPSource <= 0){
            HPBaru = 0.0;
        }
        source.getbaseStats().setHealthPoint(HPSource);
    }

    public static void useStatusMove (Monster source, Monster target, StatusMove move){
        //kalo diri sendiri harusnya heal/ganti statsbuff
        if(move.getTarget().equals("OWN")){

        }else if(move.getTarget().equals("ENEMY")){
            //kalo enemy berarti pasang statcon/ngaruhin statsbuff

        }else{

        }
    }

    public static void useMove (Monster source, Monster target, Move move, Effectivity eff){
        if(move instanceof NormalMove){
            useNormalMove(source, target, (NormalMove) move, eff);
        }else if(move instanceof SpecialMove){
            useSpecialMove(source, target, (SpecialMove) move, eff);
        }else if(move instanceof StatusMove){
            useStatusMove(source, target, (StatusMove) move);
        }else if(move instanceof DefaultMove){
            useDefaultMove(source, target, move, eff);
        }
        move.setAmmunition(move.getAmmunition() - 1);

    }

    public static void afterDamage(Monster affected){
        if(affected.getStatcon() == StatusCondition.BURN){
            double afterdamage = affected.getbaseStats().getmaxHP() * 0.125; 
            double HPBaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            if(HPBaru < 0 ){
                HPBaru = 0;
            }
        }else if(affected.getStatcon() == StatusCondition.POISON){
            double afterdamage = (double)Math.floor( affected.getbaseStats().getmaxHP() * 0.0625);
            double HPbaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            if(HPbaru < 0.0){
                HPbaru = 0.0;
            }
        }
    }

    public static Monster chooseMonster(Player current, Scanner input){
        System.out.println("Monster manakah yang ingin digunakan?");
        current.printMonsters(); 
        boolean valid = false;
        Monster chosen = current.getCurrentMonster();
        do{
            String chosenmonster = input.nextLine();
            for(Monster mons : current.getListMon()){
                if(chosenmonster.equals(mons.getName())){
                    if(!mons.isMonsDead()){
                        chosen = mons;
                        valid = true;
                    }
                }
            }
            if(!valid || chosen.isMonsDead()){
                //kalo masukan monster tidak valid (tidak ada monsternya atau monsternya udah mati)
                System.out.println("Monster tidak valid! Silakan pilih monster lain.");
            }
        }while (!valid);
        return chosen;
    }

    public static Move chooseMove(Player current, Scanner input){
        System.out.println("Move manakah yang ingin digunakan?");
        current.getCurrentMonster().printMoves();
        Move chosen = new DefaultMove("jic2");
        boolean valid = false;
        do{
            String chosenmove = input.nextLine();
            for(Move move : current.getCurrentMonster().getMoves()){
                if (chosenmove.equals(move.getName())){
                    if(!move.isAmmunitionZero()){
                        chosen = move;
                    }
                }
            }
            if(!valid || chosen.isAmmunitionZero()){
                //kalo masukan move tidak valid (tidak ada movenya atau ammunition udah abis)
                System.out.println("Move tidak valid! silakan pilih move lain");
            }
        }while(!valid);
        return chosen;
    }

    public static void commands(String command){
        if(command.equals("Help")){
            help();
            listCommand();
        }else if(command.equals("Exit")){
            exit();
        }
    }

    public static void ingameCommands(String command, Player player1, Player player2){
        if(command.equals("View Game Info")){
            printGameInfo(player1, player2);
        }else if(command.equals("View Monster Info")){
            //view monster info belom paham
        }else{
            commands(command);
        }
    }

    public static void printGameInfo(Player player1, Player player2){
        System.out.printf("Turn dalam game ini : %d\n", Game.getTurn());
        System.out.println("Current Monster " + player1.getName() + " : " + player1.getCurrentMonster().getName());
        System.out.println("Current Monster " + player2.getName() + " : " + player2.getCurrentMonster().getName());
        System.out.println("Monster dari " + player1.getName() + " yang sedang tidak bertarung : ");
        player1.printMonstersNotUsed();
        System.out.println("Monster dari " + player2.getName() + " yang sedang tidak bertarung : ");
        player2.printMonstersNotUsed();
    }


}

