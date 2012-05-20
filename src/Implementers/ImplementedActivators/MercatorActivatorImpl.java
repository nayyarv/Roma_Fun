package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.MercatorActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class MercatorActivatorImpl extends simpleActivator implements MercatorActivator  {


    public MercatorActivatorImpl(Player player) {
        super(player);
    }


    /**
     * Choose the number of victory points to buy with the Mercator.
     *
     * @param VPToBuy the number of points to buy
     */
    @Override
    public void chooseMercatorBuyNum(int VPToBuy) {
        player.getActivationData().add(VPToBuy);
    }
}
