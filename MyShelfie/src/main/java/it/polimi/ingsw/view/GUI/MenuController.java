package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.lang.model.type.NullType;

public class MenuController extends ViewObservable {
    @FXML
    TextField serverPortField ;
    @FXML
    TextField serverIPField;
    @FXML
    TextField usernameField;
    @FXML
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private ChoiceBox<String> server_type_box;
    private Stage stage;
    private String correctIp;
    private String correctPort;

    private String correctName;

    public void joinGame(ActionEvent actionEvent) throws Exception {

        if (correctName()){
            if (correctIP()){
                if (correctPort()){
                    if (correctType()){
                        this.selectConnection();
                        notifyObserver(obs -> obs.sendNickname(correctName));
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gamePanel.fxml"));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else {
                        alert.setTitle("Errore");
                        alert.setHeaderText(null);
                        alert.setContentText("Select a server type");
                        alert.showAndWait();
                    }
                } else {
                    alert.setTitle("Errore");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Port");
                    alert.showAndWait();
                }
            } else {
                alert.setTitle("Errore");
                alert.setHeaderText(null);
                alert.setContentText("IP is invalid");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Insert username");
            alert.showAndWait();
        }

    }
    public void initialize(){
        System.out.println(server_type_box.getValue());
        server_type_box.getItems().addAll("Socket", "RMI");
    }

    boolean correctName(){
        String name=usernameField.getText();
        if (name.compareTo("")!=0){
            this.correctName=name;
            return true;
        } else return false;
    }
     boolean correctIP(){
         String inputIp=serverIPField.getText();

         if (inputIp.isEmpty()) {
             this.correctIp="localhost";
             return true;
         } else if (ClientController.isValidAddress(inputIp)) {
             this.correctIp=inputIp;
             return true;
         }  else return false;
     }

     boolean correctPort(){
         String inputPort=serverPortField.getText();

         if (inputPort.isEmpty()) {
             this.correctPort="12345";
             return true;
         } else if (ClientController.isValidPort(inputPort)) {
             this.correctPort=inputPort;
             return true;
         }  else return false;
     }

     boolean correctType(){
        if (server_type_box.getValue() != null)
            return true;
        else return false;
     }

    public void selectConnection() {
        if(server_type_box.getValue().compareTo("Socket")==0) {
            System.out.println("Socket connection...");
            notifyObserver(obs -> obs.createConnection(correctIp, correctPort));
        }
        else if (server_type_box.getValue().compareTo("RMI")==0) {
            System.out.println("RMI connection to do...");
            //Chiamo connessione RMI
        }
    }

}
