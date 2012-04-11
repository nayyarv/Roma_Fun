package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legionarius extends Card {
    private final static String NAME = "Legionarius";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "Attacks the opponent's card which is directly opposite, " +
            "whether it is a character or a building card.";
    private final static int COST = 4;
    private final static int DEFENCE = 5;


    public Legionarius(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }
}
