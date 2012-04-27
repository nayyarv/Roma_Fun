package Roma;

import java.util.Random;

public class Dice {
    static final public int DICE_SIZE = 6;
    private final static boolean DEBUG = false;
    private final int playerID;

    Random generator = new Random();

    private int value = 0;

    public Dice(int playerID) {
        this.playerID = playerID;
        roll();
    }

    public void roll() {
        value = generator.nextInt(DICE_SIZE);
        value++;
    }

    public int getValue() {
        return value;
    }

    public int getPlayerID() {
        return playerID;
    }
}
