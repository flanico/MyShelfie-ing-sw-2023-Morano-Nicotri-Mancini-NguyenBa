package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.GUI.Scene.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Class that controls every change of scenes, panels and observers
 * @author Stefano Morano
 */

public class SceneController extends ViewObservable {


    private static Scene activeScene;
    private static Controller activeController;
    public static Scene getActiveScene() {
        return activeScene;
    }
    public static Controller getActiveController() {
        return activeController;
    }
    public static void setActiveScene(Scene activeScene) {
        SceneController.activeScene = activeScene;
    }
    public static <T> T changeRootPane(List<ViewObserver> observerList, Scene scene, String fxml) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));
            Parent root = loader.load();
            controller = loader.getController();
            ((ViewObservable) controller).addAllObservers(observerList);

            activeController = (Controller) controller;
            activeScene = scene;
            activeScene.setRoot(root);
        } catch (IOException e) {
            Client.LOGGER.severe(e.getMessage());
        }
        return controller;
    }

    public static <T> T changeRootPane(List<ViewObserver> observerList, String fxml) {
        return changeRootPane(observerList, activeScene, fxml);
    }

    public static void popUp(ErrorType errorType) {
        String path = "";
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(activeScene.getWindow());
        //popupStage.centerOnScreen();
        Pane popupContent = new Pane();
        popupContent.setPrefSize(400, 300);
        switch (errorType){
            case WRONG_NICKNAME -> {
                path = "Graphics/nicknameTaken.png";
                popupStage.setTitle("Nickname already taken!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_ADDRESS -> {
                path = "Graphics/wrongServerAddress.png";
                popupStage.setTitle("Wrong server address!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case EMPTY_NICKNAME -> {
                path = "Graphics/nicknameEmpty.png";
                popupStage.setTitle("Empty nickname!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_PORT -> {
                path = "Graphics/WrongIPPort.png";
                popupStage.setTitle("Wrong IP port!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_PORT_ADDRESS -> {
                path = "Graphics/twoWrong.png";
                popupStage.setTitle("Wrong IP port and Server address!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_TYPE -> {
                path = "Graphics/selectServerType.png";
                popupStage.setTitle("Select server type!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case EASTER_EGG -> {
                path = "Graphics/easter.png";
                popupStage.setTitle("Forza Napoli!");
                popupStage.getIcons().add(new Image("/Graphics/Napoli-Logo.png"));
            }
            case NOT_REMOVABLE_TILES -> {
                path = "Graphics/tilesWrong.png";
                popupStage.setTitle("Not removable tiles!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case NOT_SPACE -> {
                path = "Graphics/spaceBookshelf.png";
                popupStage.setTitle("Not enough space!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
        }
        ImageView imageView = new ImageView(path);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        popupContent.getChildren().add(imageView);
        Scene popupScene = new Scene(popupContent);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

}
