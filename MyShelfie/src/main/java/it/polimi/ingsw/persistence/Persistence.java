package it.polimi.ingsw.persistence;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.server.Server;

import java.io.*;
import java.nio.file.Files;

/**
 * class used to handle the persistence of the game, used for save, restore and delete a game saved in a file
 */
public class Persistence implements Serializable {
    @Serial
    private static final long serialVersionUID = -6902603608717399135L;
    private GameController gameController;

    public Persistence(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * save the game controller of the game in a file
     * @param gameController game controller of the match
     */
    public void storeGame(GameController gameController) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("game-saved.txt")) {
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(gameController);
            Server.LOGGER.info("Server saves the current game on the disk");
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.severe("Failed to save the game on a file");
        }
    }

    /**
     * restore a previous saved game from the file
     * @return game controller of the previous game saved in the file
     */
    public GameController restoreGame() {
        try (FileInputStream fileInputStream = new FileInputStream("game-saved.txt")) {
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            gameController = (GameController) inputStream.readObject();
            Server.LOGGER.info("Server restore the game from the disk");
            return gameController;
        } catch (IOException | ClassNotFoundException e) {
            Server.LOGGER.severe("Failed to restore the game from file");
        }
        return null;
    }

    /**
     * delete the file game
     */
    public void deleteGame() {
        File file = new File("game-saved.txt");
        try {
            Files.deleteIfExists(file.toPath());
            Server.LOGGER.info("Server delete the game from the disk");
        } catch (IOException e) {
            Server.LOGGER.severe("Failed to delete the file game");
        }
    }
}
