package it.polimi.ingsw.model;

import it.polimi.ingsw.view.cli.ColorCli;

import java.io.Serial;
import java.io.Serializable;

/**
 * class that define a tile
 * @author Alessandro Mancini
 */
public class Tile implements Serializable {
    @Serial
    private static final long serialVersionUID = 2994214506775431820L;
    private TileType type;
    private int x;
    private int y;
    private boolean blocked;
    private boolean counted;

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
        this.counted = false;
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
        this.counted = false;
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
     * @param type of the tile
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
     * @param x coordinate of the tile
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
     * @param y coordinate of the tile
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
     * @param blocked of the tile
     * @author Alessandro Mancini
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * getter of counted
     * @author Flavia Nicotri
     */
    public boolean isCounted() {
        return counted;
    }

    /**
     * setter of counted
     * @param counted of the tile
     * @author Flavia Nicotri
     */
    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        switch (type) {
            case CAT -> builder.append(ColorCli.BACK_GREEN + " C " + ColorCli.RESET);
            case BOOK -> builder.append(ColorCli.BACK_WHITE + " B " + ColorCli.RESET);
            case PLANT -> builder.append(ColorCli.BACK_PURPLE + " P " + ColorCli.RESET);
            case TROPHY -> builder.append(ColorCli.BACK_CYAN + " T " + ColorCli.RESET);
            case GAME -> builder.append(ColorCli.BACK_YELLOW + " G " + ColorCli.RESET);
            case FRAME -> builder.append(ColorCli.BACK_BLUE + " F " + ColorCli.RESET);
            case NULL -> builder.append("   ");
        }
        return builder.toString();
    }
}