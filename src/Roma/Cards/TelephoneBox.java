package Roma.Cards;

import Roma.Dice;
import Roma.DiceDiscs;
import Roma.History.TimeWarp;
import Roma.History.TurnHistory;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */
public class TelephoneBox extends CardBase {

    public final static String NAME = "Telephonebox";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "When activated by an action die the telephone Box card sends one of the " +
            "owner's cards already on the board forwards or backwards in time.  " +
            "The sent card is called the time-traveling card.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    public TelephoneBox(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new TelephoneBox(playArea);
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
            card = new TelephoneBox(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        final int BACKWARD = 1;
        final int FORWARD = 2;
        final String strPrompt = "Send card back or forward in time?";
        final String strOption1 = "Back in time";
        final String strOption2 = "Forward in time";
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardHolder[][] activeCards = diceDiscs.getActiveCards();
        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<Dice> freeDice = new ArrayList<Dice>();
        freeDice.addAll(player.getFreeDice());
        int dieIndex = player.getCurrentAction().getActionDiceIndex();
        int targetIndex;
        int option;
        PlayerInterface playerInterface = player.getPlayerInterface();
        CardHolder card;
        int timeValue;

        freeDice.remove(dieIndex);
        PlayerInterface.printOut("Send an active friendly card backward or forward in time", true);
        if (freeDice.isEmpty()) {
            PlayerInterface.printOut("Not enough free action dice!", true);
            player.cancel();
        }
        PlayerInterface.printOut("Telephone Box requires a 2nd Action Die:", true);
        dieIndex = player.getDieIndex(freeDice);
        timeValue = freeDice.remove(dieIndex).getValue();
        activationData.add(dieIndex);

        PlayerInterface.printOut("Prevent which card shall be the time-traveller?", true);
        for (int i = 0; i < DiceDiscs.CARD_POSITIONS; i++) {
            card = activeCards[player.getPlayerID()][i];
            if (card != null) {
                card.setPlayable(true);
            }
        }
        targetIndex = player.getDiceDiscIndex(activeCards, true, false);
        activationData.add(targetIndex);

        option = playerInterface.readInput(strPrompt, true, strOption1, strOption2);
        if (option == CANCEL) {
            player.cancel();
        } else if (option == BACKWARD) {
            if(timeValue > playArea.getTurn()){
                PlayerInterface.printOut("Can't go back that far in time because not enought turns have passed!", true);
                player.cancel();
            }
            option = -1;
        } else if (option == FORWARD) {
            option = 1;
        }
        activationData.add(option);
        player.commit();
    }

    //activationData: [actionDieIndex][activeCardIndex][+1/-1]
    //+1 for forwards
    //-1 for backwards

    @Override
    public void activate(Player player, int position) {
        TurnHistory turnHistory = playArea.getTurnHistory();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Integer> activationData = player.getActivationData();
        int actionDieIndex = activationData.remove(0);
        int activeCardIndex = activationData.remove(0);
        int timeDirection = activationData.remove(0);
        ArrayList<Dice> freeDice = player.getFreeDice();
        int timeValue = freeDice.remove(actionDieIndex).getValue();
        CardHolder card = diceDiscs.getTargetCard(player, activeCardIndex);

        if(timeDirection < 0){
            TimeWarp timeWarp = new TimeWarp(turnHistory, timeValue, player.getPlayerID(),
                    activeCardIndex, card.getName(), card.countLives(), playArea);
            PlayerInterface.printOut(card.getName() + " was sent to the past!", true);
            playArea.setTimeWarp(timeWarp);
        } else {
            diceDiscs.setFromPast(timeValue, player.getPlayerID(), activeCardIndex);
        }
    }
}
