package it.polimi.ingsw;
import java.util.*;

/**
 * class that define the bookshelf as a matrix of Tile
 * @author Alessandro Mancini
 */
public class Bookshelf {
    private final Tile[][] matrix;
    private final int ROW = 6;
    private final int COL = 5;

    /**
     * constructor of bookshelf
     * @author Alessandro Mancini
     */
    public Bookshelf() {
        this.matrix = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.matrix[i][j] = new Tile(TileType.NULL, i, j);
            }
        }
    }

    /**
     * getter of matrix
     * @author Alessandro Mancini
     */
    public Tile[][] getMatrix() {
        return matrix;
    }

    /**
     * insert the selected tile into the bookshelf
     * @param tiles list of Tile selected by the player
     * @param column chosen by the player
     * @author Alessandro Mancini
     */
    public void insertTile(ArrayList<Tile> tiles, int column)  {
        int x = ROW;
        while (this.matrix[x][column].getType() == TileType.NULL && x > -1) {
            x--;
        }
        for (int i = 0; i < tiles.size(); i++) {
            this.matrix[x+1-i][column].setType(tiles.get(i).getType());

        }
    }

    /**
     * check the score relative to adjacent cells
     * @return the total score gained by the player from adjacent cells
     * @author Flavia Nicotri
     */
    public int adjacentCells() {
        int counter, adjacentscore = 0;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!this.matrix[i][j].isCounted()) {
                    this.matrix[i][j].setCounted(true);
                    counter = sameGroup(i, j, 1);
                    if (counter == 3) {
                        adjacentscore = adjacentscore + 2;
                    } else if (counter == 4) {
                        adjacentscore = adjacentscore + 3;
                    } else if (counter == 5) {
                        adjacentscore = adjacentscore + 5;
                    } else if (counter > 5) {
                        adjacentscore = adjacentscore + 8;
                    }
                }

            }
        }
        return adjacentscore;
    }

    /**
     * recursive function that find group of same adjacent cells
     * @param i index of row
     * @param j index of column
     * @param counter variable to count the number of cells in the same group
     * @return the number of cells in the same group
     * @author Flavia Nicotri
     */
    private int sameGroup(int i, int j, int counter) {
        if (i == 5 && j == 4 || (!(this.matrix[i][j+1].getType().equals(this.matrix[i][j].getType())))
                && !(this.matrix[i+1][j].getType().equals(this.matrix[i][j].getType())))  {
            return counter;
        }
        if (i != 5 && j != 4 && this.matrix[i][j+1].getType().equals(this.matrix[i][j].getType()) && this.matrix[i+1][j].getType().equals(this.matrix[i][j].getType())) {
            this.matrix[i][j+1].setCounted(true);
            this.matrix[i+1][j].setCounted(true);
            sameGroup(i+1, j, counter);
            sameGroup(i, j+1, counter);
            counter= counter+2;
        } else if (j != 4 && this.matrix[i][j+1].getType().equals(this.matrix[i][j].getType())) {
            this.matrix[i][j+1].setCounted(true);
            sameGroup(i, j+1, counter);
            counter++;
        } else if (i != 5 && this.matrix[i+1][j].getType().equals(this.matrix[i][j].getType())) {
            this.matrix[i+1][j].setCounted(true);
            sameGroup(i+1, j, counter);
            counter++;
        }
        return counter;
    }

    /**
     * check if the bookshelf is full
     * @return true if there isn't any NULL cell
     * @author Flavia Nicotri
     */
    public boolean isFull() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (this.matrix[i][j].getType() == TileType.NULL)
                    return false;
            }
        }
        return true;
    }

    /**
     * check if the column of the bookshelf is full
     * @param column where to insert the tile
     * @author Alessandro mancini
     */
    public boolean isColFull(int column) {
        return (this.getMatrix()[6][column].getType() != TileType.NULL);
    }
}
