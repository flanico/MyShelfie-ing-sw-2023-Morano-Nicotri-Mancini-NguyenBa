package it.polimi.ingsw.model;
import it.polimi.ingsw.observer.Observable;

import java.util.*;

/**
 * class that defines a game
 * @author Alessandro Mancini
 */
public class Game extends Observable {
    private int num;
    private static final int MAX_PLAYERS = 4;
    private List<Player> players;
    private List<PersonalGoalCard> personalgoalcards;
    private List<CommonGoalCard> commongoalcards;
    private List<CommonGoalCardScore> commongoalcardscores;
    private Board board;
    private Stack<Tile> bag;

    /**
     * constructor of Game
     * @author Alessandro Mancini
     */
    public Game() {
        this.players = new ArrayList<>();
        this.commongoalcards = new ArrayList<>();
        this.commongoalcardscores = new ArrayList<>();
        this.personalgoalcards = new ArrayList<>();
        this.initCommongoalcards();
        this.initBag();
    }

    /**
     * initialization of the game
     * @param numMax number of players chosen by the first player joined in the game
     */
    public void initGame(int numMax) {
        if(numMax >= 2 && numMax <= MAX_PLAYERS) {
            this.num = numMax;
        }
        this.initBoard();
        this.board.fillBoard(this.bag);
        this.initCommongoalcardscores();
    }

    /**
     * number of current players added in the game
     * @return number of players
     */
    public int getCurrentNum() {
        return players.size();
    }

    /**
     * getter of number of players chosen by the first player
     * @author Alessandro Mancini
     */
    public int getNum() {
        return num;
    }

    /**
     * check if a nickname is already use or not in the game
     * @param nickname selected by the client
     * @return true if the nickname is already used, false otherwise
     */
    public boolean isNicknameTaken(String nickname) {
        return players.stream()
                .anyMatch(p -> nickname.equals(p.getNickname()));
    }

    /**
     * adds a new player to the game
     * @param nickname of the player to add to the game
     * @author Alessandro Mancini
     */
    public void addPlayer(String nickname) {
        Player player = new Player(nickname);
        players.add(player);
        //notify
    }

    /**
     * return a list of player nicknames that are already in the game
     * @return a list with all nicknames in the game
     */
    public List<String> getAllPlayers() {
        List<String> playersNicknames = new ArrayList<>();
        for (Player p : players) {
            playersNicknames.add(p.getNickname());
        }
        return playersNicknames;
    }

    /**
     * getter of players
     * @author Alessandro Mancini
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * initializer of personalgoalcards
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
    public List<PersonalGoalCard> getPersonalgoalcards() {
        return personalgoalcards;
    }

    /**
     * initializer of commongoalcards with 2 random cards
     * @author Alessandro Mancini
     */
    private void initCommongoalcards() {
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
    public List<CommonGoalCard> getCommongoalcards() {
        return commongoalcards;
    }

    /**
     * initializer of commongoalcardscores
     * @author Alessandro Mancini
     */
    private void initCommongoalcardscores() {
        this.commongoalcardscores.add(new CommonGoalCardScore(this.num));
        this.commongoalcardscores.add(new CommonGoalCardScore(this.num));
    }

    /**
     * getter of commongoalcardscores
     * @author Alessandro Mancini
     */
    public List<CommonGoalCardScore> getCommongoalcardscores() {
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
        this.bag = new Stack<>();
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
