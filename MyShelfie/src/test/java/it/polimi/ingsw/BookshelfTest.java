package it.polimi.ingsw;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class BookshelfTest extends TestCase {
    @Test
    public void testInsertTile() throws Exception {
        ArrayList<Tile> a = new ArrayList<Tile>();
        Tile t1 = new Tile(TileType.CAT);
        a.add(t1);
        Tile t2 = new Tile(TileType.CAT);
        a.add(t2);
        Bookshelf b = new Bookshelf();
        b.insertTile(a, 1);
        printBookshelf(b);
    }

    public static void printBookshelf (Bookshelf t) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.println(t.getBookshelf()[i][j].getType().toString());
            }
        }
    }
}