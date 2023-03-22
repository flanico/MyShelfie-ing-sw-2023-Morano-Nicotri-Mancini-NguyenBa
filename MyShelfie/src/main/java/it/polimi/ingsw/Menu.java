package it.polimi.ingsw;

public class Menu {

    private static void newGame (Game game) {
        game.howManyPlayers();
        //System.out.println(game.num);
        game.initPlayers();
        /*
        for (int i = 0; i < game.num; i++) {
            System.out.println(game.players.get(i).getNickname());

        }
        */
        game.initCommonGoalCard();
        //System.out.println(game.commongoalcard[0].getId());
        //System.out.println(game.commongoalcard[1].getId());
    }

    public static void main (String[] args) {
        Game game = new Game();
        newGame(game);
    }
}
