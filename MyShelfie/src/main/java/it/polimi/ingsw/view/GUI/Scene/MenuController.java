package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import it.polimi.ingsw.view.GUI.SceneController;
import javax.lang.model.type.NullType;

public class MenuController extends ViewObservable implements GenericSceneController {

    public void playPressed(ActionEvent actionEvent) throws Exception{
        SceneController.changeRootPane(observers, actionEvent, "ConnectionPanel.fxml");
    }

}
