package Roma;

import Roma.Cards.*;
import Roma.PlayerInterfaceFiles.PlayerInterface;

/**
 * User: Andrew and Varun
 * Date: 26/04/12
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */

//TODO: flesh out battle manager while creating cards

public class BattleManager {
    DiceDiscs diceDiscs;
    DiceHolder diceHolder;

    public BattleManager(PlayArea playArea){
        this.diceDiscs = playArea.getDiceDiscs();
        this.diceHolder = playArea.getDiceHolder();
    }

    //TODO: refactor into diceDiscs and split up functionality
    public boolean battle(int targetPlayerID, int target){
        boolean kill = false;
        int battleValue[];
        CardHolder targetCard = diceDiscs.getTargetCard(targetPlayerID, target);
        int defense = targetCard.getDefense();

        PlayerInterface.printOut("BATTLE!", true);
        PlayerInterface.printOut("Defense to beat: " + defense, true);
        diceHolder.rollBattleDice();
        battleValue = diceHolder.getBattleValue();
        PlayerInterface.printOut("You rolled a: " + battleValue[0], true);

        if(battleValue[0] >= defense){
            diceDiscs.discardTarget(targetPlayerID, target);
            kill = true;
            PlayerInterface.printOut("Victory!", true);
        } else {
            PlayerInterface.printOut("Defeat!", true);
        }

        return kill;
    }
}
