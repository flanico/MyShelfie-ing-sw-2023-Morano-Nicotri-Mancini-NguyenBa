package it.polimi.ingsw.view;

import it.polimi.ingsw.model.CommonGoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * interface used to define a generic view
 */
public interface View {

    /**
     * asks the client to choose a nickname
     */
    void askNickname();

    /**
     * asks the client to choose the number of players
     */
    void askPlayersNumber();

    /**
     * shows to the client the login response
     * @param isNicknameAccepted if the nickname selected is accepted or not
     * @param nickname of player
     */
    void showLoginResult(boolean isNicknameAccepted, String nickname);

    /**
     * shows to the client the information about the game
     * @param players all the players joined in the game
     * @param numberPlayers number of players in the game
     */
    void showGameInfo(ArrayList<Player> players, int numberPlayers);

    /**
     * shows to the client an error
     * @param errorMessage the text message error
     */
    void showError(String errorMessage);

    /**
     * shows to the client a generic message
     * @param genericMessage the text of generic message
     */
    void showGenericMessage(String genericMessage);

    /**
     * shows to the client who won the game
     * @param winner the winning player of the game
     */
    void showWinner(Player winner);

    void showCommonCards(List<CommonGoalCard> commonGoalCards);

    void showPersonalCard(Player player);
}
