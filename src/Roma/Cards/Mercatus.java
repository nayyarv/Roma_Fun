package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Mercatus extends Card {

    final static String NAME = "Mercatus";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "The player gets 1 victory point from the opponent " +
                    " for every face-up Forum that the opponent has.";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    public Mercatus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        Card[] enemyCards = diceDiscs.getPlayerActives(otherPlayer(player.getPlayerID()));
        int forumCount = 0;

        for(Card card : enemyCards){
            if(card != null && card.getName() == Forum.NAME){
                forumCount++;
            }
        }

        victoryTokens.playerToPlayer(otherPlayer(player.getPlayerID()), player.getPlayerID(), forumCount);

        return activated;
    }

}
