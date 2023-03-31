package it.polimi.ingsw;
import org.junit.Test;
import java.util.Iterator;

public class GameTest {

    @Test
    public void initBagTest() {
        Game game = new Game(3);
        for (int i = 0; i < 2; i++) {
            int count_game = 0;
            int count_cat = 0;
            int count_trophy = 0;
            int count_plant = 0;
            int count_frame = 0;
            int count_book = 0;

            Iterator<Tile> iterator = game.getBag().iterator();
            while (iterator.hasNext()) {
                TileType temp = iterator.next().getType();
                System.out.println(temp);
                if(temp == TileType.GAME){
                    count_game++;
                }
                if(temp == TileType.CAT){
                    count_cat++;
                }
                if(temp == TileType.TROPHY){
                    count_trophy++;
                }
                if(temp == TileType.PLANT){
                    count_plant++;
                }
                if(temp == TileType.FRAME){
                    count_frame++;
                }
                if(temp == TileType.BOOK){
                    count_book++;
                }
            }

            System.out.println("Il sacchetto contiene " + game.getBag().size() + " tessere");
            System.out.println(count_game + " tessere Game");
            System.out.println(count_cat + " tessere Gatto");
            System.out.println(count_trophy + " tessere Trofeo");
            System.out.println(count_plant + " tessere Pianta");
            System.out.println(count_frame + " tessere Cornice");
            System.out.println(count_book + " tessere Libro");
            System.out.println();
        }
    }

    @Test
    public void fillBoardTest() {
        Game game = new Game(2);
        game.getBoard().printMatrix();

        Board board3 = new Board(3);
        Game game3 = new Game();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game3.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }

        System.out.println();

        //Board for 4 players
        Board board4 = new Board(4);
        Game game4 = new Game();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game4.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void isNeedRefillTest() {
        Game game = new Game();
        //Board is constructed and all tiles are NULL
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
        game.refillBoard();
        //Case all tiles are NULL: Board is refilled
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }

        System.out.println();
        //Add NULL tiles in the board
        game.getBoard().getMatrix()[1][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[2][3].setType(TileType.NULL);
        game.getBoard().getMatrix()[2][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[2][5].setType(TileType.NULL);
        game.getBoard().getMatrix()[3][2].setType(TileType.NULL);
        game.getBoard().getMatrix()[3][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[3][5].setType(TileType.NULL);
        game.getBoard().getMatrix()[3][6].setType(TileType.NULL);
        game.getBoard().getMatrix()[4][2].setType(TileType.NULL);
        game.getBoard().getMatrix()[4][3].setType(TileType.NULL);
        //game.getBoard().getBoard()[4][5].setType(TileType.NULL);
        game.getBoard().getMatrix()[4][6].setType(TileType.NULL);
        game.getBoard().getMatrix()[4][7].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][1].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][2].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][3].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][5].setType(TileType.NULL);
        game.getBoard().getMatrix()[5][6].setType(TileType.NULL);
        game.getBoard().getMatrix()[6][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[7][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[7][5].setType(TileType.NULL);
        //Print initial Board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
        //Test if the board's refill is needed
        game.refillBoard();
        //Print resulted Board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }
}