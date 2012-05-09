package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Turris extends CardBase {
    public final static String NAME = "Turris";
    final static String TYPE = CardBase.BUILDING;
    final static String DESCRIPTION = "As long as the Turris is face-up, the defence value of all the " +
            "player's other face-up cards increases by 1.";
    final static int COST = 6;
    final static int DEFENCE = 6;
    final static boolean ACTIVATE_ENABLED = false;

    public final static int OCCURENCES = 2;


    public Turris(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        boolean activated = false;

        return activated;
    }

}
