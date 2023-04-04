package it.polimi.ingsw.model;

/**
 * class that define a personal goal card
 * @author Chiara Nguyen Ba
 */
public class PersonalGoalCard {
    private final Tile[][] matrix;
    private final PersonalGoalCardType type;
    private final Player player;
    private static final int ROW = 6;
    private static final int COL = 5;

    /**
     * constructor of personal goal card
     * @author Chiara Nguyen Ba
     */
    public PersonalGoalCard(PersonalGoalCardType type, Player player) {
        this.matrix = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.matrix[i][j] = new Tile(TileType.NULL);
            }
        }
        this.type = type;
        this.assignType();
        this.player = player;
    }

    /**
     * getter of matrix
     * @author Chiara Nguyen Ba
     */
    public Tile[][] getMatrix() {
        return matrix;
    }

    /**
     * getter of type
     * @author Chiara Nguyen Ba
     */
    public PersonalGoalCardType getType() {
        return type;
    }

    /**
     * assign the type of personal goal card
     * @author Chiara Nguyen Ba
     */
    private void assignType() {
        if (this.type == PersonalGoalCardType.GOAL1) {
            this.matrix[0][0].setType(TileType.PLANT);
            this.matrix[3][1].setType(TileType.GAME);
            this.matrix[0][2].setType(TileType.FRAME);
            this.matrix[5][2].setType(TileType.TROPHY);
            this.matrix[2][3].setType(TileType.BOOK);
            this.matrix[1][4].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL2) {
            this.matrix[1][1].setType(TileType.PLANT);
            this.matrix[2][2].setType(TileType.GAME);
            this.matrix[5][4].setType(TileType.FRAME);
            this.matrix[4][3].setType(TileType.TROPHY);
            this.matrix[3][4].setType(TileType.BOOK);
            this.matrix[2][0].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL3) {
            this.matrix[2][2].setType(TileType.PLANT);
            this.matrix[1][3].setType(TileType.GAME);
            this.matrix[1][0].setType(TileType.FRAME);
            this.matrix[3][4].setType(TileType.TROPHY);
            this.matrix[5][0].setType(TileType.BOOK);
            this.matrix[3][1].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL4) {
            this.matrix[3][3].setType(TileType.PLANT);
            this.matrix[0][4].setType(TileType.GAME);
            this.matrix[2][2].setType(TileType.FRAME);
            this.matrix[2][0].setType(TileType.TROPHY);
            this.matrix[4][1].setType(TileType.BOOK);
            this.matrix[4][2].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL5) {
            this.matrix[4][4].setType(TileType.PLANT);
            this.matrix[5][0].setType(TileType.GAME);
            this.matrix[3][1].setType(TileType.FRAME);
            this.matrix[1][1].setType(TileType.TROPHY);
            this.matrix[3][2].setType(TileType.BOOK);
            this.matrix[5][3].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL6) {
            this.matrix[5][0].setType(TileType.PLANT);
            this.matrix[4][1].setType(TileType.GAME);
            this.matrix[4][3].setType(TileType.FRAME);
            this.matrix[0][2].setType(TileType.TROPHY);
            this.matrix[2][3].setType(TileType.BOOK);
            this.matrix[0][4].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL7) {
            this.matrix[2][1].setType(TileType.PLANT);
            this.matrix[4][4].setType(TileType.GAME);
            this.matrix[1][3].setType(TileType.FRAME);
            this.matrix[3][0].setType(TileType.TROPHY);
            this.matrix[5][2].setType(TileType.BOOK);
            this.matrix[0][0].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL8) {
            this.matrix[3][0].setType(TileType.PLANT);
            this.matrix[5][3].setType(TileType.GAME);
            this.matrix[0][4].setType(TileType.FRAME);
            this.matrix[2][2].setType(TileType.TROPHY);
            this.matrix[4][3].setType(TileType.BOOK);
            this.matrix[1][1].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL9) {
            this.matrix[4][4].setType(TileType.PLANT);
            this.matrix[0][2].setType(TileType.GAME);
            this.matrix[5][0].setType(TileType.FRAME);
            this.matrix[4][1].setType(TileType.TROPHY);
            this.matrix[3][4].setType(TileType.BOOK);
            this.matrix[2][2].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL10) {
            this.matrix[5][3].setType(TileType.PLANT);
            this.matrix[1][1].setType(TileType.GAME);
            this.matrix[4][1].setType(TileType.FRAME);
            this.matrix[0][4].setType(TileType.TROPHY);
            this.matrix[2][0].setType(TileType.BOOK);
            this.matrix[3][3].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL11) {
            this.matrix[0][2].setType(TileType.PLANT);
            this.matrix[2][0].setType(TileType.GAME);
            this.matrix[3][2].setType(TileType.FRAME);
            this.matrix[5][3].setType(TileType.TROPHY);
            this.matrix[1][1].setType(TileType.BOOK);
            this.matrix[4][4].setType(TileType.CAT);
        }
        if (this.type == PersonalGoalCardType.GOAL12) {
            this.matrix[1][1].setType(TileType.PLANT);
            this.matrix[4][4].setType(TileType.GAME);
            this.matrix[2][2].setType(TileType.FRAME);
            this.matrix[3][3].setType(TileType.TROPHY);
            this.matrix[0][2].setType(TileType.BOOK);
            this.matrix[5][0].setType(TileType.CAT);
        }
    }

    /**
     * check the score of personal goal card
     * @author Chiara Nguyen Ba
     */
    public int check() {
        int correct = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (this.getMatrix()[i][j].getType() != TileType.NULL && this.getMatrix()[i][j].getType() == this.player.getBookshelf().getMatrix()[i][j].getType()) {
                    correct++;
                }
            }
        }
        return switch (correct) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 9;
            case 6 -> 12;
            default -> -1;
        };
    }
}