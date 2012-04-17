/*
* Author: Andrew Lem
* Date: 19/03/2012
* Desc: the class that will handle the dice in Roma
*/

package Roma;

import java.util.*;

public class DiceHolder {
    private final static boolean DEBUG = false;
    static final public int DICE_PER_PLAYER = 3;
    static final public int MAX_BATTLE_DICE = 1;

    private Dice[][] playerDice;
    private Dice[] battleDice;

    public DiceHolder() {
        if (DEBUG) {
            System.out.println("Constructing DiceHolder: ");
        }
        playerDice = new Dice[Roma.MAX_PLAYERS][DICE_PER_PLAYER];
        battleDice = new Dice[MAX_BATTLE_DICE];

        for (int i = 0; i < Roma.MAX_PLAYERS; i++) {
            for (int j = 0; j < DICE_PER_PLAYER; j++) {
                playerDice[i][j] = new Dice(i);
                if (DEBUG) {
                    System.out.println("Dice constructed");
                }
            }
        }

        for (int i = 0; i < MAX_BATTLE_DICE; i++) {
            battleDice[i] = new Dice(-1);
            if (DEBUG) {
                System.out.println("Dice constructed");
            }
        }

        if (DEBUG) {
            System.out.println("DiceHolder constructed");
        }
    }

    public List<Dice> rollPlayerDice(int player) {
        List<Dice> diceList = new ArrayList<Dice>();

        for (int i = 0; i < DICE_PER_PLAYER; i++) {
            playerDice[player][i].roll();
            diceList.add(playerDice[player][i]);
        }

        return diceList;
    }

    public boolean checkTriple(int player){
        boolean triple = true;

        for(int i = 0; triple && (i < DICE_PER_PLAYER - 1); i++){
            if(playerDice[player][i].getValue() != playerDice[player][i + 1].getValue()){
                triple = false;
            }
        }

        return triple;
    }

    public void rollBattleDice() {
        for (int i = 0; i < MAX_BATTLE_DICE; i++) {
            battleDice[i].roll();
        }
    }

    public int[] getBattleValue() {
        int[] diceValues = new int[MAX_BATTLE_DICE];

        for (int i = 0; i < MAX_BATTLE_DICE; i++) {
            diceValues[i] = battleDice[i].getValue();
        }

        return diceValues;
    }


    public int[] getPlayerValue(int player) {
        int[] diceValues = new int[DICE_PER_PLAYER];

        for (int i = 0; i < DICE_PER_PLAYER; i++) {
            diceValues[i] = playerDice[player][i].getValue();
        }

        return diceValues;
    }
}
