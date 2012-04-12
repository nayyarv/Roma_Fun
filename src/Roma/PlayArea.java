package Roma;

import java.util.*;

public class PlayArea {
    //Object pointers
    private Roma mainProgram;
	private CardManager cardManager;
	private DiceHolder diceHolder;
	private MoneyManager moneyManager;
	private VictoryTokens victoryTokens;
	private DiceDiscs diceDiscs;
	private Player players[];
    private Scanner input;

    //Variables
    private int turn = 0;
	
	public PlayArea(Roma mainProgram, Scanner input){
		cardManager = new CardManager();
		diceHolder = new DiceHolder();
		moneyManager = new MoneyManager();
		victoryTokens = new VictoryTokens(mainProgram);
		diceDiscs = new DiceDiscs(this);
		players = new Player[Roma.MAX_PLAYERS];
		this.mainProgram = mainProgram;
        this.input = input;

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

    public void runGame() {
        boolean endTurn = false;
        Player player = players[turn];
        char roll = 'b';

        if(player.getAutoRoll()){
            player.rollActionDice();
        } else {
            System.out.println("Press space to roll action dice. Press 'a' for automated dice roll for rest of game.");
            while(!(roll == ' ' || roll == 'a')){

            }
            if(roll == 'a'){
                player.setAutoRoll(true);
            }
        }
        while(!mainProgram.getGameOver() && !endTurn){
            endTurn = players[turn].takeAction(input);
        }
        turn++;
    }
}
