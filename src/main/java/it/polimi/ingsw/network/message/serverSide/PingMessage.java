package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to keep the connection alive
 */
public class PingMessage extends Message {
    @Serial
    private static final long serialVersionUID = -1786249975802887310L;

    public PingMessage() {
        super(null, MessageType.PING);
    }
}
