package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Integer> score;       //the order of the scores array is the same of the players' array
    private Bookshelf bookshelf;
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
        game.fillBoard();
    }

    //turnGame (check common, select da board del player, inserire nella bookshelf, 1 punto a chi finisce prima)


    public void initScore(ArrayList<Integer> score) {
        this.score = new ArrayList<Integer>();
    }


    //checkGame (personal, adiacenze)
    public void checkGame (ArrayList<Player> players)
    {
        for (int i=0; i< players.size(); i++) {
            //add the personal goal score for each player
            int partialscore;
            partialscore = players.get(i).getPersonalGoalCard().check(players.get(i).getPersonalGoalCard());
            this.score.add(i,partialscore);

            //add the ad score relative to adjacent cells for each player
            this.bookshelf = players.get(i).getBookshelf();
            partialscore = this.bookshelf.adjacentCells(this.bookshelf);
            score.set(i,score.get(i)+partialscore);
        }
    }

    public static void main (String[] args) {
        Game game = new Game(new Board(4));
        newGame(game);
    }
}
