package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Mercatus extends Card {

    private final static String NAME = "Mercatus";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION = "The player gets 1 victory point from the pool" +
                    " for every face-up Forum that the opponent has.";
    private final static int COST = 6;
    private final static int DEFENCE = 3;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Mercatus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }

}
