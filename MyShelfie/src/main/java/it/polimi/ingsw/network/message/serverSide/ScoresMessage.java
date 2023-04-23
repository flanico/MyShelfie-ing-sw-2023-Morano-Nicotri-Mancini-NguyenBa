package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

public class ScoresMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7453168361368250724L;
    private final Player winner;
    private  Player player;

    public ScoresMessage(Player winner, Player player) {
        super("SERVER", MessageType.SCORES);
        this.winner = winner;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "END GAME -WinPlayer{" + winner + "is the winner of the game. Score:" + getWinner().getScore() +
                "Your score: " + player.getScore() +
                '}';
    }
}
