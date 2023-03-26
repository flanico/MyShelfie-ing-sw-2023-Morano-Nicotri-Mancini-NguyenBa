package it.polimi.ingsw.model;

public class Bookshelf {
    private Tile[][] bookshelf;

    private int adjacentscore;
    private int counter;

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
    public boolean isFull (){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (this.bookshelf[i][j].getType().equals(TileType.NULL))
                {
                    return false;
                }
            }
        }
        return true;
    }

    //check the score relative to adjacent cells
    public int adjacentCells(Bookshelf bookshelf) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (!this.bookshelf[i][j].isCounted()){
                    this.bookshelf[i][j].setCounted(true);
                    counter= SameGroup(bookshelf, i, j, 1);

                    if(counter ==3){
                        adjacentscore=adjacentscore+2;
                    } else if (counter == 4) {
                        adjacentscore=adjacentscore+3;
                    } else if (counter==5) {
                        adjacentscore=adjacentscore+5;
                    } else if (counter>5) {
                        adjacentscore=adjacentscore+8;
                    }
                }

            }
        }
        return adjacentscore;
    }
    private int SameGroup (Bookshelf bookshelf, int i, int j, int counter){
        if(i==4 && j==5 || (! (this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType()))) && !(this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType())))
        {
            return counter;
        }
        if (this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType()) && this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType()))
        {
            this.bookshelf[i][j+1].setCounted(true);
            this.bookshelf[i+1][j].setCounted(true);
            SameGroup(bookshelf, i+1, j, counter);
            SameGroup(bookshelf, i, j+1, counter);
            counter= counter+2;
        } else if (j!= 5 && this.bookshelf[i][j+1].getType().equals(this.bookshelf[i][j].getType())) {
            this.bookshelf[i][j+1].setCounted(true);
            SameGroup(bookshelf, i, j+1, counter);
            counter++;
        } else if (i!=4 && this.bookshelf[i+1][j].getType().equals(this.bookshelf[i][j].getType())) {
            this.bookshelf[i+1][j].setCounted(true);
            SameGroup(bookshelf, i+1, j, counter);
            counter++;
        }
        return counter;
    }
}
