package it.polimi.ingsw;
import org.junit.Test;

public class GameTest {

    @Test
    public void initBagTest() {
        Game game = new Game(3);

        int count_game = 0;
        int count_cat = 0;
        int count_trophy = 0;
        int count_plant = 0;
        int count_frame = 0;
        int count_book = 0;

        for (Tile tile : game.getBag()) {
            TileType type = tile.getType();
            if (type == TileType.GAME) {
                count_game++;
            }
            if (type == TileType.CAT) {
                count_cat++;
            }
            if (type == TileType.TROPHY) {
                count_trophy++;
            }
            if (type == TileType.PLANT) {
                count_plant++;
            }
            if (type == TileType.FRAME) {
                count_frame++;
            }
            if (type == TileType.BOOK) {
                count_book++;
            }
        }

        System.out.println("Il sacchetto contiene " + game.getBag().size() + " tessere");
        System.out.println(count_game + " tessere Game");
        System.out.println(count_cat + " tessere Gatto");
        System.out.println(count_trophy + " tessere Trofeo");
        System.out.println(count_plant + " tessere Pianta");
        System.out.println(count_frame + " tessere Cornice");
        System.out.println(count_book + " tessere Libro");
        System.out.println();
    }
}