package Testers;

import Roma.*;

public class TestMoneyManager {
	public static void main(String[] args){
		boolean pass = true;
		
		pass &= Test01();
		pass &= Test02();
		
		if(pass){
			System.out.println("All tests passed! You are AWESOME!");
		}
	}
	
	private static void printStats(MoneyManager money){
		System.out.println("Player one money count: " + money.getPlayerMoney(Roma.PLAYER_ONE));
		System.out.println("Player two money count: " + money.getPlayerMoney(Roma.PLAYER_TWO));
		System.out.println();
	}
	
	private static boolean Test01(){
        Roma newGame = new Roma();
		PlayArea playArea = new PlayArea(newGame);
		MoneyManager moneyManager = new MoneyManager();
		boolean pass = true;
		
		System.out.print("Taking money from the pool: ");
		moneyManager.gainMoney(Roma.PLAYER_ONE, 16);
		moneyManager.gainMoney(Roma.PLAYER_TWO, 13);
		
		if(moneyManager.getPlayerMoney(Roma.PLAYER_ONE) != 16){
			pass = false;
		}
		if(moneyManager.getPlayerMoney(Roma.PLAYER_TWO) != 13){
			pass = false;
		}
		
		if(pass){
			System.out.println("PASS");
		}
		
		System.out.print("Returning money to the pool: ");
		moneyManager.loseMoney(Roma.PLAYER_ONE, 2);
		moneyManager.loseMoney(Roma.PLAYER_TWO, 4);
		
		if(moneyManager.getPlayerMoney(Roma.PLAYER_ONE) != 14){
			pass = false;
		}
		if(moneyManager.getPlayerMoney(Roma.PLAYER_TWO) != 9){
			pass = false;
		}
		
		if(pass){
			System.out.println("PASS");
		}
		
		System.out.print("Player one taking money from player two: ");
		moneyManager.transferMoney(Roma.PLAYER_TWO, Roma.PLAYER_ONE, 9);
		
		if(moneyManager.getPlayerMoney(Roma.PLAYER_ONE) != 23){
			pass = false;
		}
		if(moneyManager.getPlayerMoney(Roma.PLAYER_TWO) != 0){
			pass = false;
		}
		
		if(pass){
			System.out.println("PASS");
		}
		
		return pass;
	}
	
	private static boolean Test02(){
        Roma newGame = new Roma();
        PlayArea playArea = new PlayArea(newGame);
        MoneyManager moneyManager = new MoneyManager();
		boolean pass = true;
		
		moneyManager.gainMoney(Roma.PLAYER_ONE, 20);
		moneyManager.gainMoney(Roma.PLAYER_TWO, 20);
		System.out.print("Player two taking money from player one: ");
		moneyManager.transferMoney(Roma.PLAYER_ONE, Roma.PLAYER_TWO, 7);
		
		if(moneyManager.getPlayerMoney(Roma.PLAYER_ONE) != 13){
			pass = false;
		}
		if(moneyManager.getPlayerMoney(Roma.PLAYER_TWO) != 27){
			pass = false;
		}
		
		if(pass){
			System.out.println("PASS");
		}
		
		return pass;
	}
}
