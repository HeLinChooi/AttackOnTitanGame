package Main;

//Executioner from Hell
public class Weapon {

    // level 1 = attack value 2
    // level 2 = attack value 5
    // level 3 = attack value 10
    private int level = 0;
    private final int maxLevel = 3;
    private final int[] attack = {0,2, 5, 10};

    public boolean upLevel() {
        int currentLevel = getLevel();
        int tempLevel = currentLevel;
        if (tempLevel < maxLevel) {
            tempLevel++;
            setLevel(tempLevel);
            return true;
        } else {
            System.out.println("This weapon already reach level 3.");
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int attack() {
        return attack[level];
    }
}
