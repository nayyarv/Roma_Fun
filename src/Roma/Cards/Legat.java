package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legat extends Card {

    private final static String NAME = "Legat";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "A player gets 1 victory point from the stockpile for" +
            "every dice disc not occupied by the opponent.";
    private final static int COST = 5;
    private final static int DEFENCE = 2;

    public final static int OCCURENCES = 2;


    public Legat(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }
}
