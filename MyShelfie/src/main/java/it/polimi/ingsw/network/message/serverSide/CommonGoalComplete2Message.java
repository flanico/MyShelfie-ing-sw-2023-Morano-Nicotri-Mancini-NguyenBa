package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message tu notify that another player completed the common goal card
 */
public class CommonGoalComplete2Message extends Message {
    @Serial
    private static final long serialVersionUID = 7523312791357909051L;
    private CommonGoalCard commonGoalCard;
    private String nickname;
    private int commonGoalScore;

    public CommonGoalComplete2Message(String nickname, CommonGoalCard commonGoalCard, int commonGoalScore) {
        super("SERVER", MessageType.COMMON_GOAL_COMPLETE1);
        this.commonGoalCard = commonGoalCard;
        this.nickname = nickname;
        this.commonGoalScore = commonGoalScore;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public CommonGoalCard getCommongoal() {
        return commonGoalCard;
    }

    public int getCommonscores() {
        return commonGoalScore;
    }

    @Override
    public String toString() {
        return "CommonGoalComplete{" + this.nickname +
                "complete the common goal :" + commonGoalCard.toString() +
                "score : " + commonGoalScore +
                '}';
    }
}
