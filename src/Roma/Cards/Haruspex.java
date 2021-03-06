package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

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

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Haruspex(playArea);
        CardHolder cardHolder = new CardHolder(card, playArea);
        card.setContainer(cardHolder);
        card.setCardHolder(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea) {
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        CardBase card;

        for (int i = 0; i < OCCURENCES; i++) {
            card = new Haruspex(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Haruspex(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        CardManager cardManager = playArea.getCardManager();
        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<CardHolder> deck = cardManager.getPlayingDeck();
        int cardIndex = CANCEL;

        PlayerInterface.printOut("Get a card from deck", true);
        if (deck.isEmpty()) {
            PlayerInterface.printOut("No cards in deck!", true);
            player.cancel();
        }
        player.commit();

        PlayerInterface.printOut("Choose a card to put into hand", true);
        while (cardIndex == CANCEL) {
            try {
                cardIndex = player.getCardIndex(deck);
            } catch (CancelAction cancelAction) {
                PlayerInterface.printOut("Have to choose a card", true);
            }
        }
        activationData.add(cardIndex);
    }

    //activationData: [cardIndex]

    @Override
    public void activate(Player player, int position) {
        CardManager cardManager = playArea.getCardManager();
        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<CardHolder> deck = cardManager.getPlayingDeck();
        int cardIndex = activationData.remove(0);
        player.addCardToHand(deck.remove(cardIndex));
        if(playArea.getTimeWarp() == null){
            cardManager.shuffle();
        }
    }
}
