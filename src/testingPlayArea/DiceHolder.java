
/*
 * Author: Andrew Lem
 * Date: 19/03/2012
 * Desc: the class that will handle the dice in Roma
 */

package testingPlayArea;

import java.util.Random;

public class DiceHolder {
	static final public int DICE_PER_PLAYER = 3;
	static final public int MAX_BATTLE_DICE = 1;
	static final public int DICE_SIZE = 6;
	private final static boolean DEBUG = false;
	
	Random generator = new Random();
	
	private Dice[][] playerDice;
	private boolean[][] usedDice;
	private Dice[] battleDice;
	
	public DiceHolder(){
		if(DEBUG){
			System.out.println("Constructing DiceHolder: ");
		}
		playerDice = new Dice[PlayArea.MAX_PLAYERS][DICE_PER_PLAYER];
		battleDice = new Dice[MAX_BATTLE_DICE];
		
		for(int i = 0; i < PlayArea.MAX_PLAYERS; i++){
			for(int j = 0; j < DICE_PER_PLAYER; j++){
				playerDice[i][j] = new Dice();
				if(DEBUG){
					System.out.println("Dice constructed");
				}
			}
		}
		
		for(int i = 0; i < MAX_BATTLE_DICE; i++){
			battleDice[i] = new Dice(); 
			if(DEBUG){
				System.out.println("Dice constructed");
			}
		}
		
		cleanUsedDice();
		
		if(DEBUG){
			System.out.println("DiceHolder constructed");
		}
	}
	
	public void cleanUsedDice(){
		for(int i = 0; i < PlayArea.MAX_PLAYERS; i++){
			for(int j = 0; j < DICE_PER_PLAYER; j++){
				// Testing
				//usedDice[i][j] = false;
			}
		}
	}
	
	public void rollPlayerDice(int player){
		for(int i = 0; i < DICE_PER_PLAYER; i++){
			playerDice[player][i].roll();
		}
	}
	
	public void rollBattleDice(){
		for(int i = 0; i < MAX_BATTLE_DICE; i++){
			battleDice[i].roll();
		}
	}
	
	public int[] getPlayerValue(int player){
		int[] diceValues = new int[DICE_PER_PLAYER];
		
		for(int i = 0; i < DICE_PER_PLAYER; i++){
			diceValues[i] = playerDice[player][i].getValue();
		}
		
		return diceValues;
	}
	
	public int[] getBattleValue(){
		int[] diceValues = new int[MAX_BATTLE_DICE];
		
		for(int i = 0; i < MAX_BATTLE_DICE; i++){
			diceValues[i] = battleDice[i].getValue();
		}
		
		return diceValues;
	}
	
	private class Dice{
		private int value = 10;
		
		private Dice(){
			if(DEBUG){
				System.out.println("Constructing Dice: ");
			}
			roll();
		}
		
		private void roll(){
			value = generator.nextInt(DICE_SIZE);
			value++;
		}
		
		private int getValue(){
			return value;
		}
	}
}
