package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Velites extends Card {
    public final static String NAME = "Velites";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Attacks any opposing character card " +
            "(does not have to be directly opposite). The battle die is thrown once.";
    final static int COST = 8;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Velites(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        return activated;
    }
}
