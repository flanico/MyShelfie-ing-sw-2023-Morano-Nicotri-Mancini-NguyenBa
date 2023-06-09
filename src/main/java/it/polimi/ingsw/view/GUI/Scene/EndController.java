package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Map;


public class EndController extends ViewObservable implements Controller{
    @FXML
    Text place1_sentence, place2_sentence, place3_sentence, place4_sentence;
    @FXML
    ImageView place1_text, place2_text, place3_text, place4_text, place1_white, place2_white, place3_white, place4_white, won_text, lost_text, frame, won_card;
    public void initialize(){
        place1_sentence.setText("");
        place2_sentence.setText("");
        place3_sentence.setText("");
        place4_sentence.setText("");
        place1_text.setVisible(false);
        place2_text.setVisible(false);
        place3_text.setVisible(false);
        place4_text.setVisible(false);
        place1_white.setVisible(false);
        place2_white.setVisible(false);
        place3_white.setVisible(false);
        place4_white.setVisible(false);
        won_text.setVisible(false);
        lost_text.setVisible(false);
        frame.setVisible(false);
        won_card.setVisible(false);
    }
    @SuppressWarnings("unused")
    public void init(boolean winner, Map<String, Integer> playerScore, int numberOfPlayers){
        switch (numberOfPlayers){
            case 2 -> {
                place1_white.setVisible(true);
                place2_white.setVisible(true);
                place1_text.setVisible(true);
                place2_text.setVisible(true);
            }
            case 3 -> {
                place1_white.setVisible(true);
                place2_white.setVisible(true);
                place3_white.setVisible(true);
                place1_text.setVisible(true);
                place2_text.setVisible(true);
                place3_text.setVisible(true);
            }
            case 4 -> {
                place1_white.setVisible(true);
                place2_white.setVisible(true);
                place3_white.setVisible(true);
                place4_white.setVisible(true);
                place1_text.setVisible(true);
                place2_text.setVisible(true);
                place3_text.setVisible(true);
                place4_text.setVisible(true);
            }
        }
        if (winner) {
            won_card.setVisible(true);
            won_text.setVisible(true);
            frame.setVisible(true);
        } else {
            lost_text.setVisible(true);
        }
        int position = 1;
        for (String player : playerScore.keySet()) {
            switch (position){
                case 1 -> place1_sentence.setText(player + " with " + playerScore.get(player)+ " points");
                case 2 -> place2_sentence.setText(player + " with " + playerScore.get(player)+ " points");
                case 3 -> place3_sentence.setText(player + " with " + playerScore.get(player)+ " points");
                case 4 -> place4_sentence.setText(player + " with " + playerScore.get(player)+ " points");
            }
            position++;
        }
    }
}
