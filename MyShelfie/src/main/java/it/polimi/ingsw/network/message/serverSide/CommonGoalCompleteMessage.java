package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.model.CommonGoalCardScore;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

/**
 * message tu notify that a player completed the common goal card
 */
public class CommonGoalCompleteMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7523312791357909051L;
    private CommonGoalCard commongoal;
    private String nickname;
    private List<CommonGoalCardScore> commonscores;

    public CommonGoalCompleteMessage(String nickname, CommonGoalCard commongoal, List<CommonGoalCardScore> commonscores) {
        super("SERVER", MessageType.COMMON_GOAL_COMPLETE);
        this.commongoal = commongoal;
        this.nickname = nickname;
        this.commonscores= commonscores;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public CommonGoalCard getCommongoal() {
        return commongoal;
    }

    public List<CommonGoalCardScore> getCommonscores() {
        return commonscores;
    }

    @Override
    public String toString() {
        return "CommonGoalComplete{" + this.nickname +
                "complete tha common goal :" + commongoal +
                "new Available scores : " + commonscores +
                '}';
    }
}
