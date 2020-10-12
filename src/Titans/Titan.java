package Titans;

import java.util.Random;

public class Titan {

    protected int hp;
    protected boolean showUp = false;
    protected int infrontWallIndex = 0;

    public Titan() {
        this.hp = 50;
    }

    public Titan(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isShowUp() {
        return showUp;
    }

    public void setShowUp(boolean showUp) {
        Random r = new Random();
        // if toggle from false to true
        if (showUp) {
            setInfrontWallIndex(r.nextInt(10));
        }
        this.showUp = showUp;
    }

    public int getInfrontWallIndex() {
        return infrontWallIndex;
    }

    public void setInfrontWallIndex(int infrontWallIndex) {
        this.infrontWallIndex = infrontWallIndex;
    }

    public void reduceHP(int amount) {
        // only he show up then can take damage
        if (isShowUp()) {
            this.hp -= amount;
            System.out.println("The titan takes " + amount + " damage");
            System.out.println("The titan remains HP: " + getHp());
        }
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}
