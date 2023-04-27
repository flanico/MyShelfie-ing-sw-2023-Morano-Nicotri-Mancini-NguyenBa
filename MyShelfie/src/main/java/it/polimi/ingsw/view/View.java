package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;

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
    void showWinner(Player winner);

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
     */
    void askSelectTiles(Board board);

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

    void showEndTurn();

    /**
     * show the stack of the selected common goal card
     * @param commonGoalCardScores is the stack of the available scores
     */
    void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores);

    /**
     * check if you complete a common Goal Card
     * @param commonGoalCard is the common Goal to check
     * @param score is the score relative at the completed common goal
     */
    void showCommonGoalComplete1(CommonGoalCard commonGoalCard, int score);




    void showScores(List<Player> players);
}
