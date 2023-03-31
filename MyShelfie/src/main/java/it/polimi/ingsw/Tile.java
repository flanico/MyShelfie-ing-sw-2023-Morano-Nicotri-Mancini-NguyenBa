package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a tile
 * @author Alessandro Mancini
 */
public class Tile {
    private TileType type;
    private int x;
    private int y;
    private boolean blocked;
    private boolean counted;    //for adjacentCells method in Bookshelf

    /**
     * constructor of Tile
     * @param type of the tile
     * @author Alessandro Mancini
     */
    public Tile(TileType type) {
        this.type = type;
        this.x = -1;
        this.y = -1;
        this.blocked = false;
    }

    /**
     * constructor of Tile
     * @param type of the tile
     * @param x coordinate of the tile
     * @param y coordinate of the tile
     * @author Alessandro Mancini
     */
    public Tile(TileType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.blocked = false;
    }

    /**
     * getter of type
     * @author Alessandro Mancini
     */
    public TileType getType() {
        return type;
    }

    /**
     * setter of type
     * @param type
     * @author Alessandro Mancini
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * getter of x
     * @author Alessandro Mancini
     */
    public int getX() {
        return x;
    }

    /**
     * setter of x
     * @param x
     * @author Alessandro Mancini
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter of y
     * @author Alessandro Mancini
     */
    public int getY() {
        return y;
    }

    /**
     * setter of y
     * @param y
     * @author Alessandro Mancini
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getter of blocked
     * @author Alessandro Mancini
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * setter of blocked
     * @param blocked
     * @author Alessandro Mancini
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * getter of counted
     * @author Alessandro Mancini
     */
    public boolean isCounted() {
        return counted;
    }

    /**
     * setter of counted
     * @param counted
     * @author Alessandro Mancini
     */
    public void setCounted(boolean counted) {
        this.counted = counted;
    }
}
