package Roma.History;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 5/21/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeWarp extends Exception {
    private final TurnHistory turnHistory;
    private final int timeReverse;
    private final int playerID;
    private final int position;
    private final String cardName;
    private final int[][] lives;
    public TimeWarp(TurnHistory turnHistory, int timeReverse, int playerID, int position,
                    String cardName, int[][] lives){

        this.turnHistory = turnHistory;
        this.timeReverse = timeReverse;
        this.playerID = playerID;
        this.position = position;
        this.cardName = cardName;
        this.lives = lives;
    }

    //TODO: Kat must remember how many lives it has at the point of return
    //maybe just replay all the moves again?
    //three things to preserve:
    //1)action dice
    //2)battle die
    //3)deck order
}
