package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Velites extends Card {
    private final static String NAME = "Velites";
    private final static String TYPE = "Character";
    private final static String DESCRIPTION = "attacks any opposing character card " +
            "(does not have to be directly opposite). The battle die is thrown once.";
    private final static int COST = 8;
    private final static int DEFENCE = 3;


    public Velites(PlayArea playArea){
        super(NAME, TYPE , DESCRIPTION,COST, DEFENCE, playArea);

    }

    public void activate(int player){

    }
}
