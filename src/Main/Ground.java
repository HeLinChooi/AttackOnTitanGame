package Main;

import Titans.ArmouredTitan;
import Titans.ColossusTitan;

public class Ground {

    private int sideLength;
    private String[][] matrix;

    public Ground(int sideLength) {
        this.sideLength = sideLength;
        this.matrix = new String[sideLength][sideLength];
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
        // copy into 2D array
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = "   ";
            }
        }
        if (row == 9 && col == col2 && a.isShowUp() && c.isShowUp()) {
            matrix[row][col] = "AC ";
        } else {
            if (a.isShowUp()) {
                matrix[row][col] = "AA ";
            }
            if (c.isShowUp()) {
                matrix[9][col2] = "CC ";
            }
        }
        System.out.println("On The Ground");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }
}
