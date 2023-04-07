package it.polimi.ingsw.model;
import java.util.*;

/**
 * class that define the bookshelf as a matrix of Tile
 * @author Alessandro Mancini
 */
public class Bookshelf {
    private final Tile[][] matrix;
    private static final int ROW = 6;
    private static final int COL = 5;

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
        int x = ROW-1;
        if(isColFull(column)) return;
        else while (this.matrix[x][column].getType() != TileType.NULL && x > 0) {
            x--;
        }
        for (Tile tile : tiles) {
            this.matrix[x][column].setType(tile.getType());
            x--;
        }
    }

    /**
     * check the score relative to adjacent cells
     * @return the total score gained by the player from adjacent cells
     * @author Flavia Nicotri
     */
    public int adjacentCells() {
        int counter, adjacentscore = 0;

        //inizializzo counted = 0
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.matrix[i][j].setCounted(false);
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!this.matrix[i][j].isCounted()) {
                    //System.out.print( i + "" + j );
                    this.matrix[i][j].setCounted(true);
                    counter = sameGroup(i, j, 1);
                    //System.out.print(counter+" ");
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
    public int sameGroup(int i, int j, int counter) {
        if ((i == 5 && j == 4 )|| this.matrix[i][j].getType().equals(TileType.NULL))  {
            //System.out.println("Escape condition 1 " + counter);
            return counter;
        }
        if (j != 4){
            //System.out.println("Caso 1");
            if(!this.matrix[i][j+1].isCounted() && this.matrix[i][j+1].getType().equals(this.matrix[i][j].getType())) {
                //System.out.println("same group " + i + j);
                this.matrix[i][j+1].setCounted(true);
                counter++;
                counter= sameGroup(i, j+1, counter);
            }
        }
        if(i!=5)
        {
            if(!this.matrix[i+1][j].isCounted() && this.matrix[i+1][j].getType().equals(this.matrix[i][j].getType())) {
                //System.out.println("Caso 2");
                {
                    //System.out.println("same group "+ i + j);
                    this.matrix[i+1][j].setCounted(true);
                    counter++;
                    counter= sameGroup(i+1, j, counter);
                }
            }
        }
        if (i!=0)
        {
            if(!this.matrix[i-1][j].isCounted() && this.matrix[i-1][j].getType().equals(this.matrix[i][j].getType())) {
                //System.out.println("Caso 3");
                {
                    //System.out.println("same group "+ i + j);
                    this.matrix[i-1][j].setCounted(true);
                    counter++;
                    counter= sameGroup(i-1, j, counter);
                }
            }
        }
        if (j!=0)
        {
            if(!this.matrix[i][j-1].isCounted() && this.matrix[i][j-1].getType().equals(this.matrix[i][j].getType())) {
                //System.out.println("Caso 4");
                {
                    //System.out.println("same group "+ i + j);
                    this.matrix[i][j-1].setCounted(true);
                    counter++;
                    counter= sameGroup(i, j-1, counter);
                }
            }
        }


        //System.out.println("Escape condition 2 " + counter);
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
                if (this.getMatrix()[i][j].getType()==TileType.NULL)
                    return false;
            }
        }
        return true;
    }

    /**
     * check if the column of the bookshelf is full
     * @param column where to insert the tile
     * @author Alessandro Mancini
     */
    public boolean isColFull(int column) {
        return (this.getMatrix()[0][column].getType() != TileType.NULL);
    }
}