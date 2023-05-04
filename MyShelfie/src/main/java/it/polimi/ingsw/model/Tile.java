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


    public Tile(TileType type) {
        this.type = type;
        this.x = -1;
        this.y = -1;
        this.blocked = false;
        this.counted = false;
    }


    public Tile(TileType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.blocked = false;
        this.counted = false;
    }


    public TileType getType() {
        return type;
    }


    public void setType(TileType type) {
        this.type = type;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBlocked() {
        return blocked;
    }


    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


    public boolean isCounted() {
        return counted;
    }


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