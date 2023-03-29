package it.polimi.ingsw;
import java.util.*;

/**
 * class that controls more game flows
 * @author Alessandro Mancini
 */
public class Menu {

    /**
     * manages the sequencing of the turns of the players
     * @param game the current game
     * @author Alessandro Mancini
     */
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
                if (game.getCommongoalcards().get(0).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getScores().add(i, game.getScores().get(i) + game.getCommonscores0().pop());
                }
                if (game.getCommongoalcards().get(1).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getScores().add(i, game.getScores().get(i) + game.getCommonscores1().pop());
                }
                if (game.getPlayers().get(i).getBookshelf().isFull()) {
                    last = 1;
                }
            }
        }
    }

    /**
     * checks the final points of the players
     * @param game the current game
     * @author Flavia Nicotri
     */
    public static void checkGame (Game game) {
        int partialscore;
        for (int i = 0; i < game.getNum(); i++) {
            partialscore = game.getPersonalgoalcards().get(i).check();
            partialscore += game.getPlayers().get(i).getBookshelf().adjacentCells();
            game.getScores().add(i, partialscore);
        }
    }

    /**
     * main to run
     * @param args
     * @author Alessandro Mancini
     */
    public static void main (String[] args) {
        Game game = new Game();
        turnGame(game);
        checkGame(game);
    }
}
