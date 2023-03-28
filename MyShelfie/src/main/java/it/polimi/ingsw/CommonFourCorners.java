package it.polimi.ingsw;

public class CommonFourCorners extends CommonGoalCard {
    public boolean check(Player pl) {
        return !pl.getBookshelf().getBookshelf()[0][0].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[0][5].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[4][0].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[4][5].getType().equals(TileType.NULL)
                && pl.getBookshelf().getBookshelf()[0][0].getType().equals(pl.getBookshelf().getBookshelf()[0][5].getType())
                && pl.getBookshelf().getBookshelf()[0][5].getType().equals(pl.getBookshelf().getBookshelf()[4][0].getType())
                && pl.getBookshelf().getBookshelf()[4][0].getType().equals(pl.getBookshelf().getBookshelf()[4][5].getType());
    }
}
