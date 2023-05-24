package it.polimi.ingsw.view.GUI.Scene;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.GUI.ErrorType;
import it.polimi.ingsw.view.GUI.SceneController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javafx.scene.input.KeyCombination;


/**
 * This class is the controller of the game scene
 * It contains all the methods that are called by the view to update the scene
 * @author Stefano Morano
 */
public class GameControllerScene extends ViewObservable implements Controller {

    @FXML   //initialize the buttons of the board
    ImageView button_03, button_04, button_13, button_14, button_15, button_22, button_23, button_24, button_25, button_26, button_31, button_32, button_33, button_34, button_35, button_36, button_37, button_38, button_40, button_41, button_42, button_43, button_44, button_45, button_46, button_47, button_48, button_50, button_51, button_52, button_53, button_54, button_55, button_56, button_57, button_62, button_63, button_64, button_65, button_66, button_73, button_74, button_75, button_84, button_85;
    @FXML   //initialize the personal card image
    ImageView personalCardImage;
    @FXML   //initialize the selected card image
    ImageView sel_tile_1, sel_tile_2, sel_tile_3;
    @FXML
    ImageView turn_text;
    @FXML
    ImageView cancel_button;
    @FXML
    ImageView confirm_button;
    @FXML   //initialize the shelf image
    ImageView shelf_00, shelf_01, shelf_02, shelf_03, shelf_04, shelf_10, shelf_11, shelf_12, shelf_13, shelf_14, shelf_20, shelf_21, shelf_22, shelf_23, shelf_24, shelf_30, shelf_31, shelf_32, shelf_33, shelf_34, shelf_40, shelf_41, shelf_42, shelf_43, shelf_44, shelf_50, shelf_51, shelf_52, shelf_53, shelf_54;
    @FXML   //initialize the button of the column in the bookshelf
    ImageView column0, column1, column2, column3, column4;
    @FXML
    ImageView upButton, downButton, swap_button;
    @FXML
    ImageView shelf_text;
    @FXML   //initialize the common goal cards images
    ImageView common_goal1, common_goal2;
    @FXML
    ImageView okButton;
    @FXML   //initialize the common score images
    ImageView common_score_1, common_score_2;
    @FXML   //initialize the common prize images
    ImageView common_prize_1, common_prize_2, first_won, second_won, won_frame1, won_frame2;
    @FXML   //initialize the central bookshelf images
    ImageView shelf_00_c, shelf_01_c, shelf_02_c, shelf_03_c, shelf_04_c, shelf_10_c, shelf_11_c, shelf_12_c, shelf_13_c, shelf_14_c, shelf_20_c, shelf_21_c, shelf_22_c, shelf_23_c, shelf_24_c, shelf_30_c, shelf_31_c, shelf_32_c, shelf_33_c, shelf_34_c, shelf_40_c, shelf_41_c, shelf_42_c, shelf_43_c, shelf_44_c, shelf_50_c, shelf_51_c, shelf_52_c, shelf_53_c, shelf_54_c;
    @FXML   //initialize the upper bookshelf images
    ImageView shelf_00_s, shelf_01_s, shelf_02_s, shelf_03_s, shelf_04_s, shelf_10_s, shelf_11_s, shelf_12_s, shelf_13_s, shelf_14_s, shelf_20_s, shelf_21_s, shelf_22_s, shelf_23_s, shelf_24_s, shelf_30_s, shelf_31_s, shelf_32_s, shelf_33_s, shelf_34_s, shelf_40_s, shelf_41_s, shelf_42_s, shelf_43_s, shelf_44_s, shelf_50_s, shelf_51_s, shelf_52_s, shelf_53_s, shelf_54_s;
    @FXML   //initialize the down bookshelf images
    ImageView shelf_00_d, shelf_01_d, shelf_02_d, shelf_03_d, shelf_04_d, shelf_10_d, shelf_11_d, shelf_12_d, shelf_13_d, shelf_14_d, shelf_20_d, shelf_21_d, shelf_22_d, shelf_23_d, shelf_24_d, shelf_30_d, shelf_31_d, shelf_32_d, shelf_33_d, shelf_34_d, shelf_40_d, shelf_41_d, shelf_42_d, shelf_43_d, shelf_44_d, shelf_50_d, shelf_51_d, shelf_52_d, shelf_53_d, shelf_54_d;
    @FXML   //initialize the text of the names of the owner of the bookshelves
    Text owner_nickname_text, sx_text, dx_text, central_text;
    @FXML   //initialize the enemies' bookshelves
    ImageView bookshelf_s, bookshelf_c, bookshelf_d;
    @FXML   //initialize the seat image
    ImageView seat;
    @FXML
    ImageView frame1, frame2, frame3;

    public Board board;                             //the board of the game
    List<Tile> SelectedTiles = new ArrayList<>();   //the list of the tiles selected by the player
    List<Tile> finalTiles = new ArrayList<>();      //the list of the tiles that will be placed in the bookshelf
    private boolean select_card_phase;              //true if the player is in the select card phase
    private boolean shelf_phase;                    //true if the player is in the shelf phase
    PersonalGoalCard personalCard;                  //the personal goal card of the player
    List<CommonGoalCard> commonGoalCards;           //the list of the common goal cards of the game
    public Bookshelf shelf;
    public boolean isFirst = false;
    private int selected_column = -1;               //the column selected by the player
    List<CommonGoalCardScore> commonGoalCardScores = new ArrayList<>();         //the list of the common goal cards scores
    public Player currentPlayer = null;
    public Player owner;                                                        //the owner of the bookshelf
    public List<Player> playersList;                                            //the list of the players
    public int numberPlayers;                                                   //the number of the players in the game


    public void initialize(){
      KeyCombination keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
       SceneController.getActiveScene().setOnKeyPressed((event -> {
           if (keyCombination.match(event))
               Platform.runLater(() -> SceneController.popUp(ErrorType.EASTER_EGG));

       }));
       activeSelection(false);
       activeShelf(false);
       won_frame1.setVisible(false);
       won_frame2.setVisible(false);
       first_won.setVisible(false);
       second_won.setVisible(false);
       SelectedTiles.clear();
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
    public void setFinalTiles(List<Tile> finalTiles) {
        this.finalTiles = finalTiles;
    }
    public void setCommonGoalCardScores(List<CommonGoalCardScore> commonGoalCardScores){
        this.commonGoalCardScores = commonGoalCardScores;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }
    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }
    public void setFirst(boolean first) {
        isFirst = first;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * this method is called when the player has to select the tiles in the board to place in the bookshelf
     */
    public void activeSelection(boolean activate){
        if (activate) {
            updateBoard();
            SelectedTiles.clear();
            frame1.setVisible(true);
            frame2.setVisible(true);
            frame3.setVisible(true);
            sel_tile_1.setImage(null);
            sel_tile_2.setImage(null);
            sel_tile_3.setImage(null);
            turn_text.setVisible(true);
            select_card_phase = true;
            cancel_button.setVisible(true);
            confirm_button.setVisible(true);
        } else {
            frame1.setVisible(false);
            frame2.setVisible(false);
            frame3.setVisible(false);
            select_card_phase = false;
            sel_tile_1.setImage(null);
            sel_tile_2.setImage(null);
            sel_tile_3.setImage(null);
            turn_text.setVisible(false);
            cancel_button.setVisible(false);
            confirm_button.setVisible(false);
        }
    }

    /**
     * this method is called when the score of the common goal cards has to be updated
     */
    public void updateScores(){
        String path;
        for (int i = 0; i < 2; i++) {
            if (commonGoalCardScores.get(i).getStack().isEmpty())
                switch (i) {
                    case 0 -> common_score_1.setImage(null);
                    case 1 -> common_score_2.setImage(null);
                }
            else for (Integer score : commonGoalCardScores.get(i).getStack()) {
                        switch (score) {
                            case 2 -> path = "/scoring tokens/scoring_2.jpg";
                            case 4 -> path = "/scoring tokens/scoring_4.jpg";
                            case 6 -> path = "/scoring tokens/scoring_6.jpg";
                            case 8 -> path = "/scoring tokens/scoring_8.jpg";
                            default -> path = "";
                        }
                        if (i == 0)
                            common_score_1.setImage(new Image(path));
                        else common_score_2.setImage(new Image(path));
                }
        }
    }

    /**
     *  this method swaps the tiles in the bookshelf when the players click on the up or down button
     * @param direction to indicate if the player wants to swap the tiles 1/2 or 2/3
     */
    private void rotate(String direction){
        if (finalTiles.size()==2)
            Collections.swap(finalTiles,0,1);
        else if (finalTiles.size()==3)
            if (direction.equals("down"))
                Collections.swap(finalTiles, 0, 1);
            else if (direction.equals("up"))
                Collections.swap(finalTiles, 1, 2);
    }
    public void activeShelf(boolean activate){
        if (activate){
            shelf_phase=true;
            shelf_text.setVisible(true);
            column0.setVisible(true);
            column1.setVisible(true);
            column2.setVisible(true);
            column3.setVisible(true);
            column4.setVisible(true);
            okButton.setVisible(true);
            if (SelectedTiles.size()==2){
                swap_button.setVisible(true);
            } else if (SelectedTiles.size()==3){
                upButton.setVisible(true);
                downButton.setVisible(true);
            }
        } else {
            shelf_phase=false;
            shelf_text.setVisible(false);
            column0.setVisible(false);
            column1.setVisible(false);
            column2.setVisible(false);
            column3.setVisible(false);
            column4.setVisible(false);
            okButton.setVisible(false);
            upButton.setVisible(false);
            downButton.setVisible(false);
            swap_button.setVisible(false);
        }

    }
    private void insertSelected(ImageView button, int x, int y) {
        Image img = button.getImage();
        ImageView sel = getButton(x, y);
        sel.setOpacity(0.5);
        Tile tile = board.getMatrix()[x][y];
        if (!SelectedTiles.contains(tile) && !tile.getType().equals(TileType.NULL)){
            SelectedTiles.add(tile);
            switch (SelectedTiles.size()) {
                case 1 -> sel_tile_1.setImage(img);
                case 2 -> sel_tile_2.setImage(img);
                case 3 -> sel_tile_3.setImage(img);
            }
        }
    }
    public void updateBookShelf(Player pl){
        ImageView ref_shelf;
        String selector = null;
        if (pl.getNickname().equals(owner_nickname_text.getText()))
                selector = "main";
        else if (pl.getNickname().equals(sx_text.getText()))
                selector = "sx";
            else if (pl.getNickname().equals(dx_text.getText()))
                    selector = "dx";
                 else if (pl.getNickname().equals(central_text.getText()))
                    selector = "central";

        for (int x=0; x<6; x++){
            for (int y=0; y<5; y++){
                assert selector != null;
                ref_shelf = getShelf(x, y, selector);
                if (ref_shelf != null)
                    setImage(ref_shelf, pl.getBookshelf().getMatrix()[x][y].getType(), pl.getBookshelf().getMatrix()[x][y].getColortype());
            }
        }
    }
    public void updateBoard(){
        ImageView ref_but;
        for (int x=0; x<9; x++)
            for (int y = 0; y < 9; y++) {
                ref_but = getButton(x,y);
                if (ref_but != null && !board.getMatrix()[x][y].isBlocked())
                        setImage(ref_but, board.getMatrix()[x][y].getType(), board.getMatrix()[x][y].getColortype());
            }
    }
    public void winCard(CommonGoalCard commonGoalCard, int score){
        String path;
        switch (score){
            case 2 -> path = "/scoring tokens/scoring_2.jpg";
            case 4 -> path = "/scoring tokens/scoring_4.jpg";
            case 6 -> path = "/scoring tokens/scoring_6.jpg";
            case 8 -> path = "/scoring tokens/scoring_8.jpg";
            default -> path = "";
        }
        if ( commonGoalCards.get(0).getNumber() == commonGoalCard.getNumber() ){
            common_prize_1.setImage(new Image(path));
            won_frame1.setVisible(true);
            first_won.setVisible(true);
        } else {
            won_frame2.setVisible(true);
            second_won.setVisible(true);
            common_prize_2.setImage(new Image(path));
        }
    }
    public void updateCommonGoalCards(){
        String path;
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
                default -> path = "";
            }
            switch (x){
                case 1 -> common_goal1.setImage(new Image(path));
                case 2 -> common_goal2.setImage(new Image(path));
            }
        }
    }
    public void initGame(){
        while (!playersList.get(0).getNickname().equals(owner.getNickname()))
            Collections.rotate(playersList, -1);
        owner_nickname_text.setText(owner.getNickname());
        if (isFirst)
            seat.setImage(new Image("/misc/firstplayertoken.png"));
        switch (numberPlayers){
            case 2 ->{
                bookshelf_c.setImage(new Image("/boards/bookshelf.png"));
                central_text.setText(playersList.get(1).getNickname());
                sx_text.setText("");
                dx_text.setText("");
            }
            case 3 -> {
                bookshelf_s.setImage(new Image("/boards/bookshelf.png"));
                bookshelf_d.setImage(new Image("/boards/bookshelf.png"));
                sx_text.setText(playersList.get(1).getNickname());
                dx_text.setText(playersList.get(2).getNickname());
                central_text.setText("");
            }
            case 4 -> {
                bookshelf_s.setImage(new Image("/boards/bookshelf.png"));
                bookshelf_d.setImage(new Image("/boards/bookshelf.png"));
                bookshelf_c.setImage(new Image("/boards/bookshelf.png"));
                sx_text.setText(playersList.get(1).getNickname());
                central_text.setText(playersList.get(2).getNickname());
                dx_text.setText(playersList.get(3).getNickname());
            }
        }
    }
    private void tryInsert(int col){
        ImageView img;
        for (int x=0; x<finalTiles.size(); x++){
            img = getShelf(free_cells(col) - 1 - x, col, "main");
            setImage(img, finalTiles.get(x).getType(), finalTiles.get(x).getColortype());
            img.setOpacity(0.5);
        }
    }
    /**
     * @param col is the column of the shelf
     * @return the number of free cells in the column
     */
    private int free_cells(int col) {
        int empty=0;
        for (int i = 0; i < 6; i++) {
            if (shelf.getMatrix()[i][col].getType().equals(TileType.NULL))
                empty++;
        }
        return empty;
    }

    /**
     * This method is used to update the personal goal card image
     */
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

    /**
     * This method returns the ImageView of the tiles in the position (x,y) in the board
     * @param x row of the tile in the board
     * @param y column of the tile in the board
     * @return the ImageView of the tile in the position (x,y) in the board
     */
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
    /**
     * This method returns the ImageView of the tiles in the position (x,y) in the bookshelf called shelf_name
     * @param x row of the tile in the bookshelf
     * @param y column of the tile in the bookshelf
     * @param shelf_name name of the bookshelf (main, sx, dx)
     * @return the ImageView of the tile in the position (x,y) in the bookshelf called shelf_name
     */
    private ImageView getShelf(int x, int y, String shelf_name){
        String concat = x + "," + y;
        return switch (shelf_name){
            case "main" -> switch (concat) {
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
            case "dx" -> switch (concat) {
                case "0,0" -> shelf_00_d;
                case "0,1" -> shelf_01_d;
                case "0,2" -> shelf_02_d;
                case "0,3" -> shelf_03_d;
                case "0,4" -> shelf_04_d;
                case "1,0" -> shelf_10_d;
                case "1,1" -> shelf_11_d;
                case "1,2" -> shelf_12_d;
                case "1,3" -> shelf_13_d;
                case "1,4" -> shelf_14_d;
                case "2,0" -> shelf_20_d;
                case "2,1" -> shelf_21_d;
                case "2,2" -> shelf_22_d;
                case "2,3" -> shelf_23_d;
                case "2,4" -> shelf_24_d;
                case "3,0" -> shelf_30_d;
                case "3,1" -> shelf_31_d;
                case "3,2" -> shelf_32_d;
                case "3,3" -> shelf_33_d;
                case "3,4" -> shelf_34_d;
                case "4,0" -> shelf_40_d;
                case "4,1" -> shelf_41_d;
                case "4,2" -> shelf_42_d;
                case "4,3" -> shelf_43_d;
                case "4,4" -> shelf_44_d;
                case "5,0" -> shelf_50_d;
                case "5,1" -> shelf_51_d;
                case "5,2" -> shelf_52_d;
                case "5,3" -> shelf_53_d;
                case "5,4" -> shelf_54_d;
                default -> null;
            };
            case "sx" -> switch (concat) {
                case "0,0" -> shelf_00_s;
                case "0,1" -> shelf_01_s;
                case "0,2" -> shelf_02_s;
                case "0,3" -> shelf_03_s;
                case "0,4" -> shelf_04_s;
                case "1,0" -> shelf_10_s;
                case "1,1" -> shelf_11_s;
                case "1,2" -> shelf_12_s;
                case "1,3" -> shelf_13_s;
                case "1,4" -> shelf_14_s;
                case "2,0" -> shelf_20_s;
                case "2,1" -> shelf_21_s;
                case "2,2" -> shelf_22_s;
                case "2,3" -> shelf_23_s;
                case "2,4" -> shelf_24_s;
                case "3,0" -> shelf_30_s;
                case "3,1" -> shelf_31_s;
                case "3,2" -> shelf_32_s;
                case "3,3" -> shelf_33_s;
                case "3,4" -> shelf_34_s;
                case "4,0" -> shelf_40_s;
                case "4,1" -> shelf_41_s;
                case "4,2" -> shelf_42_s;
                case "4,3" -> shelf_43_s;
                case "4,4" -> shelf_44_s;
                case "5,0" -> shelf_50_s;
                case "5,1" -> shelf_51_s;
                case "5,2" -> shelf_52_s;
                case "5,3" -> shelf_53_s;
                case "5,4" -> shelf_54_s;
                default -> null;
            };
            case "central" -> switch (concat) {
                case "0,0" -> shelf_00_c;
                case "0,1" -> shelf_01_c;
                case "0,2" -> shelf_02_c;
                case "0,3" -> shelf_03_c;
                case "0,4" -> shelf_04_c;
                case "1,0" -> shelf_10_c;
                case "1,1" -> shelf_11_c;
                case "1,2" -> shelf_12_c;
                case "1,3" -> shelf_13_c;
                case "1,4" -> shelf_14_c;
                case "2,0" -> shelf_20_c;
                case "2,1" -> shelf_21_c;
                case "2,2" -> shelf_22_c;
                case "2,3" -> shelf_23_c;
                case "2,4" -> shelf_24_c;
                case "3,0" -> shelf_30_c;
                case "3,1" -> shelf_31_c;
                case "3,2" -> shelf_32_c;
                case "3,3" -> shelf_33_c;
                case "3,4" -> shelf_34_c;
                case "4,0" -> shelf_40_c;
                case "4,1" -> shelf_41_c;
                case "4,2" -> shelf_42_c;
                case "4,3" -> shelf_43_c;
                case "4,4" -> shelf_44_c;
                case "5,0" -> shelf_50_c;
                case "5,1" -> shelf_51_c;
                case "5,2" -> shelf_52_c;
                case "5,3" -> shelf_53_c;
                case "5,4" -> shelf_54_c;
                default -> null;
            };
            default -> null;
       };
    }
    private void setImage(ImageView ref_button, TileType type, int colortype) {
        if (type.equals(TileType.NULL)){
            ref_button.setImage(null);
        } else {
            String path ="/item tiles/";
            switch (type) {
                case CAT -> path += "Gatti1.";
                case PLANT -> path += "Piante1.";
                case BOOK -> path += "Libri1.";
                case FRAME -> path += "Cornici1.";
                case GAME -> path += "Giochi1.";
                case TROPHY -> path += "Trofei1.";
                default -> path = "";
            }
            switch (colortype){
                case 1 -> path += "1.png";
                case 2 -> path += "2.png";
                case 3 -> path += "3.png";
            }
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            ref_button.setImage(img);
            ref_button.setOpacity(1.0);
        }
    }
    public void confirmPressed(MouseEvent mouseEvent){
        if (select_card_phase) {
            if (SelectedTiles.size() != 0 && SelectedTiles.size() > shelf.maxTilesBookshelf()) {
                Platform.runLater(() -> SceneController.popUp(ErrorType.NOT_SPACE));
                activeSelection(true);
            } else if (SelectedTiles.size() != 0) {
                notifyObserver(obs -> obs.sendSelectTiles(SelectedTiles));
                activeSelection(false);
            }
        }
    }
    public void cancelPressed(MouseEvent mouseEvent){
        if (select_card_phase) {
            int size = SelectedTiles.size();
            ImageView button;
            if (size > 0) {
                switch (SelectedTiles.size()) {
                    case 1 -> sel_tile_1.setImage(null);
                    case 2 -> sel_tile_2.setImage(null);
                    case 3 -> sel_tile_3.setImage(null);
                }
                button = getButton(SelectedTiles.get(size - 1).getX(), SelectedTiles.get(size - 1).getY());
                button.setOpacity(1.0);
                SelectedTiles.remove(size - 1);
            }
        }
    }
    public void button_03_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(0,3), 0 , 3);
        }
    }
    public void button_04_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(0,4), 0 , 4);
        }
    }
    public void button_13_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,3), 1 , 3);
        }
    }
    public void button_14_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,4), 1 , 4);
        }
    }
    public void button_15_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[1][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(1,5), 1 , 5);
        }
    }
    public void button_22_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,2), 2 , 2);
        }
    }
    public void button_23_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,3), 2 , 3);
        }
    }
    public void button_24_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,4), 2 , 4);
        }
    }
    public void button_25_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,5), 2 , 5);
        }
    }
    public void button_26_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[2][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(2,6), 2 , 6);
        }
    }
    public void button_31_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,1), 3 , 1);
        }
    }
    public void button_32_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,2), 3 , 2);
        }
    }
    public void button_33_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,3), 3 , 3);
        }
    }
    public void button_34_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,4), 3 , 4);
        }
    }
    public void button_35_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,5), 3 , 5);
        }
    }
    public void button_36_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,6), 3 , 6);
        }
    }
    public void button_37_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,7), 3 , 7);
        }
    }
    public void button_38_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[3][8].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(3,8), 3 , 8);
        }
    }
    public void button_40_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[0][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,0), 4 , 0);
        }
    }
    public void button_41_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4, 1), 4 , 1);
        }
    }
    public void button_42_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,2), 4 , 2);
        }
    }
    public void button_43_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,3), 4 , 3);
        }
    }
    public void button_44_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,4), 4 , 4);
        }
    }
    public void button_45_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,5), 4 , 5);
        }
    }
    public void button_46_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,6), 4 , 6);
        }
    }
    public void button_47_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,7), 4, 7);
        }
    }
    public void button_48_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[4][8].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(4,8), 4 , 8);
        }
    }
    public void button_50_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][0].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,0), 5 , 0);
        }
    }
    public void button_51_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][1].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,1), 5 , 1);
        }
    }
    public void button_52_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,2), 5 , 2);
        }
    }
    public void button_53_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,3), 5 , 3);
        }
    }
    public void button_54_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,4), 5 , 4);
        }
    }
    public void button_55_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,5), 5 , 5);
        }
    }
    public void button_56_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,6), 5 , 6);
        }
    }
    public void button_57_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[5][7].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(5,7), 5 , 7);
        }
    }
    public void button_62_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][2].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,2), 6 , 2);
        }
    }
    public void button_63_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,3), 6 , 3);
        }
    }
    public void button_64_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,4), 6 , 4);
        }
    }
    public void button_65_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,5), 6 , 5);
        }
    }
    public void button_66_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[6][6].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(6,6), 6 , 6);
        }
    }
    public void button_73_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][3].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,3), 7 , 3);
        }
    }
    public void button_74_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,4), 7 , 4);
        }
    }
    public void button_75_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[7][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(7,5), 7 , 5);
        }
    }
    public void button_84_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[8][4].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(8,4), 8 , 4);
        }
    }
    public void button_85_click(MouseEvent mouseEvent) {
        if (select_card_phase && !board.getMatrix()[8][5].isBlocked() && SelectedTiles.size()<3){
            insertSelected(getButton(8,5), 8 , 5);
        }
    }
    public void downPressed(MouseEvent mouseEvent) {
        if (shelf_phase) {
            rotate("down");
            tryInsert(selected_column);
        }
    }
    public void upPressed(MouseEvent mouseEvent) {
        if (shelf_phase) {
            rotate("up");
            tryInsert(selected_column);
        }
    }
    public void column0Pressed(MouseEvent mouseEvent) {
        if (shelf_phase && finalTiles.size()<=free_cells(0) && selected_column!=0) {
            updateBookShelf(currentPlayer);
            selected_column = 0;
            tryInsert(0);
        }
    }
    public void column1Pressed(MouseEvent mouseEvent) {
        if (shelf_phase && finalTiles.size()<=free_cells(1) && selected_column!=1) {
            updateBookShelf(currentPlayer);
            selected_column = 1;
            tryInsert(1);
        }
    }
    public void column2Pressed(MouseEvent mouseEvent) {
        if (shelf_phase && finalTiles.size()<=free_cells(2) && selected_column!=2) {
            updateBookShelf(currentPlayer);
            selected_column = 2;
            tryInsert(2);
        }
    }
    public void column3Pressed(MouseEvent mouseEvent) {
        if (shelf_phase && finalTiles.size()<=free_cells(3) && selected_column!=3) {
            updateBookShelf(currentPlayer);
            selected_column = 3;
            tryInsert(3);
        }
    }
    public void column4Pressed(MouseEvent mouseEvent) {
        if (shelf_phase && finalTiles.size()<=free_cells(4) && selected_column!=4) {
            updateBookShelf(currentPlayer);
            selected_column = 4;
            tryInsert(4);
        }
    }
    public void okPressed(MouseEvent mouseEvent) {
        if (selected_column!=-1){
            activeShelf(false);
            notifyObserver(obs -> obs.sendInsertTiles(selected_column, finalTiles));
            selected_column=-1;
        }
    }
    public void sendFinalTiles() {
        notifyObserver(obs -> obs.sendOrderTiles(finalTiles));
    }

}
