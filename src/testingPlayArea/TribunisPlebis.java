package testingPlayArea;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 3/04/12
 * Desc:
 */

public class TribunisPlebis extends Card{
    private final static String NAME = "Tribunis Plebis";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "The player gets 1 victory point from their opponent.";
    private final static int COST = 5;
    private final static int DEFENCE = 5;

    public TribunisPlebis(PlayArea playArea){
        super(NAME, TYPE, DESCRIPTION, COST,DEFENCE,playArea);
    }

    public void activate(int player){
        PlayArea playArea = super.getPlayArea();
        playArea.getVictoryTokens().playerToPlayer(otherPlayer(player),player, 1);

    }

    private int otherPlayer(int player){
        if(player==PlayArea.PLAYER_ONE){
            return PlayArea.PLAYER_TWO;
        } else {
            return PlayArea.PLAYER_ONE;
        }
    }
}