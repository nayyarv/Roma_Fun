package Testers;

import Roma.MoneyManager;
import Roma.PlayArea;
import Roma.RomaGame;

public class TestMoneyManager {
    public static void main(String[] args) {
        boolean pass = true;

        pass &= Test01();
        pass &= Test02();

        if (pass) {
            System.out.println("All tests passed! You are AWESOME!");
        }
    }

    private static void printStats(MoneyManager money) {
        System.out.println("Player one money count: " + money.getPlayerMoney(RomaGame.PLAYER_ONE));
        System.out.println("Player two money count: " + money.getPlayerMoney(RomaGame.PLAYER_TWO));
        System.out.println();
    }

    private static boolean Test01() {
        RomaGame newGame = new RomaGame();
        PlayArea playArea = new PlayArea(newGame);
        MoneyManager moneyManager = new MoneyManager();
        boolean pass = true;

        System.out.print("Taking money from the pool: ");
        moneyManager.gainMoney(RomaGame.PLAYER_ONE, 16);
        moneyManager.gainMoney(RomaGame.PLAYER_TWO, 13);

        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_ONE) != 16) {
            pass = false;
        }
        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_TWO) != 13) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        System.out.print("Returning money to the pool: ");
        moneyManager.loseMoney(RomaGame.PLAYER_ONE, 2);
        moneyManager.loseMoney(RomaGame.PLAYER_TWO, 4);

        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_ONE) != 14) {
            pass = false;
        }
        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_TWO) != 9) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        System.out.print("Player one taking money from player two: ");
        moneyManager.transferMoney(RomaGame.PLAYER_TWO, RomaGame.PLAYER_ONE, 9);

        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_ONE) != 23) {
            pass = false;
        }
        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_TWO) != 0) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        return pass;
    }

    private static boolean Test02() {
        RomaGame newGame = new RomaGame();
        PlayArea playArea = new PlayArea(newGame);
        MoneyManager moneyManager = new MoneyManager();
        boolean pass = true;

        moneyManager.gainMoney(RomaGame.PLAYER_ONE, 20);
        moneyManager.gainMoney(RomaGame.PLAYER_TWO, 20);
        System.out.print("Player two taking money from player one: ");
        moneyManager.transferMoney(RomaGame.PLAYER_ONE, RomaGame.PLAYER_TWO, 7);

        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_ONE) != 13) {
            pass = false;
        }
        if (moneyManager.getPlayerMoney(RomaGame.PLAYER_TWO) != 27) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        return pass;
    }
}
