package Titans;

public class ColossusTitan {

    private int hp;
    private int attack;
    private int attackInterval;
    private boolean showUp = false;
    private int infrontWallIndex = 0;

    public ColossusTitan() {
        this.hp = 4;
        this.attack = 10;
        this.attackInterval = 5;
    }

    public ColossusTitan(int hp, int attack, int attackInterval) {
        this.hp = hp;
        this.attack = attack;
        this.attackInterval = attackInterval;
    }

    public int getHp() {
        return hp;
    }

    public int attack() {
        return attack;
    }

    public int getAttackInterval() {
        return attackInterval;
    }

    public boolean isShowUp() {
        return showUp;
    }

    public void setShowUp(boolean showUp) {
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
            System.out.println("The Colossus Titan take " + amount + " points damage");
            this.hp -= amount;
        }
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}
