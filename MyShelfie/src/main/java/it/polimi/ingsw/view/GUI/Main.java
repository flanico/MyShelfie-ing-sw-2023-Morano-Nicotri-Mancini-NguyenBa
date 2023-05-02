package it.polimi.ingsw.view.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/mainPanel.fxml"));
    primaryStage.setTitle("MyShelfie");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    }
}
