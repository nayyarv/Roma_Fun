package Roma.Cards;

import Roma.*;

public abstract class Card {
	private final String name;
	private final String type;
	private final String description;
	private final int cost;
	private final int defence;
	private final PlayArea playArea;
	
	public Card(String name, String type, String description, int cost, int defense, PlayArea playArea){
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

    public String toString(){
        return  "Card Name: " + name + "; Type: "+ type+"\nDescription: " + description + "\nCost: "
                + cost + "; Defence: " + defence;

    }



	public abstract void activate(int player);
}
