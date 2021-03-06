package Roma.Cards;

import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;
import Roma.VictoryTokens;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Mercatus extends CardBase {

    public final static String NAME = "Mercatus";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "The player gets 1 victory point from the opponent " +
            " for every face-up Forum that the opponent has.";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea) {
        CardBase card = new Mercatus(playArea);
        CardHolder cardHolder = new CardHolder(card, playArea);
        card.setContainer(cardHolder);
        card.setCardHolder(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea) {
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        CardBase card;

        for (int i = 0; i < OCCURENCES; i++) {
            card = new Mercatus(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            card.setCardHolder(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Mercatus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        int targetPlayerID = player.getOtherPlayerID();
        CardHolder[] enemyCards = playArea.getDiceDiscs().getPlayerActives(targetPlayerID);
        int forumCount = 0;

        for (CardHolder card : enemyCards) {
            if (card != null && (card.getName().equalsIgnoreCase(Forum.NAME))) {
                forumCount++;
            }
        }
        PlayerInterface.printOut("Get a victory token from your opponent for every Forum they have in play", true);
        PlayerInterface.printOut("Your opponent has " + forumCount + " forums", true);
        player.commit();
    }

    @Override
    public void activate(Player player, int position) {
        int targetPlayerID = player.getOtherPlayerID();
        CardHolder[] enemyCards = playArea.getDiceDiscs().getPlayerActives(targetPlayerID);
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int forumCount = 0;

        for (CardHolder card : enemyCards) {
            if (card != null && (card.getName().equalsIgnoreCase(Forum.NAME))) {
                forumCount++;
            }
        }

        victoryTokens.playerToPlayer(targetPlayerID, player.getPlayerID(), forumCount);
    }
}
