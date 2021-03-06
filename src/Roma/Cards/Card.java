package Roma.Cards;

import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;

/**
 * User: Andrew Lem and Varun Nayyar
 * Date: 9/05/12
 * Time: 11:36 AM
 */
public interface Card {
    public final int CANCEL = PlayerInterface.CANCEL;
    public final static String CHARACTER = "Character";
    public final static String BUILDING = "Building";

    public boolean isActivateEnabled();

    public String getName();

    public String getType();

    public String getDescription();

    public int getCost();

    public int getDefense();

    public boolean isWrapper();

    public String toString();

    public void gatherData(Player player, int position) throws CancelAction;

    public void activate(Player player, int position);

    public void enterPlay(Player player, int position);

    public Card getContents();

    public void setContents(Card card);

    public Card getContainer();

    public void setContainer(Card holder);

    public void discarded(int targetPlayerID, int position);

    public void goingToDiscard(int targetPlayerID, int position);

    public void leavePlay(int targetPlayerID, int position);

    public int countLives();
}
