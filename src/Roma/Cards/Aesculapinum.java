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
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "The temple of Asculapius (the God of healing) enables the player to " +
            "pick up any character card from the discard pile and add it to their hand.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public static CardHolder makeOne(PlayArea playArea){
        Card card = new Aesculapinum(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Aesculapinum(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    private Aesculapinum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    public boolean activate(Player player, int position) {
        boolean activated = true;
        PlayerInterface playerInterface = playArea.getPlayerInterface();
        int cardIndex;
        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardHolder> discardPile = cardManager.getDiscardPile();

        cardIndex = playerInterface.getHandIndex(discardPile, Card.CHARACTER);
        player.addCardToHand(discardPile.get(cardIndex));
        discardPile.remove(cardIndex);

        return activated;
    }

}
