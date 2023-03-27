package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final Tile[][] board;

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
            for (int i = 0; i < 4 - j; i++) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 9 - 1; j > 9 + i - 5; j--) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int j = 6; j < 9; j++) {
            for (int i = 9 - 1; i > 9 - j + 3; i--) {
                this.board[i][j].setBlocked(true);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < i - 4; j++) {
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

    //Check if the tiles are removable from the board
    public boolean isRemovable(ArrayList<Tile> tiles) {
        //Check if the tiles are in the same row/column
        boolean samerow = true;
        boolean samecolumn = true;
        int row = tiles.get(0).getX();
        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i).getX() != row) {
                //System.out.println("Tiles are not in the same row");
                samerow = false;
                break;
            }
        }

        int column = tiles.get(0).getY();
        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i).getY() != column) {
                //System.out.println("Tiles are not in the same column");
                samecolumn = false;
                break;
            }
        }

        if (!samecolumn && !samerow) {
            //System.out.println("Tiles are not aligned");
            return false;
        }

        //Check if the tiles have a free side
        for (Tile tile : tiles) {
            int rowtile = tile.getX();
            int columntile = tile.getY();
            if ((rowtile >= 1 && board[rowtile - 1][columntile].getType() != TileType.NULL) &&
                    (rowtile <= 7 && board[rowtile + 1][columntile].getType() != TileType.NULL) &&
                    (columntile >= 1 && board[rowtile][columntile - 1].getType() != TileType.NULL) &&
                    (columntile <= 7 && board[rowtile][columntile + 1].getType() != TileType.NULL)) {
                //System.out.println("Tiles haven't a free side");
                return false;
            }
        }

        //Check if the tiles are near
        if (tiles.size() >= 2) {
            if ((Math.abs(tiles.get(0).getY() - tiles.get(1).getY()) != 1)
                    && (Math.abs(tiles.get(0).getX() - tiles.get(1).getX()) != 1)) {
                //System.out.println("The two tiles are not near");
                return false;
            }
            if (tiles.size() == 3) {
                if ((Math.abs(tiles.get(1).getY() - tiles.get(2).getY()) != 1)
                        && (Math.abs(tiles.get(1).getX() - tiles.get(2).getX()) != 1)) {
                    //System.out.println("The three tiles are not near");
                    return false;
                }
            }
        }
        return true;
    }

    //Tiles removable are removed and these tiles' position on the board are set NULL
    public void removeTiles(ArrayList<Tile> tiles) {
        if (isRemovable(tiles)) {
            for (Tile tile : tiles) {
//                System.out.println("Tile " + tiles.indexOf(tile) + " removed of type " + tiles.get(tiles.indexOf(tile)).getType()
//                        + " in position x: " + tiles.get(tiles.indexOf(tile)).getX() + " y: " + tiles.get(tiles.indexOf(tile)).getY());
                this.getBoard()[tile.getX()][tile.getY()].setType(TileType.NULL);
            }
        }
    }
}