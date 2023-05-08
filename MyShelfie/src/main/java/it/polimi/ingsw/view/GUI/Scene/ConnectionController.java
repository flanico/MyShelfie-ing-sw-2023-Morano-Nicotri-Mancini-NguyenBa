package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class ConnectionController extends ViewObservable implements GenericSceneController{

    @FXML
    TextField addressField;
    @FXML
    TextField serverPortField;
    public void ContinuePressed(ActionEvent actionEvent) {
        final String address;
        final String port;
        if (serverPortField.getText().compareTo("")==0)
            port = "12345";
        else port = serverPortField.getText();
        if (addressField.getText().compareTo("")==0)
            address = "localhost";
        else address = addressField.getText();
        new Thread(() -> notifyObserver(obs -> obs.createConnection(address, port))).start();
    }
}
