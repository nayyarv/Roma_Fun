package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legat extends CardBase {

    public final static String NAME = "Legat";
    final static String TYPE = CardBase.CHARACTER;
    final static String DESCRIPTION = "A player gets 1 victory point from the stockpile for" +
            "every dice disc not occupied by the opponent.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Legat(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        VictoryTokens victoryTokens = playArea.getVictoryTokens();
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;
        CardBase[] enemyCardBases = diceDiscs.getPlayerActives(targetPlayerID);
        int emptySlotCount = 0;

        for(CardBase cardBase : enemyCardBases){
            if(cardBase == null){
                emptySlotCount++;
            }
        }

        victoryTokens.playerFromPool(player.getPlayerID(), emptySlotCount);

        return activated;
    }
}
