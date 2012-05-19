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
public class Forum extends CardBase {

    public final static String NAME = "Forum";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "Requires 2 action dice: one to activate the Forum and the other to " +
            "determine how many victory points the player receives";
    final static int COST = 5;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 6;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Forum(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Forum(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Forum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction{
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<Dice> freeDice = new ArrayList<Dice>();
        freeDice.addAll(player.getFreeDice());
        int dieIndex = player.getCurrentAction().getActionDiceIndex();

        freeDice.remove(dieIndex);
        PlayerInterface.printOut("Get Victory Tokens by using free action dice", true);
        if(freeDice.isEmpty()){
            PlayerInterface.printOut("Not enough free action dice!", true);
            player.cancel();
        }
        PlayerInterface.printOut("Forum requires a 2nd Action Die:", true);
        dieIndex = player.getDieIndex(freeDice);
        freeDice.remove(dieIndex);
        activationData.add(dieIndex);

        if(diceDiscs.checkAdjacent(player.getPlayerID(), position, Templum.NAME)){
            if(freeDice.isEmpty()){
                PlayerInterface.printOut("No free action dice to activate adjacent Templum", true);
            } else {
                PlayerInterface.printOut("Use a 3rd die to activate Templum?", true);
                try {
                    dieIndex = player.getDieIndex(freeDice);
                    activationData.add(dieIndex);
                } catch (CancelAction cancelAction) {
                    PlayerInterface.printOut("Not using a 3rd die...", true);
                }
            }
        }

        player.commit();
    }

    //activation data: [freeDieIndex] ([freeDieIndex))
    //2 if templum adjacent

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();

        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<Dice> freeDice = player.getFreeDice();
        int dieIndex = activationData.remove(0);
        Dice chosenDie = freeDice.remove(dieIndex);

        diceDiscs.addDiceToDisc(position, chosenDie);
        victoryTokens.playerFromPool(player.getPlayerID(), chosenDie.getValue());

        if(!activationData.isEmpty()){
            dieIndex = activationData.remove(0);
            chosenDie = freeDice.remove(0);
            diceDiscs.addDiceToDisc(position, chosenDie);
            victoryTokens.playerFromPool(player.getPlayerID(), chosenDie.getValue());
        }

        // check for adjacent Basilicas
        if(diceDiscs.checkAdjacentDown(player.getPlayerID(), position, Basilica.NAME)){
            PlayerInterface.printOut("You get 2 extra victory tokens from your adjacent Basilica!", true);
            victoryTokens.playerFromPool(player.getPlayerID(), 2);
        }
        if(diceDiscs.checkAdjacentUp(player.getPlayerID(), position, Basilica.NAME)){
            PlayerInterface.printOut("You get 2 extra victory tokens from your adjacent Basilica!", true);
            victoryTokens.playerFromPool(player.getPlayerID(), 2);
        }
    }
}
