package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Aesculapinum extends Card {
    private final static String NAME = "Aesculapinum";
    private final static String TYPE = "Building";
    private final static String DESCRIPTION = "The temple of Asculapius (the God of healing) enables the player to " +
            "pick up any character card from the discard pile and add it to their hand.";
    private final static int COST = 5;
    private final static int DEFENCE = 2;


    public Aesculapinum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {
        //TODO : Start actually implementing stuff!!
    }

}
