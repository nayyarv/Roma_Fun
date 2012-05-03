package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Essedum extends Card {
    public final static String NAME = "Essedum";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "The defence value of the opponent's face-up cards is reduced by 2.";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;
    final static int MOD_DEFENCE_ACTIVE = -2;

    public final static int OCCURENCES = 2;


    public Essedum(PlayArea playArea) {
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
