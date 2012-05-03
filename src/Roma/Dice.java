package Roma;

import java.util.Random;

public class Dice {
    static final public int MAX_DIE_VALUE = 6;
    static final public int MIN_DIE_VALUE = 1;
    private final static boolean DEBUG = false;
    private final int playerID;

    Random generator = new Random();

    private int value = 0;

    public Dice(int playerID) {
        this.playerID = playerID;
        roll();
    }

    public void roll() {
        value = generator.nextInt(MAX_DIE_VALUE);
        value++;
    }

    public int getValue() {
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
