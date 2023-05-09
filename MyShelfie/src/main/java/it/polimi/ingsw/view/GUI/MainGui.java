package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.view.GUI.Scene.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * main of the Gui application
 */
public class MainGui extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Gui view = new Gui();
        ClientController clientController = new ClientController(view);
        view.addObserver(clientController);
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxml/menuPanel.fxml"));
        Parent rootLayout = loader.load();
        MenuController controller = loader.getController();
        controller.addObserver(clientController);

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        //stage.setWidth(1280d);
        //stage.setHeight(720d);
        stage.setResizable(false);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("");
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle("My Shelfie");
        stage.show();

    }
}
