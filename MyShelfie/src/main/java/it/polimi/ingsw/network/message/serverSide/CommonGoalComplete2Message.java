package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.model.CommonGoalCardScore;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

/**
 * message tu notify that another player completed the common goal card
 */
public class CommonGoalComplete2Message extends Message {
    @Serial
    private static final long serialVersionUID = 5506828047860036031L;
    private CommonGoalCard commongoal;

    private List<CommonGoalCardScore> commonscores;

    public CommonGoalComplete2Message(CommonGoalCard commongoal, List<CommonGoalCardScore> commonscores) {
        super("SERVER", MessageType.COMMON_GOAL_COMPLETE1);
        this.commongoal = commongoal;
        this.commonscores= commonscores;
    }


    public CommonGoalCard getCommongoal() {
        return commongoal;
    }

    public List<CommonGoalCardScore> getCommonscores() {
        return commonscores;
    }

    @Override
    public String toString() {
        return "CommonGoalComplete{ You" +
                "complete the common goal :" + commongoal +
                "new Available scores : " + commonscores +
                '}';
    }
}
