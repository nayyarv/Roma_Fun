package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Forum extends Card {

    private final static String NAME = "Forum";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION = "Requires 2 action dice: one to activate the Forum and the other to " +
            "determine how many victory points the player receives";
    private final static int COST = 5;
    private final static int DEFENCE = 5;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 6;

    //activate utility
    Dice tempDice = null;

    public Forum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        ArrayList<Dice> freeDice = player.getFreeDice();
        Dice chosenDie = null;
        VictoryTokens victoryTokens = playArea.getVictoryTokens();

        if(freeDice.size() == 0){
            activated = false;
            System.out.println("Not enough free action dice!");
        } else {
            chosenDie = player.chooseDie(freeDice);
            if(chosenDie != null){
                victoryTokens.playerFromPool(player.getPlayerID(), chosenDie.getValue());
                //TODO: check for 2 special adjacency cases
            } else {
                activated = false;
            }
        }


        return activated;
    }

}
