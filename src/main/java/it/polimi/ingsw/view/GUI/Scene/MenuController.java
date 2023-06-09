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

public class MenuController extends ViewObservable implements Controller {

    public void playButton_click(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"ConnectionPanel.fxml");
    }

    public void goCredits(MouseEvent mouseEvent) {
        SceneController.changeRootPane(observers,"creditPanel.fxml");
    }
}
