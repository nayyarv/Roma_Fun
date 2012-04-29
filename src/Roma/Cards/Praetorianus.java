package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Praetorianus extends Card {

    private final static String NAME = "Praetorianus";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "Any of the 8 opponent's dice 3 disc can be block" +
            "ed for one go.";
    private final static int COST = 8;
    private final static int DEFENCE = 9;

    public final static int OCCURENCES = 2;


    public Praetorianus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }


    public void activate(Player player, int position) {

    }
}
