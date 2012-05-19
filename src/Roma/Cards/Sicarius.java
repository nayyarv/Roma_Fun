package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * File NAME:
 * Creator: Varun Nayyar
 * Date: 26/03/12
 * Desc:
 */
public class Sicarius extends CardBase {
    public final static String NAME = "Sicarius";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Eliminates an opposing, face-up character card." +
            "The opposing card and the Sicarius are both discarded.";
    final static int COST = 9;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Sicarius(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Sicarius(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Sicarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Integer> activationData = player.getActivationData();
        CardHolder[][] activeCards = diceDiscs.getActiveCards();
        int targetPlayerID = player.getOtherPlayerID();
        CardHolder card;
        int targetIndex;

        for(int i = 0; i < DiceDiscs.CARD_POSITIONS; i++){
            card = activeCards[targetPlayerID][i];
            if(card != null && card.getType().equalsIgnoreCase(Card.CHARACTER)){
                card.setPlayable(true);
            }
        }
        PlayerInterface.printOut("Destroy which enemy Character? (Sicarius is also discarded)", true);
        targetIndex = player.getDiceDiscIndex(activeCards, false, true);

        player.commit();
        activationData.add(targetIndex);
    }

    //activationData: [targetIndex]

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        ArrayList<Integer> activationData = player.getActivationData();
        int targetIndex = activationData.remove(0);

        diceDiscs.discardTarget(targetPlayerID, targetIndex);
        diceDiscs.discardTarget(player.getPlayerID(), position);
    }
}
