package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Turris extends Card {

    private final static String NAME = "Turris";
    private final static String TYPE = "Building";
    private final static String DESCRIPTION = "As long as the Turris is face-up, the defence value of all the " +
            "player's other face-up cards increases by 1.";
    private final static int COST = 6;
    private final static int DEFENCE = 6;


    public Turris(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }

}
