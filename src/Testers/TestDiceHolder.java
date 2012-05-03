package Testers;

import Roma.Dice;
import Roma.DiceHolder;

public class TestDiceHolder {
    static final public int MAX_PLAYERS = 2;

    private final static int PLAYER_ONE = 0;
    private final static int PLAYER_TWO = 1;

    static final private int SAMPLE_SIZE = 1000000;

    static DiceHolder diceHolder;

    public static void main(String[] args) {
        diceHolder = new DiceHolder();
        printStats();
        printStats();
        System.out.println();

        System.out.println("Rolling battle dice");
        diceHolder.rollBattleDice();
        printStats();
        System.out.println();

        System.out.println("Rolling player one dice");
        diceHolder.rollPlayerDice(PLAYER_ONE);
        printStats();
        System.out.println();

        System.out.println("Rolling player two dice");
        diceHolder.rollPlayerDice(PLAYER_TWO);
        printStats();
        System.out.println();

        statisticsTest();
    }

    private static void printStats() {

        for (int i = 0; i < MAX_PLAYERS; i++) {
            System.out.print("Player " + (i + 1) + " dice values: ");
            for (int j = 0; j < DiceHolder.DICE_PER_PLAYER; j++) {
                System.out.print(diceHolder.getPlayerValue(i)[j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < DiceHolder.MAX_BATTLE_DICE; i++) {
            System.out.print("Battle dice value: " + diceHolder.getBattleValue()[i]);
        }
        System.out.println();
    }

    private static void statisticsTest() {
        int[] diceValues = new int[Dice.MAX_DIE_VALUE];

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            diceValues[--(diceHolder.getBattleValue()[0])]++;
            diceHolder.rollBattleDice();
        }

        System.out.println("Dice values: ");
        for (int i = 0; i < Dice.MAX_DIE_VALUE; i++) {
            System.out.println((i + 1) + " = " + (double) diceValues[i] / SAMPLE_SIZE * 100 + "%");
        }
    }
}
