package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 3/04/12
 * Desc:
 */

public class TribunisPlebis extends CardBase {
    public final static String NAME = "Tribunis Plebis";
    final static String TYPE = CardBase.CHARACTER;
    final static String DESCRIPTION = "The player gets 1 victory point from their opponent.";
    final static int COST = 5;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public TribunisPlebis(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int ID = player.getPlayerID();

        victoryTokens.playerToPlayer(otherPlayer(ID), player.getPlayerID(), 1);

        return activated;
    }
}