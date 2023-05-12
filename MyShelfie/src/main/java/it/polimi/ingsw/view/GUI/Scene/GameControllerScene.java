package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.ErrorType;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


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
    @FXML
    ImageView shelf_00;
    @FXML
    ImageView shelf_01;
    @FXML
    ImageView shelf_02;
    @FXML
    ImageView shelf_03;
    @FXML
    ImageView shelf_04;
    @FXML
    ImageView shelf_10;
    @FXML
    ImageView shelf_11;
    @FXML
    ImageView shelf_12;
    @FXML
    ImageView shelf_13;
    @FXML
    ImageView shelf_14;
    @FXML
    ImageView shelf_20;
    @FXML
    ImageView shelf_21;
    @FXML
    ImageView shelf_22;
    @FXML
    ImageView shelf_23;
    @FXML
    ImageView shelf_24;
    @FXML
    ImageView shelf_30;
    @FXML
    ImageView shelf_31;
    @FXML
    ImageView shelf_32;
    @FXML
    ImageView shelf_33;
    @FXML
    ImageView shelf_34;
    @FXML
    ImageView shelf_40;
    @FXML
    ImageView shelf_41;
    @FXML
    ImageView shelf_42;
    @FXML
    ImageView shelf_43;
    @FXML
    ImageView shelf_44;
    @FXML
    ImageView shelf_50;
    @FXML
    ImageView shelf_51;
    @FXML
    ImageView shelf_52;
    @FXML
    ImageView shelf_53;
    @FXML
    ImageView shelf_54;
    @FXML
    Text boardError;
    @FXML
    Button column0;
    @FXML
    Button column1;
    @FXML
    Button column2;
    @FXML
    Button column3;
    @FXML
    Button column4;
    @FXML
    Button upButton;
    @FXML
    Button downButton;
    @FXML
    Text shelf_text;
    @FXML
    ImageView common_goal1;
    @FXML
    ImageView common_goal2;


    public Board board;
    List<Tile> SelectedTiles = new ArrayList<>();
    private boolean select_card_phase;
    PersonalGoalCard personalCard;
    List<CommonGoalCard> commonGoalCards;

    public Bookshelf shelf;

    public ErrorType error;
    private int selected_column = -1;



    public void initialize(){
       turn_text.setText("");
       boardError.setText("");
       shelf_text.setText("");
       cancel_button.setVisible(false);
       confirm_button.setVisible(false);
       column0.setVisible(false);
       column1.setVisible(false);
       column2.setVisible(false);
       column3.setVisible(false);
       column4.setVisible(false);
       upButton.setVisible(false);
       downButton.setVisible(false);
       cancel_button.setDisable(true);
       confirm_button.setDisable(true);
       column0.setDisable(true);
       column1.setDisable(true);
       column2.setDisable(true);
       column3.setDisable(true);
       column4.setDisable(true);
       upButton.setDisable(true);
       downButton.setDisable(true);
       SelectedTiles.clear();
       select_card_phase=false;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setCommonGoalCards(List<CommonGoalCard> commonGoalCard) {
        this.commonGoalCards = commonGoalCard;
    }
    public void setPersonalCard(PersonalGoalCard personalCard) {
        this.personalCard = personalCard;
    }
    public void setShelf(Bookshelf shelf) {
        this.shelf = shelf;
    }
    public void setError(ErrorType error) {
        this.error = error;
    }
    public void activeSelection(){
        updateBoard();
        SelectedTiles.clear();
        sel_tile_1.setImage(null);
        sel_tile_2.setImage(null);
        sel_tile_3.setImage(null);
        turn_text.setText("It's your turn! Select your tiles:");
        select_card_phase=true;
        cancel_button.setVisible(true);
        confirm_button.setVisible(true);
        cancel_button.setDisable(false);
        confirm_button.setDisable(false);
    }
    private void rotate(String direction){
        if (SelectedTiles.size()==2)
            Collections.swap(SelectedTiles,0,1);
        else if (SelectedTiles.size()==3)
            if (direction.equals("down"))
                Collections.rotate(SelectedTiles, -1);
            else if (direction.equals("up"))
                Collections.rotate(SelectedTiles, 1);
    }
    public void activeShelf(){
        shelf_text.setText("Please select the column where to insert the tiles and decide their orders with the lateral arrows");
        column0.setVisible(true);
        column1.setVisible(true);
        column2.setVisible(true);
        column3.setVisible(true);
        column4.setVisible(true);
        column0.setDisable(false);
        column1.setDisable(false);
        column2.setDisable(false);
        column3.setDisable(false);
        column4.setDisable(false);

        if (SelectedTiles.size()>1){
            upButton.setVisible(true);
            downButton.setVisible(true);
            upButton.setDisable(false);
            downButton.setDisable(true);
        }
    }
    private void insertSelected(ImageView button, int x, int y) {
        boardError.setText("");
        Image img = button.getImage();
        ImageView sel = getButton(x, y);
        sel.setOpacity(0.5);
        Tile tile = new Tile(board.getMatrix()[x][y].getType(), x, y);
        if (!SelectedTiles.contains(tile)){
            SelectedTiles.add(tile);
            switch (SelectedTiles.size()) {
                case 1 -> sel_tile_1.setImage(img);
                case 2 -> sel_tile_2.setImage(img);
                case 3 -> sel_tile_3.setImage(img);
            }
        }
    }
    public void showMessage() {
        switch (error) {
            case WRONG_CHOICE -> {
                boardError.setText("You can't choose this tiles!");
            }
        }
    }
    public void updateBookShelf(){
        ImageView ref_shelf;
        for (int x=0; x<6; x++){
            for (int y=0; y<5; y++){
                ref_shelf = getShelf(x, y);
                if (ref_shelf != null)
                    setImage(ref_shelf, shelf.getMatrix()[x][y].getType());
            }
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
    public void updateCommonGoalCards(){
        String path = null;
        for (int x=1; x<3; x++) {
            switch (commonGoalCards.get(x-1).getNumber()) {
                case 1 -> path = "/common goal cards/1.jpg";
                case 2 -> path = "/common goal cards/2.jpg";
                case 3 -> path = "/common goal cards/3.jpg";
                case 4 -> path = "/common goal cards/4.jpg";
                case 5 -> path = "/common goal cards/5.jpg";
                case 6 -> path = "/common goal cards/6.jpg";
                case 7 -> path = "/common goal cards/7.jpg";
                case 8 -> path = "/common goal cards/8.jpg";
                case 9 -> path = "/common goal cards/9.jpg";
                case 10 -> path = "/common goal cards/10.jpg";
                case 11 -> path = "/common goal cards/11.jpg";
                case 12 -> path = "/common goal cards/12.jpg";
            }
            switch (x){
                case 1 -> common_goal1.setImage(new Image(path));
                case 2 -> common_goal2.setImage(new Image(path));
            }
        }
    }
    private void tryInsert(int col){
        ImageView img;
            for (int x=0; x< SelectedTiles.size(); x++) {
                img = getShelf(free_cells(col) - x - 1, col);
                setImage(img, SelectedTiles.get(x).getType());
            }
    }
    private int free_cells(int col){
        int row = 0;
        while (shelf.getMatrix()[row][col].getType().equals(TileType.NULL) && row<6) row++;
        return row;
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
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
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
    private ImageView getShelf(int x, int y){
        String concat = x + "," + y;
        return switch (concat){
            case "0,0" -> shelf_00;
            case "0,1" -> shelf_01;
            case "0,2" -> shelf_02;
            case "0,3" -> shelf_03;
            case "0,4" -> shelf_04;
            case "1,0" -> shelf_10;
            case "1,1" -> shelf_11;
            case "1,2" -> shelf_12;
            case "1,3" -> shelf_13;
            case "1,4" -> shelf_14;
            case "2,0" -> shelf_20;
            case "2,1" -> shelf_21;
            case "2,2" -> shelf_22;
            case "2,3" -> shelf_23;
            case "2,4" -> shelf_24;
            case "3,0" -> shelf_30;
            case "3,1" -> shelf_31;
            case "3,2" -> shelf_32;
            case "3,3" -> shelf_33;
            case "3,4" -> shelf_34;
            case "4,0" -> shelf_40;
            case "4,1" -> shelf_41;
            case "4,2" -> shelf_42;
            case "4,3" -> shelf_43;
            case "4,4" -> shelf_44;
            case "5,0" -> shelf_50;
            case "5,1" -> shelf_51;
            case "5,2" -> shelf_52;
            case "5,3" -> shelf_53;
            case "5,4" -> shelf_54;
            default -> null;
        };
    }
    private void setImage(ImageView ref_button, TileType type) {
        if (type.equals(TileType.NULL)){
            ref_button.setImage(null);
        } else {
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
            ref_button.setOpacity(1.0);
        }

    }
    public void confirmPressed(ActionEvent actionEvent) throws Exception{
        select_card_phase = false;
        cancel_button.setVisible(false);
        confirm_button.setVisible(false);
        cancel_button.setDisable(true);
        confirm_button.setDisable(true);
        sel_tile_1.setImage(null);
        sel_tile_2.setImage(null);
        sel_tile_3.setImage(null);
        turn_text.setText("");
        notifyObserver(obs -> obs.sendSelectTiles(SelectedTiles));
    }
    public void cancelPressed(ActionEvent actionEvent) throws Exception{
        int size = SelectedTiles.size();
        ImageView button;
        if (size>0) {

            switch (SelectedTiles.size()) {
                case 1 -> sel_tile_1.setImage(null);
                case 2 -> sel_tile_2.setImage(null);
                case 3 -> sel_tile_3.setImage(null);
            }
            button = getButton(SelectedTiles.get(size-1).getX(), SelectedTiles.get(size-1).getY());
            button.setOpacity(1.0);
            SelectedTiles.remove(size - 1);
        }
    }
    public void button_03_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(0,3), 0 , 3);
        }
        System.out.println("Button_03 clicked");
    }
    public void button_04_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(0,4), 0 , 4);
        }
        System.out.println("Button_04 clicked");
    }
    public void button_13_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,3), 1 , 3);
        }
        System.out.println("Button_13 clicked");
    }
    public void button_14_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,4), 1 , 4);
        }
        System.out.println("Button_14 clicked");
    }
    public void button_15_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,5), 1 , 5);
        }
        System.out.println("Button_03 clicked");
    }
    public void button_22_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,2), 2 , 2);
        }
        System.out.println("Button_22 clicked");
    }
    public void button_23_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,3), 2 , 3);
        }
        System.out.println("Button_23 clicked");
    }
    public void button_24_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,4), 2 , 4);
        }
        System.out.println("Button_24 clicked");
    }
    public void button_25_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,5), 2 , 5);
        }
        System.out.println("Button_25 clicked");
    }
    public void button_26_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,6), 2 , 6);
        }
        System.out.println("Button_26 clicked");
    }
    public void button_31_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,1), 3 , 1);
        }
        System.out.println("Button_31 clicked");
    }
    public void button_32_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,2), 3 , 2);
        }
        System.out.println("Button_32 clicked");
    }
    public void button_33_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,3), 3 , 3);
        }
        System.out.println("Button_33 clicked");
    }
    public void button_34_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,4), 3 , 4);
        }
        System.out.println("Button_34 clicked");
    }
    public void button_35_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,5), 3 , 5);
        }
        System.out.println("Button_35 clicked");
    }
    public void button_36_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,6), 3 , 6);
        }
        System.out.println("Button_36 clicked");
    }
    public void button_37_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,7), 3 , 7);
        }
        System.out.println("Button_37 clicked");
    }
    public void button_38_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][8].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,8), 3 , 8);
        }
        System.out.println("Button_38 clicked");
    }
    public void button_40_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,0), 4 , 0);
        }
        System.out.println("Button_40 clicked");
    }
    public void button_41_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4, 1), 4 , 1);
        }
        System.out.println("Button_41 clicked");
    }
    public void button_42_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,2), 4 , 2);
        }
        System.out.println("Button_42 clicked");
    }
    public void button_43_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,3), 4 , 3);
        }
        System.out.println("Button_43 clicked");
    }
    public void button_44_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,4), 4 , 4);
        }
        System.out.println("Button_44 clicked");
    }
    public void button_45_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,5), 4 , 5);
        }
        System.out.println("Button_45 clicked");
    }
    public void button_46_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,6), 4 , 6);
        }
        System.out.println("Button_46 clicked");
    }
    public void button_47_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,7), 4, 7);
        }
        System.out.println("Button_47 clicked");
    }
    public void button_48_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][8].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,8), 4 , 8);
        }
        System.out.println("Button_48 clicked");
    }
    public void button_50_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,0), 5 , 0);
        }
        System.out.println("Button_50 clicked");
    }
    public void button_51_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,1), 5 , 1);
        }
        System.out.println("Button_51 clicked");
    }
    public void button_52_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,2), 5 , 2);
        }
        System.out.println("Button_52 clicked");
    }
    public void button_53_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,3), 5 , 3);
        }
        System.out.println("Button_53 clicked");
    }
    public void button_54_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,4), 5 , 4);
        }
        System.out.println("Button_54 clicked");
    }
    public void button_55_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,5), 5 , 5);
        }
        System.out.println("Button_55 clicked");
    }
    public void button_56_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,6), 5 , 6);
        }
        System.out.println("Button_56 clicked");
    }
    public void button_57_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,7), 5 , 7);
        }
        System.out.println("Button_57 clicked");
    }
    public void button_62_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,2), 6 , 2);
        }
        System.out.println("Button_62 clicked");
    }
    public void button_63_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,3), 6 , 3);
        }
        System.out.println("Button_63 clicked");
    }
    public void button_64_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,4), 6 , 4);
        }
        System.out.println("Button_64 clicked");
    }
    public void button_65_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,5), 6 , 5);
        }
        System.out.println("Button_65 clicked");
    }
    public void button_66_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,6), 6 , 6);
        }
        System.out.println("Button_66 clicked");
    }
    public void button_73_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,3), 7 , 3);
        }
        System.out.println("Button_73 clicked");
    }
    public void button_74_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,4), 7 , 4);
        }
        System.out.println("Button_74 clicked");
    }
    public void button_75_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,5), 7 , 5);
        }
        System.out.println("Button_75 clicked");
    }
    public void button_84_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[8][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(8,4), 8 , 4);
        }
        System.out.println("Button_84 clicked");
    }
    public void button_85_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[8][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(8,5), 8 , 5);
        }
        System.out.println("Button_85 clicked");
    }
    public void downPressed(ActionEvent actionEvent) {
        rotate("down");
    }
    public void upPressed(ActionEvent actionEvent) {
        rotate("up");
    }

    public void colum0Pressed(ActionEvent actionEvent) {
        if (SelectedTiles.size()<=free_cells(0)) {
            updateBookShelf();
            selected_column = 0;
            tryInsert(0);
        }
    }

    public void column1Pressed(ActionEvent actionEvent) {
        if (SelectedTiles.size()<=free_cells(1)) {
            updateBookShelf();
            selected_column = 1;
            tryInsert(1);
        }
    }

    public void column2Pressed(ActionEvent actionEvent) {
        if (SelectedTiles.size()<=free_cells(2)) {
            updateBookShelf();
            selected_column = 2;
            tryInsert(2);
        }
    }

    public void column3Pressed(ActionEvent actionEvent) {
        if (SelectedTiles.size()<=free_cells(3)) {
            updateBookShelf();
            selected_column = 3;
            tryInsert(3);
        }
    }

    public void column4Pressed(ActionEvent actionEvent) {
        if (SelectedTiles.size()<=free_cells(4)) {
            updateBookShelf();
            selected_column = 4;
            tryInsert(4);
        }
    }
}
