package Implementers.ImplementedActivators;

import Roma.Dice;
import Roma.Player;
import framework.interfaces.activators.ConsulActivator;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ConsulActivatorImpl  implements ConsulActivator {

    private Player player;
    private int dieIndex;
    private int changeAmount;

    public ConsulActivatorImpl(Player player) {
        this.player = player;
    }

    /**
     * Choose the amount a dice disc value changes by.
     * <p/>
     * <p>
     * Valid changes are -1 or 1 in the current game.
     * </p>
     *
     * @param amount the amount to change by.
     */
    @Override
    public void chooseConsulChangeAmount(int amount) {
        changeAmount = amount;
    }

    @Override
    public void chooseWhichDiceChanges(int originalRoll) {
        //get the die index
        ArrayList<Dice> freeDice = player.getFreeDice();
        int currActionIndex = player.getCurrentAction().getActionDiceIndex();

        freeDice.remove(currActionIndex);


        Dice dice = freeDice.get(0);
        int i;
        for(i=0; i<freeDice.size()&&dice.getValue()!=originalRoll;i++){
            dice = freeDice.get(i);
        }
        dieIndex = i;

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
        player.getActivationData().add(dieIndex);
        player.getActivationData().add(changeAmount);
        player.performActions();
    }
}
