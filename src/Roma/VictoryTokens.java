/*
 * Author: Andrew Lem
 * Date: 19/03/2012
 * Desc: class that will handle Victory Tokens in the game Roma
 */

package Roma;

import Roma.PlayerInterfaceFiles.PlayerInterface;

public class VictoryTokens {
    public final static int END_GAME_VALUE = 0;
    public final static int MAX_TOKENS = 36;
    public final static int TOKEN_START_VALUE = 10;

    final private PlayArea playArea;  // pointer to the main game to activate the gameOverMessage() function

    private int[] playerTokens = new int[RomaGame.MAX_PLAYERS];
    private int tokenPool;


    public VictoryTokens(PlayArea playArea) {
        reset();
        this.playArea = playArea;
    }

    public void reset() {
        for (int i = 0; i < RomaGame.MAX_PLAYERS; i++) {
            playerTokens[i] = TOKEN_START_VALUE;
        }
        tokenPool = MAX_TOKENS - (RomaGame.MAX_PLAYERS * TOKEN_START_VALUE);
    }

    public void playerFromPool(int playerID, int amount) {
        playerTokens[playerID] += amount;
        tokenPool -= amount;
        checkEndConditions();
    }

    public void playerToPool(int playerID, int amount) {
        playerTokens[playerID] -= amount;
        tokenPool += amount;
        checkEndConditions();
    }

    public void playerToPlayer(int fromPlayerID, int toPlayerID, int amount) {
        playerTokens[fromPlayerID] -= amount;
        playerTokens[toPlayerID] += amount;
        checkEndConditions();
    }

    private void checkEndConditions(){
        boolean shouldGameEnd = false;
        PlayerInterface playerInterface = playArea.getPlayerInterface();

        if(tokenPool <= END_GAME_VALUE){
            shouldGameEnd = true;
        }
        for (int i = 0; !shouldGameEnd && i < RomaGame.MAX_PLAYERS; i++){
            if(playerTokens[i] <= END_GAME_VALUE){
                shouldGameEnd = true;
            }
         } //checks each player isn't bankrupt of victory tokens
        if(shouldGameEnd){
            for (int i=0; i< RomaGame.MAX_PLAYERS;i++){
                PlayerInterface.printOut("Player"+(i+1)+": "+playerTokens[i], true);
            }

            PlayerInterface.printOut("Token Pool: " + tokenPool, true);

            playArea.endGame();
        }
    }

    public void setPlayerTokens(int playerID, int num) {
        int currPlayerTokens = playerTokens[playerID];
        if(num>currPlayerTokens) {
            //i.e. he needs more
            playerFromPool(playerID, num-currPlayerTokens);
        } else {
            //i.e it has more than the set
            playerToPool(playerID, currPlayerTokens-num);
        }
        playerTokens[playerID] = num;
    }

    public int getPlayerTokens(int playerID) {
        return playerTokens[playerID];
    }

    public int getPoolTokens() {
        return tokenPool;
    }

    public String toString() {
        return "Player One has: " + playerTokens[RomaGame.PLAYER_ONE] + " tokens, and Player Two has: " +
                playerTokens[RomaGame.PLAYER_TWO] + " Tokens";
    }
}
