package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.swing.text.html.ImageView;

public class Controller {
    @FXML
    TextField serverPortField ;
    @FXML
    TextField serverAddressField;
    @FXML
    TextField usernameField;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    public void joinGame(ActionEvent actionEvent) {


        if ( usernameField.getText().compareTo("")==0){
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci username");
            alert.showAndWait();
        }

    }
}
