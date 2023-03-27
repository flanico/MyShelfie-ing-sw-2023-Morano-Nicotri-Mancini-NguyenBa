package it.polimi.ingsw;
import java.util.*;

public class Bookshelf {
    private final Tile[][] bookshelf;

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
