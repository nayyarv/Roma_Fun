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

}
