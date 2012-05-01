package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

public abstract class Card {
    public final static String CHARACTER = "Character";
    public final static String BUILDING = "Building";

    private final boolean activateEnabled;
    private final String name;
    private final String type;
    private final String description;
    private final int cost;
    private final int defence;

    final PlayArea playArea;
    private boolean playable = false;
    private ArrayList<Integer> playerActions;

    //activated values
    protected boolean cardActivated = false;
    protected int playerID = -1;
    protected Dice activatingDice = null;

    public Card(String name, String type, String description, int cost, int defense, PlayArea playArea, boolean activateEnabled) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.defence = defense;
        this.playArea = playArea;
        this.activateEnabled = activateEnabled;
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

    public void cancelActivate(){
        if(cardActivated){
            playArea.getPlayer(playerID).getFreeDice().add(activatingDice);
            cardActivated = false;
        }
    }

    public boolean isActivateEnabled() {
        return activateEnabled;
    }

    public abstract boolean activate(Player player, int position);
}
