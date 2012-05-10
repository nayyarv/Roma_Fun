package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Haruspex extends CardBase {
    public final static String NAME = "Haruspex";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The player can choose any card from the pile of face-down cards " +
            "and add it to their hand. Afterwards the pile is shuffled.";
    final static int COST = 4;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public static ArrayList<Card> playSet(PlayArea playArea){
        ArrayList<Card> set = new ArrayList<Card>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Haruspex(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    private Haruspex(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        CardManager cardManager = playArea.getCardManager();
        ArrayList<Card> deck = cardManager.getPlayingDeck();
        Card chosenCard;

        chosenCard = player.chooseCard(deck);
        player.addCardToHand(chosenCard);

        cardManager.shuffle();

        return activated;
    }
}
