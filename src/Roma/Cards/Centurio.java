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
public class Centurio extends CardBase {
    public final static String NAME = "Centurio";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Attacks the card directly opposite, whether it is a character " +
            "or building card." +
            " The value of an unused action die can be added to the value of the battle die (the action die is " +
            "then counted as used)." +
            " This is decided after the battle die has been thrown.";
    final static int COST = 9;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Centurio(playArea);
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
            card = new Centurio(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Centurio(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        int targetPlayer = player.getOtherPlayerID();
        int chosenDieIndex = CANCEL;
        boolean battleVictory = false;
        ArrayList<Dice> freeDice = player.getFreeDice();
        ArrayList<Integer> activationData = player.getActivationData();
        CardHolder targetCard = diceDiscs.getTargetCard(targetPlayer, position);
        int battleValue = player.getCurrentAction().getBattleDice();

        PlayerInterface.printOut("Attack a card directly opposite", true);
        if (targetCard == null) {
            PlayerInterface.printOut("No card directly opposite!", true);
            player.cancel();
        } else {
            player.commit();
            battleVictory = diceDiscs.battle(targetPlayer, position, battleValue);
            if (!battleVictory) {
                if (freeDice.size() != 0) {
                    try {
                        PlayerInterface.printOut("Add an action die value to battle value...", true);
                        chosenDieIndex = player.getDieIndex(freeDice);
                    } catch (CancelAction cancelAction) {
                        PlayerInterface.printOut("Not choosing a die to add...", true);
                    }
                } else {
                    PlayerInterface.printOut("No free action dice to add...", true);
                }
                activationData.add(chosenDieIndex);
            }
        }
    }

    // activationData: [freeDiceIndex]
    // if size() == 0 then battleVictory was true
    // else if value is -1 then dice selection was cancelled and the target survives

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Integer> activationData = player.getActivationData();
        DiceHolder diceHolder = playArea.getDiceHolder();

        int targetPlayer = player.getOtherPlayerID();
        int chosenDieIndex = CANCEL;
        Dice chosenDie = null;
        CardHolder targetCard = diceDiscs.getTargetCard(targetPlayer, position);
        ArrayList<Dice> freeDice = player.getFreeDice();
        int battleValue = player.getBattleValue();

        //if empty then battleVictory == true
        if (activationData.isEmpty()) {
            diceDiscs.discardTarget(targetPlayer, position);
        } else {
            chosenDieIndex = activationData.remove(0);
            //if chosenDieIndex isn't cancel then add die value to battle value and check defense again
            if (chosenDieIndex != CANCEL) {
                chosenDie = freeDice.remove(chosenDieIndex);
                diceDiscs.addDiceToDisc(position, chosenDie);
                if (targetCard.getDefense() <= chosenDie.getValue() + battleValue) {
                    diceDiscs.discardTarget(targetPlayer, position);
                    PlayerInterface.printOut("Victory!", true);
                } else {
                    PlayerInterface.printOut("You wasted an action die...", true);
                }
            }
        }
    }
}
