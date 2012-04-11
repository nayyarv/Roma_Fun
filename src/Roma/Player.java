package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int playerID;
	private List<Card> hand = new ArrayList<Card>();
	private PlayArea playArea;
	
	public Player(int playerID, PlayArea playArea){
		this.playArea = playArea;
		this.playerID = playerID;
	}
	
	public void rollActionDice(){
		//playArea.rollPlayerDice(playerID);
	}
	
	public void drawCard(){
		//hand.add(playArea.drawCard());
	}
	
	public void placeCard(){
		Card card = null;
		
		//playArea.placeCard(card);
	}
	
	public void commit(){
		//playArea.commit();
	}
	
	public void allocateDice(){
		int diceID = 0;
		int position = 0;
		
		//playArea.allocateDice(diceID, position);
	}
	
	
}
