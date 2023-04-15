package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to notify an error to the client
 */
public class ErrorMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5355978810741721251L;
    private String messageError;

    public ErrorMessage(String messageError) {
        super("SERVER", MessageType.ERROR);
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "messageError='" + messageError + '\'' +
                '}';
    }
}
