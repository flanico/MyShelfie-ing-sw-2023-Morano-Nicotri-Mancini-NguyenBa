package it.polimi.ingsw;

public class Cell {
    private boolean full;

    private boolean blocked;

    private Tile tile;

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
