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
        int[] starRemain = new int[weapons.length];
        boolean gotWeapon = false;
        // copy array
        for (int i = 0; i < weapons.length; i++) {
            starRemain[i] = weapons[i].getLevel();
            if(starRemain[i]>0){
                gotWeapon = true;
            }
        }
        // print the star
        boolean isRemainStarNotPrinted = true;
        while (isRemainStarNotPrinted) {
            isRemainStarNotPrinted = false;
            // get the highest level
            int maxLevel = 0;
            for (int i = 0; i < starRemain.length; i++) {
                if (starRemain[i] > 0) {
                    isRemainStarNotPrinted = true;
                    if (starRemain[i] > maxLevel) {
                        maxLevel = starRemain[i];
                    }
                }
            }
            if (!isRemainStarNotPrinted) {
                break;
            }
            System.out.print("   ");
            for (int i = 0; i < starRemain.length; i++) {
                if (starRemain[i] == maxLevel) {
                    System.out.print("** ");
                    starRemain[i]--;
//                    maxLevel--;
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
//        if(gotWeapon){
//            System.out.println("got wapon");
//            System.out.println("");
//        }
        System.out.println("   -- -- -- -- -- -- -- -- -- --     The Wall");
        System.out.print("   ");
        for (int i = 0; i < wall.length; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println("    Index");
        System.out.print("   ");
        for (int i = 0; i < wall.length; i++) {
            System.out.print(wall[i] + " ");
        }
        System.out.println("    HP");
        System.out.println("   -- -- -- -- -- -- -- -- -- --");

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
