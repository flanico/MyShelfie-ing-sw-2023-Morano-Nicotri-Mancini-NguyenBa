package it.polimi.ingsw.model;
import it.polimi.ingsw.network.message.serverSide.InfoGameMessage;
import it.polimi.ingsw.observer.Observable;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * class that defines a game
 * @author Alessandro Mancini
 */
public class Game extends Observable implements Serializable {
    @Serial
    private static final long serialVersionUID = -8506633854746922798L;
    private int num;
    private static final int MAX_PLAYERS = 4;
    private List<Player> players;
    private List<PersonalGoalCardType> personalgoalcards;
    private List<CommonGoalCard> commongoalcards;
    private List<CommonGoalCardScore> commongoalcardscores;
    private Board board;
    private Stack<Tile> bag;
    private Map<String, Integer> playerScore;


    public Game() {
        this.players = new ArrayList<>();
        this.commongoalcards = new ArrayList<>();
        this.commongoalcardscores = new ArrayList<>();
        this.personalgoalcards = new ArrayList<>();
        this.playerScore = new HashMap<>();
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
     * number of current players added in the game in a generic time
     * @return number of current players
     */
    public int getCurrentNum() {
        return players.size();
    }

    /**
     * getter of the max number of players chosen by the first player
     */
    public int getNum() {
        return num;
    }

    /**
     * setter of the max number of players chosen by the first player
     * @param num max number of players
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * check if a nickname is already use or not in the game
     * @param nickname selected by the client
     * @return true if the nickname is already used, false otherwise
     * @author Chiara Nguyen Ba
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
        initPersonalgoalcard(player);
        playerScore.put(nickname, 0);
        notifyObserver(new InfoGameMessage(players, num));
    }

    /**
     * returns a player given his nickname
     * @param nickname of player to be found
     * @return the player of given nickname, null otherwise
     */
    public Player getPlayerByNickname(String nickname) {
        return players.stream()
                .filter(player -> nickname.equals(player.getNickname()))
                .findFirst()
                .orElse(null);
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
     * removes a player from the game
     * notifies all the views if the notifyEnabled is true
     * @param nickname of the player to remove from the game
     * @param notifyEnabled set to true to enable a lobby disconnection message, false otherwise
     * @return true if the player is removed, false otherwise
     */
    public boolean removePlayerByNickname(String nickname, boolean notifyEnabled) {
        boolean result = players.remove(getPlayerByNickname(nickname));

        if (notifyEnabled) {
            notifyObserver(new InfoGameMessage(players, num));
        }
        return result;
    }


    public List<Player> getPlayers() {
        return players;
    }

    /**
     * initializer of personalgoalcard of the player
     * @param player to be set the personal goal card
     * @author Alessandro Mancini
     */
    private void initPersonalgoalcard(Player player) {
        PersonalGoalCardType type;
        Random rand = new Random();
        do {
            type = PersonalGoalCardType.values()[rand.nextInt(PersonalGoalCardType.values().length)];
        } while (personalgoalcards.contains(type));
        personalgoalcards.add(type);
        player.setPersonalGoalCard(new PersonalGoalCard(type));
    }


    public List<PersonalGoalCardType> getPersonalgoalcards() {
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


    public Board getBoard() {
        return board;
    }

    /**
     * initializer of the bag
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

    public Stack<Tile> getBag() {
        return bag;
    }

    /**
     * checks if the common goal cards are completed
     * @param player to be checked the bookshelf
     * @author Chiara Nguyen Ba
     */
    public int checkCommonGoalCards(Player player) {
        int score = 0;
        if(!player.isDoneFirstCommon() && commongoalcards.get(0).check(player.getBookshelf())) {
            player.setDoneFirstCommon(true);
            score = commongoalcardscores.get(0).getStack().pop();
            player.setScore(player.getScore() + score);
        }
        if(!player.isDoneSecondCommon() && commongoalcards.get(1).check(player.getBookshelf())) {
            player.setDoneSecondCommon(true);
            score = commongoalcardscores.get(1).getStack().pop();
            player.setScore(player.getScore() + score);
        }
        return score;
    }

    /**
     * sets at the end of the game the ranking scores of the players
     * @author Chiara Nguyen Ba
     */
    public void setRankingScore() {
        Map<String, Integer> scores = new HashMap<>();
        for (Player p : players) {
            scores.put(p.getNickname(), p.getScore());
        }
        playerScore = scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * getter of ranking (Player, Score)
     * @return playerScore the map of ranking scores
     */
    public Map<String, Integer> getPlayerScore() {
        return playerScore;
    }

    /**
     * replace the game for the implementation of the persistence
     * @param players
     * @param num
     * @param board
     * @param commonGoalCards
     * @param commonGoalCardScores
     * @param bag
     * @param playerScore
     */
    public void replaceGame(List<Player> players, int num, Board board, List<CommonGoalCard> commonGoalCards, List<CommonGoalCardScore> commonGoalCardScores, Stack<Tile> bag, Map<String, Integer> playerScore) {
        this.players = players;
        this.num = num;
        this.board = board;
        this.commongoalcards = commonGoalCards;
        this.commongoalcardscores = commonGoalCardScores;
        this.bag = bag;
        this.playerScore = playerScore;
        for (Player p : players) {
            p.setPersonalGoalCard(getPlayerByNickname(p.getNickname()).getPersonalGoalCard());
        }
    }
}
