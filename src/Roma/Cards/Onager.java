package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Onager extends Card {

    private final static String NAME = "Onager";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION = "This Roman catapult attacks any opposing building. " +
            "The battle die is thrown once.";
    private final static int COST = 5;
    private final static int DEFENCE = 4;

    public final static int OCCURENCES = 2;


    public Onager(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }

}
