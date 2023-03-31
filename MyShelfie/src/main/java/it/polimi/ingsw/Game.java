package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a game
 * @author Alessandro Mancini
 */
public class Game {
    private int num;
    private ArrayList<Player> players;
    private ArrayList<PersonalGoalCard> personalgoalcards;
    private ArrayList<CommonGoalCard> commongoalcards;
    private ArrayList<CommonGoalCardScore> commongoalcardscores;
    private Board board;
    private Stack<Tile> bag;

    /**
     * constructor of Game
     * @author Alessandro Mancini
     */
    public Game(int num) {
        this.setNum(num);
        this.initPlayers();
        this.initPersonalgoalcards();
        this.initCommongoalcards();
        this.initCommongoalcardscores();
        this.initBoard();
        this.initBag();

        this.fillBoard();
    }

    /**
     * getter of num
     * @author Alessandro Mancini
     */
    public int getNum() {
        return num;
    }

    /**
     * setter of num
     * @author Alessandro Mancini
     */
    //Ask how many players plays
    private void setNum(int num) {
        this.num = num;
    }

    /**
     * initializer of players
     * @author Alessandro Mancini
     */
    //Initialize players asking nickname
    private void initPlayers() {
        this.players = new ArrayList<>();
        for (int i = 0; i < this.num; i++) {
            this.players.add(new Player());
        }
    }

    /**
     * getter of players
     * @author Alessandro Mancini
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * initializer of personalgoalcards with 2 random cards
     * @author Alessandro Mancini
     */
    private void initPersonalgoalcards() {
        this.personalgoalcards = new ArrayList<>();
        ArrayList<PersonalGoalCardType> used = new ArrayList<>();
        PersonalGoalCardType type;
        Random rand = new Random();

        for (int i = 0; i < this.num; i++) {
            do {
                type = PersonalGoalCardType.values()[rand.nextInt(PersonalGoalCardType.values().length)];
            } while (used.contains(type));
            used.add(type);
            this.personalgoalcards.add(new PersonalGoalCard(type, this.players.get(i)));
        }
    }

    /**
     * getter of personalgoalcards
     * @author Alessandro Mancini
     */
    public ArrayList<PersonalGoalCard> getPersonalgoalcards() {
        return personalgoalcards;
    }

    /**
     * initializer of commongoalcards with 2 random cards
     * @author Alessandro Mancini
     */
    private void initCommongoalcards() {
        this.commongoalcards = new ArrayList<>();
        Random rand = new Random();

        int id1 = rand.nextInt(12);
        CommonGoalCard c1 = typeCommongoalcards(id1);
        this.commongoalcards.add(c1);

        int id2 = rand.nextInt(12);
        while (id1 == id2) {
            id2 = rand.nextInt(12);
        }
        CommonGoalCard c2 = typeCommongoalcards(id2);
        this.commongoalcards.add(c2);
    }

    /**
     * help the initializer of commongoalcards, returning the correct type associated to the id
     * @param id random int for the switch
     * @author Alessandro Mancini
     */
    private CommonGoalCard typeCommongoalcards(int id) {
        return switch (id) {
            case 0 -> new CommonSixGroups();
            case 1 -> new CommonFourCorners();
            case 2 -> new CommonFourGroups();
            case 3 -> new CommonTwoSquares();
            case 4 -> new CommonMaxThreeColumns();
            case 5 -> new CommonEightSameType();
            case 6 -> new CommonMaxThreeRows();
            case 7 -> new CommonDifferentColumns();
            case 8 -> new CommonDifferentRows();
            case 9 -> new CommonXSameType();
            case 10 -> new CommonSameDiagonal();
            case 11 -> new CommonIncreasingHeight();
            default -> null;
        };
    }

    /**
     * getter of commongoalcards
     * @author Alessandro Mancini
     */
    public ArrayList<CommonGoalCard> getCommongoalcards() {
        return commongoalcards;
    }

    /**
     * initializer of commongoalcardscores
     * @author Alessandro Mancini
     */
    private void initCommongoalcardscores() {
        this.commongoalcardscores = new ArrayList<CommonGoalCardScore>();
        this.commongoalcardscores.add(new CommonGoalCardScore(this.num));
        this.commongoalcardscores.add(new CommonGoalCardScore(this.num));
    }

    /**
     * initializer of commonscores0
     * @author Alessandro Mancini
     */
    public ArrayList<CommonGoalCardScore> getCommongoalcardscores() {
        return commongoalcardscores;
    }

    /**
     * initializer of board
     * @author Alessandro Mancini
     */
    private void initBoard() {
        this.board = new Board(this.num);
    }

    /**
     * fill the board with tiles from the bag
     * @author Alessandro Mancini
     */
    public void fillBoard() {
        for (int i = 0; i < this.board.getROW(); i++) {
            for (int j = 0; j < this.board.getCOL(); j++) {
                if (!this.board.getMatrix()[i][j].isBlocked() && this.board.getMatrix()[i][j].getType() == TileType.NULL) {
                    this.board.getMatrix()[i][j].setType(this.bag.pop().getType());
                }
            }
        }
    }

    public boolean isBoardRefillable() {
        for (int i = 0; i < this.board.getROW(); i++) {
            for (int j = 0; j < this.board.getCOL(); j++) {
                if (!this.board.getMatrix()[i][j].isBlocked() && this.board.getMatrix()[i][j].getType() != TileType.NULL && !isTileIsolated(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTileIsolated(int row, int column) {
        if(row > 0 && this.board.getMatrix()[row - 1][column].getType() != TileType.NULL){
            return false;
        }
        if(row < 9 && this.board.getMatrix()[row + 1][column].getType() != TileType.NULL){
            return false;
        }
        if(column > 0 && this.board.getMatrix()[row][column - 1].getType() != TileType.NULL){
            return false;
        }
        if(column < 9 && this.board.getMatrix()[row][column + 1].getType() != TileType.NULL){
            return false;
        }
        return true;
    }

    /**
     * getter of board
     * @author Alessandro Mancini
     */
    public Board getBoard() {
        return board;
    }

    /**
     * initializer of bag
     * @author Alessandro Mancini
     */
    private void initBag() {
        this.bag = new Stack<Tile>();
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.CAT);
            this.bag.add(t);
        }
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.GAME);
            this.bag.add(t);
        }
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.BOOK);
            this.bag.add(t);
        }
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.PLANT);
            this.bag.add(t);
        }
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.TROPHY);
            this.bag.add(t);
        }
        for (int i = 0; i < 22; i++) {
            Tile t = new Tile(TileType.FRAME);
            this.bag.add(t);
        }
        Collections.shuffle(this.bag);
    }

    /**
     * getter of bag
     * @author Alessandro Mancini
     */
    public Stack<Tile> getBag() {
        return bag;
    }
}
