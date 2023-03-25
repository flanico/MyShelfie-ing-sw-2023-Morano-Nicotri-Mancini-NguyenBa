package it.polimi.ingsw;

public class Bookshelf {
    private Tile[][] bookshelf;

    public Bookshelf () {
        this.bookshelf = new Tile[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                this.bookshelf[i][j] = new Tile(TileType.NULL);
            }
        }
    }

    public Tile[][] getBookshelf() {
        return bookshelf;
    }

    //addTile

    //isFull
}
