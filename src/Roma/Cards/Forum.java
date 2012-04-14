package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Forum extends Card {

    private final static String NAME = "Forum";
    private final static String TYPE = "Building";
    private final static String DESCRIPTION = "Requires 2 action dice: one to activate the Forum and the other to " +
            "determine how many victory points the player receives";
    private final static int COST = 5;
    private final static int DEFENCE = 5;


    public Forum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }

}
