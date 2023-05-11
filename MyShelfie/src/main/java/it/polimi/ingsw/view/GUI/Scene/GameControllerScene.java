package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;


public class GameControllerScene extends ViewObservable implements Controller {

    @FXML
    ImageView button_03;
    @FXML
    ImageView button_04;
    @FXML
    ImageView button_13;
    @FXML
    ImageView button_14;
    @FXML
    ImageView button_15;
    @FXML
    ImageView button_22;
    @FXML
    ImageView button_23;
    @FXML
    ImageView button_24;
    @FXML
    ImageView button_25;
    @FXML
    ImageView button_26;
    @FXML
    ImageView button_31;
    @FXML
    ImageView button_32;
    @FXML
    ImageView button_33;
    @FXML
    ImageView button_34;
    @FXML
    ImageView button_35;
    @FXML
    ImageView button_36;
    @FXML
    ImageView button_37;
    @FXML
    ImageView button_38;
    @FXML
    ImageView button_40;
    @FXML
    ImageView button_41;
    @FXML
    ImageView button_42;
    @FXML
    ImageView button_43;
    @FXML
    ImageView button_44;
    @FXML
    ImageView button_45;
    @FXML
    ImageView button_46;
    @FXML
    ImageView button_47;
    @FXML
    ImageView button_48;
    @FXML
    ImageView button_50;
    @FXML
    ImageView button_51;
    @FXML
    ImageView button_52;
    @FXML
    ImageView button_53;
    @FXML
    ImageView button_54;
    @FXML
    ImageView button_55;
    @FXML
    ImageView button_56;
    @FXML
    ImageView button_57;
    @FXML
    ImageView button_62;
    @FXML
    ImageView button_63;
    @FXML
    ImageView button_64;
    @FXML
    ImageView button_65;
    @FXML
    ImageView button_66;
    @FXML
    ImageView button_73;
    @FXML
    ImageView button_74;
    @FXML
    ImageView button_75;
    @FXML
    ImageView button_84;
    @FXML
    ImageView button_85;
    @FXML
    ImageView personalCardImage;
    @FXML
    ImageView sel_tile_1;
    @FXML
    ImageView sel_tile_2;
    @FXML
    ImageView sel_tile_3;
    @FXML
    Text turn_text;
    @FXML
    Button cancel_button;
    @FXML
    Button confirm_button;
    Board board;

    List<Tile> SelectedTiles = new ArrayList<>();

    private boolean select_card_phase;

    PersonalGoalCard personalCard;
    List<CommonGoalCard> commonGoalCard;

    public void initialize(){
       turn_text.setText("");
       SelectedTiles.clear();
       select_card_phase=false;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCommonGoalCard(List<CommonGoalCard> commonGoalCard) {
        this.commonGoalCard = commonGoalCard;
    }

    public void setPersonalCard(PersonalGoalCard personalCard) {
        this.personalCard = personalCard;
    }

    public void activeSelection(){
        turn_text.setText("It's your turn! Select your tiles:");
        select_card_phase=true;
    }

    private void insertSelected(ImageView button, int x, int y){
        Image img = button.getImage();
        Tile tile = new Tile(board.getMatrix()[x][y].getType(), x, y);
        SelectedTiles.add(tile);
        switch (SelectedTiles.size()){
            case 1: sel_tile_1.setImage(img);
            case 2: sel_tile_2.setImage(img);
            case 3: sel_tile_3.setImage(img);
        }

    }

    public void updateBoard(){
        ImageView ref_but;
        for (int x=0; x<9; x++)
            for (int y = 0; y < 9; y++) {
                ref_but = getButton(x,y);
                if (ref_but != null && !board.getMatrix()[x][y].isBlocked())
                        setImage(ref_but, board.getMatrix()[x][y].getType());
            }
    }

    public void updateCommonGoalCard(){
       /* switch (commonGoalCard.get(0)) {

        }
            case*/
    }

    public void updatePersonalCard(){
        String path = null;
        switch (personalCard.getType()){
            case GOAL1 -> path = "/personal goal cards/Personal_Goals.png";
            case GOAL2 -> path = "/personal goal cards/Personal_Goals2.png";
            case GOAL3 -> path = "/personal goal cards/Personal_Goals3.png";
            case GOAL4 -> path = "/personal goal cards/Personal_Goals4.png";
            case GOAL5 -> path = "/personal goal cards/Personal_Goals5.png";
            case GOAL6 -> path = "/personal goal cards/Personal_Goals6.png";
            case GOAL7 -> path = "/personal goal cards/Personal_Goals7.png";
            case GOAL8 -> path = "/personal goal cards/Personal_Goals8.png";
            case GOAL9 -> path = "/personal goal cards/Personal_Goals9.png";
            case GOAL10 -> path = "/personal goal cards/Personal_Goals10.png";
            case GOAL11 -> path = "/personal goal cards/Personal_Goals11.png";
            case GOAL12 -> path = "/personal goal cards/Personal_Goals12.png";
        }
        Image img = new Image(getClass().getResourceAsStream(path));
        personalCardImage.setImage(img);
    }

    private ImageView getButton(int x, int y){
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

    private void setImage(ImageView ref_button, TileType type) {
        String path = "";

        switch (type) {
            case CAT -> path = "/item tiles/Gatti1.1.png";
            case PLANT -> path = "/item tiles/Piante1.1.png";
            case BOOK -> path = "/item tiles/Libri1.1.png";
            case FRAME -> path = "/item tiles/Cornici1.1.png";
            case GAME -> path = "/item tiles/Giochi1.1.png";
            case TROPHY -> path = "/item tiles/Trofei1.1.png";
        }

        Image img = new Image(getClass().getResourceAsStream(path));
        ref_button.setImage(img);

    }

    public void confirmPressed(ActionEvent actionEvent) throws Exception{
        select_card_phase = false;
        notifyObserver(obs -> obs.sendSelectTiles(SelectedTiles));
    }

    public void cancelPressed(ActionEvent actionEvent) throws Exception{
        int size = SelectedTiles.size();
        if (size>0) {
            SelectedTiles.remove(size - 1);
            switch (SelectedTiles.size()) {
                case 1:
                    sel_tile_1.setImage(null);
                case 2:
                    sel_tile_2.setImage(null);
                case 3:
                    sel_tile_3.setImage(null);
            }
        }
    }

    public void button_03_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(0,3), 0 , 3);
        }
        System.out.println("Button_03 clicked");
    }

    public void button_04_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(0,4), 0 , 4);
        }
        System.out.println("Button_04 clicked");
    }

    public void button_13_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(1,3), 1 , 3);
        }
        System.out.println("Button_13 clicked");
    }

    public void button_14_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(1,4), 1 , 4);
        }
        System.out.println("Button_14 clicked");
    }

    public void button_15_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][5].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(1,5), 1 , 5);
        }
        System.out.println("Button_03 clicked");
    }

    public void button_22_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][2].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(2,2), 2 , 2);
        }
        System.out.println("Button_22 clicked");
    }

    public void button_23_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(2,3), 2 , 3);
        }
        System.out.println("Button_23 clicked");
    }

    public void button_24_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(2,4), 2 , 4);
        }
        System.out.println("Button_24 clicked");
    }

    public void button_25_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][5].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(2,5), 2 , 5);
        }
        System.out.println("Button_25 clicked");
    }

    public void button_26_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][6].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(2,6), 2 , 6);
        }
        System.out.println("Button_26 clicked");
    }

    public void button_31_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][1].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,1), 3 , 1);
        }
        System.out.println("Button_31 clicked");
    }

    public void button_32_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][2].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,2), 3 , 2);
        }
        System.out.println("Button_32 clicked");
    }

    public void button_33_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,3), 3 , 3);
        }
        System.out.println("Button_33 clicked");
    }

    public void button_34_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,4), 3 , 4);
        }
        System.out.println("Button_34 clicked");
    }

    public void button_35_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][5].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,5), 3 , 5);
        }
        System.out.println("Button_35 clicked");
    }

    public void button_36_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][6].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,6), 3 , 6);
        }
        System.out.println("Button_36 clicked");
    }

    public void button_37_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][7].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,7), 3 , 7);
        }
        System.out.println("Button_37 clicked");
    }

    public void button_38_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][8].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(3,8), 3 , 8);
        }
        System.out.println("Button_38 clicked");
    }

    public void button_40_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,0), 4 , 0);
        }
        System.out.println("Button_40 clicked");
    }

    public void button_41_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][1].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4, 1), 4 , 1);
        }
        System.out.println("Button_41 clicked");
    }

    public void button_42_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,2), 4 , 2);
        }
        System.out.println("Button_42 clicked");
    }

    public void button_43_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][3].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,3), 4 , 3);
        }
        System.out.println("Button_43 clicked");
    }

    public void button_44_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,4), 4 , 4);
        }
        System.out.println("Button_44 clicked");
    }

    public void button_45_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][5].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,5), 4 , 5);
        }
        System.out.println("Button_45 clicked");
    }

    public void button_46_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][6].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,6), 4 , 6);
        }
        System.out.println("Button_46 clicked");
    }

    public void button_47_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][4].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,7), 4, 7);
        }
        System.out.println("Button_47 clicked");
    }

    public void button_48_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][8].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(4,8), 4 , 8);
        }
        System.out.println("Button_48 clicked");
    }

    public void button_50_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }

    public void button_51_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_52_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_53_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_54_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_55_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_56_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_57_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_62_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_63_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_64_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_65_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_66_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_73_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_74_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_75_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_84_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_85_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<=3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
}
