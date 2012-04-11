package Roma;

import Roma.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int playerID;
	private List<Card> hand = new ArrayList<Card>();
	private PlayArea playArea;
	private List<Dice> freeDice;
	
	public Player(int playerID, PlayArea playArea){
		this.playArea = playArea;
		this.playerID = playerID;
	}
	
	public void rollActionDice(){
		freeDice = playArea.getDiceHolder().rollPlayerDice(playerID);
	}
	
	public void drawCard(){
		int diceChoice = 0;
		
		//player chooses which dice to use
		diceChoice = 0;
		
		hand.add(playArea.getCardManager().drawCard(freeDice.remove(diceChoice).getValue()));
	}
	
	public void placeCard(){
		Card card = null;
		int playerChoice = 0;
		int position = 3;
		
		// player chooses a card in hand
		do{
			playerChoice = 0;
			card = hand.get(playerChoice);
			position = 4;
		}while(!commit() && !card.isPlayable());
		
		
		
		//playArea.getDiceDiscs().placeCard(card, position);
	}
	
	public void checkPlayable(){
		MoneyManager moneyManager = playArea.getMoneyManager();
		Card card;
		
		for(int i = 0; i < hand.size(); i ++){
			card = hand.get(i);
			if(card.getCost() < moneyManager.getPlayerMoney(playerID)){
				card.setPlayable(true);
			}
		}
	}
	
	public boolean commit(){
		boolean confirm = true;
		
		// player confirms
		confirm = true;
		
		if(confirm){
			
		}
		
		return confirm;
	}

    public void cry(){

    }
}
