package it.polimi.ingsw;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Tile;

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
                //show the board
                game.getBoard().printMatrix();

                //choose a tile
                int number = 3;
                Integer[][] coordinates = new Integer[number][2];
                ArrayList<Tile> t = game.getBoard().selectTile(number, coordinates);

                //insert the tile
                game.getPlayers().get(i).getBookshelf().insertTile(t, 3);

                //check if commmon goals are reached
                if (game.getCommongoalcards().get(0).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getPlayers().get(i).setScore((game.getPlayers().get(i).getScore() + game.getCommongoalcardscores().get(0).getStack().pop()));
                }
                if (game.getCommongoalcards().get(1).check(game.getPlayers().get(i).getBookshelf())) {
                    game.getPlayers().get(i).setScore((game.getPlayers().get(i).getScore() + game.getCommongoalcardscores().get(1).getStack().pop()));
                }

                //check if the player ends to play
                if (game.getPlayers().get(i).getBookshelf().isFull()) {
                    last = 1;
                }

                //check if the board has to refill
                if (game.getBoard().isRefillable()) {
                    game.getBoard().fillBoard(game.getBag());
                }
            }
        }
    }

    /**
     * checks the final points of the players
     * @param game the current game
     * @author Alessandro Mancini
     */
    public static void checkGame (Game game) {
        for (int i = 0; i < game.getNum(); i++) {
            game.getPlayers().get(i).setScore(game.getPlayers().get(i).getScore() + game.getPersonalgoalcards().get(i).check() + game.getPlayers().get(i).getBookshelf().adjacentCells());
        }
    }

    /**
     * main to run
     * @param args
     * @author Alessandro Mancini
     */
    public static void main (String[] args) {
        int num = 3;
        Game game = new Game(num);
        turnGame(game);
        checkGame(game);
    }
}
