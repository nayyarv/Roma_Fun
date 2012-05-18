package Roma.Cards;

import Roma.DiceDiscs;
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
            if(card.getType().equalsIgnoreCase(Card.CHARACTER)){
                card.setPlayable(true);
            }
        }
        PlayerInterface.printOut("Return which character to your opponent's hand?", true);
        targetIndex = player.getDiceDiscIndex(activeCards, false, true);

        player.commit();
        activationData.add(targetIndex);
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
    public void enterPlay(Player player, int position) {
        //no enter play action
    }

    @Override
    public void leavePlay() {
        //do nothing when leaving play
    }
}
