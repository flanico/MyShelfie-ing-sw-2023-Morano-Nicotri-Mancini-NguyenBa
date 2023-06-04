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

public class ChatController extends ViewObservable implements Controller{
    @FXML
    TextArea text_area;
    @FXML
    TextField text_field;
    @FXML
    ComboBox choiceBox;

    public void initialize(){
        text_area.setEditable(false);
        text_area.setText("");
    }
    public void run(ArrayList<String> buffer, List<Player> players){
        ObservableList<String> parameters = FXCollections.observableArrayList();
        parameters.add("all");
        for (int x=1; x<players.size(); x++){
            parameters.add(players.get(x).getNickname());
        }
        choiceBox.setItems(parameters);
        choiceBox.setValue("all");
        for (int x=0; x<buffer.size(); x++){
            text_area.appendText(buffer.get(x) + "\n");
        }
        text_area.setScrollTop(Double.MAX_VALUE);
    }

    public void append(String message){
        text_area.appendText(message + "\n");
        text_area.setScrollTop(Double.MAX_VALUE);
    }

    public void send(MouseEvent mouseEvent) {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage(choiceBox.getValue().toString(), text_field.getText())); //TODO Implementa choicebox
            text_field.setText("");
        }
    }
}
