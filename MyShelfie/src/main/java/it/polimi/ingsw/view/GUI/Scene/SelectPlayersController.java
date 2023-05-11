package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * Controller class for the SelectPlayer panel
 * This panel is shown only at the first player
 * Panel creating for the choice of the number of player in the game
 * @author Stefano Morano
 */
public class SelectPlayersController extends ViewObservable implements Controller {
    @FXML
    CheckBox twoCheck;

    @FXML
    CheckBox threeCheck;

    @FXML
    CheckBox fourCheck;

    @FXML
    Button okButton;

    private int num;

    public void okPressed(ActionEvent actionEvent) {
        notifyObserver(obs -> obs.sendNumPlayers(num));
    }

    public void twoPressed(ActionEvent actionEvent) {
      threeCheck.setSelected(false);
      fourCheck.setSelected(false);
      num = 2;
    }

    public void threePressed(ActionEvent actionEvent) {
        twoCheck.setSelected(false);
        fourCheck.setSelected(false);
        num = 3;
    }

    public void fourPressed(ActionEvent actionEvent) {
        threeCheck.setSelected(false);
        twoCheck.setSelected(false);
        num = 4;
    }
}
