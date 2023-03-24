package it.polimi.ingsw.model;

import it.polimi.ingsw.PersonalGoalCard;
import org.junit.Test;

public class PersonalGoalCardTest {

    @Test
    public void generateTargetsTest() {
        PersonalGoalCard personalGoalCard = new PersonalGoalCard();
        for (int k = 0; k < 3; k++) {
            personalGoalCard.init();
            System.out.println();
            personalGoalCard.generateTargets();
            for (int i = 0; i < personalGoalCard.getRow(); i++) {
                for (int j = 0; j < personalGoalCard.getColumn(); j++) {
                    System.out.print(personalGoalCard.getPersonalbookshelf()[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}