package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.TileType;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;


public class GameController extends ViewObservable implements GenericSceneController {
    private String frase;

    @FXML
    Button button_03;
    @FXML
    Button button_04;
    @FXML
    Button button_13;
    @FXML
    Button button_14;
    @FXML
    Button button_15;
    @FXML
    Button button_22;
    @FXML
    Button button_23;
    @FXML
    Button button_24;
    @FXML
    Button button_25;
    @FXML
    Button button_26;
    @FXML
    Button button_31;
    @FXML
    Button button_32;
    @FXML
    Button button_33;
    @FXML
    Button button_34;
    @FXML
    Button button_35;
    @FXML
    Button button_36;
    @FXML
    Button button_37;
    @FXML
    Button button_38;
    @FXML
    Button button_40;
    @FXML
    Button button_41;
    @FXML
    Button button_42;
    @FXML
    Button button_43;
    @FXML
    Button button_44;
    @FXML
    Button button_45;
    @FXML
    Button button_46;
    @FXML
    Button button_47;
    @FXML
    Button button_48;
    @FXML
    Button button_50;
    @FXML
    Button button_51;
    @FXML
    Button button_52;
    @FXML
    Button button_53;
    @FXML
    Button button_54;
    @FXML
    Button button_55;
    @FXML
    Button button_56;
    @FXML
    Button button_57;
    @FXML
    Button button_62;
    @FXML
    Button button_63;
    @FXML
    Button button_64;
    @FXML
    Button button_65;
    @FXML
    Button button_66;
    @FXML
    Button button_73;
    @FXML
    Button button_74;
    @FXML
    Button button_75;
    @FXML
    Button button_84;
    @FXML
    Button button_85;
    Button reference;
    Board board;

    public void initialize(){
        for (int x=0; x<8; x++){
            for (int y=0; x<9; y++){
                reference = getButton(x,y);
                if (!reference.equals(null) && board.getMatrix()[x][y].isBlocked()){
                    reference.setVisible(false);
                }
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private Button getButton (int x, int y) {
        String concat = x + "," + y;
        return switch (concat) {
            case "0,3" -> button_03;
            case "0,4" -> button_04;
            case "1,3" -> button_13;
            case "1,4" -> button_14;
            case "1,5" -> button_15;
            case "2,2" -> button_22;
            case "2,3" -> button_23;
            case "2,4" -> button_24;
            case "2,5" -> button_25;
            case "2,6" -> button_26;
            case "3,1" -> button_31;
            case "3,2" -> button_32;
            case "3,3" -> button_33;
            case "3,4" -> button_34;
            case "3,5" -> button_35;
            case "3,6" -> button_36;
            case "3,7" -> button_37;
            case "3,8" -> button_38;
            case "4,0" -> button_40;
            case "4,1" -> button_41;
            case "4,2" -> button_42;
            case "4,3" -> button_43;
            case "4,4" -> button_44;
            case "4,5" -> button_45;
            case "4,6" -> button_46;
            case "4,7" -> button_47;
            case "4,8" -> button_48;
            case "5,0" -> button_50;
            case "5,1" -> button_51;
            case "5,2" -> button_52;
            case "5,3" -> button_53;
            case "5,4" -> button_54;
            case "5,5" -> button_55;
            case "5,6" -> button_56;
            case "5,7" -> button_57;
            case "6,2" -> button_62;
            case "6,3" -> button_63;
            case "6,4" -> button_64;
            case "6,5" -> button_65;
            case "6,6" -> button_66;
            case "7,3" -> button_73;
            case "7,4" -> button_74;
            case "7,5" -> button_75;
            case "8,4" -> button_84;
            case "8,5" -> button_85;
            default -> null;
        };
    }

    /*private ImageView getImage(TileType type){
        switch (type){
            case CAT:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
            case PLANT:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
            case BOOK:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
            case FRAME:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
            case GAME:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
            case TROPHY:
                Image path = new Image("UIControls/logo.png");
                ImageView imageView = new ImageView(path);
                break;
                };
        }
    }*/

}
