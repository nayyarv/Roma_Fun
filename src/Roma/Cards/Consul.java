package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Consul extends Card {
    final static String NAME = "Gladiator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The score on an action die which has not yet been " +
            "used can be " +
            "increased or decreased by 1 point.";
    final static int COST = 3;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Consul(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        ArrayList<Dice> freeDice = player.getFreeDice();
        Dice chosenDice = null;

        if(freeDice.size() != 0){
            chosenDice = player.chooseDie(freeDice);
            if(chosenDice == null){
                activated = false;
            } else {
                //TODO: access player interface
            }
        }

        return activated;
    }
}
