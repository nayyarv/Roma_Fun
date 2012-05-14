package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legat extends CardBase {

    public final static String NAME = "Legat";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "A player gets 1 victory point from the stockpile for" +
            "every dice disc not occupied by the opponent.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public static CardHolder makeOne(PlayArea playArea){
        Card card = new Legat(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Legat(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    private Legat(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;
        CardHolder[] enemyCards = diceDiscs.getPlayerActives(targetPlayerID);
        int emptySlotCount = 0;

        for(CardHolder card : enemyCards){
            if(card == null){
                emptySlotCount++;
            }
        }

        victoryTokens.playerFromPool(player.getPlayerID(), emptySlotCount);

        return activated;
    }
}
