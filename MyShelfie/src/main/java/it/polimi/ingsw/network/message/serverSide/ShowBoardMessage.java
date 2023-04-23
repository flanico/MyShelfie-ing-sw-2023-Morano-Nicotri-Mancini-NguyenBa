package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to the client to show the board
 */

public class ShowBoardMessage extends Message {
    @Serial
    private static final long serialVersionUID = -1220642280749517895L;
    private boolean hasToBeRefilled;
    private Board board;

    public ShowBoardMessage (boolean hasToBeRefilled, Board board){
        super("SERVER", MessageType.SHOW_BOARD);
        this.hasToBeRefilled = hasToBeRefilled;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "ShowBoard{ Board needs to refill" +
                "=" + hasToBeRefilled + "}";
    }
}
