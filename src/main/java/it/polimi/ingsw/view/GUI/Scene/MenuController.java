package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import it.polimi.ingsw.view.GUI.SceneController;

/**
 * controller of the menu panel
 * @Author: Stefano Morano
 */
public class MenuController extends ViewObservable implements Controller {

    /**
     * method called when the play button is pressed: the game starts
     * @param mouseEvent
     */
    public void playButton_click(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"ConnectionPanel.fxml");
    }

    /**
     * method called when the credits button is pressed: it shows the credits
     * @param mouseEvent
     */
    public void goCredits(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"creditPanel.fxml");
    }
}
