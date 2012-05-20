package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.ConsulActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ConsulActivatorImpl extends simpleActivator implements ConsulActivator {

    public ConsulActivatorImpl(Player player) {
        super(player);
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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void chooseWhichDiceChanges(int originalRoll) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
