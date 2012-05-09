package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Praetorianus extends CardBase {

    public final static String NAME = "Praetorianus";
    final static String TYPE = CardBase.CHARACTER;
    final static String DESCRIPTION = "Any of the opponent's dice discs can be blocked" +
            " for one go.";
    final static int COST = 8;
    final static int DEFENCE = 9;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;


    public Praetorianus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        BattleManager battleManager = playArea.getBattleManager();
        int targetDisc = -1;

        playArea.printStats();
        System.out.println("Blocking...");
        targetDisc = player.chooseCardDisc();
        if(targetDisc != -1){ // not cancel
            battleManager.block(otherPlayer(player.getPlayerID()), targetDisc);
        } else {
            activated = false;
        }

        return activated;
    }
}
