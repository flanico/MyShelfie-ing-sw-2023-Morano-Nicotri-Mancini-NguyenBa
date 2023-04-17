package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

public class WinnerPlayerMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7453168361368250724L;
    private final Player winner;

    public WinnerPlayerMessage(Player winner) {
        super("SERVER", MessageType.WINNER);
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "WinPlayer{" +
               // "winner=" + winner.getNickname() +
                '}';
    }
}
