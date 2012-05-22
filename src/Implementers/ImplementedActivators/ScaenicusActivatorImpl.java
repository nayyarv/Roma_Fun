package Implementers.ImplementedActivators;

import Implementers.ActivatorFunctions;
import Roma.Cards.CardHolder;
import Roma.Player;
import framework.cards.Card;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.ScaenicusActivator;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ScaenicusActivatorImpl extends simpleActivator implements ScaenicusActivator {

    public ScaenicusActivatorImpl(Player player) {
        super(player);
    }

    /**
     * Select a card to mimic with the Scaenicus.
     * <p/>
     * <p>
     * This method selects a card for an activated Scaenicus to mimic.
     * A new CardActivator corresponding to the chosen card is returned,
     * so the test may use it to activate the selected card.
     * </p>
     *
     * @param diceDisc
     * @return
     */
    @Override
    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        int diceIndex =diceDisc-1;
        player.getActivationData().add(diceIndex);
        CardHolder toImitate = player.getDiceDiscsList().get(diceIndex);
        try {
            Card imitated = Card.valueOf(toImitate.getName().toUpperCase().replaceAll(" ", ""));
            return ActivatorFunctions.getCorrectActivator(imitated, player);
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
        return new  dummyActivator();
    }
}
