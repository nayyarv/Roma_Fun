package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Senator extends CardBase {
    public final static String NAME = "Senator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Enables the player to lay as many character cards as " +
            "they wish free of " +
            "charge. The player is allowed to cover any cards.";
    final static int COST = 3;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Senator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        ArrayList<Card> tempHand = new ArrayList<Card>();
        ArrayList<Card> hand = player.getHand();
        boolean endSelection = false;
        Card chosenCard = null;
        int targetPosition;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        for(Card card : hand){
            if(card.getType() == Card.CHARACTER){
                if(hand.remove(card)){
                    tempHand.add(card);
                }
            }
        }

        if(tempHand.isEmpty()){
            activated = false;
        } else {
            while(!endSelection){
                playArea.printStats();
                chosenCard = player.chooseCard(tempHand);
                if(chosenCard == null){
                    endSelection = true;
                } else {
                    targetPosition = player.chooseCardDisc();
                    diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCard);
                }
            }
        }

        return activated;
    }
}
