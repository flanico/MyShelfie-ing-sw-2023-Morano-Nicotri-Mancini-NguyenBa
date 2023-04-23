package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.message.clientSide.PositionReplyMessage;
import it.polimi.ingsw.network.message.clientSide.TilesReplyMessage;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * class that verifies the correctness of the messages sent by the client
 */
public class InputController implements Serializable {
    @Serial
    private static final long serialVersionUID = -7743359196379310184L;
    private final Game game;
    private final GameController gameController;
    private final Map<String, VirtualView> virtualViewMap;

    /**
     * constructor of the input controller
     * @param gameController the game controller
     * @param virtualViewMap the virtual view of all the clients
     */
    public InputController(GameController gameController, Map<String, VirtualView> virtualViewMap) {
        this.game = gameController.getGame();
        this.gameController = gameController;
        this.virtualViewMap = virtualViewMap;
    }

    //Check all message types send by the client


    /**
     * checks if a nickname is valid or not
     * @param nickname of the client
     * @param view the view for the current client
     * @return true if the nickname is valid, false otherwise
     */
    public boolean checkNickname(String nickname, View view){
        if(nickname.isEmpty() || game.isNicknameTaken(nickname)) {
            view.showLoginResult(false, nickname);
            return false;
        }
        return true;
    }

    /**
     * checks the player number reply message
     * @param message the message from the client
     * @return true if it's a valid number, false otherwise
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

    /**
     * checks if the selected tiles are valid or not
     * @param message the message from the client
     * @return true if the tiles are valid, false otherwise
     */
    public boolean checkTiles(Message message) {
        TilesReplyMessage tilesReplyMessage = (TilesReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(message.getNickname());
        //Check
        virtualView.showGenericMessage("You didn't provide a correct select of the tiles!");
        return false;
    }

    /**
     * checks if the selected position is valid or not
     * @param message the message from the client
     * @return true if the position is valid, false otherwise
     */
    public boolean checkPosition(Message message) {
        PositionReplyMessage positionReplyMessage = (PositionReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(message.getNickname());
        //Check
        virtualView.showGenericMessage("You didn't provide a correct select of the position!");
        return false;
    }

}
