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
        Scanner scanner = new Scanner(System.in);
        int last = 0;
        while (last == 0) {
            for (int i = 0; i < game.getNum(); i++) {
                //choose a tile
                ArrayList<Tile> t = game.getPlayers().get(i).selectTile();

                //insert the tile
                System.out.println("In which column of your bookshelf?");
                int column = scanner.nextInt();
                while (column < 0 || column > 4 || game.getPlayers().get(i).getBookshelf().isColFull(column)); {
                    column = scanner.nextInt();
                }
                game.getPlayers().get(i).getBookshelf().insertTile(t, column);

                //check if commmon goals are reached
                if (game.getCommongoalcards().get(0).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getScores().add(i, game.getScores().get(i) + game.getCommonscores().get(0).getStack().pop());
                }
                if (game.getCommongoalcards().get(1).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getScores().add(i, game.getScores().get(i) + game.getCommonscores().get(1).getStack().pop());
                }

                //check if the player ends to play
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
            partialscore = game.getPlayers().get(i).getPersonalgoalcard().check();
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
