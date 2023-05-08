package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;

public class SelectPlayersController extends ViewObservable implements GenericSceneController{
    @FXML
    JCheckBox twoCheck;

    @FXML
    JCheckBox threeCheck;

    @FXML
    JCheckBox fourCheck;

    @FXML
    Button okButton;


    public void okPressed(ActionEvent actionEvent) {
    }

    public void twoPressed(ActionEvent actionEvent) {
      //  twoCheck.setValue();
    }

    public void threePressed(ActionEvent actionEvent) {
    }

    public void fourPressed(ActionEvent actionEvent) {
    }
}
