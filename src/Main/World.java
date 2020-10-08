package Main;

import Titans.ColossusTitan;
import java.util.Random;
import java.util.Scanner;

public class World {

    public static int money = 100;

    public boolean randomTrueOrFalse() {
        Random r = new Random();
        return r.nextInt(2) == 1;
    }

    public void enemiesTurn(Wall w, ColossusTitan c) {
        // every hour the wall reduce hp by 1
        w.reduceHPForAll();
        // does the titan want to appear or disappear?
        if (randomTrueOrFalse()) {
            c.setShowUp(!c.isShowUp());
            if (c.isShowUp()) {
                System.out.println("Titan show up!");
                // in front of random wall unit
                // !!!!!!!
                w.reduceHPOn1Unit(c.attack(), c.getInfrontWallIndex());
            } else {
                System.out.println("Titan disappear!");
            }
        } else {
            // if it aleader in front of wall
            if (c.isShowUp()) {
                w.reduceHPOn1Unit(c.attack(), c.getInfrontWallIndex());
            }
        }
    }

    public void playerTurn(Weapon[] weapons, boolean[] weaponUpgradeAraay) {
        System.out.println("Player's turn");
        for (int i = 0; i < weapons.length; i++) {
            // if player want to upgrade this weapon
            if (weaponUpgradeAraay[i]) {
                if (weapons[i] == null) {
                    // new weapon built!
                    weapons[i] = new Weapon();
                } 
//                else {
//                    weapons[i].upLevel();
//                }
            }
        }
    }

    public void run() {
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        Wall w = new Wall(10);
        // setup weapon reference, no weapon there yet
        Weapon[] weapons = new Weapon[10];
        ColossusTitan c = new ColossusTitan();
        // start the game
        System.out.println("The game started.");
        w.printWallWithHP();
        for (int i = 1; i <= 10; i++) {
            // n th hour
            System.out.println("HOUR " + i);
            enemiesTurn(w, c);
            w.printWallWithHP();
            if (w.isWallHasFallen()) {
                System.out.println("THE WALL HAS FALLEN");
                System.out.println("YOU LOST");
                break;
            }
            // player's turn
            boolean[] weaponUpgradeAraay = new boolean[10];
            for (int j = 0; j < weaponUpgradeAraay.length; j++) {
                if (j == 0) {
                    weaponUpgradeAraay[j] = true;
                }
            }
            playerTurn(weapons, weaponUpgradeAraay);
            // attack the titan with our new upgraded weapons!
            for (int j = 0; j < weapons.length; j++) {
                // the titan is on this position
                if(c.getInfrontWallIndex() == j){
                    // attack him
                    c.reduceHP(weapons[j].attack());
                }
            }
            // check if the titam is dead
            if(c.isDead()){
                System.out.println("YOU WIN!");
                break;
            }
        }
    }
}
