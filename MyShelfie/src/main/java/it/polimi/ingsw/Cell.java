package it.polimi.ingsw;

public class Cell {
    private boolean full;

    private boolean blocked;

    private Tile tile;

    public Cell(Tile tile) {
        tile = new Tile(TileType.NULL);
        this.tile = tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isFull() {
        return full;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setFull(boolean value) {
        this.full = value;
    }

    public void setBlocked(boolean value) {
        this.blocked = value;
    }
}
