package Main;

import Titans.ArmouredTitan;
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

    public boolean randomTrueOrFalse(double rate) {
        Random r = new Random();
        int temp = r.nextInt(10);
        double bound = rate * 10;
        System.out.println(temp + " " + bound);
        return temp >= bound;
    }

    public void enemiesTurn(Wall w, Weapon[] weapons, ColossusTitan c, ArmouredTitan a) {
        Random r = new Random();
        System.out.println("Enemies' turn");
        if (!c.isShowUp()) {
            c.setShowUp(!c.isShowUp());
            System.out.println(String.format("Titan show up and hit wall %d !", c.getInfrontWallIndex()));
            w.reduceHPOn1Unit(c.attack(), c.getInfrontWallIndex());
        } else {
            // if random number is 1 then move right, else move left
            if (r.nextInt(2) == 1) {
                if (c.getInfrontWallIndex() != w.getWallLength() - 1) {
                    c.setInfrontWallIndex(c.getInfrontWallIndex() + 1);
                }
            } else {
                if (c.getInfrontWallIndex() != 0) {
                    c.setInfrontWallIndex(c.getInfrontWallIndex() - 1);
                }
            }
            System.out.println(String.format("Colossus Titan continue to destroy wall %d !", c.getInfrontWallIndex()));
            w.reduceHPOn1Unit(c.attack(), c.getInfrontWallIndex());
        }
        // Armoured Titan
        if (!a.isShowUp()) {
            if (r.nextInt(2) == 1) {
                System.out.println("Armoued Titan show up!");
                a.setShowUp(true);
            }
        } else {
            if (a.getDistanceFromWall() == 0) {
                System.out.println(String.format("Armoured Titan on wall %d !", a.getInfrontWallIndex()));
                // if the weapon is in front of titan
                int position = a.getInfrontWallIndex();
//                for (int i = 0; i < weapons.length; i++) {
//                    if (i == a.getInfrontWallIndex()) {
                if (weapons[position].getLevel() > 0) {
//                    System.out.println("got weapon");
                    weapons[position].levelDownTo0();
                } else if (weapons[position].getLevel() == 0 && !isNoWeapons(weapons)) {
//                    System.out.println("no weapon here but got other weapon and move to other weapon");
                    // if still got weapons on wall, destroy the weapons first
                    a.getCloserToWeapon(weapons);
                } else if (isNoWeapons(weapons)) {
//                    System.out.println("no weapon on wall any more");
                    w.reduceHPOn1Unit(a.attack(), position);
                }
//                    }
//                }
            } else {
                a.getCloserToWall();
                System.out.println("current distance: " + a.getDistanceFromWall());
            }
        }
    }

    public boolean isNoWeapons(Weapon[] weapons) {
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i].getLevel() > 0) {
                return false;
            }
        }
        return true;
    }

    public void upgradeWeapons(Weapon[] weapons) {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        int[] weaponUpgradeAraay = new int[10];
        String inputs = s.nextLine();
        for (int j = 0; j < inputs.length(); j++) {
            weaponUpgradeAraay[Integer.parseInt(inputs.substring(j, j + 1))]++;
        }
        for (int i = 0; i < weapons.length; i++) {
            // if player want to upgrade this weapon
            if (weaponUpgradeAraay[i] > 0) {
                for (int j = 0; j < weaponUpgradeAraay[i]; j++) {
                    int expense = weapons[i].upLevel(money);
                    spendMoney(expense);
                }
            }
        }
    }

    public void upgradeWall(Wall w) {
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current money amount: " + money);
        String inputStr = s.nextLine();
        int input = inputStr.equals("") ? 2 : Integer.parseInt(inputStr);
        if (input == 1) {
            System.out.println("How many HP do you want to add up?");
            int amount = s.nextInt();
            s.nextLine();
            int expenses = w.addHPForAll(money, amount);
            spendMoney(expenses);
        }
        System.out.println("Choose the wall(s) that you would like to upgrade (Type a string of integer or hit Enter to skip)");
        int[] weaponUpgradeAraay = new int[10];
        String indexStr = s.nextLine();
        System.out.println("How many HP do you want to add up to the wall(s)? Current money amount: " + money);
        for (int i = 0; i < indexStr.length(); i++) {
            // if player want to upgrade this weapon
            int amount = s.nextInt();
            weaponUpgradeAraay[Integer.parseInt(indexStr.substring(i, i + 1))] = amount;
        }
        for (int i = 0; i < weaponUpgradeAraay.length; i++) {
            // if player want to upgrade this weapon
            int expenses = w.addHPOn1Unit(money, weaponUpgradeAraay[i], i);
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
        Ground g = new Ground(10);
        // setup weapon reference, all weapon level 0
        Weapon[] weapons = new Weapon[10];
        for (int i = 0; i < weapons.length; i++) {
            weapons[i] = new Weapon();
        }
        ColossusTitan c = new ColossusTitan();
        ArmouredTitan a = new ArmouredTitan();
        // start the game
        for (int i = 0; i <= 24; i++) {
            // n th hour
            System.out.println("HOUR " + i);
            System.out.println("Money: " + getMoney());
//            if (i > 5) {
            enemiesTurn(w, weapons, c, a);
//            }
            g.printGround(c, a);
            w.printWallWithHP(weapons);
            if (w.isWallHasFallen()) {
                System.out.println("THE WALL HAS FALLEN");
                System.out.println("YOU LOST");
                break;
            }
            // player's turn
            playerTurn(weapons, w);
            // Show Result
            showResult(c, a, weapons, w, g);
            // check if the titam is dead
            if (c.isDead()) {
                System.out.println("YOU WIN!");
                break;
            }
        }
    }

    public void showResult(ColossusTitan c, ArmouredTitan a, Weapon[] weapons, Wall w, Ground g) {
        System.out.println("---------------- Result ----------------");
        g.printGround(c, a);
        w.printWallWithHP(weapons);
        // attack the titan with our new upgraded weapons!
        for (int j = 0; j < weapons.length; j++) {
            // the titan is on this position
            if (c.getInfrontWallIndex() == j) {
                // attack him

                c.reduceHP(weapons[j].attack());
            }
            if (a.getInfrontWallIndex() == j) {
                // attack him
                a.reduceHP(weapons[j].attack());
            }
        }
        System.out.println("Money + 5");
        setMoney(getMoney() + 5);
//        if (c.isShowUp()) {
//            System.out.println(String.format("Titan still in front of the wall %d and he hit the wall", c.getInfrontWallIndex()));
//        }
        System.out.println("----------------------------------------");
    }
}
