package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.GameController;

import java.util.logging.Logger;

/**
 *  class that represents tha main server
 */
public class Server {
    public static final Logger LOGGER =  Logger.getLogger(Server.class.getName());
    private final GameController gameController;

    public Server(GameController gameController) {
        this.gameController = gameController;
    }

}
