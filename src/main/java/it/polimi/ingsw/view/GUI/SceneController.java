package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.GUI.Scene.Controller;
import it.polimi.ingsw.view.GUI.Scene.popUpController;
import javafx.application.Platform;
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

    /**
     * Method that changes the root pane of the scene
     * @param observerList the list of the observers in the game
     * @param fxml the name of the fxml file to load
     */
    public static void changeRootPane(List<ViewObserver> observerList, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));
            Parent root = loader.load();
            activeScene.setRoot(root);
            activeController = loader.getController();
            ((ViewObservable) activeController).addAllObservers(observerList);
        } catch (IOException e) {
            System.out.println("Error in loading the fxml file");
        }
    }

    /**
     * This method creates a popup window with a message
     * @param errorType the type of the error
     */
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
                path = "/Graphics/nicknameTaken.png";
                popupStage.setTitle("Nickname already taken!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_ADDRESS -> {
                path = "/Graphics/wrongServerAddress.png";              //TODO MODIFICA LA GRAFICA
                popupStage.setTitle("Wrong server address!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case EMPTY_NICKNAME -> {
                path = "/Graphics/nicknameEmpty.png";
                popupStage.setTitle("Empty nickname!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_PORT -> {
                path = "/Graphics/WrongIPPort.png";
                popupStage.setTitle("Wrong IP port!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_PORT_ADDRESS -> {
                path = "/Graphics/twoWrong.png";
                popupStage.setTitle("Wrong IP port and Server address!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case EASTER_EGG -> {
                path = "/Graphics/easter.png";
                popupStage.setTitle("Forza Napoli!");
                popupStage.getIcons().add(new Image("/Graphics/Napoli-Logo.png"));
            }
            case NOT_REMOVABLE_TILES -> {
                path = "/Graphics/tilesWrong.png";
                popupStage.setTitle("Not removable tiles!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case NOT_SPACE -> {
                path = "/Graphics/spaceBookshelf.png";
                popupStage.setTitle("Not enough space!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
            }
            case WRONG_SERVER -> {
                path = "/Graphics/serverImpossible.png";
                popupStage.setTitle("Server not reachable!");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
                popupStage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(1);
                });
            }
            case EIGHT_SAME -> {
                path = "/Graphics/eightSameType.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case FOUR_GROUPS -> {
                path = "/Graphics/fourGroups.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case INCREASING_HEIGHT -> {
                path = "/Graphics/increasingHeight.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case THREE_ROW -> {
                path = "/Graphics/maxFourRows.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case THREE_COLUMNS -> {
                path = "/Graphics/maxThreeColumn.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case TWO_COLUMN -> {
                path = "/Graphics/maxTwoColumn.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case TWO_ROW -> {
                path = "/Graphics/maxTwoRows.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case SAME_CORNER -> {
                path = "/Graphics/sameCorner.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case SAME_DIAGONAL -> {
                path = "/Graphics/sameDiagonal.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case SIX_GROUPS -> {
                path = "/Graphics/sixGroups.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case TWO_SQUARES -> {
                path = "/Graphics/twoSquares.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case X_SHAPE -> {
                path = "/Graphics/xShape.png";
                popupStage.setTitle("Common Goal Card");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
            }

            case ERROR_OCCURRED -> {
                path = "/Graphics/errorOccurred.png";
                popupStage.setTitle("Error");
                popupStage.getIcons().add(new Image("/Graphics/alert.png"));
                popupStage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(1);
                });
            }

            case ONLY_ONE -> {
                path = "/Graphics/onlyOne.png";
                popupStage.setTitle("You are the only player in the game!");
                popupStage.getIcons().add(new Image("/Graphics/questionMark.png"));
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

    /**
     * This method creates a popup window with a message in string format
     * @param message which is the message to show
     */
    public static void popUpString(String message) {
        try {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(activeScene.getWindow());
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/popUp.fxml"));
            Parent rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            popUpController controller = loader.getController();
            Platform.runLater(() -> controller.init(message));
            popupStage.setScene(scene);
            popupStage.setResizable(false);
            popupStage.setTitle("Disconnection");
            popupStage.showAndWait();
        } catch (IOException e) {
            System.out.println(("Error"));
        }
    }

}
