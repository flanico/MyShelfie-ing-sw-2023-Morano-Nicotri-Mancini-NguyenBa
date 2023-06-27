package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Controller for the pop-up window
 * @author Stefano Morano
 */
public class popUpController extends ViewObservable implements Controller {
    @FXML
    Text textField;

    /**
     * method that initialize the pop-up window
     * @param message the message to print
     */
    public void init(String message){
        textField.setText(message);
    }
}
