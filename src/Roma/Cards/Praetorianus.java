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
public class Praetorianus extends CardBase {

    public final static String NAME = "Praetorianus";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Any of the opponent's dice discs can be blocked" +
            " for one go.";
    final static int COST = 8;
    final static int DEFENCE = 9;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Praetorianus(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Praetorianus(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    Praetorianus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

//    @Override
//    public ArrayList<Integer> gatherData(Player player, int position) throws CancelAction {
//        int targetDisc = -1;
//        playArea.printStats();
//        targetDisc = player.getDiceDiscIndex("");
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public boolean activate(Player player, int position, ArrayList<Integer> activationData) {
//        boolean activated = true;
//        BattleManager battleManager = playArea.getBattleManager();
//        int targetDisc = -1;
//
//        PlayerInterface.printOut("Blocking...", true);
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //TODO: fill in
    }

    //activationData: [targetIndex]

    @Override
    public void activate(Player player, int position) {
        ArrayList<Integer> activationData = player.getActivationData();
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        int targetIndex = activationData.remove(0);
        CardHolder[] enemyActives = diceDiscs.getPlayerActives(targetPlayerID);
        CardHolder targetCard = enemyActives[targetIndex];
        Wrapper wrapper = new Wrapper(targetCard);

        wrapper.disableActivate();
        playArea.addToEndNextTurnList(wrapper);
    }

    @Override
    public void clearWrappers() {
        //do nothing when discarded
    }
}
