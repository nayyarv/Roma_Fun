package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.*;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class Assassin implements Targeted, CardActivator,
        NeroActivator, SicariusActivator, PraetorianusActivator, GladiatorActivator {

    Player player;

    public Assassin(Player player) {
        this.player = player;
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
        //Do i need to set playable?
        player.getActivationData().add(diceDisc-1);

    }
}
