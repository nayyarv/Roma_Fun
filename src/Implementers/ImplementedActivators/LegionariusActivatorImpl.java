package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.LegionariusActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class LegionariusActivatorImpl extends simpleActivator implements LegionariusActivator {


    public LegionariusActivatorImpl(Player player) {
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
