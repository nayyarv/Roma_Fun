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
public class Consiliarius extends CardBase {
    public final static String NAME = "Consiliarius";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The player picks up their character cards and can then lay them again " +
            "on any dice disc. Buildings can be covered.";
    final static int COST = 4;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Consiliarius(playArea);
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
            card = new Consiliarius(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Consiliarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        ArrayList<Integer> activationData = player.getActivationData();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardHolder[][] activeCards = diceDiscs.getActiveCards();
        CardHolder[][] activeCardsPrime = new CardHolder[Roma.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];
        CardHolder card;
        int[] fromIndices = new int[DiceDiscs.CARD_POSITIONS];
        int[] toIndices = new int[DiceDiscs.CARD_POSITIONS];
        for(int i = 0; i < DiceDiscs.CARD_POSITIONS; i++){
            fromIndices[i] = CANCEL;
            toIndices[i] = CANCEL;
        }
        int fromIndex;
        int toIndex;
        boolean endSelection = false;
        boolean checkNewPlacement = false;

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            System.arraycopy(activeCards[i], 0, activeCardsPrime[i], 0, DiceDiscs.CARD_POSITIONS);
        }

        for (int i = 0; i < DiceDiscs.CARD_POSITIONS; i++) {
            card = activeCards[player.getPlayerID()][i];
            if (card != null && card.getType().equalsIgnoreCase(Card.CHARACTER)) {
                card.setPlayable(true);
            }
        }

        int i = 0;
        PlayerInterface.printOut("Rearrange character cards...", true);
        while (!endSelection) {
            try {
                PlayerInterface.printOut("Select card to move:", true);
                fromIndex = player.getDiceDiscIndex(activeCards, true, false);
                fromIndices[i] = fromIndex;
                PlayerInterface.printOut("Move to where:", true);
                toIndex = player.getDiceDiscIndex(activeCardsPrime, false, false);
                toIndices[i] = toIndex;
                checkNewPlacement = false;
                for(int index : toIndices){
                    if(fromIndex == index){
                        checkNewPlacement = true;
                    }
                }
                if(!checkNewPlacement){
                    activeCardsPrime[player.getPlayerID()][fromIndex] = null;
                }
                activeCardsPrime[player.getPlayerID()][toIndex] = activeCards[player.getPlayerID()][fromIndex];
                activeCards[player.getPlayerID()][fromIndex].setPlayable(false);
                PlayerInterface.printOut("New card positions: ", true);
                player.printDiceDiscs(activeCardsPrime);
                i++;
            } catch (CancelAction cancelAction) {
                endSelection = true;
            }
        }

        player.commit();

        for (int j = 0; j < i; j++) {
            activationData.add(fromIndices[j]);
            activationData.add(toIndices[j]);
        }
    }

    //activationData: ([fromIndex][toIndex])*repeated as desired

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardManager cardManager = playArea.getCardManager();
        CardHolder[] activeCards = diceDiscs.getPlayerActives(player.getPlayerID());
        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<Integer> fromIndices = new ArrayList<Integer>();
        ArrayList<Integer> toIndices = new ArrayList<Integer>();
        ArrayList<CardHolder> cardList = new ArrayList<CardHolder>();

        while (!activationData.isEmpty()) {
            fromIndices.add(activationData.remove(0));
            toIndices.add(activationData.remove(0));
        }

        for (int i : fromIndices) {
            cardList.add(activeCards[i]);
            activeCards[i] = null;
        }

        for (int i : toIndices) {
            if (activeCards[i] != null) {
                cardManager.discard(activeCards[i]);
            }
            activeCards[i] = cardList.remove(0);
        }
    }
}
