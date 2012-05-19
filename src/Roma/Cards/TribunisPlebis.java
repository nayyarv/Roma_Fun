package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

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

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new TribunisPlebis(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
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

    TribunisPlebis(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        PlayerInterface.printOut("Take a victory token from your opponent", true);
        player.commit();
    }

    @Override
    public void activate(Player player, int position) {
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int playerID = player.getPlayerID();
        int targetPlayerID = player.getOtherPlayerID();

        victoryTokens.playerToPlayer(targetPlayerID, playerID, 1);
    }
}