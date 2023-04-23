package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

public class ShowCommonCardsMessage extends Message {
    @Serial
    private static final long serialVersionUID = 4574676858326174828L;
    private final List<CommonGoalCard> commonGoalCards;

    public ShowCommonCardsMessage(List<CommonGoalCard> commonGoalCards) {
        super("SERVER", MessageType.SHOW_COMMON);
        this.commonGoalCards = commonGoalCards;
    }

    public List<CommonGoalCard> getCommonGoalCards() {
        return commonGoalCards;
    }

    @Override
    public String toString() {
        return "ShowCommonCards{" +
                "commonGoalCards=" + commonGoalCards +
                '}';
    }
}
