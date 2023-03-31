package it.polimi.ingsw;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void isRemovableTest() {
        boolean removable;
        Game game = new Game(3);
//        game.initBag();
//        game.fillBoard();
        //Test for remove three tiles
        game.getBoard().getMatrix()[1][3] = new Tile(TileType.NULL);
        game.getBoard().getMatrix()[1][4] = new Tile(TileType.NULL);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(game.getBoard().getMatrix()[2][3].getType()));
        tiles.add(new Tile(game.getBoard().getMatrix()[2][4].getType()));
        tiles.add(new Tile(game.getBoard().getMatrix()[2][5].getType()));
        tiles.get(0).setX(2);
        tiles.get(0).setY(3);
        tiles.get(1).setX(2);
        tiles.get(1).setY(4);
        tiles.get(2).setX(2);
        tiles.get(2).setY(5);
//        tiles.add(new Tile(game.getBoard().getBoard()[3][4].getType()));
//        tiles.get(0).setX(3);
//        tiles.get(0).setY(4);
        System.out.println();
        for (Tile tile : tiles){
            System.out.println("La tessera numero " + tiles.indexOf(tile) + " scelta è " + tile.getType());
        }
        removable = game.getBoard().isRemovable(tiles);
//        System.out.println("Coordinata x di " + tiles.get(0).getType() + ": " + tiles.get(0).getX());
//        System.out.println("Coordinata y di " + tiles.get(0).getType() + ": " + tiles.get(0).getY());
//        System.out.println("Coordinata x di " + tiles.get(1).getType() + ": " + tiles.get(1).getX());
//        System.out.println("Coordinata y di " + tiles.get(1).getType() + ": " + tiles.get(1).getY());
//        System.out.println("Differenza cordinata y: " + Math.abs(tiles.get(0).getY() - tiles.get(1).getY()));
//        System.out.println("Differenza cordinata x: " + Math.abs(tiles.get(0).getX() - tiles.get(1).getX()));
        if(removable){
            System.out.println("Le tessere sono rimovibili");
        }
        else System.out.println("Le tessere NON sono rimovibili");
    }

    @Test
    public void removeTilesTest() {
        Game game = new Game(3);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(game.getBoard().getMatrix()[4][1].getType()));
        tiles.add(new Tile(game.getBoard().getMatrix()[5][1].getType()));
        //tiles.add(new Tile(game.getBoard().getBoard()[2][5].getType()));
        tiles.get(0).setX(4);
        tiles.get(0).setY(1);
        tiles.get(1).setX(5);
        tiles.get(1).setY(1);
        //tiles.get(2).setX(2);
        //tiles.get(2).setY(5);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
        System.out.println();
        game.getBoard().removeTiles(tiles);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(game.getBoard().getMatrix()[i][j].getType() + " ");
            }
            System.out.println();
        }
    }
}
