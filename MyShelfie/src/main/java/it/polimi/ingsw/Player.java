package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a player
 * @author Alessandro Mancini
 */
public class Player {
    private final String nickname;
    private final Bookshelf bookshelf;
    private int score;

    /**
     * constructor of Player
     * @param nickname of the player
     * @author Alessandro Mancini
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();
    }

    /**
     * getter of nickname
     * @author Alessandro Mancini
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * getter of bookshelf
     * @author Alessandro Mancini
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * getter of score
     * @author Alessandro Mancini
     */
    public int getScore() {
        return score;
    }

    /**
     * setter of score
     * @author Alessandro Mancini
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * takes tiles from the board
     * @param board of the game
     * @author Alessandro Mancini
     */
    public ArrayList<Tile> selectTile(Board board) {
        ArrayList<Tile> selected = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("How many tiles do you want to get?");
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.println("x?");
                int x = scanner.nextInt();
                System.out.println("y?");
                int y = scanner.nextInt();
                selected.add(board.getMatrix()[x][y]);
            }
        } while (!board.isRemovable(selected));

        board.removeTiles(selected);
        return selected;
    }
}
