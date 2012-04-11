package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Consiliarus extends Card {
    private final static String NAME = "Consiliarus";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "The player picks up their character cards and can then lay them again " +
            "on any dice disc. Buildings can be covered.";
    private final static int COST = 4;
    private final static int DEFENCE = 5;




    public Consiliarus(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }
}
