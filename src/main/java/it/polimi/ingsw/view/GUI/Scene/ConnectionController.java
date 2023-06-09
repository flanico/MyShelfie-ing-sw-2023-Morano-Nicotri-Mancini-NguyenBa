package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.ErrorType;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

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
    private int type;
    int error;

    @SuppressWarnings("unused")
    public void continue_pressed(MouseEvent mouseEvent) {
        String address;
        String port;
        String defaultPort_socket = "12345";
        String defaultPort_rmi = "1099";
        address = addressField.getText();
        port = serverPortField.getText();
        final String correctAddress;
        final String correctPort;

        if (serverType.getValue()!=null) {
            error = 0;
            if (port.compareTo("") == 0) {
                if (serverType.getValue().equals("Socket")){
                    type=1;
                    correctPort = defaultPort_socket;
                } else {
                    correctPort = defaultPort_rmi;
                    type=2;
                }
            }
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
                case 0 -> new Thread(() -> notifyObserver(obs -> obs.createConnection(correctAddress, correctPort, type))).start();
                case 1 -> SceneController.popUp(ErrorType.WRONG_PORT);
                case 2 -> SceneController.popUp(ErrorType.WRONG_ADDRESS);
                case 3 -> SceneController.popUp(ErrorType.WRONG_PORT_ADDRESS);
            }
        } else SceneController.popUp(ErrorType.WRONG_TYPE);
    }
}
