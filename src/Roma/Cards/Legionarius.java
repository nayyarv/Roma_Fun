package Roma.Cards;

import Roma.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Legionarius extends Card {
    public final static String NAME = "Legionarius";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Attacks the opponent's card which is directly opposite, " +
            "whether it is a character or a building card.";
    final static int COST = 4;
    final static int DEFENCE = 5;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 3;

    public Legionarius(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        BattleManager battleManager = playArea.getBattleManager();
        int targetPlayerID = (player.getPlayerID() + 1) % Roma.MAX_PLAYERS;

        if(diceDiscs.getTargetCard(targetPlayerID, position) == null){
            System.out.println("No card to attack!");
            activated = false;
        } else {
            battleManager.battle(targetPlayerID, position);
        }

        return activated;
    }
}
