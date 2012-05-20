package Roma;

import Roma.PlayerInterfaceFiles.PlayerInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 5/19/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndGameInterrupt extends Exception {
    public void message(){
        PlayerInterface.printOut("GAME OVER!", true);
    }
}
