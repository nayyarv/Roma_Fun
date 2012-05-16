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

    @Override
    public void gatherData(Player player, int position) {
        ArrayList<Integer> activationData = new ArrayList<Integer>();
        PlayerInterface playerInterface = playArea.getPlayerInterface();
        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardHolder> discardPile = cardManager.getDiscardPile();
        int cardIndex;

        if(discardPile.isEmpty()){

        }
        player.commit();


        try {
            cardIndex = player.getCardIndex(discardPile, Card.CHARACTER);
        } catch (CancelAction cancelAction) {
            PlayerInterface.printOut("Must chose a card");
        }

        activationData.add(cardIndex);
    }

    @Override
    public boolean activate(Player player, int position) {
        boolean activated = true;
        ArrayList<Integer> activationData = player.getCurrentAction().getActivationData();
        int cardIndex = activationData.remove(0);
        CardManager cardManager = playArea.getCardManager();
        ArrayList<CardHolder> discardPile = cardManager.getDiscardPile();

        //retrieve cardIndex from activationData
        player.addCardToHand(discardPile.get(cardIndex));
        discardPile.remove(cardIndex);

        return activated;
    }

    @Override
    public void discarded() {
        //do nothing when discarded
    }

}
