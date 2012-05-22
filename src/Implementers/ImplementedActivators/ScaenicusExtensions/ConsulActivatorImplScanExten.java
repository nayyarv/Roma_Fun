package Implementers.ImplementedActivators.ScaenicusExtensions;

import Implementers.ImplementedActivators.ConsulActivatorImpl;
import Roma.Player;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 22/05/12
 * Desc:
 */
public class ConsulActivatorImplScanExten extends ConsulActivatorImpl{
    public ConsulActivatorImplScanExten(Player player) {
        super(player);
    }

    @Override
    public void complete(){
        player.getActivationData().add(dieIndex);
        player.getActivationData().add(changeAmount);

    }
}
