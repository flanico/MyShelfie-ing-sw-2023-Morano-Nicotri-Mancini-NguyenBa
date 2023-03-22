package it.polimi.ingsw;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    //Number of players
    private int num;

    //Array of Player
    private ArrayList<Player> players;

    //Array of CommonGoalCard
    private ArrayList<CommonGoalCard> commongoalcards;

    //Ask and initialize num
    private void howManyPlayers () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        this.num = scanner.nextInt();
        if (this.num > 4 || this.num < 2) {
            System.out.println("How many players?");
            this.num = scanner.nextInt();
        }
    }

    //Ask and initialize players
    private void initPlayers () {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < this.num; i++) {
            Player t = new Player("Ciao");
            this.players.add(t);
        }
    }

    //Initialize commongoalcard with 2 random cards
    private void initCommonGoalCard () {
        this.commongoalcards = new ArrayList<CommonGoalCard>();
        Random rand = new Random();

        int id1 = rand.nextInt(12);
        CommonGoalCard c1 = typeCommonGoalCard(id1);
        this.commongoalcards.add(c1);

        int id2 = rand.nextInt(12);
        while (id1 == id2) {
            id2 = rand.nextInt(12);
        }
        CommonGoalCard c2 = typeCommonGoalCard(id2);
        this.commongoalcards.add(c2);
    }

    private CommonGoalCard typeCommonGoalCard (int id) {
        if (id == 0) {
            return new CommonSixGroups();
        }
        if (id == 1) {
            return new CommonFourCorners();
        }
        if (id == 2) {
            return new CommonFourGroups();
        }
        if (id == 3) {
            return new CommonTwoSquares();
        }
        if (id == 4) {
            return new CommonMaxThreeColumns();
        }
        if (id == 5) {
            return new CommonEightSameType();
        }
        if (id == 6) {
            return new CommonMaxThreeRows();
        }
        if (id == 7) {
            return new CommonDifferentColumns();
        }
        if (id == 8) {
            return new CommonDifferentRows();
        }
        if (id == 9) {
            return new CommonXSameType();
        }
        if (id == 10) {
            return new CommonSameDiagonal();
        }
        if (id == 11) {
            return new CommonIncreasingHeight();
        }
        return null;
    }

    public static void main (String[] args) {
        Game game = new Game();
        game.howManyPlayers();
        //System.out.println(game.num);
        game.initPlayers();
        /*
        for (int i = 0; i < game.num; i++) {
            System.out.println(game.players.get(i).getNickname());

        }
        */
        game.initCommonGoalCard();
        //System.out.println(game.commongoalcard[0].getId());
        //System.out.println(game.commongoalcard[1].getId());
    }
}
