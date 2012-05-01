package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Centurio extends Card {
    private final static String NAME = "Centurio";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "attacks the card directly opposite, whether it is a character " +
            "or building card." +
            " The value of an unused action die can be added to the value of the battle die (the action die is " +
            "then counted as used)." +
            " This is decided after the battle die has been thrown.";
    private final static int COST = 9;
    private final static int DEFENCE = 5;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Centurio(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
