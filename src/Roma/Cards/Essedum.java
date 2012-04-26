package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Essedum extends Card {
    private final static String NAME = "Essedum";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "The defence value of the opponent's face-up cards is reduced by 2.";
    private final static int COST = 6;
    private final static int DEFENCE = 3;

    public final static int OCCURENCES = 2;


    public Essedum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }
}
