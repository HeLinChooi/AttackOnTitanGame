package Main;

import Titans.ColossusTitan;
import java.util.Random;
import java.util.Scanner;

public class World {

    private int money = 40;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void spendMoney(int amount) {
        int money = getMoney();
        money -= amount;
        if (money < 0) {
            setMoney(0);
        } else {
            setMoney(money);
        }
    }

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

    public void upgradeWeapons(Weapon[] weapons) {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose the weapon you would like to upgrade then hit ENTER to hit the titans");
        boolean[] weaponUpgradeAraay = new boolean[10];
        String inputs = s.nextLine();
        for (int j = 0; j < inputs.length(); j++) {
            weaponUpgradeAraay[Integer.parseInt(inputs.substring(j, j + 1))] = true;
        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(weaponUpgradeAraay[i]);
//        }
        for (int i = 0; i < weapons.length; i++) {
            // if player want to upgrade this weapon
            if (weaponUpgradeAraay[i]) {
                int expense = weapons[i].upLevel(money);
                spendMoney(expense);
            }
        }
        System.out.println("");
    }

    public void upgradeWall(Wall w) {
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to upgrade all walls? (0 = no, 1 = yes)");
        int input = s.nextInt();
        if (input == 1) {
            System.out.println("How many HP do you want to add up?");
            int amount = s.nextInt();
            int expenses = w.addHPForAll(money, amount);
            spendMoney(expenses);
        }
    }

    public void playerTurn(Weapon[] weapons, Wall w) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player's turn");
        upgradeWeapons(weapons);
        upgradeWall(w);
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
        w.printWallWithHP(weapons);
        for (int i = 1; i <= 10; i++) {
            // n th hour
            System.out.println("HOUR " + i);
            System.out.println("Money: " + getMoney());
            enemiesTurn(w, c);
            w.printWallWithHP(weapons);
            if (w.isWallHasFallen()) {
                System.out.println("THE WALL HAS FALLEN");
                System.out.println("YOU LOST");
                break;
            }
            // player's turn
            playerTurn(weapons, w);
            // Show Result
            System.out.println("Result ---------------------------------");
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
            System.out.println("Money + 5 at night\n");
            setMoney(getMoney() + 5);
            System.out.println("----------------------------------------");
        }
    }
}
