package it.polimi.ingsw;

public class Bookshelf {
    private Cell[][] bookshelf;

    public Bookshelf () {
        this.bookshelf = new Cell[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.bookshelf[i][j] = new Cell();
                this.bookshelf[i][j].setFull(false);
                this.bookshelf[i][j].setBlocked(false);
            }
        }
    }

    public Cell[][] getBookshelf() {
        return bookshelf;
    }
}
