package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Praetorianus extends Card {

    private final static String NAME = "Praetorianus";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "Any of the opponent's dice discs can be blocked" +
            " for one go.";
    private final static int COST = 8;
    private final static int DEFENCE = 9;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Praetorianus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
