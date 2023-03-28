package it.polimi.ingsw;
import java.util.*;

public class Bookshelf {
    private final Tile[][] bookshelf;
    private int adjacentscore;

    public Bookshelf() {
        this.bookshelf = new Tile[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                this.bookshelf[i][j] = new Tile(TileType.NULL, i, j);
            }
        }
    }

    public Tile[][] getBookshelf() {
        return bookshelf;
    }

    public void insertTile(ArrayList<Tile> t, int x)  {
        int y = 5;
        while (this.bookshelf[x][y].getType() == TileType.NULL && y > -1) {
            y--;
        }
        for (int k = 0; k < t.size(); k++) {
            this.bookshelf[x][y+1-k].setType(t.get(k).getType());

        }
    }

    //Check the score relative to adjacent cells
    public int adjacentCells() {
        int counter;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (!this.bookshelf[i][j].isCounted()){
                    this.bookshelf[i][j].setCounted(true);
                    counter = sameGroup(i, j, 1);
                    if (counter == 3) {
                        this.adjacentscore = this.adjacentscore + 2;
                    } else if (counter == 4) {
                        this.adjacentscore = this.adjacentscore + 3;
                    } else if (counter == 5) {
                        this.adjacentscore = this.adjacentscore + 5;
                    } else if (counter > 5) {
                        this.adjacentscore = this.adjacentscore + 8;
                    }
                }

            }
        }
        return adjacentscore;
    }

    private int sameGroup(int i, int j, int counter) {
        if (i == 4 && j == 5 || (!(this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType())))
                && !(this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType())))  {
            return counter;
        }
        if (this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType()) && this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType())) {
            this.bookshelf[i][j+1].setCounted(true);
            this.bookshelf[i+1][j].setCounted(true);
            sameGroup(i+1, j, counter);
            sameGroup(i, j+1, counter);
            counter= counter+2;
        } else if (j != 5 && this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType())) {
            this.bookshelf[i][j+1].setCounted(true);
            sameGroup(i, j+1, counter);
            counter++;
        } else if (i != 4 && this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType())) {
            this.bookshelf[i+1][j].setCounted(true);
            sameGroup(i+1, j, counter);
            counter++;
        }
        return counter;
    }


    public boolean isColFull(int x) {
        return (this.bookshelf[x][5].getType() != TileType.NULL);
    }

    public boolean isFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (this.bookshelf[i][j].getType() == TileType.NULL)
                    return false;
            }
        }
        return true;
    }
}
