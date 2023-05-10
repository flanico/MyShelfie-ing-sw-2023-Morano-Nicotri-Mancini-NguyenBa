package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.TileType;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;


public class GameControllerScene extends ViewObservable implements GenericSceneController {
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
        updateBoard();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void updateBoard(){
        Button ref_but;
        for (int x=0; x<9; x++)
            for (int y = 0; y < 9; y++) {
                ref_but = getButton(x,y);
                if (ref_but != null)
                    if (board.getMatrix()[x][y].isBlocked()) {
                        ref_but.setVisible(false);
                        ref_but.setDisable(true);
                    } else {
                        setImage(ref_but, board.getMatrix()[x][y].getType());
                    }
            }
    }

    private Button getButton(int x, int y){
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

    private void setImage(Button ref_button, TileType type) {
       /* Image img = null;

        switch (type) {
            case CAT -> img = new ImageIcon("/item tiles/Gatti1.1.png").getImage();
            case PLANT -> img = new ImageIcon("/item tiles/Piante1.1.png").getImage();
            case BOOK -> img = new ImageIcon("/item tiles/Libri1.1.png").getImage();
            case FRAME -> img = new ImageIcon("/item tiles/Cornici1.1.png").getImage();
            case GAME -> img = new ImageIcon("/item tiles/Giochi1.1.png").getImage();
            case TROPHY -> img = new ImageIcon("/item tiles/Trofei1.1.png").getImage();
        }
        ImageView view = new ImageView(getClass().getClassLoader().getResource("/pawn.png"));

        ref_button.setGraphic(view);

    }*/
    }
}
