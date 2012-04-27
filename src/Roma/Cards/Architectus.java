package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc: Architectus Card
 */
public class Architectus extends Card {
    private final static String NAME = "Architectus";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "Enables the player to lay as many building cards as they wish free " +
            "of charge. The player is allowed to cover any cards.";
    private final static int COST = 3;
    private final static int DEFENCE = 4;

    public final static int OCCURENCES = 2;


    public Architectus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    public void activate(int player) {

    }

}
