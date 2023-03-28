package it.polimi.ingsw;
import java.util.*;

public class Game {
    private int num;
    private ArrayList<Integer> scores;  //Same order of players
    private ArrayList<Player> players;
    private ArrayList<CommonGoalCard> commongoalcards;
    private Stack<Integer> commonscores0;
    private Stack<Integer> commonscores1;
    private ArrayList<PersonalGoalCard> personalgoalcards;
    private Board board;
    private Stack<Tile> bag;


    public Game() {
        this.setNum();
        this.initPlayers();
        this.initCommongoalcards();
        this.initCommonscores();
        this.initPersonalgoalcards();
        this.initBoard();
        this.initBag();
        this.fillBoard();
    }


    public int getNum() {
        return num;
    }

    //Ask how many players plays
    private void setNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        this.num = scanner.nextInt();
        while (this.num > 4 || this.num < 2) {
            System.out.println("How many players?");
            this.num = scanner.nextInt();
        }
    }



    public ArrayList<Integer> getScores() {
        return scores;
    }



    //Initialize players asking nickname
    private void initPlayers() {
        this.players = new ArrayList<Player>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.num; i++) {
            Player t = new Player(scanner.next());
            this.players.add(t);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }



    //Initialize commongoalcards with 2 random cards
    private void initCommongoalcards() {
        this.commongoalcards = new ArrayList<CommonGoalCard>();
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

    //Defining the dynamic type of the CommmonGoalCard
    private CommonGoalCard typeCommongoalcards(int id) {
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

    public ArrayList<CommonGoalCard> getCommongoalcards() {
        return commongoalcards;
    }

    private void initCommonscores() {
        this.commonscores0 = new Stack<Integer>();
        this.commonscores1 = new Stack<Integer>();
        if (this.num == 4) {
            this.commonscores0.push(8);
            this.commonscores0.push(6);
            this.commonscores0.push(4);
            this.commonscores0.push(2);
            this.commonscores1.push(8);
            this.commonscores1.push(6);
            this.commonscores1.push(4);
            this.commonscores1.push(2);
        }
        if (this.num == 3) {
            this.commonscores0.push(8);
            this.commonscores0.push(6);
            this.commonscores0.push(4);
            this.commonscores1.push(8);
            this.commonscores1.push(6);
            this.commonscores1.push(4);
        }
        if (this.num == 2) {
            this.commonscores0.push(8);
            this.commonscores0.push(4);
            this.commonscores1.push(8);
            this.commonscores1.push(4);
        }
    }

    public Stack<Integer> getCommonscores0() {
        return commonscores0;
    }

    public Stack<Integer> getCommonscores1() {
        return commonscores1;
    }

    

    //Initialize personalgoalcards
    private void initPersonalgoalcards() {
        for (int i = 0; i < this.num; i++) {
            this.personalgoalcards.add(new PersonalGoalCard(players.get(i)));
        }
    }

    public ArrayList<PersonalGoalCard> getPersonalgoalcards() {
        return personalgoalcards;
    }



    private void initBoard() {
        this.board = new Board(this.num);
    }

    //Fill the board with tiles picked from the bag
    private void fillBoard() {
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

    //Check if the board is empty or has only isolated tiles, if true board is refilled
    public void refillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(this.board.getBoard()[i][j].getType() != TileType.NULL && !isTileIsolated(i, j)){
                    //System.out.println("Board is not need to refill");
                    return;
                }
            }
        }
        //System.out.println("Board needs to refill");
        this.fillBoard();
    }

    //Check if a tile is isolated in the board
    public boolean isTileIsolated(int row, int column) {
        if(row > 0 && this.board.getBoard()[row - 1][column].getType() != TileType.NULL){
            return false;
        }
        if(row < 9 && this.board.getBoard()[row + 1][column].getType() != TileType.NULL){
            return false;
        }
        if(column > 0 && this.board.getBoard()[row][column - 1].getType() != TileType.NULL){
            return false;
        }
        if(column < 9 && this.board.getBoard()[row][column + 1].getType() != TileType.NULL){
            return false;
        }
        return true;
    }

    public Board getBoard() {
        return board;
    }



    //Create a stack (bag) that contains the tiles
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

    public Stack<Tile> getBag() {
        return bag;
    }
}
