package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketServer;

import java.util.Scanner;

/**
 * main of the server application
 */
public class ServerApp {
    public static void main(String[] args) {

        int serverPort = 12345; //default port

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the port (Default port: " + serverPort + ")");

        try {
            serverPort = Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            Server.LOGGER.warning("Invalid port inserted! Using default port");
        }

        System.out.println("The server is running...");

        GameController gameController = new GameController();
        Server server = new Server(gameController);

        //Socket
        SocketServer socketServer = new SocketServer(serverPort, server);
        Thread thread = new Thread(socketServer, "socketserver_");
        thread.start();

        //RMI

    }
}
