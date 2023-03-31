package it.polimi.ingsw;
import java.util.*;

/**
 * class that define the living room
 * @author Flavia Nicotri
 */
public class Board {
    private final Tile[][] matrix;
    private final int ROW = 9;
    private final int COL = 9;

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

    /**
     * getter of matrix
     * @author Alessandro Mancini
     */
    public Tile[][] getMatrix() {
        return matrix;
    }

    /**
     * getter of ROW
     * @author Alessandro Mancini
     */
    public int getROW() {
        return ROW;
    }

    /**
     * getter of COL
     * @author Alessandro Mancini
     */
    public int getCOL() {
        return COL;
    }

    /**
     * takes tiles from the board
     * @param number of tiles to remove
     * @param coordinates of the tiles
     * @author Alessandro Mancini
     */
    public ArrayList<Tile> selectTile(int number, Integer[][] coordinates) {
        ArrayList<Tile> selected = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        do {
            for (int i = 0; i < number; i ++) {
                int x = coordinates[i][0];
                int y = coordinates[i][1];
                selected.add(this.getMatrix()[x][y]);
            }
        } while (!this.isRemovable(selected));

        this.removeTiles(selected);
        return selected;
    }

    /**
     * check if the tiles are removable from the board
     * @param tiles item selected by the RoundPlayer
     * @return true if the selection is allowed
     * @author Chiara Nguyen Ba
     */
    public boolean isRemovable(ArrayList<Tile> tiles) {
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

        //check if the tiles have a free side
        for (Tile tile : tiles) {
            int rowtile = tile.getX();
            int columntile = tile.getY();
            if ((rowtile >= 1 && matrix[rowtile - 1][columntile].getType() != TileType.NULL) &&
                    (rowtile <= 7 && matrix[rowtile + 1][columntile].getType() != TileType.NULL) &&
                    (columntile >= 1 && matrix[rowtile][columntile - 1].getType() != TileType.NULL) &&
                    (columntile <= 7 && matrix[rowtile][columntile + 1].getType() != TileType.NULL)) {
                //System.out.println("Tiles haven't a free side");
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
    public void removeTiles(ArrayList<Tile> tiles) {
        if (isRemovable(tiles)) {
            for (Tile t : tiles) {
                //System.out.println("Tile " + tiles.indexOf(tile) + " removed of type " + tiles.get(tiles.indexOf(tile)).getType() + " in position x: " + tiles.get(tiles.indexOf(tile)).getX() + " y: " + tiles.get(tiles.indexOf(tile)).getY());
                this.getMatrix()[t.getX()][t.getY()].setType(TileType.NULL);
            }
        }
    }

    /**
     * print the matrix
     * @author Alessandro Mancini
     */
    public void printMatrix() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!this.matrix[i][j].isBlocked())
                    System.out.println(this.matrix[i][j].getType().toString() + " ");
            }
            System.out.println();
        }
    }
}