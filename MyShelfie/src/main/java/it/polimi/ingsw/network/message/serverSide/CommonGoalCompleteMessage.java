package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message tu notify that another player completed the common goal card
 */
public class CommonGoalCompleteMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5506828047860036031L;
    private CommonGoalCard commongoal;

    private int commonGoalScore;
    public CommonGoalCompleteMessage(CommonGoalCard commongoal, int commonGoalScore) {
        super("SERVER", MessageType.COMMON_GOAL_COMPLETE);
        this.commongoal = commongoal;
        this.commonGoalScore = commonGoalScore;
    }


    public CommonGoalCard getCommongoal() {
        return commongoal;
    }

    public int getCommonscores() { return commonGoalScore;
    }

    @Override
    public String toString() {
        return "CommonGoalComplete{ You" +
                "complete the common goal :" + commongoal +
                "score: " + commonGoalScore +
                '}';
    }
}
