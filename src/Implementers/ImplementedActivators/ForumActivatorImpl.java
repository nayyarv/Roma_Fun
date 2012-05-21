package Implementers.ImplementedActivators;

import Implementers.ActivatorFunctions;
import Roma.Player;
import framework.interfaces.activators.ForumActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ForumActivatorImpl extends  simpleActivator implements ForumActivator {

    public ForumActivatorImpl(Player player) {
        super(player);
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
        int index = ActivatorFunctions.getDieindex(player, actionDiceValue);
        player.getActivationData().add(index);
    }

    /**
     * Choose whether to activate a Templum with your forum activation
     *
     * @param activate true if the user wishes to activate a Templum.
     */
    @Override
    public void chooseActivateTemplum(boolean activate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Choose the dice to use with the Templum.
     * <p/>
     * This should only be called if chooseActivateTemplum has previously
     * been called with true as a parameter.
     *
     * @param diceValue the dice to use
     */
    @Override
    public void chooseActivateTemplum(int diceValue) {
        //Index must be 0 no matter what :)
        player.getActivationData().add(0);

    }
}
