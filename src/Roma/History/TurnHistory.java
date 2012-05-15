package Roma.History;

import Roma.Roma;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 15/05/12
 * Desc:
 */
public class TurnHistory {

  /*
   * Stores all the actions made by the player in a SINGLE turn
   * Action data takes care of all the data needed
   * turnNumber: which turn we are at - starting at 0
   */

    private ArrayList<ActionData> history; // Stores all the actions taken during this turn
    private PlayState playState; // Stores the game state at the beginning of turn
    private int turnNumber;




    public TurnHistory(){

    }

}
