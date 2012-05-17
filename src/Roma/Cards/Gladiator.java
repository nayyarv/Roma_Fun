package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Gladiator extends CardBase {
    public final static String NAME = "Gladiator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "An opponent's face-up character card (chosen by the player " +
            "whose turn it is) must be returned to the opponent's hand.";
    final static int COST = 6;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Gladiator(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Gladiator(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Gladiator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


//    public boolean activate(Player player, int position) {
//        //TODO: refactor input to interface or player
//        Scanner input = new Scanner(System.in);
//        ArrayList<Integer> validInput = new ArrayList<Integer>();
//        boolean inputValid = false;
//        int chosenInput = -1;
//
//        boolean activated = true;
//        DiceDiscs diceDiscs = playArea.getDiceDiscs();
//        int targetPlayerID = player.getOtherPlayerID();
//
//        CardHolder[] enemyCards = diceDiscs.getPlayerActives(targetPlayerID);
//
//        PlayerInterface.printOut("Available Targets:", true);
//        for(int i = 0; i < enemyCards.length; i++){
//            if(enemyCards[i] != null && enemyCards[i].getType() == Card.CHARACTER){
//                PlayerInterface.printOut((i + 1) + ") " + enemyCards[i].getName(), true);
//                validInput.add(i + 1);
//            } else {
//                PlayerInterface.printOut((i + 1) + ") #", true);
//            }
//        }
//
//        if(validInput.isEmpty()){
//            PlayerInterface.printOut("No valid targets!", true);
//            inputValid = true;
//            activated = false;
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
//        diceDiscs.returnTarget(targetPlayerID, chosenInput);
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //TODO: fill in
    }

    //activationData: [targetDiscIndex]

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        ArrayList<Integer> activationData = player.getActivationData();
        int targetPlayerID = player.getOtherPlayerID();
        int targetIndex = activationData.remove(0);

        diceDiscs.returnTarget(targetPlayerID, targetIndex);
    }

    @Override
    public void discarded() {
        //do nothing when discarded
    }
}
