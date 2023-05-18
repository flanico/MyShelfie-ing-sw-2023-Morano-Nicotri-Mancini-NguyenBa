package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.Scene.Controller;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.network.client.Client;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public static <T> T changeRootPane(List<ViewObserver> observerList, Event event, String fxml) {
        Scene scene = ((Node) event.getSource()).getScene();
        return changeRootPane(observerList, scene, fxml);
    }

    public static <T> T changeRootPane(List<ViewObserver> observerList, String fxml) {
        return changeRootPane(observerList, activeScene, fxml);
    }

    public static void changeRootPane(Controller controller, Scene scene, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));

            loader.setController(controller);
            activeController = controller;
            Parent root = loader.load();

            activeScene = scene;
            activeScene.setRoot(root);
        } catch (IOException e) {
            Client.LOGGER.severe(e.getMessage());
        }
    }

    public static void changeRootPane(Controller controller, Event event, String fxml) {
        Scene scene = ((Node) event.getSource()).getScene();
        changeRootPane(controller, scene, fxml);
    }

    public static void changeRootPane(Controller controller, String fxml) {
        changeRootPane(controller, activeScene, fxml);
    }

    public static void popUp(ErrorType errorType) {
        String path = "";
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(activeScene.getWindow());
        Pane popupContent = new Pane();
        popupContent.setPrefSize(400, 300);
        switch (errorType){
            case WRONG_NICKNAME -> {
                path = "Graphics/nicknameTaken.png";
                popupStage.setTitle("Nickname already taken");
            }
            case WRONG_ADDRESS -> {
                path = "Graphics/wrongServerAddress.png";
                popupStage.setTitle("Wrong server address");
            }
            case EMPTY_NICKNAME -> {
                path = "Graphics/nicknameEmpty.png";
                popupStage.setTitle("Empty nickname");
            }
            case WRONG_PORT -> {
                path = "Graphics/WrongIPPort.png";
                popupStage.setTitle("Wrong IP port");
            }
            case WRONG_PORT_ADDRESS -> {
                path = "Graphics/twoWrong.png";
                popupStage.setTitle("Wrong IP port and Server address");
            }
            case WRONG_TYPE -> {
                path = "Graphics/selectServerType.png";
                popupStage.setTitle("Select server type");
            }
            case EASTER_EGG -> {
                path = "Graphics/easter.png";
                popupStage.setTitle("Forza Napoli!");
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
