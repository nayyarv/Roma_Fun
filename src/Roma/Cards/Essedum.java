package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Essedum extends CardBase {
    public final static String NAME = "Essedum";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The defence value of the opponent's face-up cards is reduced by 2.";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;
    final static int MOD_DEFENCE_ACTIVE = -2;

    public final static int OCCURENCES = 2;

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Essedum(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    private Essedum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        BattleManager battleManager = playArea.getBattleManager();

        boolean activated = true;
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;

        battleManager.modDefenseModActive(targetPlayerID, MOD_DEFENCE_ACTIVE);

        return activated;
    }
}
