package Roma;

//import testingVictoryTokens.Roma;

import Roma.Cards.Roma;

public class MoneyManager {
	public final static int STARTING_MONEY = 0;
	
	private int[] playerMoney = new int[Roma.MAX_PLAYERS];
	
	public MoneyManager(){
		
	}
	
	public void gainMoney(int player, int amount){
		playerMoney[player] += amount;
	}
	
	public boolean loseMoney(int player, int amount){
        boolean enoughMoney = true;
		if(amount > playerMoney[player]){
			System.out.println("Not enough money!");
            enoughMoney = false;
		} else {
			playerMoney[player] -= amount;
		}
        return false;
	}
	
	public boolean transferMoney(int from, int to, int amount){
        boolean enoughMoney = true;
        if(amount > playerMoney[from]){
			System.out.println("Not enough money!");
            enoughMoney  = false;
		} else {
			playerMoney[from] -= amount;
			playerMoney[to] += amount;
		}
        return enoughMoney;
	}
	
	public int getPlayerMoney(int player){
		return playerMoney[player];
	}

    public String toString(){
        return "Player One has: $" + playerMoney[PlayArea.PLAYER_ONE] + ", and Player Two has: $"+
                playerMoney[PlayArea.PLAYER_TWO];
    }
}
