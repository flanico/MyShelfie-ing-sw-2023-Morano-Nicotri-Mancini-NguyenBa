package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

/**
 * message used to send to the client information about the game (players and number)
 */
public class InfoGameMessage extends Message {
    @Serial
    private static final long serialVersionUID = -6954035833019978470L;
    private List<Player> players;
    private int num;

    public InfoGameMessage(List<Player> players, int num) {
        super("SERVER", MessageType.INFO_GAME);
        this.players = players;
        this.num = num;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "InfoGame{" +
                "players=" + players.toString() +
                ", num=" + num +
                '}';
    }
}
