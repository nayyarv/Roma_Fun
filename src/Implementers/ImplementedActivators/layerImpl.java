package Implementers.ImplementedActivators;

import Roma.Cards.CardHolder;
import Roma.Player;
import framework.cards.Card;
import framework.interfaces.activators.ArchitectusActivator;
import framework.interfaces.activators.SenatorActivator;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class layerImpl extends simpleActivator implements ArchitectusActivator, SenatorActivator {

    public layerImpl(Player player) {
        super(player);
    }

    @Override
    public void layCard(Card myCard, int whichDiceDisc) {
        ArrayList<CardHolder> hand = player.getHand();
        int i = 0;
        for (CardHolder cardHolder:hand){
            String name = cardHolder.getName().replaceAll("\\s", "");
            if (name.equalsIgnoreCase(myCard.toString())) break;
            i++;
        }
        player.getActivationData().add(i);
        player.getActivationData().add(whichDiceDisc-1);

    }
}
