package Roma;

import Roma.Cards.Card;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 26/04/12
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */

//TODO: flesh out battle manager while creating cards

public class BattleManager {
    private int[] defenseMod = new int[Roma.MAX_PLAYERS];
    private boolean active[][] = new boolean[Roma.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];
    private final PlayArea playArea;
    DiceDiscs diceDiscs;
    DiceHolder diceHolder;

    public BattleManager(PlayArea playArea){
        this.playArea = playArea;

        for(int defense : defenseMod){
            defense = 0;
        }
        for(int i = 0; i < Roma.MAX_PLAYERS; i++){
            for(int j = 0; j < DiceDiscs.CARD_POSITIONS; j++){
                active[i][j] = true;
            }
        }
        this.diceDiscs = playArea.getDiceDiscs();
        this.diceHolder = playArea.getDiceHolder();
    }

    public boolean checkBlock(int playerID, int position) {
        return active[playerID][position];
    }

    public void block(int playerID, int position) {
        active[playerID][position] = false;
    }

    public void unblock(int playerID, int position) {
        active[playerID][position] = true;
    }

    public int[] getDefenseMod() {
        return defenseMod;
    }

    public void setDefenseMod(int[] defenseMod) {
        this.defenseMod = defenseMod;
    }

    public boolean battle(int targetPlayer, int target){
        //TODO: battle initiated print statement
        boolean kill = false;
        int battleValue[];
        Card targetCard = diceDiscs.getTargetCard(targetPlayer, target);

        //TODO: Allow player to roll
        diceHolder.rollBattleDice();
        battleValue = diceHolder.getBattleValue();

        //TODO: Print battle die value

        if(battleValue[0] >= targetCard.getDefense() + defenseMod[targetPlayer]){
            diceDiscs.discardTarget(targetPlayer, target);
            kill = true;
        }

        return kill;
    }
}
