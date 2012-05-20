package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.OnagerActivator;
import framework.interfaces.activators.VelitesActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class FighterImpl extends Assassin implements
        VelitesActivator, OnagerActivator {

    public FighterImpl(Player player) {
        super(player);
    }

    /**
     * Give the result of an attack die roll.
     * <p/>
     * <p>
     * Only valid if the pending activation requires an attack dice
     * roll.
     * </p>
     *
     * @param roll the outcome of the attack dice roll
     */
    @Override
    public void giveAttackDieRoll(int roll) {
        player.getCurrentAction().setBattleDice(roll);
    }
}
