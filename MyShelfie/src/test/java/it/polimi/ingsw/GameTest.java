package it.polimi.ingsw;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initBagTest() {
        Game game = new Game(new Board(4));
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

            System.out.println("Il sacchetto contiene " + game.getBag().size() + " tessere");
            System.out.println(countGame + " tessere Game");
            System.out.println(countCat + " tessere Gatto");
            System.out.println(countTrophy + " tessere Trofeo");
            System.out.println(countPlant + " tessere Pianta");
            System.out.println(countFrame + " tessere Cornice");
            System.out.println(countBook + " tessere Libro");
            System.out.println();
        }
    }

    @Test
    public void fillBoardTest() {
        //Board for 2 players
        Board board2 = new Board(2);
        Game game2 = new Game(board2);
        game2.initBag();
        game2.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game2.getBoard().getBoard()[i][j].getTile().getType() + " ");
            }
            System.out.println();
        }

        System.out.println();

        //Board for 3 players
        Board board3 = new Board(3);
        Game game3 = new Game(board3);
        game3.initBag();
        game3.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game3.getBoard().getBoard()[i][j].getTile().getType() + " ");
            }
            System.out.println();
        }

        System.out.println();

        //Board for 4 players
        Board board4 = new Board(4);
        Game game4 = new Game(board4);
        game4.initBag();
        game4.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game4.getBoard().getBoard()[i][j].getTile().getType() + " ");
            }
            System.out.println();
        }
    }
}