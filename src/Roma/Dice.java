package Roma;

import java.util.Random;

public class Dice {
    static final public int DICE_SIZE = 6;
    private final static boolean DEBUG = false;
    //TODO: Review a playerID value in dice
    private final int playerID;

    Random generator = new Random();

    private int value = 0;

    public Dice(int playerID) {
        if (DEBUG) {
            System.out.println("Constructing Dice: ");
        }

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
}
