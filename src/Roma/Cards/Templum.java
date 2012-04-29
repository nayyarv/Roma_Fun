package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Templum extends Card {

    private final static String NAME = "Templum";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION =
            "If a Forum is activated (it must lie directly next to the Templum), the third action die can be \n" +
                    "used to determine the number of additional victory points which the player gets from the general \n" +
                    "stockpile. The action dice must not yet have been used in this go. The Templum itself is not \n" +
                    "activated separately.";
    private final static int COST = 2;
    private final static int DEFENCE = 2;


    public final static int OCCURENCES = 2;

    public Templum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }


    public void activate(Player player, int position) {

    }

}
