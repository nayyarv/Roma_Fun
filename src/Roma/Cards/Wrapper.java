package Roma.Cards;

import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/05/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Wrapper implements Card {
    private final static int INITIAL_SHIFT = 0;
    private final static int INITIAL_SCALE = 1;
    private final boolean isWrapper = true;

    private Card container;
    private Card contents;
    private boolean activateEnabled;
    private int defenseShift = INITIAL_SHIFT;
    private int defenseScale = INITIAL_SCALE;
    private int costShift = INITIAL_SHIFT;
    private int costScale = INITIAL_SCALE;

    public Wrapper(Card card, Card holder){
        contents = card;
        container = holder;
        activateEnabled = contents.isActivateEnabled();
    }

    public Card getContents() {
        return contents;
    }

    public void setContents(Card card){
        contents = card;
    }

    public Card getContainer(){
        return container;
    }

    public void setContainer(Card holder){
        container = holder;
    }

    @Override
    public void discarded() {
        //To change body of implemented methods use File | Settings | File Templates.
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

    public String toString() {
        return "Card Name: " + getName() + "; Type: " + getType() +
               "\nDescription: " + getDescription() +
                "\nCost: " + getCost() + "; Defence: " + getDefense();
    }

    @Override
    public ArrayList<Integer> gatherData(Player player, int position) throws CancelAction {
        return contents.gatherData(player, position);
    }

    @Override
    public boolean activate(Player player, int position, ArrayList<Integer> activationData) {
        return contents.activate(player, position, activationData);
    }

    public void deleteThisWrapper(){
        if(container != null && contents != null){
            container.setContents(contents);
            contents.setContainer(container);
        }
    }
}
