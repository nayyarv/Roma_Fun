package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Aesculapinum extends CardBase {
    public final static String NAME = "Aesculapinum";
    final static String TYPE = CardBase.BUILDING;
    final static String DESCRIPTION = "The temple of Asculapius (the God of healing) enables the player to " +
            "pick up any character card from the discard pile and add it to their hand.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Aesculapinum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardBase> discardPile = cardManager.getDiscardPile();
        ArrayList<CardBase> tempHand = new ArrayList<CardBase>();

        for(CardBase cardBase : discardPile){
            if(cardBase.getType() == CardBase.CHARACTER){
                tempHand.add(cardBase);
                discardPile.remove(cardBase);
            }
        }

        if(tempHand.size() != 0){
            player.addCardToHand(player.chooseCard(tempHand));
        } else {
            System.out.println("No character cards in discard pile");
            activated = false;
        }

        discardPile.addAll(tempHand);

        return activated;
    }

}
