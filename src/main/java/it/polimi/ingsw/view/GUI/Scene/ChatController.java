package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the chat scene
 * @author Stefano Morano
 */
public class ChatController extends ViewObservable implements Controller{
    @FXML
    TextArea text_area;
    @FXML
    TextField text_field;
    @FXML
    ComboBox<String> choiceBox;

    @SuppressWarnings("unused")
    public void initialize(){
        text_field.setOnKeyPressed(event -> {
            if (event.getCode().getName().equals("Enter")) {
                send_enter();
            }
        });
        text_area.setEditable(false);
        text_area.setText("");
    }

    /**
     * method called when the chat button is pressed, and it is initially closed
     * @param buffer the buffer of the chat
     * @param players  the list of players
     */
    @SuppressWarnings("unused")
    public void run(ArrayList<String> buffer, List<Player> players){
        ObservableList<String> parameters = FXCollections.observableArrayList();
        parameters.add("all");
        for (int x=1; x<players.size(); x++){
            parameters.add(players.get(x).getNickname());
        }
        choiceBox.setItems(parameters);
        choiceBox.setValue("all");
        for (String s : buffer) {
            text_area.appendText(s + "\n");
        }
        text_area.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * method that appends a message to the open chat
     * @param message the message to append
     */
    @SuppressWarnings("unused")
    public void append(String message){
        text_area.appendText(message + "\n");
        text_area.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * method that sends a message to the server if the send button is pressed
     * @param mouseEvent the mouse event
     */
    @SuppressWarnings("unused")
    public void send(MouseEvent mouseEvent) {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage(choiceBox.getValue(), text_field.getText()));
            text_field.setText("");
        }
    }

    /**
     * method that sends a message to the server if the (enter) key is pressed
     */
    public void send_enter() {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage(choiceBox.getValue(), text_field.getText()));
            text_field.setText("");
        }
    }
}
