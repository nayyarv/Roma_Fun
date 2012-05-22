package Implementers.ImplementedActivators;

import Implementers.ActivatorFunctions;
import Roma.Player;
import Roma.PlayerInterfaceFiles.PlayerInterface;
import framework.interfaces.activators.CenturioActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class CenturioActivatorImpl extends simpleActivator implements CenturioActivator {


    public CenturioActivatorImpl(Player player) {
        super(player);
    }

    /**
     * Choose whether to add an action dice to your current attack.
     *
     * @param attackAgain whether to attack again
     */

    boolean attackAgain = true;

    @Override
    public void chooseCenturioAddActionDie(boolean attackAgain) {
        this.attackAgain = attackAgain;
        if(!attackAgain){
            player.getActivationData().add(PlayerInterface.CANCEL);
        }
    }

    /**
     * The user chooses an action dice.
     * <p/>
     * <p>
     * An action dice of the given value is used. A dice of that value
     * must exist in the user's current unused action dice. The same
     * dice cannot be used multiple times on the same turn. The same
     * value can be used if the user has multiple dice of that value.
     * </p>
     * <p/>
     * <p>
     * Action dice are always referred to by their value, rather than
     * their index, because action dice do not have either an implicit
     * or explicit ordering.
     * </p>
     *
     * @param actionDiceValue the value of the dice to use
     */
    @Override
    public void chooseActionDice(int actionDiceValue) {
        if (attackAgain){
            int dieIndex = ActivatorFunctions.getDieindex(player, actionDiceValue);
            player.getActivationData().add(dieIndex);
        }
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
