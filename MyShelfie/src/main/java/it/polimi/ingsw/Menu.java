package it.polimi.ingsw;

public class Menu {

    private static void newGame (Game game) {
        game.howManyPlayers();
        //System.out.println(game.getNum());
        game.initPlayers();
        //for (int i = 0; i < game.getNum(); i++) {
        //    System.out.println(game.getPlayers().get(i).getNickname());
        //}
        game.initCommonGoalCard();
        game.initBag();
        //for (Tile t : game.getBag()) {
        //    System.out.println(t.getType());
        //}
        //System.out.println(game.getBag().size());
    }

    public static void main (String[] args) {
        Game game = new Game();
        newGame(game);
    }
}
