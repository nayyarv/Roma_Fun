package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Consul extends Card {
    private final static String NAME = "Gladiator";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "The score on an action die which has not yet been " +
            "used can be " +
            "increased or decreased by 1 point.";
    private final static int COST = 3;
    private final static int DEFENCE = 3;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Consul(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
