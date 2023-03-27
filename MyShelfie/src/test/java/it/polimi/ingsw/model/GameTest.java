package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.Iterator;

public class GameTest {

    @Test
    public void initBagTest() {
        Game game = new Game();
        //Check correct number of tiles in the bag and if the tiles are shuffled
        for (int i = 0; i < 2; i++) {
            int countGame = 0;
            int countCat = 0;
            int countTrophy = 0;
            int countPlant = 0;
            int countFrame = 0;
            int countBook = 0;

            game.initBag();
            Iterator<Tile> iterator = game.getBag().iterator();
            while (iterator.hasNext()) {
                TileType temp = iterator.next().getType();
                System.out.println(temp);
                if(temp == TileType.GAME){
                    countGame++;
                }
                if(temp == TileType.CAT){
                    countCat++;
                }
                if(temp == TileType.TROPHY){
                    countTrophy++;
                }
                if(temp == TileType.PLANT){
                    countPlant++;
                }
                if(temp == TileType.FRAME){
                    countFrame++;
                }
                if(temp == TileType.BOOK){
                    countBook++;
                }
            }

            System.out.println("The bag contains " + game.getBag().size() + " tiles");
            System.out.println(countGame + " Game tiles");
            System.out.println(countCat + " Cat tiles");
            System.out.println(countTrophy + " Trophy tiles");
            System.out.println(countPlant + " Plants tiles");
            System.out.println(countFrame + " Frame tiles");
            System.out.println(countBook + " Book tiles");
            System.out.println();
        }
    }

    @Test
    public void fillBoardTest() {
        //Board for 2 players
        System.out.println("Board for 2 players");
        Game game2 = new Game();
        game2.initBag();
        game2.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game2.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }

        System.out.println();

        //Board for 3 players
        System.out.println("Board for 3 players");
        Game game3 = new Game();
        game3.initBag();
        game3.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game3.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }

        System.out.println();

        //Board for 4 players
        System.out.println("Board for 4 players");
        Game game4 = new Game();
        game4.initBag();
        game4.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game4.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void isNeedRefill() {
        Game game = new Game();
        //Board is constructed and all tiles are NULL
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        game.refillBoard();
        //Case all tiles are NULL: Board is refilled
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }

        System.out.println();
        //Add NULL tiles in the board
        game.getBoard().getBoard()[1][4].setType(TileType.NULL);
        game.getBoard().getBoard()[2][3].setType(TileType.NULL);
        game.getBoard().getBoard()[2][4].setType(TileType.NULL);
        game.getBoard().getBoard()[2][5].setType(TileType.NULL);
        game.getBoard().getBoard()[3][2].setType(TileType.NULL);
        game.getBoard().getBoard()[3][4].setType(TileType.NULL);
        game.getBoard().getBoard()[3][5].setType(TileType.NULL);
        game.getBoard().getBoard()[3][6].setType(TileType.NULL);
        game.getBoard().getBoard()[4][2].setType(TileType.NULL);
        game.getBoard().getBoard()[4][3].setType(TileType.NULL);
        //game.getBoard().getBoard()[4][5].setType(TileType.NULL);
        game.getBoard().getBoard()[4][6].setType(TileType.NULL);
        game.getBoard().getBoard()[4][7].setType(TileType.NULL);
        game.getBoard().getBoard()[5][1].setType(TileType.NULL);
        game.getBoard().getBoard()[5][2].setType(TileType.NULL);
        game.getBoard().getBoard()[5][3].setType(TileType.NULL);
        game.getBoard().getBoard()[5][4].setType(TileType.NULL);
        game.getBoard().getBoard()[5][5].setType(TileType.NULL);
        game.getBoard().getBoard()[5][6].setType(TileType.NULL);
        game.getBoard().getBoard()[6][4].setType(TileType.NULL);
        game.getBoard().getBoard()[7][4].setType(TileType.NULL);
        game.getBoard().getBoard()[7][5].setType(TileType.NULL);
        //Print initial Board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }
        //Test if the board's refill is needed
        game.refillBoard();
        //Print resulted Board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getBoard()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }
}