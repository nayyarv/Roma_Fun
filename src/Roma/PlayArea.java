package Roma;

import Roma.Cards.Roma;

public class PlayArea {
	public final static int MAX_PLAYERS = 2;
	public final static int PLAYER_ONE = 0;
	public final static int PLAYER_TWO = 1;
	
	private CardManager cardManager;
	private DiceHolder diceHolder;
	private MoneyManager moneyManager;
	private VictoryTokens victoryTokens;
	private DiceDiscs diceDiscs;
	private Player players[];
	
	public PlayArea(Roma newGame){
		cardManager = new CardManager();
		diceHolder = new DiceHolder();
		moneyManager = new MoneyManager();
		victoryTokens = new VictoryTokens(newGame);
		diceDiscs = new DiceDiscs(this);
		players = new Player[MAX_PLAYERS];
		
		for(int i = 0; i < MAX_PLAYERS; i++){
			players[i] = new Player(i, this);
		}
	}
	
	public CardManager getCardManager(){
		return cardManager;
	}
	
	public DiceHolder getDiceHolder(){
		return diceHolder;
	}
	
	public MoneyManager getMoneyManager(){
		return moneyManager;
	}
	
	public VictoryTokens getVictoryTokens(){
		return victoryTokens;
	}
	
	public DiceDiscs getDiceDiscs(){
		return diceDiscs;
	}
	
	public Player getPlayer(int playerID){
		return players[playerID];
	}
}
