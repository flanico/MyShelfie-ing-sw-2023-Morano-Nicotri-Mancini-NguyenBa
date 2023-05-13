package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

/**
 * class that controls the connectionPanel.fxml for the client connection
 * @author Stefano Morano
 */
public class ConnectionController extends ViewObservable implements Controller {

    @FXML
    TextField addressField;
    @FXML
    TextField serverPortField;
    @FXML
    ChoiceBox<String> serverType;

    private String address;
    private String port;

    int error;
    public void ContinuePressed(ActionEvent actionEvent) {
        address = addressField.getText();
        port = serverPortField.getText();
        final String correctAddress;
        final String correctPort;

        if (serverType.getValue()!=null) {
            if (serverType.getValue().equals("Socket")) {
                error = 0;

                if (port.compareTo("") == 0)
                    correctPort = "12345";
                else if (ClientController.isValidPort(port)) {
                    correctPort = port;
                } else {
                    error = 1;
                    correctPort = null;
                }

                if (address.compareTo("") == 0)
                    correctAddress = "localhost";
                else if (ClientController.isValidAddress(address)) {
                    correctAddress = address;
                } else {
                    correctAddress = null;
                    if (error == 0)
                        error = 2;
                    else error = 3;
                }

                switch (error) {
                    case 0:
                        new Thread(() -> notifyObserver(obs -> obs.createSocketConnection(correctAddress, correctPort))).start();
                        break;
                    case 1:
                        SceneController.showAlert("Invalid Port");
                        break;
                    case 2:
                        SceneController.showAlert("Invalid Address");
                        break;
                    case 3:
                        SceneController.showAlert("Invalid Port and Address");
                        break;
                }
            } else {
                //ACTIVE RMI
            }
        } else SceneController.showAlert("Please select a server type!");
    }
}
