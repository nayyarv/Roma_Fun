package Roma.Cards;

import Roma.CardManager;
import Roma.History.ActionData;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

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

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Aesculapinum(playArea);
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
            card = new Aesculapinum(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Aesculapinum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        ActionData actionData = player.getCurrentAction();
        ArrayList<Integer> activationData = player.getActivationData();
        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardHolder> discardPile = cardManager.getDiscardPile();
        int cardIndex = CANCEL;

        PlayerInterface.printOut("Get a character card from the discard pile", true);
        if (player.countType(discardPile, Card.CHARACTER) == 0) {
            PlayerInterface.printOut("No character cards in Discard pile", true);
            player.cancel();
        }
        player.commit();

        while (cardIndex == CANCEL) {
            try {
                cardIndex = player.getCardIndex(discardPile, Card.CHARACTER);
            } catch (CancelAction cancelAction) {
                PlayerInterface.printOut("Must chose a card", true);
            }
        }
        actionData.setTargetCardName(discardPile.get(cardIndex).getName());
        activationData.add(cardIndex);
    }

    //activationData: [cardIndex]

    @Override
    public void activate(Player player, int position) {
        ArrayList<Integer> activationData = player.getActivationData();
        int cardIndex = activationData.remove(0);
        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardHolder> discardPile = cardManager.getDiscardPile();

        //retrieve cardIndex from activationData
        if (cardIndex != CANCEL) {
            player.addCardToHand(discardPile.get(cardIndex));
            discardPile.remove(cardIndex);
        }
    }
}
