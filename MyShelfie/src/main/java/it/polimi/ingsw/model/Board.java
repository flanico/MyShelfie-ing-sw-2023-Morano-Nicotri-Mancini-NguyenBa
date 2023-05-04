package it.polimi.ingsw.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * class that define the living room
 * @author Flavia Nicotri
 */
public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = 5741045381452833395L;
    private final Tile[][] matrix;
    private static final int ROW = 9;
    private static final int COL = 9;

    /**
     * constructor of board
     * @author Flavia Nicotri
     */
    public Board(int num) {
        this.matrix = new Tile[ROW][COL];
        //initialization board
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.matrix[i][j] = new Tile(TileType.NULL, i, j);
            }
        }
        //block unavailable tiles
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4-j; i++) {
                this.matrix[i][j].setBlocked(true);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 9-1; j > 9+i-5; j--) {
                this.matrix[i][j].setBlocked(true);
            }
        }
        for (int j = 6; j < 9; j++) {
            for (int i = 9-1; i >9-j+3; i--) {
                this.matrix[i][j].setBlocked(true);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < i-4; j++) {
                this.matrix[i][j].setBlocked(true);
            }
        }
        //block tiles unavailable for 2/3 players
        if (num < 4) {
            this.matrix[0][4].setBlocked(true);
            this.matrix[1][5].setBlocked(true);
            this.matrix[3][1].setBlocked(true);
            this.matrix[4][0].setBlocked(true);
            this.matrix[5][7].setBlocked(true);
            this.matrix[4][8].setBlocked(true);
            this.matrix[7][3].setBlocked(true);
            this.matrix[8][4].setBlocked(true);
            //block tiles unavailable for 2 players
            if (num == 2) {
                this.matrix[0][3].setBlocked(true);
                this.matrix[2][6].setBlocked(true);
                this.matrix[3][8].setBlocked(true);
                this.matrix[2][2].setBlocked(true);
                this.matrix[5][0].setBlocked(true);
                this.matrix[6][2].setBlocked(true);
                this.matrix[6][6].setBlocked(true);
                this.matrix[8][5].setBlocked(true);
            }
        }
    }


    public Tile[][] getMatrix() {
        return matrix;
    }


    public int getROW() {
        return ROW;
    }


    public int getCOL() {
        return COL;
    }


    public void fillBoard(Stack<Tile> bag) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!this.matrix[i][j].isBlocked() && this.matrix[i][j].getType() == TileType.NULL) {
                    this.matrix[i][j].setType(bag.pop().getType());
                }
            }
        }
    }

    /**
     * looks if board is refillable or not
     * @author Chiara Nguyen Ba
     */
    public boolean isRefillable() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!this.matrix[i][j].isBlocked() && this.matrix[i][j].getType() != TileType.NULL && !isTileIsolated(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * looks if tiles are isolated in the board
     * @author Chiara Nguyen Ba
     */
    private boolean isTileIsolated(int row, int column) {
        if(row > 0 && this.matrix[row - 1][column].getType() != TileType.NULL) {
            return false;
        }
        if(row < ROW && this.matrix[row + 1][column].getType() != TileType.NULL) {
            return false;
        }
        if(column > 0 && this.matrix[row][column - 1].getType() != TileType.NULL) {
            return false;
        }
        if(column < COL && this.matrix[row][column + 1].getType() != TileType.NULL) {
            return false;
        }
        return true;
    }

    /**
     * check if the tiles are removable from the board
     * @param tiles item selected by the RoundPlayer
     * @return true if the selection is allowed
     * @author Chiara Nguyen Ba
     */
    public boolean isRemovable(List<Tile> tiles) {

        //check if the tiles are not NULL and not blocked
        for (Tile tile : tiles) {
            if (tile.isBlocked() || tile.getType().equals(TileType.NULL)) {
//                System.out.println("Tiles are NULL or blocked");
                return false;
            }
        }

        boolean samerow = true;
        boolean samecolumn = true;
        int row = tiles.get(0).getX();
        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i).getX() != row) {
//                System.out.println("Tiles are not in the same row");
                samerow = false;
                break;
            }
        }

        int column = tiles.get(0).getY();
        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i).getY() != column) {
//                System.out.println("Tiles are not in the same column");
                samecolumn = false;
                break;
            }
        }

        if (!samecolumn && !samerow) {
//            System.out.println("Tiles are not aligned");
            return false;
        }

        //Reorder the tiles in the list based on x or y for easy checking
        if (samerow) {
            tiles.sort(Comparator.comparingInt(Tile::getY));
//            System.out.println(tiles.toString());
        }
        if (samecolumn) {
            tiles.sort(Comparator.comparingInt(Tile::getX));
//            System.out.println(tiles.toString());
        }

        //check if the tiles have a free side
        for (Tile tile : tiles) {
            int rowtile = tile.getX();
            int columntile = tile.getY();
            if ((rowtile >= 1 && matrix[rowtile - 1][columntile].getType() != TileType.NULL) &&
                    (rowtile <= 7 && matrix[rowtile + 1][columntile].getType() != TileType.NULL) &&
                    (columntile >= 1 && matrix[rowtile][columntile - 1].getType() != TileType.NULL) &&
                    (columntile <= 7 && matrix[rowtile][columntile + 1].getType() != TileType.NULL)) {
//                System.out.println("Tiles haven't a free side");
                return false;
            }
        }

        //check if the tiles are near
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

    /**
     * tiles removable are removed and these tiles position on the board are set NULL
     * @param tiles item to remove from the board
     * @author Chiara Nguyen Ba
     */
    public void removeTiles(List<Tile> tiles) {
        if (isRemovable(tiles)) {
            for (Tile t : tiles) {
//                System.out.println("Tile " + tiles.indexOf(t) + " removed of type " + tiles.get(tiles.indexOf(t)).getType() + " in position x: " + tiles.get(tiles.indexOf(t)).getX() + " y: " + tiles.get(tiles.indexOf(t)).getY());
                this.getMatrix()[t.getX()][t.getY()].setType(TileType.NULL);
            }
        }
    }

    public int maxTilesBoard(){
        int max = 1;
        List<Tile> tiles = new ArrayList<>();

        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++)
            {

                if (i< ROW-2)
                {
                    tiles.add(this.getMatrix()[i][j]);
                    tiles.add(this.getMatrix()[i+1][j]);
                    tiles.add(this.getMatrix()[i+2][j]);
                    if(isRemovable(tiles)){
                        return 3;
                    }
                }
                if (i < ROW-1)
                {
                    tiles.removeAll(tiles);
                    //System.out.println("\ncontrollo 2 row : i= "+ i + "j=" +j);
                    tiles.add(this.getMatrix()[i][j]);
                    tiles.add(this.getMatrix()[i+1][j]);
                    if(isRemovable(tiles)){
                        if (max == 1){
                            max= 2;
                        }
                        //System.out.println("max:" + max);
                    }
                }


                if (j< COL-2) {
                    tiles.add(this.getMatrix()[i][j]);
                    tiles.add(this.getMatrix()[i][j + 1]);
                    tiles.add(this.getMatrix()[i][j + 2]);
                    if (isRemovable(tiles)) {
                        return 3;
                    }
                }
                if (j < COL-1)
                {
                    tiles.removeAll(tiles);
                    //System.out.println("\ncontrollo 2 col : i= "+ i + "j=" +j);
                    tiles.add(this.getMatrix()[i][j]);
                    tiles.add(this.getMatrix()[i][j+1]);
                    if(isRemovable(tiles)){
                        if (max == 1){
                            max= 2;
                        }
                        //System.out.println("max:" + max);
                    }
                }


            }
        }
        return max;
    }
}