package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * class used from client side, it is the mediator between the network and a generic view
 */
public class ClientController implements Observer {
    private final View view;
    private Client client;
    private String nickname;
    private final ExecutorService executorService;

    public ClientController(View view) {
        this.view = view;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * it updates the client with a specific message based on the message type received from the server
     * @param message received from the server
     */
    @Override
    public void update(Message message) {
        switch (message.getMessageType()) {
            //Tutti i messaggi lato server
        }
    }
}
