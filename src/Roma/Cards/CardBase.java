package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

public abstract class CardBase implements Card {
    private final boolean activateEnabled;
    private final String name;
    private final String type;
    private final String description;
    private final int cost;
    private final int defence;
    private final boolean wrapper = false;

    final PlayArea playArea;
    private ArrayList<Integer> playerActions;
    private Card container;

    //activated values
    protected boolean cardActivated = false;
    protected int playerID = -1;
    protected Dice activatingDice = null;

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

    public Card getContents(){
        return null;
    }
    public void setContents(Card card){
        assert(false);
    }

    public Card getContainer() {
        return container;
    }

    public void setContainer(Card holder) {
        container = holder;
    }

    public boolean isWrapper(){
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
        return "Card Name: " + name + "; Type: " + type + "\nDescription: " + description + "\nCost: "
                + cost + "; Defence: " + defence;
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

    @Override //TODO: wtf is this hack? :s
    public boolean equals(Object o) {
        //Takes either a string or card and compares them
        String test = "Equal";
        if (this == o) return true; //same object
        if (o == null) return false; //if we are comparing to null
        if (o.getClass()== test.getClass()){ // for strings
            //System.err.println("We have a string");
            //we have a string
            return (name.equalsIgnoreCase(o.toString()));
        }
        if (getClass() != o.getClass()) return false; //another class entirely
        Card card = (Card) o;
        //autogenerated
        return  !(name != null ? !name.equals(card.getName()) : card.getName() != null);
        //check they are the same name
    }

    @Override //TODO: wtf is this hack? :s
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public abstract boolean activate(Player player, int position);


    public int otherPlayer(int player) {
        if (player == Roma.PLAYER_ONE) {
            return Roma.PLAYER_TWO;
        } else {
            return Roma.PLAYER_ONE;
        }
    }
}