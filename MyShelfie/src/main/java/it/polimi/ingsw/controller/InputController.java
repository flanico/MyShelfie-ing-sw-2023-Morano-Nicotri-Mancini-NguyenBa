package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.Map;

public class InputController {
    private final Game game;
    private final GameController gameControl;
    private final Map<String, VirtualView> virtualViewMap;

    public InputController(GameController gameControl, Map<String, VirtualView> virtualViewMap) {
        this.game = gameControl.getGame();
        this.gameControl = gameControl;
        this.virtualViewMap = virtualViewMap;
    }

    /**
     * check if a nickname is valid or not
     * @param nickname of client
     * @param view view for current client
     * @return true if the nickname is valid false otherwise
     */
    public boolean checkNickname(String nickname, View view){
        if(nickname.isEmpty() || game.isNicknameTaken(nickname)) {
            view.showLoginResult(false, nickname);
            return false;
        }
        return true;
    }

    /**
     * check player number reply message
     * @param message message from the client
     * @return true if it's a valid number false otherwise
     */
    public boolean checkNumPlayers(Message message) {
        NumPlayersReplyMessage numPlayersReplyMessage = (NumPlayersReplyMessage) message;

        if(numPlayersReplyMessage.getNumPlayers() >= 2 && numPlayersReplyMessage.getNumPlayers() <= 4) {
            return  true;
        }
        else {
            VirtualView virtualView = virtualViewMap.get(message.getNickname());
            virtualView.askPlayersNumber();
            return false;
        }
    }
}
