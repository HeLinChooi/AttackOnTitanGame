package Main;

public class Wall {

    private int wallLength;
    private int[] wall;
    private int[] weapon;

    public Wall(int wallLength) {
        this.wallLength = wallLength;
        this.wall = new int[wallLength];
        buildUpWall();
    }

    public int getWallLength() {
        return wallLength;
    }

    private void buildUpWall() {
        for (int i = 0; i < wall.length; i++) {
            wall[i] = 50;
        }
    }

    public void printWallWithHP(Weapon[] weapons) {
        System.out.println("\nThe Wall -- -- -- -- -- -- --");
        for (int i = 0; i < wall.length; i++) {
            System.out.print(wall[i] + " ");
        }
        System.out.println("    HP");
        System.out.println("-- -- -- -- -- -- -- -- -- --");
        int[] starRemain = new int[weapons.length];
        // copy array
        for (int i = 0; i < weapons.length; i++) {
            starRemain[i] = weapons[i].getLevel();
        }
        // print the star
        boolean isRemainStarNotPrinted = true;
        while(isRemainStarNotPrinted){
            isRemainStarNotPrinted = false;
            for (int i = 0; i < starRemain.length; i++) {
                if(starRemain[i]>0){
                    isRemainStarNotPrinted = true;
                    break;
                }
            }
            if(!isRemainStarNotPrinted){
                break;
            }
            for (int i = 0; i < starRemain.length; i++) {
                if(starRemain[i]>0) System.out.print("** ");
                else System.out.print("   ");
                starRemain[i]--;
            }
            System.out.println("");
        }
//        for (int i = 0; i < wall.length; i++) {
//            System.out.print(weapons[i].getLevel() + "  ");
//        }
//        System.out.println("Weapon Level----------------️⤴️");
        System.out.println("");
    }

    public void reduceHPForAll() {
        for (int i = 0; i < wall.length; i++) {
            wall[i]--;
        }
    }

    public int addHPForAll(int money, int amount) {
        int expenses = 0;
        for (int i = 0; i < wall.length; i++) {
            expenses += amount;
        }
        if (money < expenses) {
            System.out.println("Your money is not enough");
            return 0;
        }
        for (int i = 0; i < wall.length; i++) {
            wall[i] += amount;
        }
        return expenses;
    }

    public void reduceHPOn1Unit(int attack, int index) {
        wall[index] -= attack;
    }

    public int addHPOn1Unit(int money, int amount, int index) {
        if (money < amount) {
            System.out.println("Your money is not enough");
            return 0;
        } else {
            wall[index] += amount;
            return amount;
        }
    }

    public boolean isWallHasFallen() {
        for (int i = 0; i < wall.length; i++) {
            if (wall[i] <= 0) {
                return true;
            }
        }
        return false;
    }
}
