package Roma;

import java.util.Random;

public class Dice {
    private final static boolean DEBUG = false;
    static final public int DICE_SIZE = 6;

    Random generator = new Random();

    private int value = 0;

    public Dice() {
        if (DEBUG) {
            System.out.println("Constructing Dice: ");
        }
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
