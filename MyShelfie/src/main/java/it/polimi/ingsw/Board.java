package it.polimi.ingsw;

public class Board {
    private Cell[][] board;

    public Board () {
        this.board = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new Cell();
                this.board[i][j].setFull(false);
                this.board[i][j].setBlocked(false);
            }
        }
    }
}