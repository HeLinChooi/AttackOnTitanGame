package Main;

import Titans.ArmouredTitan;
import Titans.ColossusTitan;

public class Ground {

    private int sideLength;
    private char[][] matrix;

    public Ground(int sideLength) {
        this.sideLength = sideLength;
        this.matrix = new char[sideLength][sideLength];
    }

    public void printGround(ColossusTitan c, ArmouredTitan a) {
        int row = 0;
        int col = 0;
        if (a.isShowUp()) {
            row = 9 - a.getDistanceFromWall();
            col = a.getInfrontWallIndex();
        }
        int col2 = 0;
        if (c.isShowUp()) {
            col2 = c.getInfrontWallIndex();
        }
        System.out.println("On The Ground");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 9 && j == col && j == col2 && a.isShowUp() && c.isShowUp()) {
                    System.out.print("AC ");
                } else if (i == row && j == col && a.isShowUp()) {
                    System.out.print("AA ");
                } else if (i == 9 && j == col2 && c.isShowUp()) {
                    // c titan on show up on row 8
                    System.out.print("CC ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }
}
