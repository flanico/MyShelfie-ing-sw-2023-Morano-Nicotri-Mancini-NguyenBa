package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.LoginRequestMessage;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.message.serverSide.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * class used from client side, it is the mediator between the network and a generic view
 */
public class ClientController implements Observer, ViewObserver {
    private final View view;
    private Client client;
    private String nickname;
    private final ExecutorService executorService;

    public ClientController(View view) {
        this.view = view;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * checks if the given ip address is correct (ip address is X.X.X.X, where every X is a number bewtween 0 and 255)
     * @param ip address inserted by the client
     * @return true if the ip address is valid, false otherwise
     */
    public static boolean isValidAddress(String ip) {
        String[] subStrings=ip.split("\\.");

        if(subStrings.length==4){
            for(int i=0; i<4; i++){
                if(Integer.parseInt(subStrings[i])<0 || Integer.parseInt(subStrings[i])>255){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * checks if the given port is correct (port is a number between 1 and 65535)
     * @param port the port number inserted by the client
     * @return true if the port is valid, false otherwise
     */
    public static boolean isValidPort(String port) {
        try {
            int p = Integer.parseInt(port);
            return p >= 1 && p <= 65535;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * it updates the client with a specific message based on the message type received from the server
     * it must handle all message types from SERVER
     * @param message the received message from the server
     */
    @Override
    public void update(Message message) {
        switch (message.getMessageType()) {
            //Tutti i messaggi lato server
            case LOGIN_REPLY -> {
                LoginReplyMessage loginReplyMessage = (LoginReplyMessage) message;
                executorService.execute(() -> view.showLoginResult(loginReplyMessage.isNicknameAccepted(), nickname));
            }
            case NUM_PLAYERS_REQ -> executorService.execute(view::askPlayersNumber);
            case ERROR -> {
                ErrorMessage errorMessage = (ErrorMessage) message;
                executorService.execute(() -> view.showError(errorMessage.getMessageError()));
            }
            case GENERIC -> {
                GenericMessage genericMessage = (GenericMessage) message;
                executorService.execute(() -> view.showGenericMessage(genericMessage.getMessage()));
            }
            case SHOW_COMMON -> {
                ShowCommonCardsMessage showCommonCardsMessage = (ShowCommonCardsMessage) message;
                executorService.execute(() -> view.showCommonCards(showCommonCardsMessage.getCommonGoalCards()));
            }
            case SHOW_PERSONAL -> {
                ShowPersonalCardMessage showPersonalCardMessage = (ShowPersonalCardMessage) message;
                executorService.execute(() -> view.showPersonalCard(showPersonalCardMessage.getPlayer()));
            }
            case SELECT_TILE_REQ -> {
                SelectTileRequestMessage selectTileRequestMessage = (SelectTileRequestMessage) message;
                executorService.execute(() -> view.askSelectTiles(selectTileRequestMessage.getBoard()));
            }
            case SHOW_BOARD -> {
                ShowBoardMessage showBoardMessage = (ShowBoardMessage) message;
                executorService.execute(() -> view.showBoard(showBoardMessage.getBoard()));
            }
        }
    }

    /**
     * create a new socket connection between server and client
     * @param ip the ip address
     * @param port the port number
     */
    @Override
    public void createConnection(String ip, String port) {
        try {
            client = new SocketClient(ip, Integer.parseInt(port));
            client.addObserver(this);
            client.readMessage();
            client.sendPingMessage(true);
            executorService.execute(view::askNickname);
        } catch (IOException e) {
            executorService.execute(() -> view.showLoginResult(false, null));
        }
    }

    @Override
    public void sendNickname(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new LoginRequestMessage(this.nickname));
    }

    @Override
    public void sendNumPlayers(int numPlayers) {
        client.sendMessage(new NumPlayersReplyMessage(this.nickname, numPlayers));
    }
}
