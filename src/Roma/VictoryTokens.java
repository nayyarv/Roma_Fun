/*
 * Author: Andrew Lem
 * Date: 19/03/2012
 * Desc: class that will handle Victory Tokens in the game Roma
 */

package Roma;

public class VictoryTokens {
	public final static int END_GAME_VALUE = 0;
	public final static int MAX_TOKENS = 36;
	public final static int TOKEN_START_VALUE = 10;
	
	final private Roma game;  // pointer to the main game to activate the endGame() function
	
	private int[] playerTokens = new int[Roma.MAX_PLAYERS];
	private int tokenPool;
	
	
	public VictoryTokens(Roma game){
		reset();
		
		this.game = game;
	}
	
	public void reset(){
		for(int i = 0; i < Roma.MAX_PLAYERS; i++){
			playerTokens[i] = TOKEN_START_VALUE;	
		}
		tokenPool = MAX_TOKENS - (Roma.MAX_PLAYERS * TOKEN_START_VALUE);
	}
	
	public void playerFromPool(int player, int amount){
		playerTokens[player] += amount;
		tokenPool -= amount;
		if(tokenPool <= END_GAME_VALUE){
			game.endGame();
		}
	}
	
	public void playerToPool(int player, int amount){
		playerTokens[player] -= amount;
		tokenPool += amount;
		if(playerTokens[player] <= END_GAME_VALUE){
			game.endGame();
		}
	}
	
	public void playerToPlayer(int fromPlayer, int toPlayer, int amount){
		playerTokens[fromPlayer] -= amount;
		playerTokens[toPlayer] += amount;
		if(playerTokens[fromPlayer] <= END_GAME_VALUE){
			game.endGame();
		}
	}
	
	public int getPlayerTokens(int player){
		return playerTokens[player];
	}
	
	public int getPoolTokens(){
		return tokenPool;
	}

    public String toString(){
        return "Player One has: " + playerTokens[Roma.PLAYER_ONE] + " tokens, and Player Two has: "+
                playerTokens[Roma.PLAYER_TWO] + " Tokens";
	}

    public void cry(){
        System.out.println("wtf");
    }
}
