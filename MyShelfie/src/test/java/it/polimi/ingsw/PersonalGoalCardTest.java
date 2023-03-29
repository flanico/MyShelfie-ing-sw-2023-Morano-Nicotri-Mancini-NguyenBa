package it.polimi.ingsw;

import org.junit.Test;

public class PersonalGoalCardTest {

    @Test
    public void assignPersonalCard() {
        PersonalGoalCard personal1 = new PersonalGoalCard(PersonalGoalCardType.GOAL1);
        personal1.assignPersonalCard(personal1.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal1.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal2 = new PersonalGoalCard(PersonalGoalCardType.GOAL2);
        personal2.assignPersonalCard(personal2.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal2.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal3 = new PersonalGoalCard(PersonalGoalCardType.GOAL3);
        personal3.assignPersonalCard(personal3.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal3.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal4 = new PersonalGoalCard(PersonalGoalCardType.GOAL4);
        personal4.assignPersonalCard(personal4.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal4.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal5 = new PersonalGoalCard(PersonalGoalCardType.GOAL5);
        personal5.assignPersonalCard(personal5.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal5.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal6 = new PersonalGoalCard(PersonalGoalCardType.GOAL6);
        personal6.assignPersonalCard(personal6.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal6.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal7 = new PersonalGoalCard(PersonalGoalCardType.GOAL7);
        personal7.assignPersonalCard(personal7.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal7.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal8 = new PersonalGoalCard(PersonalGoalCardType.GOAL8);
        personal8.assignPersonalCard(personal8.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal8.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal9 = new PersonalGoalCard(PersonalGoalCardType.GOAL9);
        personal9.assignPersonalCard(personal9.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal9.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal10 = new PersonalGoalCard(PersonalGoalCardType.GOAL10);
        personal10.assignPersonalCard(personal10.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal10.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal11 = new PersonalGoalCard(PersonalGoalCardType.GOAL11);
        personal11.assignPersonalCard(personal11.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal11.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        PersonalGoalCard personal12 = new PersonalGoalCard(PersonalGoalCardType.GOAL12);
        personal12.assignPersonalCard(personal12.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal12.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void numberOfMatch() {
        Player player = new Player("Pippo");
        //Create the bookshelf
        int[][] matrix = {
                {0, 0, 2, 0, 0},
                {2, 4, 0, 0, 1},
                {1, 2, 2, 4, 4},
                {0, 5, 3, 1, 2},
                {3, 4, 4, 4, 4},
                {2, 4, 3, 5, 3}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                switch (matrix[i][j]) {
                    case 0 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.PLANT);
                    case 1 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.CAT);
                    case 2 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.FRAME);
                    case 3 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.TROPHY);
                    case 4 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.BOOK);
                    case 5 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.GAME);
                }
            }
        }
        //Print the bookshelf
        System.out.println("The Bookshelf: ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(player.getBookshelf().getBookshelf()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();
        //Try PersonalGoalCards and see how many matches there are
        PersonalGoalCard personal1 = new PersonalGoalCard(PersonalGoalCardType.GOAL1);
        player.setPersonalgoalcard(personal1);
        System.out.println("Personal goal card " + personal1.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal1.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        int correct = player.getPersonalgoalcard().numberOfMatch(personal1, player.getBookshelf());
        System.out.println("Number of matches: " + correct);
        System.out.println();

        PersonalGoalCard personal2 = new PersonalGoalCard(PersonalGoalCardType.GOAL2);
        player.setPersonalgoalcard(personal2);
        System.out.println("Personal goal card " + personal2.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal2.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        int correct2 = player.getPersonalgoalcard().numberOfMatch(personal2, player.getBookshelf());
        System.out.println("Number of matches: " + correct2);
        System.out.println();

        PersonalGoalCard personal11 = new PersonalGoalCard(PersonalGoalCardType.GOAL11);
        player.setPersonalgoalcard(personal11);
        System.out.println("Personal goal card " + personal11.getType());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(personal11.getPersonalcard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();

        int correct11 = player.getPersonalgoalcard().numberOfMatch(personal11, player.getBookshelf());
        System.out.println("Number of matches: " + correct11);
        System.out.println();
    }

    @Test
    public void scorePersonalCard() {
        Player player = new Player("Pluto");
        PersonalGoalCard personal = new PersonalGoalCard(PersonalGoalCardType.GOAL4);
        player.setPersonalgoalcard(personal);
        int[][] matrix = {
                {0, 0, 2, 0, 0},
                {2, 4, 0, 0, 1},
                {1, 2, 2, 4, 4},
                {0, 5, 3, 1, 2},
                {3, 4, 4, 4, 4},
                {2, 4, 3, 5, 3}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                switch (matrix[i][j]) {
                    case 0 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.PLANT);
                    case 1 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.CAT);
                    case 2 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.FRAME);
                    case 3 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.TROPHY);
                    case 4 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.BOOK);
                    case 5 -> player.getBookshelf().getBookshelf()[i][j].setType(TileType.GAME);
                }
            }
        }
        System.out.println(player.getNickname() + " made " + personal.scorePersonalCard(personal.numberOfMatch(personal, player.getBookshelf()))
                + " points with PersonalGoalCard");
    }
}