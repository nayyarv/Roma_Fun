package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc: Architectus Card
 */
public class Architectus extends CardBase {
    public final static String NAME = "Architectus";
    final static String TYPE = CardBase.CHARACTER;
    final static String DESCRIPTION = "Enables the player to lay as many building cards as they wish free " +
            "of charge. The player is allowed to cover any cards.";
    final static int COST = 3;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Architectus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public boolean activate(Player player, int position) {
        boolean activated = true;

        ArrayList<CardBase> tempHand = new ArrayList<CardBase>();
        ArrayList<CardBase> hand = player.getHand();
        boolean endSelection = false;
        CardBase chosenCardBase = null;
        int targetPosition;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        for(CardBase cardBase : hand){
            if(cardBase.getType() == CardBase.BUILDING){
                if(hand.remove(cardBase)){
                    tempHand.add(cardBase);
                }
            }
        }

        while(!endSelection){
            playArea.printStats();
            chosenCardBase = player.chooseCard(tempHand);
            if(chosenCardBase == null){
                endSelection = true;
            } else {
                targetPosition = player.chooseCardDisc();
                diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCardBase);
            }
        }

        return activated;
    }
}
