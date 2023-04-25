package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to show the personal goal card
 */
public class ShowPersonalCardMessage extends Message {
    @Serial
    private static final long serialVersionUID = 4878920160881688169L;
    private final Player player;

    public ShowPersonalCardMessage(Player player) {
        super("SERVER", MessageType.SHOW_PERSONAL);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "ShowPersonalCard{Number: " + player.getPersonalGoalCard().getType() +
                ", player=" + player.getNickname() +
                '}';
    }
}
