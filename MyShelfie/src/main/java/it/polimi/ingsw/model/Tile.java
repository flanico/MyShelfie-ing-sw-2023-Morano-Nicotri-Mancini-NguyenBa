package it.polimi.ingsw.model;

public class Tile {
    private final TileType type;

    private int x;

    private int y;

    private boolean blocked;
    private boolean counted;


    public Tile (TileType type) {
        this.type = type;
        this.x = -1;
        this.y = -1;
        this.blocked = false;
        this.counted = false;
    }

    public TileType getType() {
        return type;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
