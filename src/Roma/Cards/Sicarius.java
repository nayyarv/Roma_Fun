package Roma.Cards;

import Roma.*;

/**
 * File NAME:
 * Creator: Varun Nayyar
 * Date: 26/03/12
 * Desc:
 */
public class Sicarius extends Card {
    final static String NAME = "Sicarius";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Eliminates an opposing, face-up character card." +
            "The opposing card and the Sicarius are both discarded.";
    final static int COST = 9;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    public Sicarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
