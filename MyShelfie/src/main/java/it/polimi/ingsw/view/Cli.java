package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

/**
 * class that represents the interface view via command line interface
 */
public class Cli implements View {
    @Override
    public void askNickname() {

    }

    @Override
    public void askPlayersNumber() {

    }

    @Override
    public void showLoginResult(boolean isNicknameAccepted, String nickname) {

    }

    @Override
    public void showGameInfo(ArrayList<Player> players, int numberPlayers) {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showWinner(Player winner) {

    }
}
