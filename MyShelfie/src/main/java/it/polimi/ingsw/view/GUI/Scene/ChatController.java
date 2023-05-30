package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ChatController extends ViewObservable implements Controller{
    @FXML
    TextArea text_area;
    @FXML
    TextField text_field;

    public void initialize(){
        text_area.setEditable(false);
        text_area.setText("");
    }
    public void run(ArrayList<String> buffer){
        for (int x=0; x<buffer.size(); x++){
            text_area.appendText(buffer.get(x) + "\n");
        }
    }

    public void send(ActionEvent actionEvent) {
        if (!text_field.getText().equals("")){
            notifyObserver(obs -> obs.sendChatMessage("all", text_field.getText())); //TODO Implementa choicebox
            text_field.setText("");
        }
    }
}
