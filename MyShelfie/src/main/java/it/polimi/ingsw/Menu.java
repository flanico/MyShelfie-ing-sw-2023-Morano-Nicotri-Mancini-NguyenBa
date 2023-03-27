package it.polimi.ingsw;
import java.util.*;

public class Menu {

    private static void newGame (Game game) {
        game.howManyPlayers();
        //System.out.println(game.getNum());
        game.initPlayers();
        //for (int i = 0; i < game.getNum(); i++) {
        //    System.out.println(game.getPlayers().get(i).getNickname());
        //}
        game.initCommonGoalCard();
        game.initPersonalGoalCard();
        game.initBag();
        //for (Tile t : game.getBag()) {
        //    System.out.println(t.getType());
        //}
        //System.out.println(game.getBag().size());
        game.fillBoard();
    }

    //turnGame (check common, select da board del player, inserire nella bookshelf, 1 punto a chi finisce prima)
    private static void turnGame (Game game) {
        for (int i = 0; i < game.getNum(); i++) {
            ArrayList<Tile> t = game.getPlayers().get(i).selectTile();
            Scanner scanner = new Scanner(System.in);
            System.out.println("In which column of your bookshelf?");
            int x = scanner.nextInt();
            while (x < 0 || x > 4); {
                x = scanner.nextInt();
            }
            game.getPlayers().get(i).getBookshelf().insertTile(t, x);
        }
    }

    //checkGame (personal, adiacenze, vincitore)

    public static void main (String[] args) {
        Game game = new Game(new Board(4));
        newGame(game);
    }
}
