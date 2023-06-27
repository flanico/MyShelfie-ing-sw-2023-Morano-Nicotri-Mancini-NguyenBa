package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.scene.input.MouseEvent;
import it.polimi.ingsw.view.GUI.SceneController;

/**
 * controller of the menu panel
 * @author Stefano Morano
 */
public class MenuController extends ViewObservable implements Controller {

    /**
     * method called when the play button is pressed: the game starts
     * @param mouseEvent the mouse event
     */
    @SuppressWarnings("unused")
    public void playButton_click(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"ConnectionPanel.fxml");
    }

    /**
     * method called when the credits button is pressed: it shows the credits
     * @param mouseEvent the mouse event
     */
    @SuppressWarnings("unused")
    public void goCredits(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"creditPanel.fxml");
    }
}
