package Roma.Cards;

import Roma.CardManager;
import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/05/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardHolder implements Card {
    private final boolean isWrapper = false;

    private boolean playable = false;
    private Card contents;
    private PlayArea playArea;

    public CardHolder(Card card, PlayArea playArea) {
        contents = card;
        this.playArea = playArea;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public boolean getPlayable() {
        return playable;
    }

    public Card getContents() {
        return contents;
    }

    public void setContents(Card contents) {
        this.contents = contents;
    }

    public Card getContainer() {
        return null;
    }

    public void setContainer(Card holder) {
        assert false;
    }

    public boolean isActivateEnabled() {
        return contents.isActivateEnabled();
    }

    public String getName() {
        return contents.getName();
    }

    public String getType() {
        return contents.getType();
    }

    public String getDescription() {
        return contents.getDescription();
    }

    public int getCost() {
        return contents.getCost();
    }

    public int getDefense() {
        return contents.getDefense();
    }

    public boolean isWrapper() {
        return isWrapper;
    }

    public String toString() {
        return "Card Name: " + getName() + "; Type: " + getType() +
                "\nDescription: " + getDescription() +
                "\nCost: " + getCost() + "; Defence: " + getDefense();
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        contents.gatherData(player, position);
    }

    @Override
    public void activate(Player player, int position) {
        contents.activate(player, position);
    }

    @Override
    public void enterPlay(Player player, int position) {
        contents.enterPlay(player, position);
    }

    public void discarded(int targetPlayerID, int position) {
        contents.discarded(targetPlayerID, position);
    }

    @Override
    public void goingToDiscard(int targetPlayerID, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardManager cardManager = playArea.getCardManager();
        CardHolder[] playerActives = diceDiscs.getPlayerActives(targetPlayerID);
        cardManager.discard(playerActives[position]);
        leavePlay(targetPlayerID, position);
    }

    public void leavePlay(int targetPlayerID, int position) {
        DiceDiscs diceDiscs = playArea.getDiceDiscs();
        CardHolder[] playerActives = diceDiscs.getPlayerActives(targetPlayerID);
        playerActives[position] = null;
        contents.leavePlay(targetPlayerID, position);
        deleteAllWrappers();
    }

    @Override
    public int countLives() {
        return contents.countLives();
    }

    public void deleteAllWrappers() {
        Wrapper wrapper;
        //remove all wrappers
        while (contents.isWrapper()) {
            wrapper = (Wrapper) contents;
            wrapper.deleteThisWrapper();
        }
    }
}
