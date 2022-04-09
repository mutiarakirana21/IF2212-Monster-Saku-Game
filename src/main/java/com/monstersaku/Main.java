package com.monstersaku;

import com.monstersaku.util.CSVReader;

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
        ArrayList<Move> listmove = new ArrayList<Move>();
        try {
            CSVReader fileMove = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            fileMove.setSkipHeader(true);
            List<String[]> line = fileMove.read();
            for (String[] row : line) {
                int idmove = Integer.parseInt(row[0]);
                String tipeMove = String.valueOf(row[1]);
                String nameMove = String.valueOf(row[2]);
                ElementType elementType = ElementType.valueOf(row[3]);
                int accuracy = Integer.parseInt(row[4]);
                int priority = Integer.parseInt(row[5]);
                int ammunition = Integer.parseInt(row[6]);

                if(tipeMove.equals("STATUS")){
                    String target = String.valueOf(row[7]);
                    String state = row[8];

                    StatusCondition statcon;
                    if(!state.equals("-")){
                        statcon = StatusCondition.valueOf(state);
                    }else{
                        statcon = StatusCondition.NOTHING;
                    }
                    String movestats = row[9];
                    String[] arrayofmovestats = movestats.split(",", 10);
                    int healthPoint = Integer.parseInt(arrayofmovestats[0]);
                    int attack = Integer.parseInt(arrayofmovestats[1]);
                    int defense = Integer.parseInt(arrayofmovestats[2]);
                    int specialAttack = Integer.parseInt(arrayofmovestats[3]);
                    int specialDefense = Integer.parseInt(arrayofmovestats[4]);
                    int speed = Integer.parseInt(arrayofmovestats[5]);
                    StatusMove moveStatus = new StatusMove(idmove, nameMove, elementType, accuracy, priority, ammunition, target, statcon, healthPoint, attack, defense, specialAttack, specialDefense, speed);
                    listmove.add(moveStatus);
                    // mov.printMove();  
                }else if (tipeMove.equals("NORMAL")){
                    int basePower = Integer.parseInt(row[8]);
                    NormalMove move = new NormalMove(idmove, nameMove, elementType, accuracy, priority, ammunition, basePower);
                    listmove.add(move);
                    // mov.printMove();
                }else if (tipeMove.equals("SPECIAL")){
                    int basePower = Integer.parseInt(row[8]);
                    SpecialMove move = new SpecialMove(idmove, nameMove, elementType, accuracy, priority, ammunition, basePower);
                    listmove.add(move);
                }
                System.out.println();    
            }
            CSVReader fileMonster = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            fileMonster.setSkipHeader(true);
            List<String[]> lines = fileMonster.read();
            for (String[] row : lines) {
                int idMonster = Integer.parseInt(row[0]);
                String nameMonster = row[1];
                String elementTypes = row[2];
                ArrayList<ElementType> elType = new ArrayList<ElementType>();
                String[] arrayofeltype = elementTypes.split(",", 10);
                for(String eltype : arrayofeltype){
                   if(eltype.equals("FIRE")){
                        elType.add(ElementType.FIRE);
                    }if(eltype.equals("NORMAL")){
                        elType.add(ElementType.NORMAL);
                    }if(eltype.equals("GRASS")){
                        elType.add(ElementType.GRASS);
                    }if(eltype.equals("WATER")){
                        elType.add(ElementType.WATER);
                    }
                }
                
                String basestats = row[3];
                ArrayList<Double> stats = new ArrayList<Double>();
                String[] arrayofstats = basestats.split(",", 10);
                for(String stat : arrayofstats){
                    Double value = Double.parseDouble(stat);
                    stats.add(value);
                }
                Stats baseStats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));

                String moves = row[4];
                Monster mons = new Monster(idMonster, nameMonster, elType, baseStats);
                String[] arrayofmove = moves.split(",", 10);
                for (String monsmove : arrayofmove){
                    mons.getIDmove().add(Integer.parseInt(monsmove)-1);
                }
                listmonster.add(mons);
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

        //config awal
        Game game = new Game();
        Scanner input = new Scanner(System.in);
        game.start(input);
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
            System.out.printf("Player 1 mendapatkan monster : %s\n", listmonster.get(monsterrand).getName());
        }

        System.out.println("-----------------------------------------------------------------------");

        ArrayList<Monster> ListMonsP2 = new ArrayList<Monster>();
        for (int i = 1; i<= 6; i++){
            int monsterrand = rand.nextInt(upperbound);
            ListMonsP2.add(listmonster.get(monsterrand));
            System.out.printf("Player 2 mendapatkan monster : %s\n", listmonster.get(monsterrand).getName());
        }

        System.out.println("-----------------------------------------------------------------------");

        Player player1 = new Player(name1, ListMonsP1);
        player1.switchCurrMonster(ListMonsP1.get(0));
        Player player2 = new Player(name2, ListMonsP2);
        player2.switchCurrMonster(ListMonsP2.get(0));
        //loop game (battle)
        while(!(player1.isAllDead() && player2.isAllDead())){
            //giliran player1
            game.newTurn();
            System.out.println("Sekarang giliran " + player1.getName() + ".");
            System.out.println("Apa yang ingin Anda lakukan?");
            System.out.println("[1] Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
            System.out.println("[2] Mengganti monster (switch).");
            String action1 = game.ingamegetinput(input, player1, player2);
            Move p1chosenmove = null;
            Monster p1chosenmons = player1.getCurrentMonster(); 
            if(action1.equals("1")){
                //use move
                if(player1.getCurrentMonster().isAllMovesUnavailable()){
                    //kalo udah gaada move yang bisa dipilih (ammunitionnya udah 0 semua)
                    p1chosenmove = new DefaultMove("jic");
                    System.out.println("Sayang sekali ammunition semua move sudah habis, terpaksa menggunakan default move.");
                }else{
                    //ada move yang bisa dipake
                    p1chosenmove = game.chooseMove(player1, p1chosenmove, input);
                }
            }else if(action1.equals("2")){
                //switch monster
                p1chosenmons = game.chooseMonster(player1, input);
            }else{
                game.ingameCommands(action1, player1, player2);
            }

            //giliran player 2
            System.out.println("Sekarang giliran " + player2.getName() + ".");
            System.out.println("Apa yang ingin Anda lakukan?");
            System.out.println("[1] Menggunakan Move dari " + player1.getCurrentMonster().getName() + ".");
            System.out.println("[2] Mengganti monster (switch).");
            String action2 = game.ingamegetinput(input, player1, player2);
            Move p2chosenmove = null;
            Monster p2chosenmons = player2.getCurrentMonster(); 
            if(action2.equals("1")){
                //use move
                if(player2.getCurrentMonster().isAllMovesUnavailable()){
                    //kalo udah gaada move yang bisa dipilih (ammunitionnya udah 0 semua)
                    p2chosenmove = new DefaultMove("jic");
                    System.out.println("Sayang sekali ammunition semua move sudah habis, terpaksa menggunakan default move.");
                }else{
                    //ada move yang bisa dipake
                    p2chosenmove = game.chooseMove(player2, p2chosenmove, input);
                }
            }else if(action2.equals("2")){
                //switch monster
                p2chosenmons = game.chooseMonster(player2, input);
            }else{
                game.ingameCommands(action2, player1, player2);
            }

            //battle
            if(action1.equals("1") && action2.equals("1")){
                //p1 dan p2 move
                Move firstmove = p1chosenmove.compareMove(player1.getCurrentMonster(), player2.getCurrentMonster(), p2chosenmove);
                if(firstmove == p1chosenmove){
                    //p1 duluan
                    game.useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), firstmove, listeff);
                    game.useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), firstmove, listeff);
                    //afterdamage
                    game.afterEffect(player1.getCurrentMonster());
                    game.afterEffect(player2.getCurrentMonster());
                    //kalo mati pilih monster baru
                    if(player1.getCurrentMonster().isMonsDead()){
                        Monster replacement = game.chooseMonster(player1, input);
                        player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                        player1.switchCurrMonster(replacement);
                    }else if(player2.getCurrentMonster().isMonsDead()){
                        Monster replacement = game.chooseMonster(player2, input);
                        player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                        player2.switchCurrMonster(replacement);
                    }
                }else{
                    //p2 duluan
                    game.useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), firstmove, listeff);
                    game.useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), firstmove, listeff);
                    //afterdamage
                    game.afterEffect(player1.getCurrentMonster());
                    game.afterEffect(player2.getCurrentMonster());
                    //kalo mati pilih monster baru
                    if(player1.getCurrentMonster().isMonsDead()){
                        Monster replacement = game.chooseMonster(player1, input);
                        player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                        player1.switchCurrMonster(replacement);
                    }else if(player2.getCurrentMonster().isMonsDead()){
                        Monster replacement = game.chooseMonster(player2, input);
                        player2.switchCurrMonster(replacement);
                        player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                    }
                }
            }else if(action1.equals("1") && action2.equals("2")){
                //p1 move, p2 switch
                player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                player2.switchCurrMonster(p2chosenmons);
                //pake movenya
                game.useMove(player1.getCurrentMonster(), player2.getCurrentMonster(), p1chosenmove, listeff);
                game.afterEffect(player1.getCurrentMonster());
                game.afterEffect(player2.getCurrentMonster());
                //kalo mati pilih monster baru
                if(player1.getCurrentMonster().isMonsDead()){
                    Monster replacement = game.chooseMonster(player1, input);
                    player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                    player1.switchCurrMonster(replacement);
                }else if(player2.getCurrentMonster().isMonsDead()){
                    Monster replacement = game.chooseMonster(player2, input);
                    player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                    player2.switchCurrMonster(replacement);
                }
            }else if(action1.equals("2") && action2.equals("1")){
                //p1 switch, p2 move
                player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                player1.switchCurrMonster(p1chosenmons);
                //pake move
                game.useMove(player2.getCurrentMonster(), player1.getCurrentMonster(), p2chosenmove, listeff);
                game.afterEffect(player1.getCurrentMonster());
                game.afterEffect(player2.getCurrentMonster());
                //kalo mati pilih monster baru
                if(player1.getCurrentMonster().isMonsDead()){
                    Monster replacement = game.chooseMonster(player1, input);
                    player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                    player1.switchCurrMonster(replacement);
                }else if(player2.getCurrentMonster().isMonsDead()){
                    Monster replacement = game.chooseMonster(player2, input);
                    player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                    player2.switchCurrMonster(replacement);
                }
            }else if(action1.equals("2") && action2.equals("2")){
                //p1 dan p2 switch
                player1.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                player2.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                player1.switchCurrMonster(p1chosenmons);
                player2.switchCurrMonster(p2chosenmons);
            }

            game.incrTurn();
        }   
    }
    
}