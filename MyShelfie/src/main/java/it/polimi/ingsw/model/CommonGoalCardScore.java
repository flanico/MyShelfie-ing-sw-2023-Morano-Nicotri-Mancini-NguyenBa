package it.polimi.ingsw.model;
import java.util.*;

/**
 * class to manage the scores of the 2 common goal cards
 * @author Alessandro Mancini
 */
public class CommonGoalCardScore {
    private Stack<Integer> stack;

    /**
     * constructor of CommonScores
     * @param num of players of the game
     * @author Alessandro Mancini
     */
    public CommonGoalCardScore(int num) {
        this.stack = new Stack<Integer>();
        if (num == 4) {
            this.stack.push(2);
            this.stack.push(4);
            this.stack.push(6);
            this.stack.push(8);
        }
        if (num == 3) {
            this.stack.push(4);
            this.stack.push(6);
            this.stack.push(8);
        }
        if (num == 2) {
            this.stack.push(4);
            this.stack.push(8);
        }
    }

    /**
     * getter of stack
     * @author Alessandro Mancini
     */
    public Stack<Integer> getStack() {
        return stack;
    }
}
