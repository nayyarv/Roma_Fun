package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Scaenicus extends Card {
    private final static String NAME = "Scaenicus";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "He performs no action of his own but can copy the action of any of " +
            "the player's own face-up character cards, and the next time round that of another.";
    private final static int COST = 8;
    private final static int DEFENCE = 3;


    public Scaenicus(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }

}
