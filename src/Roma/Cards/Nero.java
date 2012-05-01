package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Nero extends Card {
    private final static String NAME = "Nero";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "Destroys any face-up opposing building card. " +
            "The destroyed card and Nero are both discarded.";
    private final static int COST = 8;
    private final static int DEFENCE = 9;
    private final static boolean ACTIVATE_ENABLED = true;


    public final static int OCCURENCES = 1;

    public Nero(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
