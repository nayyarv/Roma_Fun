package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Haruspex extends Card {
    private final static String NAME = "Haruspex";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "The player can choose any card from the pile of face-down cards " +
            "and add it to their hand. Afterwards the pile is shuffled.";
    private final static int COST = 4;
    private final static int DEFENCE = 3;

    public final static int OCCURENCES = 2;


    public Haruspex(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }
}
