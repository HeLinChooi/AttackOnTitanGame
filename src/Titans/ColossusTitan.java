package Titans;

import java.util.Random;

public class ColossusTitan extends Titan{

    private int attack;
    private static final int ATTACK_HOURS = 10;
    private int hoursRemained = 0;

    public ColossusTitan() {
        this.hp = 50;
        this.attack = 10;
    }

    public ColossusTitan(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public int attack() {
        return attack;
    }

    // override
    public void setShowUp(boolean showUp) {
        Random r = new Random();
        // if toggle from false to true
        if (showUp) {
            setInfrontWallIndex(r.nextInt(10));
            setHoursRemained(ATTACK_HOURS);
        }
        this.showUp = showUp;
    }

    public int getHoursRemained() {
        return hoursRemained;
    }

    public void setHoursRemained(int hoursRemained) {
        this.hoursRemained = hoursRemained;
    }
}
