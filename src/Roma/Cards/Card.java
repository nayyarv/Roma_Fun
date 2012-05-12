package Roma.Cards;

import Roma.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/05/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Card {
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
    public boolean activate(Player player, int position);
    public Card getContents();
    public void setContents(Card card);
    public Card getContainer();
    public void setContainer(Card holder);

}
