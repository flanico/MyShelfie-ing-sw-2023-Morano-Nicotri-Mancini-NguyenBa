package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML

    Label scritta;

    public void showtitle(ActionEvent actionEvent){
        scritta.setOpacity(100);
    }

}
