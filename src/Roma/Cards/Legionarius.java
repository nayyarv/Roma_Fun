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
public class Legionarius extends CardBase {
    public final static String NAME = "Legionarius";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Attacks the opponent's card which is directly opposite, " +
            "whether it is a character or a building card.";
    final static int COST = 4;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 3;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Legionarius(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Legionarius(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Legionarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


//    public boolean activate(Player player, int position) {
//        boolean activated = true;
//        DiceDiscs diceDiscs = playArea.getDiceDiscs();
//        BattleManager battleManager = playArea.getBattleManager();
//        int targetPlayerID = player.getOtherPlayerID();
//
//        if(diceDiscs.getTargetCard(targetPlayerID, position) == null){
//            PlayerInterface.printOut("No card to attack!", true);
//            activated = false;
//        } else {
//            battleManager.battle(targetPlayerID, position);
//        }
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();

        PlayerInterface.printOut("Attack a directly opposite card", true);
        if(diceDiscs.getTargetCard(targetPlayerID, position) == null){
            PlayerInterface.printOut("No card to attack!", true);
            player.cancel();
        } else {
            player.commit();
        }
    }

    //activationData: no data
    //checks currentAction for battleValue

    @Override
    public void activate(Player player, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        int targetPlayerID = player.getOtherPlayerID();
        int battleValue = player.getCurrentAction().getBattleDice();
        diceDiscs.battle(targetPlayerID, position, battleValue);
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
