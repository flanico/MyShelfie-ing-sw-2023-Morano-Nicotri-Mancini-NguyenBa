package it.polimi.ingsw;

import java.util.*;

public class PersonalGoalCard {
    private final TileType[][] personalbookshelf;
    private final int column;
    private final int row;
    private final Random rand = new Random();
    private final Map<Integer, Integer> personalscore;

    public PersonalGoalCard() {
        row = 6;
        column = 5;
        this.personalbookshelf = new TileType[row][column];
        this.personalscore = new HashMap<Integer,Integer>();
        personalscore.put(1,1);
        personalscore.put(2,2);
        personalscore.put(3,4);
        personalscore.put(4,6);
        personalscore.put(5,9);
        personalscore.put(6,12);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }


    public TileType[][] getPersonalbookshelf() {
        return personalbookshelf;
    }

    public void init() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                personalbookshelf[i][j] = null;
            }
        }
    }

    public void generateTargets(){
        for (int i = 0; i < 6; i++){
            int randRow, randCol;
            do {
                randRow = rand.nextInt(row);
                randCol = rand.nextInt(column);
            } while (personalbookshelf[randRow][randCol] != null);
            personalbookshelf[randRow][randCol] = TileType.values()[i];
        }
    }

    public int check(Bookshelf bookshelf, PersonalGoalCard personalGoalCard) {
        int numCorrect = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(personalGoalCard.getPersonalbookshelf()[i][j] != null){
                    if(personalGoalCard.getPersonalbookshelf()[i][j].equals(bookshelf.getBookshelf()[i][j])){
                        numCorrect++;
                    }
                }
            }
        }
        return numCorrect;
    }

    public int calculatePersonalScore(int numCorrect){
        return personalscore.get(numCorrect);
    }
}

