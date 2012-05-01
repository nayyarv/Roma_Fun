package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Aesculapinum extends Card {
    private final static String NAME = "Aesculapinum";
    private final static String TYPE = Card.BUILDING;
    private final static String DESCRIPTION = "The temple of Asculapius (the God of healing) enables the player to " +
            "pick up any character card from the discard pile and add it to their hand.";
    private final static int COST = 5;
    private final static int DEFENCE = 2;
    private final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Aesculapinum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        CardManager cardManager = playArea.getCardManager();
        ArrayList<Card> discardPile = cardManager.getDiscardPile();
        ArrayList<Card> tempHand = new ArrayList<Card>();

        for(Card card : discardPile){
            if(card.getType() == Card.CHARACTER){
                tempHand.add(card);
                discardPile.remove(card);
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
