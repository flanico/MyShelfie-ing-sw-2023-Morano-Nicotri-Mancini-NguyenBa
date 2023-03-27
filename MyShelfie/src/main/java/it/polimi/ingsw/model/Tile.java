package it.polimi.ingsw.model;

public class Tile {
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

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
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
}
