package it.polimi.ingsw.network.client.socket;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.serverSide.ErrorMessage;
import it.polimi.ingsw.network.message.serverSide.PingMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * class that represents a socket client implementation
 */
public class SocketClient extends Client {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private final ExecutorService executorService;
    private  final ScheduledExecutorService pinger;
    private static final int SOCKET_TIMEOUT = 10000;

    public SocketClient(String ipAddress, int port) throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(ipAddress, port), SOCKET_TIMEOUT);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.executorService = Executors.newSingleThreadExecutor();
        this.pinger = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * sends a message to the server via socket
     * @param message to be sent
     */
    @Override
    public void sendMessage(Message message) {
        try {
            outputStream.writeObject(message);
            outputStream.reset();
        } catch (IOException e) {
            disconnect();
            notifyObserver(new ErrorMessage("Error in sending message"));
        }
    }

    /**
     * reads a message from the server via socket and notifies the ClientController
     */
    @Override
    public void readMessage() {
        executorService.execute(() -> {
            while(!executorService.isShutdown()) {
                Message message;
                try {
                    message = (Message) inputStream.readObject();
                    //Client.LOGGER.info("Received: " + message);
                } catch (IOException | ClassNotFoundException e) {
                    message = new ErrorMessage("Connection lost with the server");
                    disconnect();
                    executorService.shutdownNow();
                }
                notifyObserver(message);
            }
        });
    }

    /**
     * disconnect the socket client
     */
    @Override
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                executorService.shutdownNow();
                socket.close();
            }
        } catch (IOException e) {
            notifyObserver(new ErrorMessage("Error in disconnecting"));
        }
    }

    /**
     * sends a ping message in order to keep alive the connection between server and client sockets
     * @param isActive is true to enable the ping, false otherwise
     */
    @Override
    public void sendPingMessage(boolean isActive) {
        if(isActive) {
            pinger.scheduleAtFixedRate(() -> sendMessage(new PingMessage()), 0, 1000, TimeUnit.MILLISECONDS);
        } else {
            pinger.shutdownNow();
        }
    }
}
