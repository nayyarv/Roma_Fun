package Implementers.ImplementedActivators;

import Implementers.ActivatorFunctions;
import Roma.Player;
import framework.interfaces.activators.TelephoneBoxActivator;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 22/05/12
 * Desc:
 */
public class TelephoneBoxActivatorImpl  implements TelephoneBoxActivator {


    Player player;
    int secondDie;
    int targetIndex;
    int direction;
    public TelephoneBoxActivatorImpl(Player player) {
        this.player = player;

    }

    /**
     * Select direction in time
     * <p/>
     * <p>
     * This method selects the direction in time the time
     * machine moves the selected card, where true is forward movement.
     * </p>
     *
     * @param isForward
     * @return
     */
    @Override
    public void shouldMoveForwardInTime(boolean isForward) {
        if (isForward) {
            direction = 1;
        } else {
            direction = -1;
        }
    }

    /**
     * Select direction in time
     * <p/>
     * <p>
     * This method selects the duration of the time jump through the use of a second dice
     * </p>
     *
     * @param diceValue
     * @return
     */
    @Override
    public void setSecondDiceUsed(int diceValue) {
        int secondDie = ActivatorFunctions.getDieindex(player, diceValue);
    }

    /**
     * Mark the pending activation as complete.
     * <p/>
     * <p>
     * This method must be called when an activation is complete.
     * This method cannot be called until all required activation
     * methods have been called. No other methods in the move maker can
     * be called after a CardActivator has been received until after its
     * complete method is called. This is really important.
     * </p>
     */
    @Override
    public void complete() {
        ArrayList<Integer> activationData = player.getActivationData();
        activationData.add(secondDie);
        activationData.add(targetIndex);
        activationData.add(direction);
        player.performActions();
    }

    /**
     * The user chooses a dice disc.
     * <p/>
     * <p>
     * Only valid if the pending activation requires a dice disc from
     * the user at the point this method is called.
     * </p>
     *
     * @param diceDisc dice disc to use, by dice value
     */
    @Override
    public void chooseDiceDisc(int diceDisc) {
        targetIndex = diceDisc-1;
    }
}
