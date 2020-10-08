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

    private void buildUpWall() {
        for (int i = 0; i < wall.length; i++) {
            wall[i] = 50;
        }
    }

    public void printWallWithHP() {
        System.out.println("The Wall -- -- -- -- -- -- --");
        for (int i = 0; i < wall.length; i++) {
            System.out.print(wall[i] + " ");
        }
        System.out.println("");
        System.out.println("-- -- -- -- -- -- -- -- -- --");
        System.out.println("");
    }

    public void reduceHPForAll() {
        for (int i = 0; i < wall.length; i++) {
            wall[i]--;
        }
    }

    public void reduceHPOn1Unit(int attack, int index) {
        wall[index] -= attack;
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
