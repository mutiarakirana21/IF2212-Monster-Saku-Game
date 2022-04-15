package com.monstersaku;

import java.util.Scanner;
import java.util.Random;

public class Game {
    private int turn;

    //Konstruktor
    public Game(){
        this.turn = 1;
    }

    //getter turn
    public int getTurn(){
        return turn;
    }

    //increment turn
    public void incrTurn(){
        turn++;
    }

    // Kata Pembuka
    public void start(Scanner sc) {
        System.out.println("");
        System.out.println("==============================================");
        System.out.println("********SELAMAT DATANG DI MONSTER SAKU********");
        System.out.println("==============================================\n");
        System.out.println("");
        System.out.println("------Ketik 'Start Game' untuk memulai game-----");
        System.out.println("");
        
        boolean start = false;
        do{
            System.out.printf("Masukkan Command : ");
            String command = sc.nextLine();
            if (command.toLowerCase().equals("start game")){
                start = true;
            }else{
                commands(command);
            }
            space();
        }while(!start);
    }

    // Print Spasi 
    public void space() {
        System.out.println("");
        System.out.println("=================================================================");
        System.out.println("");
    }

    // Print help
    public void help() {
        space();
        System.out.println("Cara memainkan game monster saku : ");
        System.out.println("Pertama-tama setiap pemain diberi 6 monster random.");
        System.out.println("Lalu pemain dapat memilih satu command setiap turnnya.");
        System.out.println("Ada Move untuk memilih tipe serangan atau gerakan.");
        System.out.println("Ada Switch untuk mengubah monster yang ditarungkan.");
        System.out.println("Ada juga View Monsters Info untuk melihat informasi monster.");
        System.out.println("Ada juga View Game Info untuk melihat informasi turn dan informasi monster lainnya.");
        System.out.println("Help dapat digunakan untuk melihat bantuan ini kembali.");
        System.out.println("Exit dapat digunakan untuk keluar dari game.\n");
        System.out.println("Semoga Membantu Yaaa!!!");
    }

    // Command dalam battle
    public void listCommand() {
        System.out.println("Command yang dapat dijalankan : ");
        System.out.println("[1] Move");
        System.out.println("[2] Switch");
        System.out.println("[3] View Monsters Info (khusus dalam game)");
        System.out.println("[4] View Game Info (khusus dalam game)");
        System.out.println("[5] Help");
        System.out.println("[6] Exit");
    }

    //Keluar dari aplikasi
    public void exit(){
        space();
        System.out.println("Terima kasih telah bermain Monster Saku! Sampai jumpa lagi!");
        space();
        System.exit(0);
    }

    // Print info semua monster yang sedang bertarung untuk 1 player
    public void printMonstersInfo(Player player){
        System.out.printf("Info dari monster yang dimiliki %s : \n", player.getName());
        for(Monster monsterx : player.getListMon()){
            Stats baseStats = monsterx.getbaseStats();
            System.out.println("Monster          : " + monsterx.getName());
            System.out.print("Element Type     : ");
            for(ElementType eltype : monsterx.getelemenTypes()){
                switch(eltype) {
                    case NORMAL:
                        System.out.print("Normal ");
                        break;
                    case FIRE:
                        System.out.print("Fire ");
                        break;
                    case WATER:
                        System.out.print("Water ");
                        break;
                    case GRASS:
                        System.out.print("Grass ");
                        break;
                }
                System.out.print("\n");
            }
            System.out.println("Max HP           : " + baseStats.getmaxHP());
            System.out.println("HP               : " + baseStats.getHealthPoint());
            System.out.println("Attack           : " + baseStats.getAttack());
            System.out.println("Defense          : " + baseStats.getDefense());
            System.out.println("Special Attack   : " + baseStats.getSpecialAttack());
            System.out.println("Special Defense  : " + baseStats.getSpecialDefense());
            System.out.println("Speed            : " + baseStats.getSpeed());
            System.out.print("Status Condition : ");
            switch(monsterx.getStatcon()){
                case BURN:
                    System.out.println("Burn");
                    break;
                case POISON:
                    System.out.println("Poison");
                    break;
                case SLEEP:
                    System.out.println("Sleep");
                    break;
                case PARALYZE:
                    System.out.println("Paralyze");
                    break;
                default:
                    System.out.println("-");
            }
            System.out.println("Moves   : ");
            for(Move movex : monsterx.getMoves()){
                System.out.println(movex.getName());
            }
            System.out.print("\n\n");
        }
    }
 
    public void viewMonstersInfo(Player player1, Player player2){
        space();
        printMonstersInfo(player1);
        printMonstersInfo(player2);
        space();
    }

    //Move Related Commands
    public void useMove (Monster source, Monster target, Move move, Effectivity eff, Player player1, Player player2){
        Random rand = new Random();
        int number = rand.nextInt(100);
        if(source.getStatcon() != StatusCondition.SLEEP){
            if(source.getStatcon() != StatusCondition.PARALYZE){
                if(number <= move.getAccuracy()){
                    //bisa pake movenya kalo angka randomnya ada di range 0-accuracy dari movenya
                    System.out.printf("\n%s (Monster %s) menggunakan move %s!\n", source.getName(), player1.getName(), move.getName());
                    executeMove(source, target, move, eff, player1, player2);
                }else{
                    //gabisa pake move soalnya ga di dalem range accuracynya
                    System.out.printf("\nSayang sekali move %s dari %s (Monster %s) miss (tidak berhasil).\n", move.getName(), source.getName(), player1.getName());
                    move.setAmmunition(move.getAmmunition() - 1);
                }
            }else{
                if(rand.nextInt(4) == 3){
                    System.out.printf("\nSayang sekali move %s dari %s (Monster %s) miss (tidak berhasil) karena monster paralyzed.\n", move.getName(), source.getName(), player1.getName());
                }else{
                    System.out.printf("%s menggunakan move %s!\n", source.getName(), move.getName());
                    executeMove(source, target, move, eff, player1, player2);
                }
            }
        }else if(source.getStatcon() == StatusCondition.SLEEP){
            //monsternya kena sleep
            if(source.getnumsleep() != 0){
                System.out.printf("%s tidak dapat melakukan move karena sedang sleep. Silakan coba lagi dalam %d turn.\n", source.getName(), source.getnumsleep());
            }
        }
    }

    public void executeMove(Monster source, Monster target, Move move, Effectivity eff, Player player1, Player player2){
        if(move instanceof NormalMove){
            useNormalMove(source, target, (NormalMove) move, eff, player1, player2);
        }else if(move instanceof SpecialMove){
            useSpecialMove(source, target, (SpecialMove) move, eff, player1, player2);
        }else if(move instanceof StatusMove){
            useStatusMove(source, target, (StatusMove) move, player1, player2);
        }else if(move instanceof DefaultMove){
            useDefaultMove(source, target, move, eff, player1, player2);
        }
        move.setAmmunition(move.getAmmunition() - 1);
    }

    public void useNormalMove (Monster source, Monster target, NormalMove move, Effectivity eff, Player player1, Player player2){
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
        Double finaldamage = (double)Math.floor((move.getBasePower() * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        System.out.printf("\n%s (Monster %s) terkena damage sebesar %.2f dari move %s milik %s (Monster %s).\n\n", target.getName(), player2.getName(), finaldamage, move.getName(), source.getName(), player1.getName());
        if (HPBaru <= 0){
            HPBaru = 0.0;
            target.monsterDie();
        }
        target.getbaseStats().setHealthPoint(HPBaru);
    }
    
    public void useSpecialMove (Monster source, Monster target, SpecialMove move, Effectivity eff, Player player1, Player player2){
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
        Double finaldamage = (double)Math.floor((move.getBasePower() * ((source.getbaseStats().getSpecialAttack()) / (target.getbaseStats().getSpecialDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        System.out.printf("\n%s (Monster %s) terkena damage sebesar %.2f dari move %s milik %s (Monster %s).\n\n", target.getName(), player2.getName(), finaldamage, move.getName(), source.getName(), player1.getName());
        if (HPBaru <= 0){
            HPBaru = 0.0;
            target.monsterDie();
        }
        target.getbaseStats().setHealthPoint(HPBaru);

    }
    
    public void useDefaultMove (Monster source, Monster target, Move move, Effectivity eff, Player player1, Player player2){
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
        Double finaldamage = (double)Math.floor((50 * ((source.getbaseStats().getAttack()) / (target.getbaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * ElementEffectivity * burn);
        Double HPBaru = target.getbaseStats().getHealthPoint() - finaldamage;
        System.out.printf("\n%s (Monster %s) terkena damage sebesar %.2f dari default move milik %s (Monster %s).\n", target.getName(), player2.getName(), finaldamage, source.getName(), player1.getName());
        if (HPBaru <= 0){
            HPBaru = 0.0;
            target.monsterDie();
        }
        target.getbaseStats().setHealthPoint(HPBaru);
        Double sourcedmg = Math.floor(0.25 * source.getbaseStats().getmaxHP());
        Double HPSource = Math.floor(source.getbaseStats().getHealthPoint() - sourcedmg);
        System.out.printf("%s (Monster %s) terkena damage sebesar %.2f dari penggunaan default movenya.\n", source.getName(), player1.getName(), sourcedmg);
        if (HPSource <= 0){
            HPBaru = 0.0;
            target.monsterDie();
            System.out.printf("%s (Monster %s) mati karena menggunakan default movenya.\n", source.getName(), player1.getName());
        }
        source.getbaseStats().setHealthPoint(HPSource);
    }

    public void useStatusMove (Monster source, Monster target, StatusMove move, Player player1, Player player2){
        //kalo diri sendiri harusnya heal/ganti statsbuff
        if(move.getTarget().equals("OWN")){
            //healnya belom dipisah sm yg ngaruh ke stats buff doang
            if(move.getHPEffect() > 0){
                //ada efek healnya, jadi heal
                if(source.getbaseStats().getHealthPoint() == source.getbaseStats().getmaxHP()){
                    //HP monster masih penuh, jadi ga keheal (sia-sia)
                    System.out.printf("%s (Monster %s) tidak dapat melakukan heal karena HP masih full.\n", source.getName(), player1.getName());
                }else{
                    //HP monsternya ga penuh, jadi bisa keheal
                    double HPincr = (double) (move.getHPEffect() * source.getbaseStats().getmaxHP())/100;
                    double HP = source.getbaseStats().getmaxHP() + HPincr;
                    if(HP >= source.getbaseStats().getmaxHP()){
                        //kalo healnya ngelebihin maxHP
                        HP = source.getbaseStats().getmaxHP();
                        System.out.printf("%s (Monster %s) melakukan heal dan sekarang sudah mencapai maximum HP.\n", source.getName(), player1.getName());
                    }else{
                        System.out.printf("%s (Monster %s) melakukan heal dan mendapat penambahan HP sebesar %.2f.\n", source.getName(), player1.getName(), HPincr);
                    }
                    source.getbaseStats().setHealthPoint(HP);
                }
            }
            StatsBuff statsBuff = source.getbaseStats().getStatsBuff();
            if((move.getAttackEffect() != 0) || (move.getDefenseEffect() != 0) || (move.getSpAttEffect() != 0) || (move.getSpDefEffect() != 0) || (move.getSpeedEffect() != 0)){
                if(statsBuff.canChange()){
                    statsBuff.setAttackBuff((int) statsBuff.getAB() + move.getAttackEffect());
                    statsBuff.setDefenseBuff((int) statsBuff.getDB() + move.getDefenseEffect());
                    statsBuff.setSpAttBuff((int) statsBuff.getSAB() + move.getSpAttEffect());
                    statsBuff.setSpDefBuff((int) statsBuff.getSDB() + move.getSpDefEffect());
                    statsBuff.printStatsBuff(source);
                }else{
                    System.out.printf("%s (Monster %s) tidak dapat mengubah stats buff karena nilainya sudah mencapai maksimal.\n", source.getName(), player1.getName());
                }
            }
        }else if(move.getTarget().equals("ENEMY")){
            //kalo enemy berarti pasang statcon/ngaruhin statsbuff
            //ngasih statscon dulu
            System.out.printf("\n%s (Monster %s) terkena move %s dari %s (Monster %s) .\n", target.getName(), player2.getName(), move.getName(), source.getName(), player1.getName());
            if (move.getStatusCondition() == StatusCondition.BURN){
                if (target.getStatcon() == StatusCondition.NOTHING){
                    target.burn();
                    System.out.printf("%s (Monster %s) terkena status condition burn.\n", target.getName(), player2.getName());
                }else{
                    System.out.printf("%s (Monster %s) telah memiliki status condition lain.\n", target.getName(), player2.getName());
                }
            }else if (move.getStatusCondition() == StatusCondition.POISON){
                if (target.getStatcon() == StatusCondition.NOTHING){
                    target.poison();
                    System.out.printf("%s (Monster %s) terkena status condition poison.\n", target.getName(), player2.getName());
                }else{
                    System.out.printf("%s (Monster %s) telah memiliki status condition lain.\n", target.getName(), player2.getName());
                }
            }else if (move.getStatusCondition() == StatusCondition.PARALYZE){
                if (target.getStatcon() == StatusCondition.NOTHING){
                    target.paralyze();
                    System.out.printf("%s (Monster %s) terkena status condition paralyze.\n", target.getName(), player2.getName());
                }else{
                    System.out.printf("%s (Monster %s) telah memiliki status condition lain.\n", target.getName(), player2.getName());
                }
            }else if (move.getStatusCondition() == StatusCondition.SLEEP){
                if (target.getStatcon() == StatusCondition.NOTHING){
                    target.sleep();
                    System.out.printf("%s (Monster %s) terkena status condition sleep selama %d turn.\n", target.getName(), player2.getName(), target.getnumsleep());
                }else{
                    System.out.printf("%s (Monster %s) telah memiliki status condition lain.\n", target.getName(), player2.getName());
                }
            }
            //ngasih pengaruh ke statsbuff lawan
            StatsBuff statsBuff = target.getbaseStats().getStatsBuff();
            if((move.getAttackEffect() != 0) || (move.getDefenseEffect() != 0) || (move.getSpAttEffect() != 0) || (move.getSpDefEffect() != 0) || (move.getSpeedEffect() != 0)){
                if(statsBuff.canChange()){
                    statsBuff.setAttackBuff((int) statsBuff.getAB() + move.getAttackEffect());
                    statsBuff.setDefenseBuff((int) statsBuff.getDB() + move.getDefenseEffect());
                    statsBuff.setSpAttBuff((int) statsBuff.getSAB() + move.getSpAttEffect());
                    statsBuff.setSpDefBuff((int) statsBuff.getSDB() + move.getSpDefEffect());
                    statsBuff.setSpeedBuff((int) statsBuff.getSB() + move.getSpeedEffect());
                    statsBuff.printStatsBuff(target);
                }else{
                    System.out.printf("%s (Monster %s) tidak dapat mengubah stats buff karena nilainya sudah mencapai maksimal.\n", target.getName(), player2.getName());
                }
            }
        }
    }

    //After battle effects
    public void afterEffect(Player player){
        Monster affected = player.getCurrentMonster();
        StatusCondition statcon = affected.getStatcon();
        if(statcon == StatusCondition.BURN){
            double afterdamage = (double)Math.floor(affected.getbaseStats().getmaxHP() * 0.125); 
            double HPBaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            System.out.printf("%s mendapatkan afterdamage dari burn. HPnya berkurang sebesar %.2f.\n", player.getCurrentMonster().getName(), afterdamage);
            if(HPBaru <= 0 ){
                HPBaru = 0;
                affected.monsterDie();
            }
            affected.getbaseStats().setHealthPoint(HPBaru);
        }else if(statcon == StatusCondition.POISON){
            double afterdamage = (double)Math.floor(affected.getbaseStats().getmaxHP() * 0.0625);
            double HPBaru = affected.getbaseStats().getHealthPoint() - afterdamage;
            System.out.printf("%s mendapatkan afterdamage dari poison. HPnya berkurang sebesar %.2f.\n", player.getCurrentMonster().getName(), afterdamage);
            if(HPBaru <= 0){
                HPBaru = 0;
                affected.monsterDie();
            }
            affected.getbaseStats().setHealthPoint(HPBaru);
        }
    }

    public void afterSleep(Player player){
        for(Monster monsterx : player.getListMon()){
            if (monsterx.getStatcon() == StatusCondition.SLEEP){
                monsterx.sleepdecr(player);
            }
        }
    }

    //Pilih monster
    public Monster chooseMonster(Player current, Player other, Scanner input){
        if(current.isNoMoreMonsterAvailable()){
            //kalo udah gaada monster yg idup selain dia, balikin dia lagi (gabisa switch), harus pilih move
            System.out.printf("\nSayang sekali tidak ada lagi monster yang hidup selain %s.\n", current.getCurrentMonster().getName());
            System.out.printf("Silakan pilih move yang tersedia untuk %s.\n", current.getCurrentMonster().getName());
            return current.getCurrentMonster();
        }else{
            System.out.printf("%s akan memilih monster.\n\n", current.getName());
            System.out.println("Monster manakah yang ingin digunakan? (Masukkan nomornya).\n");
            current.printAvailableMonsters(); 
            boolean valid = false;
            Monster chosen = current.getCurrentMonster();
            do{
                System.out.printf("\nMasukkan command : ");
                String chosenmonster = input.nextLine();
                Integer chosenmonsidx = 10;
                if(chosenmonster.equals("1") || chosenmonster.equals("2") || chosenmonster.equals("3") || chosenmonster.equals("4") || chosenmonster.equals("5") || chosenmonster.equals("6")){
                    chosenmonsidx = (Integer.valueOf(chosenmonster) - 1);
                }
                for(int i = 0; i < current.getListMon().size(); i++){
                    Monster mons = current.getListMon().get(i);
                    if(chosenmonsidx == i && !valid && !mons.equals(chosen)){
                        if(!mons.isMonsDead()){
                            chosen = mons;
                            valid = true;
                            System.out.printf("%s memilih monster %s.\n", current.getName(), chosen.getName());
                            break;               
                        }
                    }
                }
                if(!valid){
                    //kalo masukan monster tidak valid (tidak ada monsternya)
                    if(chosenmonster.toLowerCase().equals("help") || chosenmonster.toLowerCase().equals("exit") || chosenmonster.toLowerCase().equals("view game info") || chosenmonster.toLowerCase().equals("view monsters info")){
                        ingameCommands(chosenmonster, current, other);
                    }else{
                        System.out.println("Monster tidak valid! Silakan pilih monster lain.");
                    }
                }
            }while (!valid);
            return chosen;
        }
    }

    //tuker monsternya
    public boolean replaceMon(Player current, Player other, Scanner input){
        //return true kalo monster terganti
        boolean switched = false;
        if(current.getCurrentMonster().isMonsDead()){
            if(!current.isNoMoreMonsterAvailable()){
                //masih ada monster lain yang masih hidup
                Monster replacement = chooseMonster(current, other, input);
                current.getCurrentMonster().getbaseStats().getStatsBuff().resetbuff();
                current.switchCurrMonster(replacement);
                switched = true;
            }else{
                System.out.printf("Oh tidak! Monster terakhir %s sudah mati!\n", current.getName());
            }
        }
        return switched;
    }

    //Pilih Move
    public Move chooseMove(Player current, Player other, Move chosen, Scanner input){
        if(current.getCurrentMonster().isAllMovesUnavailable()){
            //kalo misalnya udah gaada move yang bisa dipilih
            System.out.println("\nSayang sekali ammunition semua move sudah habis, terpaksa menggunakan default move.");
            return chosen;
        }
        System.out.printf("%s akan memilih move.\n\n", current.getName());
        System.out.println("Move manakah yang ingin digunakan?\n");
        current.getCurrentMonster().printAvailableMoves();
        boolean valid = false;
        do{
            System.out.printf("\nMasukkan command : ");
            String chosenmove = input.nextLine();
            for(Move move : current.getCurrentMonster().getMoves()){
                if(chosenmove.toLowerCase().equals(move.getName().toLowerCase()) && !move.isAmmunitionZero()){
                    chosen = move;
                    valid = true;
                    System.out.printf("%s memilih move %s.\n", current.getName(), chosen.getName());
                    break;
                }
                if(chosenmove.toLowerCase().equals(move.getName().toLowerCase()) && move.isAmmunitionZero()){
                    //udah nemu movenya tapi amunisinya udah 0 (kurang) berarti gabisa jalan, pilh move lain
                    System.out.printf("%s tidak dapat dijalankan karena ammunition sudah habis. Silakan pilih move lain.\n", move.getName());
                }
            }
            if(!valid){
                if(chosenmove.toLowerCase().equals("help") || chosenmove.toLowerCase().equals("exit") || chosenmove.toLowerCase().equals("view game info") || chosenmove.toLowerCase().equals("view monsters info")){
                    ingameCommands(chosenmove, current, other);
                }else{
                    //kalo masukan move tidak valid (tidak ada movenya)
                    System.out.println("Move tidak valid! silakan pilih move lain.");
                }
            }
        }while(!valid);
        return chosen;
    }

    //command lain dari yang diminta
    public void commands(String command){
        if(command.toLowerCase().equals("help")){
            help();
            listCommand();
            space();;
        }else if(command.toLowerCase().equals("exit")){
            exit();
        }else{
            System.out.println("Command tidak valid! Masukkan command lain.");
        }
    }

    //command dalam game
    public void ingameCommands(String command, Player player1, Player player2){
        if(command.toLowerCase().equals("view game info")){
            printGameInfo(player1, player2);
            space();
        }else if(command.toLowerCase().equals("view monsters info")){
            viewMonstersInfo(player1, player2);
        }else{
            commands(command);
        }
    }

    //mencetak ke layar informasi game (turn, monster yang sedang dimainkan, dan monster yang tidak bertarung)
    public void printGameInfo(Player player1, Player player2){
        System.out.printf("Turn dalam game ini : %d\n", turn);
        System.out.println("Monster yang sedang dimainkan " + player1.getName() + " : " + player1.getCurrentMonster().getName());
        System.out.println("Monster yang sedang dimainkan " + player2.getName() + " : " + player2.getCurrentMonster().getName());
        System.out.println("Monster dari " + player1.getName() + " yang sedang tidak bertarung : ");
        player1.printMonstersNotUsed();
        System.out.println("Monster dari " + player2.getName() + " yang sedang tidak bertarung : ");
        player2.printMonstersNotUsed();
    }

    //awalan turn
    public void newTurn(Player player1, Player player2) {
        System.out.printf("*** ROUND %d ***\n\n", turn);
        System.out.printf("!!!   FIGHT  !!!\n\n");
        System.out.printf("Monster yang sedang dimainkan %s : %s dengan HP = %.2f\n", player1.getName(), player1.getCurrentMonster().getName(), player1.getCurrentMonster().getbaseStats().getHealthPoint());
        System.out.printf("Monster yang sedang dimainkan %s : %s dengan HP = %.2f\n", player2.getName(), player2.getCurrentMonster().getName(), player2.getCurrentMonster().getbaseStats().getHealthPoint());
    }

    //akhir turn mencetak status game saat ini
    public void endTurn(Player player1, Player player2){
        System.out.printf("\nTurn ke-%d berakhir.\n", turn);
        space();
        incrTurn();
    }

    //input player ingame
    public String ingamegetinput(Scanner scan, Player player1, Player player2){
        boolean valid = false;
        String input = null;
        do{
            input = scan.nextLine();
            if(input.toLowerCase().equals("help") || input.toLowerCase().equals("exit") || input.toLowerCase().equals("view game info") || input.toLowerCase().equals("view monsters info") || input.equals("1")|| (input.equals("2"))){
                valid = true;
                if(input.toLowerCase().equals("help") || input.toLowerCase().equals("exit") || input.toLowerCase().equals("view game info") || input.toLowerCase().equals("view monsters info")){
                    ingameCommands(input, player1, player2);
                }
            }else{
                System.out.println("Command tidak valid! Silakan masukkan command lain.\n");
                System.out.printf("Masukkan command : ");
            }
        }while(!valid);
        return input;
    }

    //ask what to do
    public void whatToDo(Player player){
        System.out.printf("\n");
        System.out.println("Sekarang giliran " + player.getName() + ".\n");
        System.out.println("Apa yang ingin Anda lakukan?");
        System.out.println("[1] Menggunakan Move dari " + player.getCurrentMonster().getName() + ".");
        System.out.println("[2] Mengganti monster (switch).");
    }

    //pengumuman pemenang dan exit aplikasi
    public void endGame(Player winner){
        System.out.println("Congratulationss!!");
        System.out.printf("The winner is : %s!!\n", winner.getName());
        exit();
    }
}