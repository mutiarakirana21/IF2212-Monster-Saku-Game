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
        while(!(player1.isAllDead() && player2.isAllDead())){
            Scanner scanx = new Scanner(System.in);
            int action = scanx.nextInt();
            if(action == 1){
                //use move
                System.out.println("Move manakah yang ingin digunakan?");
                player1.getCurrentMonster().printMoves();
                
                for(Move moves : player1.getMoves()){

                }
            }
            
        }
    }
}
