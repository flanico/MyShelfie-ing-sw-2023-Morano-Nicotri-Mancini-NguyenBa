package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

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

    private int num=0;

    /**
     * method called when the ok button is pressed: the number of players is sent to the server
     * @param actionEvent
     */

    public void okPressed(MouseEvent actionEvent) {
        if (num!=0)
            notifyObserver(obs -> obs.sendNumPlayers(num));
    }

    /**
     * method called when the two button is pressed: the number of players is set to 2
     * @param actionEvent
     */
    public void twoPressed(ActionEvent actionEvent) {
      threeCheck.setSelected(false);
      fourCheck.setSelected(false);
      num = 2;
    }

    /**
     * method called when the three button is pressed: the number of players is set to 3
     * @param actionEvent
     */
    public void threePressed(ActionEvent actionEvent) {
        twoCheck.setSelected(false);
        fourCheck.setSelected(false);
        num = 3;
    }

    /**
     * method called when the four button is pressed: the number of players is set to 4
     * @param actionEvent
     */
    public void fourPressed(ActionEvent actionEvent) {
        threeCheck.setSelected(false);
        twoCheck.setSelected(false);
        num = 4;
    }
}
