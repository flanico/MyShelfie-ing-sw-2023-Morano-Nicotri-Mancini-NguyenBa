package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatController extends ViewObservable implements Controller{
    @FXML
    TextArea text_area;
    @FXML
    TextField text_field;
    @FXML
    ComboBox<String> choiceBox;

    @SuppressWarnings("unused")
    public void initialize(){
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.ENTER);
        SceneController.getActiveScene().setOnKeyPressed((event -> {
            if (keyCombination.match(event))
                send_enter();
        }));
        text_area.setEditable(false);
        text_area.setText("");
    }
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

    @SuppressWarnings("unused")
    public void append(String message){
        text_area.appendText(message + "\n");
        text_area.setScrollTop(Double.MAX_VALUE);
    }
    @SuppressWarnings("unused")
    public void send(MouseEvent mouseEvent) {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage(choiceBox.getValue(), text_field.getText()));
            text_field.setText("");
        }
    }

    public void send_enter() {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage(choiceBox.getValue(), text_field.getText()));
            text_field.setText("");
        }
    }
}
