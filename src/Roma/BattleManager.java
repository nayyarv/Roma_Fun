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
    private boolean freeCharacters = false;
    private boolean freeBuildings = false;
    private boolean blocked[][] = new boolean[Roma.MAX_PLAYERS][DiceDiscs.CARD_POSITIONS];

    public BattleManager(){
        for(int defense : defenseMod){
            defense = 0;
        }
    }
}
