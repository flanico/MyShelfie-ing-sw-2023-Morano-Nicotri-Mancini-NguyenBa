package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * socket implementation of the ClientHandler interface
 */
public class SocketClientHandler implements ClientHandler, Runnable {
    private final Socket clientSocket;
    private final SocketServer socketServer;
    private boolean isConnected;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private final Object inputLock;
    private final Object outputLock;

    /**
     * default constructor
     * @param clientSocket client connecting
     * @param socketServer socket of the server
     */
    public SocketClientHandler(Socket clientSocket, SocketServer socketServer) {
        this.clientSocket = clientSocket;
        this.socketServer = socketServer;
        this.inputLock = new Object();
        this.outputLock = new Object();
        this.isConnected = true;

        try {
            this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            this.handleClientConnection();
        } catch (IOException e) {
            Server.LOGGER.severe("Client " + clientSocket.getInetAddress() + " connection dropped");
            disconnect();
        }
    }

    /**
     * handles the connection with the new client and keep listening to the socket for new messages
     * @throws IOException for Input/Output exceptions
     */
    private void handleClientConnection() throws IOException {
        Server.LOGGER.info("Client connected from address " + clientSocket.getInetAddress());
        Server.LOGGER.info("Client connected from port " + clientSocket.getLocalPort());

        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (inputLock) {
                    Message message = (Message) inputStream.readObject();
                    //Aggiungere Ping message
                    if(message != null && message.getMessageType() != MessageType.PING) {
                        //if is a LOGIN message
                        if (message.getMessageType() == MessageType.LOGIN_REQ) {
                            Server.LOGGER.info(() -> "Message LoginRequest received from " + message.getNickname() + ": " + message);
                            socketServer.addClient(message.getNickname(), this);
                        } else {
                            Server.LOGGER.info(() -> "Message received from: " + message.getNickname() + ": " + message);
                            //forwards the message to the server that sends it to the main controller
                            socketServer.forwardsMessage(message);
                        }
                    }
                }
            }
        } catch (ClassCastException | ClassNotFoundException e) {
            Server.LOGGER.severe("Invalid stream from client");
        }
        clientSocket.close();
    }

    /**
     * disconnects the socket
     */
    @Override
    public void disconnect() {
        if(isConnected) {
            try {
                if(!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                Server.LOGGER.severe(e.getMessage());
            }
            isConnected = false;
            Thread.currentThread().interrupt();
            socketServer.onDisconnect(this);
        }
    }

    /**
     * sends a message to the client
     * @param message to be sent
     */
    @Override
    public void sendMessageToClient(Message message) {
        try {
            synchronized (outputLock) {
                outputStream.writeObject(message);
                outputStream.reset();
                Server.LOGGER.info( () -> "Message sent : " + message);
            }
        } catch (IOException e) {
            Server.LOGGER.severe(e.getMessage());
            disconnect();
        }
    }
}
