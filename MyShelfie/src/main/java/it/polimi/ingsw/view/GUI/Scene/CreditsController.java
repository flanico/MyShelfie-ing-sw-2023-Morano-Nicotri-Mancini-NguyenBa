package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.scene.input.MouseEvent;

public class CreditsController extends ViewObservable implements Controller{
    public void backPressed(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"menuPanel.fxml");
    }
}
