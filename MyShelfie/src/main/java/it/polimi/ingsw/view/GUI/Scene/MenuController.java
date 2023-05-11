package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import it.polimi.ingsw.view.GUI.SceneController;

public class MenuController extends ViewObservable implements Controller {
    @FXML
    Pane panel;
    public void playPressed(ActionEvent actionEvent) throws Exception{
        SceneController.changeRootPane(observers, actionEvent,"ConnectionPanel.fxml");
    }

}
