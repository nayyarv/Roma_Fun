package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.Targeted;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class Assassin extends simpleActivator implements Targeted {


    public Assassin(Player player) {
        super(player);
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
        player.getActivationData().add(diceDisc-1);

    }
}
