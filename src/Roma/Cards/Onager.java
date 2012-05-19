package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Onager extends CardBase {

    public final static String NAME = "Onager";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "This Roman catapult attacks any opposing building. " +
            "The battle die is thrown once.";
    final static int COST = 5;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Onager(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Onager(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Onager(PlayArea playArea) {
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
            if(card != null && card.getType().equalsIgnoreCase(Card.BUILDING)){
                card.setPlayable(true);
            }
        }
        PlayerInterface.printOut("Attack which building?", true);
        targetIndex = player.getDiceDiscIndex(activeCards, false, true);

        player.commit();
        activationData.add(targetIndex);
    }

    //activationData: [targetIndex]
    //reads battle value from currentAction
    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        ArrayList<Integer> activationData = player.getActivationData();
        int targetIndex = activationData.remove(0);
        int battleValue = player.getBattleValue();

        diceDiscs.battle(targetPlayerID, targetIndex, battleValue);
    }
}
