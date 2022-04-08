package com.monstersaku;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Game {
    private int turn;

    //Konstruktor
    public Game(){
        this.turn = 1;
    }

    public int getTurn(){
        return turn;
    }

    public void incrTurn(){
        turn++;
    }

    // Kata Pembuka
    public void start(Scanner sc) {
        System.out.println("");
        System.out.println("********SELAMAT DATANG DI MONSTER SAKU GAME********");
        System.out.println("");
        System.out.println("_________Ketik 'Start Game' untuk memulai game_________");
        System.out.println("");
        
        boolean start = false;
        do{
            String command = sc.nextLine();
            if (command.equals("Start Game")){
                start = true;
            }else{
                commands(command);
            }
            space();
        }while(!start);
    }

    // Command dalam battle
    public void listCommand() {
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
    public void help() {
        System.out.println("Cara memainkan game monster saku");
        System.out.println("Pertama-tama setiap pemain diberi 6 monster");
        System.out.println("Lalu pemain dapat memilih command setiap turnnya");
        System.out.println("Ada Move untuk memilih tipe serangan atau gerakan");
        System.out.println("Ada Switch untuk mengubah monster yang ditarungkan");
        System.out.println("Ada juga View Monster Info untuk melihat informasi monster");
        System.out.println("Ada juga View Game Info untuk melihat informasi turn dan informasi monster lainnya");
        System.out.println("Help dapat digunakan untuk melihat bantuan ini kembali");
        System.out.println("Exit dapat digunakan untuk keluar dari game");
        System.out.println("Semoga Membantu Yaaa!!!");
    }

    // Print Info monster yang sedang bertarung
    public void viewMonstersInfo(Player player1, Player player2) {
        // Info Monster Player 1
        System.out.println(player1.getName());
        printMonstersInfo(player1);
        System.out.println("");

        // Info Monster Player 2
        System.out.println(player2.getName());
        printMonstersInfo(player2);
        space();
    }

    public void printMonstersInfo(Player player){
        for(Monster monsterx : player.getListMon()){
            Stats baseStats = monsterx.getbaseStats();
            System.out.print("Monster      : " + monsterx.getName());
            System.out.print("Element Type : ");
            for(ElementType eltype : monsterx.getelemenTypes()){
                switch(eltype) {
                    case NORMAL:
                        System.out.print("Normal ");
                    case FIRE:
                        System.out.print("Fire ");
                    case WATER:
                        System.out.print("Water ");
                    case GRASS:
                        System.out.print("Grass ");
                }
                System.out.println("");
            }
            System.out.println("Max HP           : " + baseStats.getmaxHP());
            System.out.println("HP               : " + baseStats.getHealthPoint());
            System.out.println("Attack           : " + baseStats.getAttack());
            System.out.println("Defense          : " + baseStats.getDefense());
            System.out.println("Special Attack   : " + baseStats.getSpecialAttack());
            System.out.println("Special Defense  : " + baseStats.getSpecialDefense());
            System.out.println("Speed            : " + baseStats.getSpeed());
            System.out.print("Status Condition : " + monsterx.getStatcon());
            switch(monsterx.getStatcon()){
                case NOTHING:
                    System.out.println("-");
                case BURN:
                    System.out.println("Burn");
                case POISON:
                    System.out.println("Poison");
                case SLEEP:
                    System.out.println("Sleep");
                case PARALYZE:
                    System.out.println("Paralyze");
            }
            System.out.print("Moves         : ");
            for(Move movex : monsterx.getMoves()){
                System.out.print(movex.getName() + " ");
            }
            System.out.println("");
        }
    }

    public void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public void exit(){
        System.exit(0);
    }
    
    public void useNormalMove (Monster source, Monster target, NormalMove move, Effectivity eff){
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
    
    public void useSpecialMove (Monster source, Monster target, SpecialMove move, Effectivity eff){
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
    
    public void useDefaultMove (Monster source, Monster target, Move move, Effectivity eff){
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

    public void useStatusMove (Monster source, Monster target, StatusMove move){
        //kalo diri sendiri harusnya heal/ganti statsbuff
        if(move.getTarget().equals("OWN")){
            double HP = move.getHPEffect() + (move.getHPEffect() * source.getbaseStats().getmaxHP());
            source.getbaseStats().setHealthPoint(HP);
            StatsBuff statsBuff = source.getbaseStats().getStatsBuff();
            statsBuff.setAttackBuff((int) statsBuff.getAttackBuff() + move.getAttackEffect());
            statsBuff.setDefenseBuff((int) statsBuff.getDefenseBuff() + move.getDefenseEffect());
            statsBuff.setSpAttBuff((int) statsBuff.getAttackBuff() + move.getSpAttEffect());
            statsBuff.setSpDefBuff((int) statsBuff.getAttackBuff() + move.getSpDefEffect());
        }else if(move.getTarget().equals("ENEMY")){
            //kalo enemy berarti pasang statcon/ngaruhin statsbuff
            if (move.getStatusCondition() == StatusCondition.BURN){
                if (target.getStatcon() == StatusCondition.NOTHING){
                    target.setStatcon(StatusCondition.BURN);

                }else{

                }
            }else if (move.getStatusCondition() == StatusCondition.POISON){

            }else if (move.getStatusCondition() == StatusCondition.PARALYZE){

            }else if (move.getStatusCondition() == StatusCondition.SLEEP){

            }
        }else{

        }
    }

    public void useMove (Monster source, Monster target, Move move, Effectivity eff){
        Random rand = new Random();
        int number = rand.nextInt(100);
        if(source.getStatcon() != StatusCondition.PARALYZE){
            if(number <= move.getAccuracy()){
                //bisa pake movenya kalo angka randomnya ada di range 0-accuracy dari movenya
                executeMove(source, target, move, eff);
            }else{
                //gabisa pake move soalnya ga di dalem range accuracynya
                System.out.println("Sayang sekali move miss (tidak berhasil).");
            }
        }else{
            if(rand.nextInt(4) == 3){
                System.out.println("Sayang sekali move miss (tidak berhasil) karena monster paralyzed.");
            }else{
                executeMove(source, target, move, eff);
            }
        }
    }

    public void executeMove(Monster source, Monster target, Move move, Effectivity eff){
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

    public void afterEffect(Monster affected){
        StatusCondition statcon = affected.getStatcon();
        if(statcon == StatusCondition.BURN){
            double afterdamage = affected.getbaseStats().getmaxHP() * 0.125; 
            double HPBaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            if(HPBaru < 0 ){
                HPBaru = 0;
            }
        }else if(statcon == StatusCondition.POISON){
            double afterdamage = (double)Math.floor( affected.getbaseStats().getmaxHP() * 0.0625);
            double HPbaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            if(HPbaru < 0.0){
                HPbaru = 0.0;
            }
        }else if(statcon == StatusCondition.SLEEP){
            affected.sleepdecr();
        }
    }

    public Monster chooseMonster(Player current, Scanner input){
        System.out.println("Monster manakah yang ingin digunakan?");
        current.printAvailableMonsters(); 
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
            if(!valid){
                //kalo masukan monster tidak valid (tidak ada monsternya)
                System.out.println("Monster tidak valid! Silakan pilih monster lain.");
            }
        }while (!valid);
        return chosen;
    }

    public Move chooseMove(Player current, Move chosen, Scanner input){
        System.out.println("Move manakah yang ingin digunakan?");
        current.getCurrentMonster().printAvailableMoves();
        boolean valid = false;
        do{
            String chosenmove = input.nextLine();
            for(Move move : current.getCurrentMonster().getMoves()){
                if (chosenmove.equals(move.getName())){
                    chosen = move;
                }
            }
            if(!valid){
                //kalo masukan move tidak valid (tidak ada movenya)
                System.out.println("Move tidak valid! silakan pilih move lain.");
            }
        }while(!valid);
        return chosen;
    }

    public void commands(String command){
        if(command.equals("Help")){
            help();
            listCommand();
        }else if(command.equals("Exit")){
            exit();
        }
    }

    public void ingameCommands(String command, Player player1, Player player2){
        if(command.equals("View Game Info")){
            printGameInfo(player1, player2);
        }else if(command.equals("View Monster Info")){
            viewMonstersInfo(player1, player2);
        }else{
            commands(command);
        }
    }

    public void printGameInfo(Player player1, Player player2){
        System.out.printf("Turn dalam game ini : %d\n", turn);
        System.out.println("Current Monster " + player1.getName() + " : " + player1.getCurrentMonster().getName());
        System.out.println("Current Monster " + player2.getName() + " : " + player2.getCurrentMonster().getName());
        System.out.println("Monster dari " + player1.getName() + " yang sedang tidak bertarung : ");
        player1.printMonstersNotUsed();
        System.out.println("Monster dari " + player2.getName() + " yang sedang tidak bertarung : ");
        player2.printMonstersNotUsed();
    }

    public void newTurn() {
        System.out.printf("ROUND %d", turn);
        System.out.println(getTurn());
        System.out.println("FIGHT");
    }

    public String ingamegetinput(Scanner scan, Player player1, Player player2){
        boolean valid = false;
        String input = null;
        do{
            input = scan.nextLine();
            if(input.equals("Help") || input.equals("Exit") || input.equals("View Game Info") || input.equals("View Monsters Info") || input.equals("1")|| (input.equals("2"))){
                valid = true;
            }else{
                System.out.println("Command tidak valid! Silakan masukkan command lain.");
            }
        }while(!valid);
        return input;
    }

}