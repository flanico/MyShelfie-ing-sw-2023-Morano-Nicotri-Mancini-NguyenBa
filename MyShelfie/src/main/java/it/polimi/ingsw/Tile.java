package it.polimi.ingsw;

public class Tile {
    private final TileType type;

    private int x;

    private int y;

    private boolean blocked;

    public Tile (TileType type) {
        this.type = type;
        this.x = -1;
        this.y = -1;
        this.blocked = false;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
