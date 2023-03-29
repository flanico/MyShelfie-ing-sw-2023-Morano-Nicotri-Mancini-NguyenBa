package it.polimi.ingsw;

/**
 * class that define a personal goal card
 * @author Chiara Nguyen Ba
 */
public class PersonalGoalCard {
    private final Tile[][] matrix;
    private final PersonalGoalCardType type;
    private final int ROW = 6;
    private final int COL = 5;

    public PersonalGoalCard(PersonalGoalCardType type) {
        this.matrix = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.matrix[i][j] = new Tile(TileType.NULL);
            }
        }
        this.assignType(type);
        this.type = type;
    }

    public PersonalGoalCardType getType() {
        return type;
    }

    public Tile[][] getMatrix() {
        return matrix;
    }

    public void assignType(PersonalGoalCardType type) {
        if (type == PersonalGoalCardType.GOAL1) {
            this.matrix[0][0].setType(TileType.PLANT);
            this.matrix[3][1].setType(TileType.GAME);
            this.matrix[0][2].setType(TileType.FRAME);
            this.matrix[5][2].setType(TileType.TROPHY);
            this.matrix[2][3].setType(TileType.BOOK);
            this.matrix[1][4].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL2) {
            this.matrix[1][1].setType(TileType.PLANT);
            this.matrix[2][2].setType(TileType.GAME);
            this.matrix[5][4].setType(TileType.FRAME);
            this.matrix[4][3].setType(TileType.TROPHY);
            this.matrix[3][4].setType(TileType.BOOK);
            this.matrix[2][0].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL3) {
            this.matrix[2][2].setType(TileType.PLANT);
            this.matrix[1][3].setType(TileType.GAME);
            this.matrix[1][0].setType(TileType.FRAME);
            this.matrix[3][4].setType(TileType.TROPHY);
            this.matrix[5][0].setType(TileType.BOOK);
            this.matrix[3][1].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL4) {
            this.matrix[3][3].setType(TileType.PLANT);
            this.matrix[0][4].setType(TileType.GAME);
            this.matrix[2][2].setType(TileType.FRAME);
            this.matrix[2][0].setType(TileType.TROPHY);
            this.matrix[4][1].setType(TileType.BOOK);
            this.matrix[4][2].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL5) {
            this.matrix[4][4].setType(TileType.PLANT);
            this.matrix[5][0].setType(TileType.GAME);
            this.matrix[3][1].setType(TileType.FRAME);
            this.matrix[1][1].setType(TileType.TROPHY);
            this.matrix[3][2].setType(TileType.BOOK);
            this.matrix[5][3].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL6) {
            this.matrix[5][0].setType(TileType.PLANT);
            this.matrix[4][1].setType(TileType.GAME);
            this.matrix[4][3].setType(TileType.FRAME);
            this.matrix[0][2].setType(TileType.TROPHY);
            this.matrix[2][3].setType(TileType.BOOK);
            this.matrix[0][4].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL7) {
            this.matrix[2][1].setType(TileType.PLANT);
            this.matrix[4][4].setType(TileType.GAME);
            this.matrix[1][3].setType(TileType.FRAME);
            this.matrix[3][0].setType(TileType.TROPHY);
            this.matrix[5][2].setType(TileType.BOOK);
            this.matrix[0][0].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL8) {
            this.matrix[3][0].setType(TileType.PLANT);
            this.matrix[5][3].setType(TileType.GAME);
            this.matrix[0][4].setType(TileType.FRAME);
            this.matrix[2][2].setType(TileType.TROPHY);
            this.matrix[4][3].setType(TileType.BOOK);
            this.matrix[1][1].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL9) {
            this.matrix[4][4].setType(TileType.PLANT);
            this.matrix[0][2].setType(TileType.GAME);
            this.matrix[5][0].setType(TileType.FRAME);
            this.matrix[4][1].setType(TileType.TROPHY);
            this.matrix[3][4].setType(TileType.BOOK);
            this.matrix[2][2].setType(TileType.CAT);
        }
        if (type == PersonalGoalCardType.GOAL10) {
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
        if (type == PersonalGoalCardType.GOAL12) {
            this.matrix[1][1].setType(TileType.PLANT);
            this.matrix[4][4].setType(TileType.GAME);
            this.matrix[2][2].setType(TileType.FRAME);
            this.matrix[3][3].setType(TileType.TROPHY);
            this.matrix[0][2].setType(TileType.BOOK);
            this.matrix[5][0].setType(TileType.CAT);
        }
    }

    public int numberOfMatch(Bookshelf bookshelf) {
        int correct = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (this.getMatrix()[i][j].getType() != TileType.NULL &&
                        this.getMatrix()[i][j].getType() == bookshelf.getMatrix()[i][j].getType()){
                    System.out.println("Match in position x: " + i + " y: " + j + " found " + bookshelf.getMatrix()[i][j].getType());
                    correct++;
                }
            }
        }
        System.out.println("Number of correct macthes: " + correct);
        return correct;
    }

    //Calculate the score of PersonalGoalCard
    public int scorePersonalCard(int correct) {
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