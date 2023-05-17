package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NameController extends ViewObservable implements Controller {
    @FXML
    TextField usernameField;
    @FXML
    ImageView continueButton;

    public void continueButtonPressed(MouseEvent mouseEvent) {
        String Nickname = usernameField.getText();
        if (Nickname.compareTo("")==0)
            SceneController.showAlert("The nickname field is empty!");
        else new Thread(() -> notifyObserver(obs -> obs.sendNickname(Nickname))).start();
    }
}
