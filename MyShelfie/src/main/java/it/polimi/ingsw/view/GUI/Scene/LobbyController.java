package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.awt.*;
import java.util.List;


public class LobbyController extends ViewObservable implements GenericSceneController {
    @FXML
    Label player_1;

    @FXML
    Label player_2;

    @FXML
    Label player_3;

    @FXML
    Label player_4;

    @FXML
    Label number;
    private List<Player> nicknames;
    private int num_players;



    void init(){
        player_1.setText(nicknames.get(0).getNickname());
        player_2.setText("");
        player_3.setText("");
        player_4.setText("");
        number.setText(nicknames.size() + "/" + num_players);
    }

    public void setNicknames(List<Player> nicknames) {
        this.nicknames = nicknames;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    public void update() {
        switch (nicknames.size()){
            case 2:
                player_2.setText(nicknames.get(1).getNickname());
                break;
            case 3:
                player_3.setText(nicknames.get(2).getNickname());
                break;
            case 4:
                player_4.setText(nicknames.get(3).getNickname());
                break;
        }
        number.setText(nicknames.size() + "/" + num_players);
    }
}
