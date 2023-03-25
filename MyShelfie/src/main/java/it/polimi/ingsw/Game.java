package it.polimi.ingsw;
import java.util.*;

public class Game {
    private int num;

    private ArrayList<Player> players;

    private ArrayList<CommonGoalCard> commongoalcards;

    private Board board;

    private Stack<Tile> bag;

    public int getNum() {
        return num;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Game(Board board) {
        this.board = board;
    }

    //Ask how many players plays
    public void howManyPlayers () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        this.num = scanner.nextInt();
        while (this.num > 4 || this.num < 2) {
            System.out.println("How many players?");
            this.num = scanner.nextInt();
        }
    }

    //Initialize players asking nickname
    public void initPlayers () {
        this.players = new ArrayList<Player>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.num; i++) {
            Player t = new Player(scanner.next());
            this.players.add(t);
        }
    }

    public ArrayList<CommonGoalCard> getCommongoalcards() {
        return commongoalcards;
    }

    //Initialize commongoalcard with 2 random cards
    public void initCommonGoalCard () {
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

    //initPersonalGoalCard

    public Board getBoard() {
        return board;
    }

    public Stack<Tile> getBag() {
        return bag;
    }

    //Create a stack (bag) that contains the tiles
    public void initBag() {
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

    //Fill the board with tiles picked from the bag
    public void fillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!this.board.getBoard()[i][j].isBlocked() && this.board.getBoard()[i][j].getType() == TileType.NULL) {
                    this.board.getBoard()[i][j] = this.bag.pop();
                    this.board.getBoard()[i][j].setX(i);
                    this.board.getBoard()[i][j].setY(j);
                }
            }
        }
    }
}
