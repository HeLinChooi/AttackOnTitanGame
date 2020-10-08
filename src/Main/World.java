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

    public void run() {
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        Wall w = new Wall(10);
        ColossusTitan c = new ColossusTitan();
        // start the game
        System.out.println("The game started.");
        w.printWallWithHP();
        for (int i = 1; i <= 10; i++) {
            // n th hour
            System.out.println("HOUR " + i);
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
            w.printWallWithHP();
            if(w.isWallHasFallen()){
                System.out.println("THE WALL HAS FALLEN");
                System.out.println("YOU LOST");
                break;
            }
        }
    }
}
