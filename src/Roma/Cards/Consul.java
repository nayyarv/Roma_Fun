package Roma.Cards;

import Roma.Dice;
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
public class Consul extends CardBase {
    public final static String NAME = "Consul";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The score on an action die which has not yet been " +
            "used can be " +
            "increased or decreased by 1 point.";
    final static int COST = 3;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Consul(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Consul(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Consul(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


//    public boolean activate(Player player, int position) {
//        PlayerInterface playerInterface = player.getPlayerInterface();
//        final String strPrompt = "Would you like to...";
//        final String strOption1 = "Increase the die?";
//        final String strOption2 = "Decrease the die?";
//        final String strOption3 = "Cancel";
//        final int INCREASE = 1;
//        final int DECREASE = 2;
//        final int CANCEL = 3;
//
//        int choice = 3;
//        boolean validChoice = false;
//
//        boolean activated = true;
//        ArrayList<Dice> freeDice = player.getFreeDice();
//        Dice chosenDice = null;
//
//        if(freeDice.size() != 0){
//            chosenDice = player.getDieIndex(freeDice);
//            if(chosenDice == null){
//                activated = false;
//            } else {
//                while(!validChoice){
//                    choice = playerInterface.readInput(strPrompt, strOption1, strOption2, strOption3);
//                    if(choice == INCREASE){
//                        if(chosenDice.getValue() == Dice.MAX_DIE_VALUE){
//                            PlayerInterface.printOut("Can't increase die value over" + Dice.MAX_DIE_VALUE + "!", true);
//                        } else {
//                            chosenDice.incrementValue();
//                            validChoice = true;
//                        }
//                    } else if(choice == DECREASE){
//                        if(chosenDice.getValue() == Dice.MIN_DIE_VALUE){
//                            PlayerInterface.printOut("Can't decrease die value over" + Dice.MIN_DIE_VALUE + "!", true);
//                        } else {
//                            chosenDice.decrementValue();
//                            validChoice = false;
//                        }
//                    } else if(choice == CANCEL){
//                        validChoice = true;
//                        activated = false;
//                    }
//                }
//            }
//        }
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //TODO: fill in
    }

    //activation data: [diceIndex][+1/-1]

    @Override
    public void activate(Player player, int position) {
        ArrayList<Integer> activationData = player.getActivationData();
        int diceIndex = activationData.remove(0);
        int modValue = activationData.remove(0);
        ArrayList<Dice> freeDice = player.getFreeDice();
        if(modValue == 1){
            freeDice.get(diceIndex).incrementValue();
        } else if (modValue == -1){
            freeDice.get(diceIndex).decrementValue();
        } else {
            System.err.println("Error with Consul data");
            assert(false);
        }
    }

    @Override
    public void clearWrappers() {
        //do nothing when discarded
    }
}
