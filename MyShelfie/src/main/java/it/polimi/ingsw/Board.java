package it.polimi.ingsw;

public class Board {
    private final Tile[][] board;
    //Private final int numPlayers;
    //Private Game game = new Game();

    public Board(int num) {
        this.board = new Tile[9][9];
        //Initialization board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new Tile(TileType.NULL, i, j);
            }
        }
        //Block unavailable tiles
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4-j; i++) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 9-1; j > 9+i-5; j--) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int j = 6; j < 9; j++) {
            for (int i = 9-1; i >9-j+3; i--) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < i-4; j++) {
                this.board[i][j].setBlocked(true);
            }
        }
        //Block tiles unavailable for 2/3 players
        if (num < 4) {
            this.board[0][4].setBlocked(true);
            this.board[1][5].setBlocked(true);
            this.board[3][1].setBlocked(true);
            this.board[4][0].setBlocked(true);
            this.board[5][7].setBlocked(true);
            this.board[4][8].setBlocked(true);
            this.board[7][3].setBlocked(true);
            this.board[8][4].setBlocked(true);
            //Block tiles unavailable for 2 players
            if (num == 2) {
                this.board[0][3].setBlocked(true);
                this.board[2][6].setBlocked(true);
                this.board[3][8].setBlocked(true);
                this.board[2][2].setBlocked(true);
                this.board[5][0].setBlocked(true);
                this.board[6][2].setBlocked(true);
                this.board[6][6].setBlocked(true);
                this.board[8][5].setBlocked(true);
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    //selectTile

    //isRemovable
}