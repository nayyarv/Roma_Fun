package Roma;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 26/04/12
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class BattleManager {
    private int[] defenseMod = new int[Roma.MAX_PLAYERS];
    private boolean freeCharacters = false;
    private boolean freeBuildings = false;

    public BattleManager(){
        for(int defense : defenseMod){
            defense = 0;
        }
    }
}
