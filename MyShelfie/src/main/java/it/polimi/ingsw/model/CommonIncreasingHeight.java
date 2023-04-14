package it.polimi.ingsw.model;

public class CommonIncreasingHeight extends CommonGoalCard {        //the algorithm works, the method of the matrix must be modified
    public boolean check(Bookshelf bookshelf) {                               //it can be optimized
        boolean direction = true;
        if (bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL) && bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL)){

            for (int x=1; x<6; x++)
                if (bookshelf.getMatrix()[x][x-1].getType().equals(TileType.NULL)){
                    direction=false;
                    break;
                }

            if (direction){
                for (int x=0; x<5; x++)
                    if (!bookshelf.getMatrix()[x][x].getType().equals(TileType.NULL))
                        return false;
                return true;
            }

            for (int x=1; x<6; x++)
                if (!bookshelf.getMatrix()[x][5-x].getType().equals(TileType.NULL))
                    return false;

            for (int x=0; x<5; x++)
                if (bookshelf.getMatrix()[x][4-x].getType().equals(TileType.NULL))
                    return false;

            return true;

        } else {

            for (int x=1; x<5; x++)
                if (bookshelf.getMatrix()[x][x].getType().equals(TileType.NULL)){
                    direction=false;
                    break;
                }

            if (direction){
                for (int x=0; x<4; x++)
                    if (!bookshelf.getMatrix()[x][x+1].getType().equals(TileType.NULL))
                        return false;
                return true;
            }

            for (int x=0; x<5; x++)
                if (bookshelf.getMatrix()[x][4-x].getType().equals(TileType.NULL))
                    return false;

            for (int x=3; x>=0; x--)
                if (!bookshelf.getMatrix()[x][3-x].getType().equals(TileType.NULL))
                    return false;

            return true;
        }
    }
}
