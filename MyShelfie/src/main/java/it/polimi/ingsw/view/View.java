package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.Message;

import java.util.List;
import java.util.Map;

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
     * @param isConnectionSuccessful if the connection is successful or not
     * @param nickname of player
     */
    void showLoginResult(boolean isNicknameAccepted, boolean isConnectionSuccessful, String nickname);

    /**
     * shows to the client the information about the game
     * @param players all the players joined in the game
     * @param numberPlayers number of players in the game
     */
    void showGameInfo(List<Player> players, int numberPlayers);

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
    void showWinner(String winner);

    /**
     * shows to the client the two common goal card of the match
     * @param commonGoalCards the common goal card of the game
     */
    void showCommonCards(List<CommonGoalCard> commonGoalCards);

    /**
     * shows to the client his personal goal card
     * @param player of the game
     */
    void showPersonalCard(Player player);

    /**
     * shows to the client the board of the game
     * @param board the board of the game
     */
    void showBoard(Board board);

    /**
     * asks the client to select the tiles from the board
     * @param board the board of the game
     * @param bookshelf of the player
     */
    void askSelectTiles(Board board, Bookshelf bookshelf);

    /**
     * asks the client to insert the tiles in the bookshelf
     * @param bookshelf of the player
     * @param tiles to insert in the bookshelf
     */
    void askInsertTiles(Bookshelf bookshelf, List<Tile> tiles);

    /**
     * shows to the client the bookshelf
     * @param player to show his bookshelf
     */
    void showBookshelf(Player player);

    /**
     * asks the client to select the order of the insertion of the tiles
     * @param tiles to order
     */
    void askOrderTiles(List<Tile> tiles);

    /**
     * shows the stack of the selected common goal card
     * @param commonGoalCardScores is the stack of the available scores
     */
    void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores);

    /**
     * checks if the player has completed a common Goal Card
     * @param commonGoalCard is the common Goal to check
     * @param score is the score relative at the completed common goal
     */
    void showCommonGoalComplete(CommonGoalCard commonGoalCard, int score);

    /**
     * shows the final scores of the players
     * @param playerScore map contains players and their scores
     */
    void showScores(Map<String, Integer> playerScore);

    /**
     * add a new message into the buffer of the chat
     * @param sender sender of the message
     * @param destination destination of the message
     * @param message message to add
     */
    void addChatMessage(String sender, String destination, String message);

    /**
     * notify to all the client a player disconnection
     * @param nickname of the player disconnected
     * @param isStarted if the game is started or not
     */
    void disconnection(String nickname, boolean isStarted);
}
