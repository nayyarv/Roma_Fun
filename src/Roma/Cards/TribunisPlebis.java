package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 3/04/12
 * Desc:
 */

public class TribunisPlebis extends CardBase {
    public final static String NAME = "Tribunis Plebis";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The player gets 1 victory point from their opponent.";
    final static int COST = 5;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public static ArrayList<Card> playSet(PlayArea playArea){
        ArrayList<Card> set = new ArrayList<Card>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new TribunisPlebis(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    private TribunisPlebis(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    public boolean activate(Player player, int position) {
        boolean activated = true;

        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int ID = player.getPlayerID();

        victoryTokens.playerToPlayer(otherPlayer(ID), player.getPlayerID(), 1);

        return activated;
    }
}