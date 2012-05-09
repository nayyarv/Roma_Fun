package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Basilica extends CardBase {

    public final static String NAME = "Basilica";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "If a Forum is activated (it must lie directly next to the basilica)," +
            " the player gets 2 more victory points. The Basilica itself is not activiated.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = false;

    public final static int OCCURENCES = 2;


    public Basilica(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public boolean activate(Player player, int position) {
        boolean activated = false;

        return activated;
    }
}
