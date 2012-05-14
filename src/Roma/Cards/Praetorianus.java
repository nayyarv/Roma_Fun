package Roma.Cards;

import Roma.*;

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

    public static CardHolder makeOne(PlayArea playArea){
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


    private Praetorianus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        BattleManager battleManager = playArea.getBattleManager();
        int targetDisc = -1;

        playArea.printStats();
        System.out.println("Blocking...");
        targetDisc = player.chooseDiceDisc();
        if(targetDisc != -1){ // not cancel
            battleManager.block(otherPlayer(player.getPlayerID()), targetDisc);
        } else {
            activated = false;
        }

        return activated;
    }
}
