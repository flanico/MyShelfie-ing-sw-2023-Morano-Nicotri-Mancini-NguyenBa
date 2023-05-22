package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;

import java.util.Map;

public class EndController extends ViewObservable implements Controller{
    public void init(boolean winner, Map<String, Integer> playerScore, String winner_nickname){
        if (winner) {
            //set the winner image
        } else {
            //set the loser image
        }
        //set the score of the players
    }
}
