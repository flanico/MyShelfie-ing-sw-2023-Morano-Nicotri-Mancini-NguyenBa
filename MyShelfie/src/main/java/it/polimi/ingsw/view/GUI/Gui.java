package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.Scene.GameControllerScene;
import it.polimi.ingsw.view.GUI.Scene.LobbyController;
import it.polimi.ingsw.view.View;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class Gui extends ViewObservable implements View {
    @Override
    public void askNickname(){
        Platform.runLater(() -> SceneController.changeRootPane(observers, "NamePanel.fxml"));
    }

    @Override
    public void askPlayersNumber(){
        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectPlayersPanel.fxml"));
    }

    @Override
    public void showLoginResult(boolean isNicknameAccepted, String nickname){
        if(isNicknameAccepted) {

        }
        else {
            Platform.runLater(() -> SceneController.showAlert("This nickname is already taken!"));
            askNickname();
        }
    }

    /**
     * shows to the client the information about the game
     * @param players all the players joined in the game
     * @param numberPlayers number of players in the game
     */
    @Override
    public void showGameInfo(List<Player> players, int numberPlayers){
        LobbyController lobby_ctrl;
        try {
            lobby_ctrl = (LobbyController) SceneController.getActiveController();
            lobby_ctrl.setNicknames(players);
            lobby_ctrl.setNum_players(numberPlayers);
            Platform.runLater(lobby_ctrl::update);
        } catch (ClassCastException e) {
            lobby_ctrl = new LobbyController();
            lobby_ctrl.addAllObservers(observers);
            lobby_ctrl.setNicknames(players);
            lobby_ctrl.setNum_players(numberPlayers);
            LobbyController new_ctrl = lobby_ctrl;
            Platform.runLater(() -> SceneController.changeRootPane(new_ctrl, "lobbyPanel.fxml"));
        }
    }

    /**
     * shows to the client an error
     * @param errorMessage the text message error
     */
    @Override
    public void showError(String errorMessage){
        //Platform.runLater(() -> SceneController.showAlert("Error", errorMessage));
    }

    /**
     * shows to the client a generic message
     * @param genericMessage the text of generic message
     */
    @Override
    public void showGenericMessage(String genericMessage){

    }

    /**
     * shows to the client who won the game
     * @param winner the winning player of the game
     */
    @Override
    public void showWinner(String winner){

    }

    /**
     * shows to the client the two common goal card of the match
     * @param commonGoalCards the common goal card of the game
     */
    @Override
    public void showCommonCards(List<CommonGoalCard> commonGoalCards){

    }

    /**
     * shows to the client his personal goal card
     * @param player of the game
     */
    @Override
    public void showPersonalCard(Player player){
        GameControllerScene game_ctrl = getGameControllerScene();
        game_ctrl.setPersonalCard(player.getPersonalGoalCard());
        Platform.runLater(game_ctrl::updatePersonalCard);
    }

    /**
     * shows to the client the board of the game
     * @param board the board of the game
     */
    @Override
    public void showBoard(Board board){
        GameControllerScene game_ctrl = getGameControllerScene();
        game_ctrl.setBoard(board);
        Platform.runLater(game_ctrl::updateBoard);
    }

    /**
     * asks the client to select the tiles from the board
     * @param board the board of the game
     */
    @Override
    public void askSelectTiles(Board board, Bookshelf bookshelf){
        GameControllerScene game_ctrl = getGameControllerScene();
        Platform.runLater(game_ctrl::activeSelection);
    }

    /**
     * asks the client to insert the tiles in the bookshelf
     * @param bookshelf of the player
     * @param tiles to insert in the bookshelf
     */
    @Override
    public void askInsertTiles(Bookshelf bookshelf, List<Tile> tiles){

    }

    /**
     * shows to the client the bookshelf
     * @param player to show his bookshelf
     */
    @Override
    public void showBookshelf(Player player){

    }

    /**
     * asks the client to select the order of the insertion of the tiles
     * @param tiles to order
     */
    @Override
    public void askOrderTiles(List<Tile> tiles){

    }

    /**
     * shows the stack of the selected common goal card
     * @param commonGoalCardScores is the stack of the available scores
     */
    @Override
    public void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores){

    }

    /**
     * checks if the player has completed a common Goal Card
     * @param commonGoalCard is the common Goal to check
     * @param score is the score relative at the completed common goal
     */
    @Override
    public void showCommonGoalComplete(CommonGoalCard commonGoalCard, int score){
        GameControllerScene game_ctrl = getGameControllerScene();

    }

    /**
     * shows the final scores of the players
     * @param playerScore map contains players and their scores
     */
    @Override
    public void showScores(Map<String, Integer> playerScore){

    }

    /**
     * notify to all the client a player disconnection
     */
    @Override
    public void disconnection(String nickname){

    }

    private GameControllerScene getGameControllerScene(){
        GameControllerScene game_ctrl;
        try {
            game_ctrl = (GameControllerScene) SceneController.getActiveController();
        } catch (ClassCastException e) {
            game_ctrl = new GameControllerScene();
            game_ctrl.addAllObservers(observers);
            GameControllerScene new_ctrl = game_ctrl;
            Stage stage = (Stage) SceneController.getActiveScene().getWindow();
            stage.setWidth(1280d);
            stage.setHeight(720d);
            SceneController.changeRootPane(new_ctrl, SceneController.getActiveScene(),"gamePanel.fxml");
        }
        return game_ctrl;
    }

}
