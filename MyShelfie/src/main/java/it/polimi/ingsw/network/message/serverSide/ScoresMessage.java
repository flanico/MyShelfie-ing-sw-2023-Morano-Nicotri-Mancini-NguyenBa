package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.Map;

public class ScoresMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7453168361368250724L;
    private Map<String, Integer> playerScore;

    public ScoresMessage(Map<String, Integer> playerScore) {
        super("SERVER", MessageType.SCORES);
        this.playerScore = playerScore;
    }

    public Map<String, Integer> getPlayerScore() {
        return playerScore;
    }

    @Override
    public String toString() {
        return "ScoresMessage{" +
                "playerScore=" + playerScore +
                '}';
    }
}
