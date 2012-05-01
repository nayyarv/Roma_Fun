package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Gladiator extends Card {
    private final static String NAME = "Gladiator";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "An opponent's face-up character card (chosen by the player " +
            "whose turn it is) must be returned to the opponent's hand.";
    private final static int COST = 6;
    private final static int DEFENCE = 5;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Gladiator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
