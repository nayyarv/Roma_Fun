package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.CardActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 20/05/12
 * Desc:
 */
public class simpleActivator implements
        CardActivator
{
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
    Player player;

    public simpleActivator(Player player) {
        this.player = player;
    }

    @Override
    public void complete() {
        player.performActions();

    }


}
