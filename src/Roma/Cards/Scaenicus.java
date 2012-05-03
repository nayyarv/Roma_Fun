package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Scaenicus extends Card {
    final static String NAME = "Scaenicus";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "He performs no action of his own but can copy the action of any of " +
            "the player's own face-up character cards, and the next time round that of another.";
    final static int COST = 8;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;


    public final static int OCCURENCES = 2;

    public Scaenicus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    //TODO: handle infinite loop selecting self
    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }

}
