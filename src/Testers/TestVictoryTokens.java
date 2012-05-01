package Testers;

import Roma.*;

public class TestVictoryTokens {

    public static void main(String[] args) {
        boolean pass = true;

        pass &= Test01();
        pass &= Test02();
        pass &= Test03();

        if (pass) {
            System.out.println("All tests passed! You are AWESOME!");
        }
    }

    private static void printStats(VictoryTokens tokens) {
        System.out.println("Player one victory token count: " + tokens.getPlayerTokens(Roma.PLAYER_ONE));
        System.out.println("Player two victory token count: " + tokens.getPlayerTokens(Roma.PLAYER_TWO));
        System.out.println("Pool victory token count: " + tokens.getPoolTokens());
        System.out.println();
    }

    private static boolean Test01() {
        Roma newGame = new Roma();
        PlayArea playArea = new PlayArea(newGame);
        VictoryTokens victoryTokens = new VictoryTokens(playArea);
        boolean pass = true;

        System.out.println("Running Test01: ");

        System.out.print("Taking tokens from the pool: ");
        victoryTokens.playerFromPool(Roma.PLAYER_ONE, 6);
        victoryTokens.playerFromPool(Roma.PLAYER_TWO, 3);

        if (victoryTokens.getPlayerTokens(Roma.PLAYER_ONE) != 16) {
            pass = false;
        }
        if (victoryTokens.getPlayerTokens(Roma.PLAYER_TWO) != 13) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        System.out.print("Returning tokens to the pool: ");
        victoryTokens.playerToPool(Roma.PLAYER_ONE, 2);
        victoryTokens.playerToPool(Roma.PLAYER_TWO, 4);

        if (victoryTokens.getPlayerTokens(Roma.PLAYER_ONE) != 14) {
            pass = false;
        }
        if (victoryTokens.getPlayerTokens(Roma.PLAYER_TWO) != 9) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        System.out.print("Player one taking tokens from player two: ");
        victoryTokens.playerToPlayer(Roma.PLAYER_TWO, Roma.PLAYER_ONE, 31);

        if (victoryTokens.getPlayerTokens(Roma.PLAYER_ONE) != 45) {
            pass = false;
        }
        if (!newGame.getGameOver()) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        return pass;
    }

    private static boolean Test02() {
        Roma newGame = new Roma();
        PlayArea playArea = new PlayArea(newGame);
        VictoryTokens victoryTokens = new VictoryTokens(playArea);
        boolean pass = true;

        System.out.print("Player two taking tokens from player one: ");
        victoryTokens.playerToPlayer(Roma.PLAYER_ONE, Roma.PLAYER_TWO, 31);

        if (victoryTokens.getPlayerTokens(Roma.PLAYER_TWO) != 41) {
            pass = false;
        }
        if (!newGame.getGameOver()) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        return pass;
    }

    private static boolean Test03() {
        Roma newGame = new Roma();
        PlayArea playArea = new PlayArea(newGame);
        VictoryTokens victoryTokens = new VictoryTokens(playArea);
        boolean pass = true;

        System.out.print("Player one emptying the pool: ");
        victoryTokens.playerFromPool(Roma.PLAYER_ONE, 16);

        if (!newGame.getGameOver()) {
            pass = false;
        }

        if (pass) {
            System.out.println("PASS");
        }

        return pass;
    }
}
