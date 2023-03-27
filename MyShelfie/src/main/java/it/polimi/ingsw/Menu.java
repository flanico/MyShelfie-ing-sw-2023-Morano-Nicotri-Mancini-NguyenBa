package it.polimi.ingsw;
import java.util.*;

public class Menu {
    private static void turnGame (Game game) {
        int last = 0;
        while (last == 0) {
            for (int i = 0; i < game.getNum(); i++) {
                ArrayList<Tile> t = game.getPlayers().get(i).selectTile();
                Scanner scanner = new Scanner(System.in);
                System.out.println("In which column of your bookshelf?");
                int x = scanner.nextInt();
                while (x < 0 || x > 4 || game.getPlayers().get(i).getBookshelf().isColFull(x)); {
                    x = scanner.nextInt();
                }
                game.getPlayers().get(i).getBookshelf().insertTile(t, x);
                if (game.getPlayers().get(i).getBookshelf().isFull()) {
                    last = 1;
                }
            }
        }
    }

    //checkGame (personal, adiacenze, vincitore)

    public static void main (String[] args) {
        Game game = new Game();
        turnGame(game);
        checkGame(game);


    }
}
