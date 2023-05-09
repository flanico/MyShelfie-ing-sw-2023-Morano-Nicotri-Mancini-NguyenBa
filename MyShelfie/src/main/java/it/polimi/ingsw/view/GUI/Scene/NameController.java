package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class NameController extends ViewObservable implements GenericSceneController{
    @FXML
    TextField usernameField;
    public void continuePressed(ActionEvent actionEvent) {
        String Nickname = usernameField.getText();
        if (Nickname.compareTo("")==0)
            SceneController.showAlert("The nickname field is empty!");
        else new Thread(() -> notifyObserver(obs -> obs.sendNickname(Nickname))).start();
    }
}
