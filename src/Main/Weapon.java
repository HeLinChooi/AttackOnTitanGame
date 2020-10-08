package Main;

//Executioner from Hell
public class Weapon {

    // level 1 = attack value 2
    // level 2 = attack value 5
    // level 3 = attack value 10
    private int level = 0;
    private final int maxLevel = 3;
    private final int[] attack = {0 ,2, 5, 10};
    private final int[] moneyRequiredForEachLevel = {0 ,2, 5, 10};

    // return the expenses
    public int upLevel(int moneyAmount) {
        if(level < 3 && moneyAmount < moneyRequiredForEachLevel[level+1]){
            System.out.println("Not enough money!");
            return 0;
        }
        int currentLevel = getLevel();
        int tempLevel = currentLevel;
        if (tempLevel < maxLevel) {
            tempLevel++;
            setLevel(tempLevel);
            return moneyRequiredForEachLevel[tempLevel];
        } else {
            System.out.println("This weapon already reach level 3.");
            return 0;
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
