package Roma.History;

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

    private ArrayList<PlayState> history = new ArrayList<PlayState>(); // Stores all the actions taken during this turn
    private int currentTurnNumber = 0;

    public TurnHistory(){
    }

    public ArrayList<PlayState> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<PlayState> history) {
        this.history = history;
    }

    public void addPlayState(PlayState playState){
        history.add(playState);
    }

    public int getCurrentTurnNumber() {
        return currentTurnNumber;
    }

    public void setCurrentTurnNumber(int currentTurnNumber) {
        this.currentTurnNumber = currentTurnNumber;
    }
}
