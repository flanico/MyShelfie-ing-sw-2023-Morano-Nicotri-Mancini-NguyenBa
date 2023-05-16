package it.polimi.ingsw.network.server.socket;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * class that handles the socket connection with a new client
 */
public class SocketServer implements Runnable {
    private final Server server;
    private final int port;
    private ServerSocket serverSocket;

    public SocketServer(int port, Server server) {
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Server.LOGGER.info(() -> "The socket of the server started on port " + port);
        } catch (IOException e) {
            Server.LOGGER.severe("Server impossible to start!");
            return;
        }

        while(!Thread.currentThread().isInterrupted()) {
            try {
                Socket client = serverSocket.accept();
                //if data do not arrive from the client, SocketTimeoutException will be thrown
                client.setSoTimeout(5000);
                SocketClientHandler socketClientConnection = new SocketClientHandler(client,this);
                Thread thread = new Thread(socketClientConnection, "SocketServerThread:" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                Server.LOGGER.severe("The connection has dropped");
            }
        }
    }

    /**
     * handles the addition of a new client
     * @param nickname of the new client
     * @param clientHandler of the new client
     */
    public void addClient(String nickname, ClientHandler clientHandler) {
        server.addClient(nickname, clientHandler);
    }

    /**
     * forwards a received message from the client to the server
     * @param message received
     */
    public void forwardsMessage(Message message) {
        server.forwardsMessage(message);
    }

    /**
     * handles the disconnection of a client
     * @param clientHandler of the disconnecting client
     */
    public void onDisconnect(ClientHandler clientHandler) {
        server.onDisconnect(clientHandler);
    }
}
