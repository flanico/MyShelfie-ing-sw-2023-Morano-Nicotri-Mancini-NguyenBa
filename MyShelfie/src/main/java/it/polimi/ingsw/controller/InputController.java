package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.message.clientSide.OrderReplyMessage;
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
    protected InputController(GameController gameController, Map<String, VirtualView> virtualViewMap) {
        this.game = gameController.getGame();
        this.gameController = gameController;
        this.virtualViewMap = virtualViewMap;
    }

    /**
     * checks if a nickname is valid or not
     * @param nickname of the client
     * @param view the view for the current client
     * @return true if the nickname is valid, false otherwise
     */
    protected boolean checkNickname(String nickname, View view){
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
    protected boolean checkNumPlayers(Message message) {
        NumPlayersReplyMessage numPlayersReplyMessage = (NumPlayersReplyMessage) message;
        if(numPlayersReplyMessage.getNumPlayers() >= 2 && numPlayersReplyMessage.getNumPlayers() <= 4) {
            return true;
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
    protected boolean checkTiles(Message message) {
        Bookshelf bookshelfOfTheRoundPlayer;
        int maxTiles;
        TilesReplyMessage tilesReplyMessage = (TilesReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(tilesReplyMessage.getNickname());
        if(game.getBoard().isRemovable(tilesReplyMessage.getTiles())) {
//            virtualView.showGenericMessage("Tiles selected are removable from the board!");
            bookshelfOfTheRoundPlayer = game.getPlayerByNickname(tilesReplyMessage.getNickname()).getBookshelf();
            maxTiles = bookshelfOfTheRoundPlayer.maxTilesBookshelf();
            if (tilesReplyMessage.getTiles().size() > maxTiles)
            {
                virtualView.showGenericMessage("Sorry, you don't have enough space in your bookshelf. You can select MAX "+ maxTiles + " tiles. Retry.");
                return false;
            }else
                 return true;
        }
        else {
            virtualView.showGenericMessage("Sorry, tiles selected are NOT removable from the board! Retry.");
            virtualView.askSelectTiles(game.getBoard());
            return false;
        }
    }

    /**
     * checks if the selected position is valid or not
     * @param message the message from the client
     * @return true if the position is valid, false otherwise
     */
    protected boolean checkPosition(Message message) {
        PositionReplyMessage positionReplyMessage = (PositionReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(positionReplyMessage.getNickname());
        if(positionReplyMessage.getColumn() >= 0 && positionReplyMessage.getColumn() <= 4 &&
                game.getPlayerByNickname(positionReplyMessage.getNickname()).getBookshelf().isInsertableTile(positionReplyMessage.getTiles(), positionReplyMessage.getColumn())) {
//            virtualView.showGenericMessage("The selected column is correct!");
            return true;
        }
        else {
            virtualView.showGenericMessage("Sorry, you didn't provide a correct select of the column! Retry.");
            virtualView.askInsertTiles(game.getPlayerByNickname(positionReplyMessage.getNickname()).getBookshelf(), positionReplyMessage.getTiles());
            return false;
        }
    }

    /**
     * checks if the selected order of tiles is valid or not
     * @param message the message from the client
     * @return true if the order is valid, false otherwise
     */
    protected boolean checkOrder(Message message) {
        OrderReplyMessage orderReplyMessage = (OrderReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(orderReplyMessage.getNickname());
        if(!orderReplyMessage.getTiles().isEmpty()) {
            return true;
        }
        else {
            virtualView.showGenericMessage("Sorry, you didn't provide a correct select of the order of the tiles! Retry");
            virtualView.askOrderTiles(orderReplyMessage.getTiles());
            return false;
        }
    }
}
