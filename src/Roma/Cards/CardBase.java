package Roma.Cards;

import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;
import Roma.PlayerInterfaceFiles.PlayerInterface;
import Roma.RomaGame;

import java.util.ArrayList;

public abstract class CardBase implements Card {
    public final int CANCEL = PlayerInterface.CANCEL;
    private final boolean activateEnabled;
    private final String name;
    private final String type;
    private final String description;
    private final int cost;
    private final int defence;
    private final boolean wrapper = false;

    final PlayArea playArea;
    private ArrayList<Integer> playerActions;
    Card container;
    CardHolder cardHolder;

    public CardBase(String name, String type, String description,
                    int cost, int defense, PlayArea playArea, boolean activateEnabled) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.defence = defense;
        this.playArea = playArea;
        this.activateEnabled = activateEnabled;
    }

    void setCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Card getContents() {
        return null;
    }

    public void setContents(Card card) {
        assert (false);
    }

    public Card getContainer() {
        return container;
    }

    public void setContainer(Card holder) {
        container = holder;
    }

    public boolean isWrapper() {
        return wrapper;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getDefense() {
        return defence;
    }

    public PlayArea getPlayArea() {
        return playArea;
    }

    public String toString() {
        return "Card Name: " + name + "; Type: " + type + "\nDescription: " + getDescription() + "\nCost: "
                + cost + "; Defence: " + defence;
    }

    public boolean isActivateEnabled() {
        return activateEnabled;
    }

    @Override
    public abstract void gatherData(Player player, int position) throws CancelAction;

    @Override
    public abstract void activate(Player player, int position);

    @Override
    public void enterPlay(Player player, int position) {
        //no enter play action by default
    }

    @Override
    public void discarded(int targetPlayerID, int position) {
        goingToDiscard(targetPlayerID, position);
    }

    @Override
    public void goingToDiscard(int targetPlayerID, int position) {
        container.goingToDiscard(targetPlayerID, position);
    }

    public abstract CardHolder makeOne(PlayArea playArea);

    public int otherPlayer(int player) {
        return ((player == RomaGame.PLAYER_ONE) ? RomaGame.PLAYER_TWO : RomaGame.PLAYER_ONE);
    }

    public void leavePlay(int targetPlayerID, int position) {
        //no leave play action by default
    }

    public int countLives() {
        return 1;
    }
}
