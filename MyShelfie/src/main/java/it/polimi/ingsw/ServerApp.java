package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.server.SocketServer;

import it.polimi.ingsw.network.server.RMIServer;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * main of the server application
 */
public class ServerApp {
    public static void main(String[] args) throws RemoteException {

        //socket
        int serverSocketPort = 12345; //default socket port

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the socket port (default: " + serverSocketPort + ") : ");
        try {
            serverSocketPort = Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            Server.LOGGER.warning("Invalid port inserted! Using default port");
        }

        GameController gameController = new GameController();
        Server server = new Server(gameController);

        SocketServer socketServer = new SocketServer(serverSocketPort, server);
        Thread socketThread = new Thread(socketServer, "socketserver_");
        socketThread.start();

        //RMI
        int serverRMIPort = 1099; //default RMI port

        System.out.print("Enter the RMI port (default: " + serverRMIPort + ") : ");
        try {
            serverRMIPort = Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            Server.LOGGER.warning("Invalid port inserted! Using default port");
        }

        RMIServer rmiServer = null;
        try {
            rmiServer = new RMIServer(serverRMIPort, server);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        Thread rmiThread = new Thread(rmiServer, "rmiserver_");
        rmiThread.start();

        System.out.println ("The server is running...");

    }
}
