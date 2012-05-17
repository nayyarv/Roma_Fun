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
public class Scaenicus extends CardBase {
    public final static String NAME = "Scaenicus";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "He performs no action of his own but can copy the action of any of " +
            "the player's own face-up character cards, and the next time round that of another.";
    final static int COST = 8;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;


    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Scaenicus(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Scaenicus(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Scaenicus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

//    //TODO: handle infinite loop selecting self
//    public boolean activate(Player player, int position) {
//        //TODO: refactor input to interface or player
//        Scanner input = new Scanner(System.in);
//        ArrayList<Integer> validInput = new ArrayList<Integer>();
//        boolean inputValid = false;
//        int chosenInput = -1;
//
//        boolean activated = true;
//        DiceDiscs diceDiscs = playArea.getDiceDiscs();
//        int targetPlayerID = player.getOtherPlayer();
//
//        CardHolder[] friendlyCards = diceDiscs.getPlayerActives(targetPlayerID);
//
//        PlayerInterface.printOut("Available Targets:", true);
//        for(int i = 0; i < friendlyCards.length; i++){
//            if(friendlyCards[i] != null && friendlyCards[i].getType() == Card.CHARACTER){
//                PlayerInterface.printOut((i + 1) + ") " + friendlyCards[i].getName(), true);
//                validInput.add(i + 1);
//            } else {
//                PlayerInterface.printOut((i + 1) + ") #", true);
//            }
//        }
//
//        while(!inputValid){
//            chosenInput = input.nextInt();
//            for(int number : validInput){
//                if(chosenInput == number){
//                    inputValid = true;
//                }
//            }
//        }
//        chosenInput--;
//
//        friendlyCards[chosenInput].activate(player, position);
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //TODO: fill in
    }

    @Override
    public void activate(Player player, int position) {
        //TODO: fill in
    }

    @Override
    public void discarded() {
        //do nothing when discarded
    }
}
