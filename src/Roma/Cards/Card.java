package Roma.Cards;

import Roma.Dice;
import Roma.PlayArea;

public abstract class Card {
    private final String name;
    private final String type;
    private final String description;
    private final int cost;
    private final int defence;
    private final PlayArea playArea;
    private boolean playable = false;

    //TODO: needs a "canBeActivated" boolean value for passive cards
    // for the card that blocks dice disc activation, have an "environment effect: cantBeActivated" or something

    //activated values
    protected boolean cardActivated = false;
    protected int playerID = -1;
    protected Dice activatingDice = null;

    public Card(String name, String type, String description, int cost, int defense, PlayArea playArea) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.defence = defense;
        this.playArea = playArea;
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
        return "Card Name: " + name + "; Type: " + type + "\nDescription: " + description + "\nCost: "
                + cost + "; Defence: " + defence;

    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public abstract void activate(int player);
}
