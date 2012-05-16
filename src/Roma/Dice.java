package Roma;

import java.util.Random;

/**
 * File Name:
 * Creator: Andrew Lem & Varun Nayyar
 * Date: 19/03/12
 * Desc: This object creates a Dice object
 *       which is simply a random number generator,
 *       It has other useful functions too - which are specific to Roma
 */

public class Dice {
    static final public int MAX_DIE_VALUE = 6;
    static final public int MIN_DIE_VALUE = 1;
    private final int playerID;

    Random generator = new Random();

    private Integer value = 0;

    public Dice(int playerID) {
        this.playerID = playerID;
        roll();
    }

    public void roll() {
        value = generator.nextInt(MAX_DIE_VALUE);
        value++;
    }

    public Integer getValue() {
        return value;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void incrementValue(){
        value++;
    }

    public void decrementValue(){
        value--;
    }
}
