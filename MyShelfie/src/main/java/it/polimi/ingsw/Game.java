package it.polimi.ingsw;
import java.util.Random;

public class Game {
    private CommonGoalCard[] commongoalcard;

    private static CommonGoalCard[] initCommonGoalCard () {
        CommonGoalCard[] t = new CommonGoalCard[2];
        Random rand = new Random();

        int n1 = rand.nextInt(13);
        t[0] = new CommonGoalCard();
        t[0].setId(n1);

        int n2 = rand.nextInt(13);
        while (n1 == n2) {
            n2 = rand.nextInt(13);
        }
        t[1] = new CommonGoalCard();
        t[1].setId(n2);

        return t;
    }

    public static void main (String[] args) {
        Game game = new Game();
        game.commongoalcard = initCommonGoalCard();
        System.out.println(game.commongoalcard[0].getId());
        System.out.println(game.commongoalcard[1].getId());
    }
}
