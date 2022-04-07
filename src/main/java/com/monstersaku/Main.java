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
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        ArrayList<Monster> listmonster = new ArrayList<Monster>(); // menyimpan list monster dari csv
        Effectivity listeff = new Effectivity(); // menyimpan effectivity dari csv
        try {
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
            CSVReader fileEfficiency = new CSVReader(new File(Main.class.getResource("configs/element-type-effectivity-chart.csv").toURI()), ";");
            fileEfficiency.setSkipHeader(true);
            List<String[]> readf = fileEfficiency.read();
            for (String[] row : readf){
                ElementType source, target;
                double effectivity;
                try{
                    source = ElementType.valueOf(row[0]);
                    target = ElementType.valueOf(row[1]);
                    effectivity = Double.parseDouble(row[2]);
                    listeff.add(source, target, effectivity);
                }catch (Exception e){
                    System.out.println(e.getMessage()); 
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //disini harusnya ada main manu dulu kalo udah baru masuk sini
        Scanner input = new Scanner(System.in);
        String mainmenu = input.nextLine();
        if(mainmenu.equals("Start Game")){
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
                System.out.printf("Player 1 mendapatkan monster : %s\n", listmonster.get(monsterrand));
            }
            ArrayList<Monster> ListMonsP2 = new ArrayList<Monster>();
            for (int i = 1; i<= 6; i++){
                int monsterrand = rand.nextInt(upperbound);
                ListMonsP2.add(listmonster.get(monsterrand));
                System.out.printf("Player 2 mendapatkan monster : %s\n", listmonster.get(monsterrand));
            }
            Player player1 = new Player(name1, ListMonsP1);
            Player player2 = new Player(name2, ListMonsP2);

            //loop game
            while(!(player1.isAllDead() && player2.isAllDead())){

                //giliran player1
                System.out.println("Sekarang giliran " + player1.getName() + ".");
                System.out.println("Apa yang ingin Anda lakukan?");
                System.out.println("[1] Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
                System.out.println("[2] Mengganti monster.");
                String action1 = input.nextLine();
                Move p1chosenmove = new DefaultMove("jic"); //just in case
                Monster p1chosenmons = player1.getCurrentMonster(); //buat null aja harusnya ntar bisa pake exception 
                if(action1.equals("1")){
                    //use move
                    p1chosenmove = chooseMove(player1 , input);
                }else if(action1.equals("2")){
                    //switch monster
                    p1chosenmons = chooseMonster(player1, input);
                }else{
                    ingameCommands(action1, player1, player2);
                }

                //giliran player 2
                System.out.println("Sekarang giliran " + player2.getName() + ".");
                System.out.println("Apa yang ingin Anda lakukan?");
                System.out.println("[1] Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
                System.out.println("[2] Mengganti monster.");
                String action2 = input.nextLine();
                Move p2chosenmove = new DefaultMove("jic");; //idem
                Monster p2chosenmons = player2.getCurrentMonster(); //buat null aja harusnya ntar bisa pake exception 
                if(action2.equals("1")){
                    //use move
                    p2chosenmove = chooseMove(player2, input);
                }else if(action2.equals("2")){
                    //switch monster
                    p2chosenmons = chooseMonster(player2, input);
                }else{
                    ingameCommands(action2, player1, player2);
                }

                //battle
                if(action1.equals("1") && action2.equals("1")){
                    //p1 dan p2 move
                    Move firstmove = p1chosenmove.compareMove(player1.getCurrentMonster(), player2.getCurrentMonster(), p2chosenmove);
                    if(firstmove == p1chosenmove){
                        //p1 duluan
                        useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), firstmove, listeff);
                        useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), firstmove, listeff);
                        //afterdamage
                        afterDamage(player1.getCurrentMonster());
                        afterDamage(player2.getCurrentMonster());
                        //kalo mati pilih monster baru
                        if(player1.getCurrentMonster().isMonsDead()){
                            Monster replacement = chooseMonster(player1, input);
                            player1.switchCurrMonster(replacement);
                        }else if(player2.getCurrentMonster().isMonsDead()){
                            Monster replacement = chooseMonster(player2, input);
                            player2.switchCurrMonster(replacement);
                        }
                    }else{
                        //p2 duluan
                        useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), firstmove, listeff);
                        useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), firstmove, listeff);
                        //afterdamage
                        afterDamage(player1.getCurrentMonster());
                        afterDamage(player2.getCurrentMonster());
                        //kalo mati pilih monster baru
                        if(player1.getCurrentMonster().isMonsDead()){
                            Monster replacement = chooseMonster(player1, input);
                            player1.switchCurrMonster(replacement);
                        }else if(player2.getCurrentMonster().isMonsDead()){
                            Monster replacement = chooseMonster(player2, input);
                            player2.switchCurrMonster(replacement);
                        }
                    }
                }else if(action1.equals("1") && action2.equals("2")){
                    //p1 move, p2 switch
                    player2.switchCurrMonster(p2chosenmons);
                    //pake movenya
                    useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), p1chosenmove, listeff);
                    afterDamage(player1.getCurrentMonster());
                    afterDamage(player2.getCurrentMonster());
                    //kalo mati pilih monster baru
                    if(player1.getCurrentMonster().isMonsDead()){
                        Monster replacement = chooseMonster(player1, input);
                        player1.switchCurrMonster(replacement);
                    }else if(player2.getCurrentMonster().isMonsDead()){
                        Monster replacement = chooseMonster(player2, input);
                        player2.switchCurrMonster(replacement);
                    }
                }else if(action1.equals("2") && action2.equals("1")){
                    //p1 switch, p2 move
                    player1.switchCurrMonster(p1chosenmons);
                    //pake move
                    useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), p2chosenmove, listeff);
                    afterDamage(player1.getCurrentMonster());
                    afterDamage(player2.getCurrentMonster());
                    //kalo mati pilih monster baru
                    if(player1.getCurrentMonster().isMonsDead()){
                        Monster replacement = chooseMonster(player1, input);
                        player1.switchCurrMonster(replacement);
                    }else if(player2.getCurrentMonster().isMonsDead()){
                        Monster replacement = chooseMonster(player2, input);
                        player2.switchCurrMonster(replacement);
                    }
                }else if(action1.equals("2") && action2.equals("2")){
                    //p1 dan p2 switch
                    player1.switchCurrMonster(p1chosenmons);
                    player2.switchCurrMonster(p2chosenmons);
                }

                //separator turn
            }   
        }else{
            commands(mainmenu);
        }
    }

    //additional methods
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
        if(move.getTarget() == )
        //kalo enemy berarti pasang statcon/ngatuhin statsbuff
    }

    public static void useMove (Monster source, Monster target, Move move, Effectivity eff){
        if(move instanceof NormalMove){
            useNormalMove(source, target, (NormalMove) move, eff);
        }else if(move instanceof SpecialMove){
            useSpecialMove(source, target, (SpecialMove) move, eff);
        }else if(move instanceof StatusMove){
            useStatusMove(source, target, (StatusMove) move);
        }//tambahin defaultmove
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
        String chosenmonster = input.nextLine();
        Monster chosen = current.getCurrentMonster();
        for(Monster mons : current.getListMon()){
            if(chosenmonster.equals(mons.getName())){
                if(!mons.isMonsDead()){
                    chosen = mons;
                }
            }
        }
        return chosen;
    }

    public static Move chooseMove(Player current, Scanner input){
        System.out.println("Move manakah yang ingin digunakan?");
        current.getCurrentMonster().printMoves();
        String chosenmove = input.nextLine();
        Move chosen = new DefaultMove("jic2");
        for(Move move : current.getCurrentMonster().getMoves()){
            if (chosenmove.equals(move.getName())){
                if(!move.isAmmunitionZero()){
                    chosen = move;
                }
            }
        }

        return chosen;
    }

    public static void commands(String command){
        if(command.equals("Help")){
            Game.help();
        }else if(command.equals("Exit")){
            Game.exit();
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

