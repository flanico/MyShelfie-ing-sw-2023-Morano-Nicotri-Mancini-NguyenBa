package it.polimi.ingsw;



public class PersonalGoalCard {
    private final Tile[][] personalcard;
    private final PersonalGoalCardType type;
    private final int ROW = 6;
    private final int COL = 5;

    public PersonalGoalCard(PersonalGoalCardType type) {
        this.personalcard = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.personalcard[i][j] = new Tile(TileType.NULL);
            }
        }
        this.assignPersonalCard(type);
        this.type = type;
    }

    public PersonalGoalCardType getType() {
        return type;
    }

    public Tile[][] getPersonalcard() {
        return personalcard;
    }

    public void assignPersonalCard(PersonalGoalCardType type) {
        if(type == PersonalGoalCardType.GOAL1){
            this.personalcard[0][0].setType(TileType.PLANT);
            this.personalcard[3][1].setType(TileType.GAME);
            this.personalcard[0][2].setType(TileType.FRAME);
            this.personalcard[5][2].setType(TileType.TROPHY);
            this.personalcard[2][3].setType(TileType.BOOK);
            this.personalcard[1][4].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL2){
            this.personalcard[1][1].setType(TileType.PLANT);
            this.personalcard[2][2].setType(TileType.GAME);
            this.personalcard[5][4].setType(TileType.FRAME);
            this.personalcard[4][3].setType(TileType.TROPHY);
            this.personalcard[3][4].setType(TileType.BOOK);
            this.personalcard[2][0].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL3){
            this.personalcard[2][2].setType(TileType.PLANT);
            this.personalcard[1][3].setType(TileType.GAME);
            this.personalcard[1][0].setType(TileType.FRAME);
            this.personalcard[3][4].setType(TileType.TROPHY);
            this.personalcard[5][0].setType(TileType.BOOK);
            this.personalcard[3][1].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL4){
            this.personalcard[3][3].setType(TileType.PLANT);
            this.personalcard[0][4].setType(TileType.GAME);
            this.personalcard[2][2].setType(TileType.FRAME);
            this.personalcard[2][0].setType(TileType.TROPHY);
            this.personalcard[4][1].setType(TileType.BOOK);
            this.personalcard[4][2].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL5){
            this.personalcard[4][4].setType(TileType.PLANT);
            this.personalcard[5][0].setType(TileType.GAME);
            this.personalcard[3][1].setType(TileType.FRAME);
            this.personalcard[1][1].setType(TileType.TROPHY);
            this.personalcard[3][2].setType(TileType.BOOK);
            this.personalcard[5][3].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL6){
            this.personalcard[5][0].setType(TileType.PLANT);
            this.personalcard[4][1].setType(TileType.GAME);
            this.personalcard[4][3].setType(TileType.FRAME);
            this.personalcard[0][2].setType(TileType.TROPHY);
            this.personalcard[2][3].setType(TileType.BOOK);
            this.personalcard[0][4].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL7){
            this.personalcard[2][1].setType(TileType.PLANT);
            this.personalcard[4][4].setType(TileType.GAME);
            this.personalcard[1][3].setType(TileType.FRAME);
            this.personalcard[3][0].setType(TileType.TROPHY);
            this.personalcard[5][2].setType(TileType.BOOK);
            this.personalcard[0][0].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL8){
            this.personalcard[3][0].setType(TileType.PLANT);
            this.personalcard[5][3].setType(TileType.GAME);
            this.personalcard[0][4].setType(TileType.FRAME);
            this.personalcard[2][2].setType(TileType.TROPHY);
            this.personalcard[4][3].setType(TileType.BOOK);
            this.personalcard[1][1].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL9){
            this.personalcard[4][4].setType(TileType.PLANT);
            this.personalcard[0][2].setType(TileType.GAME);
            this.personalcard[5][0].setType(TileType.FRAME);
            this.personalcard[4][1].setType(TileType.TROPHY);
            this.personalcard[3][4].setType(TileType.BOOK);
            this.personalcard[2][2].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL10){
            this.personalcard[5][3].setType(TileType.PLANT);
            this.personalcard[1][1].setType(TileType.GAME);
            this.personalcard[4][1].setType(TileType.FRAME);
            this.personalcard[0][4].setType(TileType.TROPHY);
            this.personalcard[2][0].setType(TileType.BOOK);
            this.personalcard[3][3].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL11){
            this.personalcard[0][2].setType(TileType.PLANT);
            this.personalcard[2][0].setType(TileType.GAME);
            this.personalcard[3][2].setType(TileType.FRAME);
            this.personalcard[5][3].setType(TileType.TROPHY);
            this.personalcard[1][1].setType(TileType.BOOK);
            this.personalcard[4][4].setType(TileType.CAT);
        }
        if(type == PersonalGoalCardType.GOAL12){
            this.personalcard[1][1].setType(TileType.PLANT);
            this.personalcard[4][4].setType(TileType.GAME);
            this.personalcard[2][2].setType(TileType.FRAME);
            this.personalcard[3][3].setType(TileType.TROPHY);
            this.personalcard[0][2].setType(TileType.BOOK);
            this.personalcard[5][0].setType(TileType.CAT);
        }
    }

    //Count the number of matches made in the PersonalGoalCard
    public int numberOfMatch(PersonalGoalCard personal, Bookshelf bookshelf){
        int correct = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(personal.getPersonalcard()[i][j].getType() != TileType.NULL &&
                        personal.getPersonalcard()[i][j].getType() == bookshelf.getBookshelf()[i][j].getType()){
                    System.out.println("Match in position x: " + i + " y: " + j + " found " + bookshelf.getBookshelf()[i][j].getType());
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