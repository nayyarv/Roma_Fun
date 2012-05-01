package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 3/04/12
 * Desc:
 */

public class TribunisPlebis extends Card {
    private final static String NAME = "Tribunis Plebis";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "The player gets 1 victory point from their opponent.";
    private final static int COST = 5;
    private final static int DEFENCE = 5;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public TribunisPlebis(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        int ID = player.getPlayerID();
        PlayArea playArea = super.getPlayArea();
        playArea.getVictoryTokens().playerToPlayer(otherPlayer(ID), player.getPlayerID(), 1);

        return activated;
    }

    private int otherPlayer(int player) {
        if (player == Roma.PLAYER_ONE) {
            return Roma.PLAYER_TWO;
        } else {
            return Roma.PLAYER_ONE;
        }
    }
}