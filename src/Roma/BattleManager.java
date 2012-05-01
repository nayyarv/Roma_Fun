package Roma;

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
    private boolean blocked[][] = new boolean[Roma.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];

    public BattleManager(){
        for(int defense : defenseMod){
            defense = 0;
        }
        for(int i = 0; i < Roma.MAX_PLAYERS; i++){
            for(int j = 0; j < DiceDiscs.CARD_POSITIONS; j++){
                blocked[i][j] = false;
            }
        }
    }

    public boolean getBlocked(int playerID, int position) {
        return blocked[playerID][position];
    }

    public void block(int playerID, int position) {
        blocked[playerID][position] = true;
    }

    public void unblock(int playerID, int position) {
        blocked[playerID][position] = false;
    }

    public int[] getDefenseMod() {
        return defenseMod;
    }

    public void setDefenseMod(int[] defenseMod) {
        this.defenseMod = defenseMod;
    }
}
