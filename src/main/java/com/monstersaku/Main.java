package com.monstersaku;

import com.monstersaku.util.CSVReader;
import com.monstersaku.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private 

    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        ArrayList<Monster> listmonster = new ArrayList<Monster>(); // menyimpan list monster dari csv
        for (String fileName : CSV_FILE_PATHS) {
            try {
                System.out.printf("Filename: %s\n", fileName);
                CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
                reader.setSkipHeader(true);
                List<String[]> lines = reader.read();
                System.out.println("=========== CONTENT START ===========");
                for (String[] line : lines) {
                    for (String word : line) {
                        System.out.printf("%s ", word);
                    }
                    System.out.println();
                }
                System.out.println("=========== CONTENT END ===========");
                System.out.println();
            } catch (Exception e) {
                // do nothing
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan nama pemain 1: ");
        String name1 = input.nextLine();
        System.out.println("Masukkan nama pemain 2: ");
        String name2 = input.nextLine();
        Random rand = new Random();
        int jumlahMons = listmonster.size();
        int upperbound = jumlahMons;
        ArrayList<Monster> ListMonsP1 = new ArrayList<Monster>();
        for (int i = 1; i<= 6; i++){
            int monsterrand = rand.nextInt(upperbound);
            ListMonsP1.add(listmonster.get(monsterrand));
            System.out.printf("Player 1 mendapatkan monster : %s", listmonster.get(monsterrand));
        }
        ArrayList<Monster> ListMonsP2 = new ArrayList<Monster>();
        for (int i = 1; i<= 6; i++){
            int monsterrand = rand.nextInt(upperbound);
            ListMonsP2.add(listmonster.get(monsterrand));
            System.out.printf("Player 2 mendapatkan monster : %s", listmonster.get(monsterrand));
        }
        Player player1 = new Player(name1, ListMonsP1);
        Player player2 = new Player(name2, ListMonsP2);
        //loop game
        while(!(player1.isAllDead() && player2.isAllDead())){
            //giliran player1
            System.out.println("Sekarang giliran " + player1.getName() + ".");
            System.out.println("Apa yang ingin Anda lakukan?");
            System.out.println("1. Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
            System.out.println("2. Mengganti monster.");
            Scanner scan1 = new Scanner(System.in);
            int action1 = scan1.nextInt();
            scan1.close();
            Move p1chosenmove = new DefaultMove("jic"); //gatau ini just in case aja
            Monster p1chosenmons = player1.getCurrentMonster();
            if(action1 == 1){
                //use move
                System.out.println("Move manakah yang ingin digunakan?");
                player1.getCurrentMonster().printMoves();
                Scanner scan2 = new Scanner(System.in);
                String chosenmove = scan2.nextLine();
                scan2.close();
                for(Move move : player1.getCurrentMonster().getMoves()){
                    if (chosenmove.equals(move.getName())){
                        p1chosenmove = move;
                    }
                }
            }else{
                //switch monster
                System.out.println("Monster manakah yang ingin digunakan?");
                player1.printMonsters();
                Scanner scan5 = new Scanner(System.in);
                String chosenmonster = scan5.nextLine();
                scan5.close();
                for(Monster mons : player1.getListMon()){
                    if(chosenmonster.equals(mons.getName())){
                        p1chosenmons = mons;
                    }
                }
            }

            //giliran player 2
            System.out.println("Sekarang giliran " + player2.getName() + ".");
            System.out.println("Apa yang ingin Anda lakukan?");
            System.out.println("1. Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
            System.out.println("2. Mengganti monster.");
            Scanner scan3 = new Scanner(System.in);
            int action2 = scan3.nextInt();
            scan3.close();
            Move p2chosenmove = new DefaultMove("jic");; //idem
            Monster p2chosenmons = player2.getCurrentMonster();
            if(action2 == 1){
                //use move
                System.out.println("Move manakah yang ingin digunakan?");
                player2.getCurrentMonster().printMoves();
                Scanner scan4 = new Scanner(System.in);
                String chosenmove = scan4.nextLine();
                scan4.close();
                for(Move move : player2.getCurrentMonster().getMoves()){
                    if (chosenmove.equals(move.getName())){
                        p2chosenmove = move;
                    }
                }
            }else{
                //switch monster
                System.out.println("Monster manakah yang ingin digunakan?");
                player1.printMonsters();
                Scanner scan6 = new Scanner(System.in);
                String chosenmonster = scan6.nextLine();
                scan6.close();
                for(Monster mons : player2.getListMon()){
                    if(chosenmonster.equals(mons.getName())){
                        p2chosenmons = mons;
                    }
                }
            }

            //battle
            if(action1 == 1 && action2 == 1){
                //p1 dan p2 move
                Move firstmove = p1chosenmove.compareMove(player1.getCurrentMonster(), player2.getCurrentMonster(), p2chosenmove);
                if(firstmove == p1chosenmove){
                    //p1 duluan
                    useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), firstmove);
                
                }else{
                    //p2 duluan
                    useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), (NormalMove) firstmove);

                }
            }else if(action1 == 1 && action2 == 2){
                //p1 move, p2 switch
                player2.switchCurrMonster(p2chosenmons);
                //pake movenya
            }else if(action1 == 2 && action1 == 1){
                //p1 switch, p2 move
                player1.switchCurrMonster(p1chosenmons);
                //pake move
            }else{
                //p1 dan p2 switch
                player1.switchCurrMonster(p1chosenmons);
                player2.switchCurrMonster(p2chosenmons);
            }
        }
    }

    public static void useNormalMove (Monster source, Monster target, NormalMove move){
        float finaldamage = (float)Math.floor((move.getBasePower() * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * getEffectivity(source, target));
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
    }
    
    public static void useSpecialMove (Monster source, Monster target, SpecialMove move){
        float finaldamage = (float)Math.floor((move.getBasePower() * ((source.getbaseStats().getSpecialAttack()) / (target.getbaseStats().getSpecialDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * getEffectivity(source, target));
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
    }
    
    public static void useDefaultMove (Monster source, Monster target){
        float finaldamage = (float)Math.floor((50 * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * getEffectivity(source, target));
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        if (HPBaru <= 0){
            HPBaru = 0.0;
        }
        target.getbaseStats().setHealthPoint(HPBaru);
        Double HPSource = Math.floor(source.getbaseStats().getHealthPoint() - (1/4) * source.getbaseStats().getMaxHP());
        if (HPSource <= 0){
            HPBaru = 0.0;
        }
        source.getbaseStats().setHealthPoint(HPSource);
    }

    public static void useStatusMove (Monster source, Monster target){

    }

    public static void useMove (Monster source, Monster target, Move move){
        if(move instanceof NormalMove){
            useNormalMove(source, target, (NormalMove) move);
        }else if(move instanceof SpecialMove){
            useSpecialMove(source, target, (SpecialMove) move);
        }else if(move instanceof StatusMove){
            useStatusMove(source, target);
        }
        move.setAmmunition(move.getAmmunition() - 1);
    }
}
