package Titans;

import java.util.Random;

public class ColossusTitan {

    private int hp;
    private int initialHp;
    private int attack;
    private static final int attackHours = 10;
    private int hoursRemained = 0;
    private boolean showUp = false;
    private int infrontWallIndex = 0;

    public ColossusTitan() {
        this.hp = 50;
        this.initialHp = 50;
        this.attack = 10;
    }

    public ColossusTitan(int hp, int attack, int attackInterval) {
        this.hp = hp;
        this.initialHp = hp;
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int attack() {
        return attack;
    }

    public boolean isShowUp() {
        return showUp;
    }

    public void setShowUp(boolean showUp) {
        Random r = new Random();
        // if toggle from false to true
        if (showUp) {
            setHp(initialHp);
            setInfrontWallIndex(r.nextInt(10));
            setHoursRemained(attackHours);
        }
        this.showUp = showUp;
    }

    public int getInfrontWallIndex() {
        return infrontWallIndex;
    }

    public void setInfrontWallIndex(int infrontWallIndex) {
        this.infrontWallIndex = infrontWallIndex;
    }

    public int getHoursRemained() {
        return hoursRemained;
    }

    public void setHoursRemained(int hoursRemained) {
        this.hoursRemained = hoursRemained;
    }

    public void reduceHP(int amount) {
        // only he show up then can take damage
        if (isShowUp()) {
            System.out.println("The Colossus Titan take " + amount + " points damage");
            this.hp -= amount;
            System.out.println("Remain HP: " + getHp());
        }
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}
