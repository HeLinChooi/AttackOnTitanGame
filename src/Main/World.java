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

    public void playerTurn(Weapon[] weapons) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player's turn");
        System.out.println("Choose the weapon you would like to upgrade");
        boolean[] weaponUpgradeAraay = new boolean[10];
        String inputs = s.nextLine();
        for (int j = 0; j < inputs.length(); j++) {
//            if (inputs.contains(Integer.toString(inputs.charAt(j)))) {
                weaponUpgradeAraay[Integer.parseInt(inputs.substring(j,j+1))] = true;
//            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(weaponUpgradeAraay[i]);
        }
        for (int i = 0; i < weapons.length; i++) {
            // if player want to upgrade this weapon
            if (weaponUpgradeAraay[i]) {
                weapons[i].upLevel();
            }
        }
        System.out.println("After upgrading, the weapons' level");
        for (int i = 0; i < weapons.length; i++) {
            System.out.print(weapons[i].getLevel() + "  ");
        }
        System.out.println("");
        System.out.println("");
    }

    public void run() {
        Wall w = new Wall(10);
        // setup weapon reference, all weapon level 0
        Weapon[] weapons = new Weapon[10];
        for (int i = 0; i < weapons.length; i++) {
            weapons[i] = new Weapon();
        }
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
            playerTurn(weapons);
            // attack the titan with our new upgraded weapons!
            for (int j = 0; j < weapons.length; j++) {
                // the titan is on this position
                if (c.getInfrontWallIndex() == j) {
                    // attack him
                    c.reduceHP(weapons[j].attack());
                }
            }
            // check if the titam is dead
            if (c.isDead()) {
                System.out.println("YOU WIN!");
                break;
            }
        }
    }
}
