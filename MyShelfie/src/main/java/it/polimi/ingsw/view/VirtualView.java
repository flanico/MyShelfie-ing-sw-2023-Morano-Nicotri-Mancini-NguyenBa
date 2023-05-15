package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.*;
import it.polimi.ingsw.network.message.serverSide.*;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.observer.Observer;

import java.util.List;
import java.util.Map;

/**
 * class used as view for the controller, it hides the real view and the network to the controller
 */
public class VirtualView implements View, Observer {
    private ClientHandler clientHandler;

    /**
     * default constructor
     * @param clientHandler to send messages
     */
    public VirtualView(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public void askNickname() {
        clientHandler.sendMessageToClient(new LoginReplyMessage(false));
    }

    @Override
    public void askPlayersNumber() {
        clientHandler.sendMessageToClient(new NumPlayersRequestMessage());
    }

    @Override
    public void showLoginResult(boolean isNicknameAccepted, String nickname) {
        clientHandler.sendMessageToClient(new LoginReplyMessage(isNicknameAccepted));
    }

    @Override
    public void showGameInfo(List<Player> players, int num) {
        System.out.println("eccoci");
        clientHandler.sendMessageToClient(new InfoGameMessage(players, num));
    }

    @Override
    public void showError(String errorMessage) {
        clientHandler.sendMessageToClient(new ErrorMessage(errorMessage));
    }

    @Override
    public void showWinner(String winner) {
        clientHandler.sendMessageToClient(new WinnerPlayerMessage(winner));
    }

    @Override
    public void update(Message message) {
        clientHandler.sendMessageToClient(message);
    }

    @Override
    public void showGenericMessage(String genericMessage) {
        clientHandler.sendMessageToClient(new GenericMessage("SERVER", genericMessage));
    }

    @Override
    public void showCommonCards(List<CommonGoalCard> commonGoalCards) {
        clientHandler.sendMessageToClient(new ShowCommonCardsMessage(commonGoalCards));
    }

    @Override
    public void showPersonalCard(Player player) {
        clientHandler.sendMessageToClient(new ShowPersonalCardMessage(player));
    }

    @Override
    public void showBoard(Board board) {
        clientHandler.sendMessageToClient(new ShowBoardMessage(board.isRefillable(), board));
    }

    @Override
    public void askSelectTiles(Board board, Bookshelf bookshelf) {
        clientHandler.sendMessageToClient(new SelectTileRequestMessage(board, bookshelf));
    }

    @Override
    public void askInsertTiles(Bookshelf bookshelf, List<Tile> tiles) {
        clientHandler.sendMessageToClient(new InsertTilesRequestMessage(tiles, bookshelf));
    }

    @Override
    public void showBookshelf(Player player) {
        clientHandler.sendMessageToClient(new ShowBookshelfMessage(player, player.getBookshelf()));
    }

    @Override
    public void askOrderTiles(List<Tile> tiles) {
        clientHandler.sendMessageToClient(new OrderRequestMessage(tiles));
    }

    @Override
    public void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores) {
        clientHandler.sendMessageToClient(new ShowCommonScoresMessage(commonGoalCardScores));
    }

    @Override
    public void showCommonGoalComplete(CommonGoalCard commonGoalCard, int score) {
        clientHandler.sendMessageToClient(new CommonGoalCompleteMessage(commonGoalCard, score));
    }

    @Override
    public void disconnection(String nickname, boolean isStarted) {
        clientHandler.sendMessageToClient(new DisconnectionMessage(nickname, isStarted));
    }

    @Override
    public void showScores(Map<String, Integer> playerScore) {
        clientHandler.sendMessageToClient(new ScoresMessage(playerScore));
    }
}
