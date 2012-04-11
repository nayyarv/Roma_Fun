package Roma;

public class PlayArea {
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
		players = new Player[Roma.MAX_PLAYERS];
		
		for(int i = 0; i < Roma.MAX_PLAYERS; i++){
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
