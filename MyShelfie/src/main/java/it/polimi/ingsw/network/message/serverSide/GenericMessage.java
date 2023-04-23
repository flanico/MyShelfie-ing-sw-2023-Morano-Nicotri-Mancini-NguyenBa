package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

public class GenericMessage extends Message {
    @Serial
    private static final long serialVersionUID = -1459285387719574659L;
    private String message;

    public GenericMessage(String nickname, String message) {
        super(nickname, MessageType.GENERIC);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GenericMessage{" +
                "message : '" + message + '\'' +
                '}';
    }
}
