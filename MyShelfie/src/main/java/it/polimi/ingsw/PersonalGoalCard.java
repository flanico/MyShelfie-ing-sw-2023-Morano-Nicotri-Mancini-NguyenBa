package it.polimi.ingsw;
import java.util.*;

import java.util.Random;

public class PersonalGoalCard {
    private final int[][] personalcard;
    private final Player player;

    public PersonalGoalCard(Player t) {
        this.personalcard = new int[6][2];
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int x = rand.nextInt(5);
            int y = rand.nextInt(6);
            this.personalcard[i][0] = x;
            this.personalcard[i][1] = y;
        }
        this.player = t;
    }

    //Check if the targets are matched
    public int check() {
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            if (this.player.getBookshelf().getBookshelf()[this.personalcard[i][0]][this.personalcard[i][1]].getType().equals(TileType.values()[i])) {
                counter++;
            }
        }
        switch (counter) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 6;
            case 5:
                return 9;
            case 6:
                return 12;
            default:
                return -1;
        }
    }
}

