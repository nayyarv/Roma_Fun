package Roma.Cards;

import Roma.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/05/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Wrapper implements Card {
    private final boolean isWrapper = true;

    private Card contents;
    private boolean activateEnabled;
    private int defenseShift = 0;
    private int defenseScale = 1;
    private int costShift = 0;
    private int costScale = 1;
    private boolean inWrapper = false;

    public Wrapper(Card card){
        contents = card;
        contents.putInWrapper();
        activateEnabled = contents.isActivateEnabled();
    }

    public Card getContents() {
        contents.takeFromWrapper();
        return contents;
    }

    public void setDefenseShift(int defenseShift) {
        this.defenseShift = defenseShift;
    }

    public void setDefenseScale(int defenseScale) {
        this.defenseScale = defenseScale;
    }

    public void setCostShift(int costShift) {
        this.costShift = costShift;
    }

    public void setCostScale(int costScale) {
        this.costScale = costScale;
    }

    public void enableActivate(){
        activateEnabled = true;
    }

    public void disableActivate(){
        activateEnabled = false;
    }

    @Override
    public boolean isActivateEnabled() {
        return activateEnabled;
    }

    @Override
    public String getName() {
        return contents.getName();
    }

    @Override
    public String getType() {
        return contents.getType();
    }

    @Override
    public String getDescription() {
        return contents.getDescription();
    }

    @Override
    public int getCost() {
        int cost;

        cost = contents.getCost() * costScale + costShift;

        return cost;
    }

    @Override
    public int getDefense() {
        int defense;

        defense = contents.getDefense() * defenseScale + defenseShift;

        return defense;
    }

    @Override
    public boolean isWrapper() {
        return isWrapper;
    }

    @Override
    public boolean isInWrapper() {
        return inWrapper;
    }

    @Override
    public void putInWrapper() {
        inWrapper = true;
    }

    @Override
    public void takeFromWrapper() {
        inWrapper = false;
    }

    public String toString() {
        return "Card Name: " + contents.getName() + "; Type: " + contents.getType() +
               "\nDescription: " + contents.getDescription() + "\nCost: " + getCost() + "; Defence: " + getDefense();
    }

    public boolean activate(Player player, int position){
        return contents.activate(player, position);
    }
}
