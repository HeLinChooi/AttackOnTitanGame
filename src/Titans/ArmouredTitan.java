package Titans;

import Main.Weapon;
import java.util.Random;

public class ArmouredTitan extends Titan {

    private int distanceFromWall = 9;
    private int attack = 5;

    public ArmouredTitan() {
        this.hp = 100;
    }

    public void getCloserToWall() {
        Random r = new Random();
        if (r.nextInt(2) == 1) {
            if (getInfrontWallIndex() != 9) {
                setInfrontWallIndex(getInfrontWallIndex() + 1);
            }
        } else {
            if (getInfrontWallIndex() != 0) {
                setInfrontWallIndex(getInfrontWallIndex() - 1);
            }
        }
        if (distanceFromWall > 0) {
            distanceFromWall--;
        }
    }

    public int getDistanceFromWall() {
        return distanceFromWall;
    }

    public int attack() {
        return this.attack;
    }

    // if got weapons on wall, call this
    public void getCloserToWeapon(Weapon[] weapons) {
        // find the closer weapon
        int position = getInfrontWallIndex();
        // to left
        int leftCloseWeaponIndex = -100;
        for (int i = position - 1; i >= 0; i--) {
            if (weapons[i].getLevel() > 0) {
                leftCloseWeaponIndex = i;
            }
        }
        int rightCloseWeaponIndex = 100;
        for (int i = position + 1; i < 10; i++) {
            if (weapons[i].getLevel() > 0) {
                rightCloseWeaponIndex = i;
            }
        }
//        System.out.println("leftCloseWeaponIndex :" + leftCloseWeaponIndex);
//        System.out.println("rightCloseWeaponIndex :" + rightCloseWeaponIndex);
        if ((position - leftCloseWeaponIndex) < (rightCloseWeaponIndex - position)) {
//            System.out.println("move left");
            if (getInfrontWallIndex() != 0) {
                setInfrontWallIndex(getInfrontWallIndex() - 1);
            }
        } else if ((position - leftCloseWeaponIndex) > (rightCloseWeaponIndex - position)) {
//            System.out.println("move right");
            if (getInfrontWallIndex() != 9) {
                setInfrontWallIndex(getInfrontWallIndex() + 1);
            }
        } else {
//            System.out.println("move random direction");
            getCloserToWall();
        }
    }
}
