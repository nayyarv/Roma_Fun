package Implementers.ImplementedActivators;

import Roma.Player;
import framework.interfaces.activators.CardSelector;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class SelectorImpl extends simpleActivator implements CardSelector {

    public SelectorImpl(Player player) {
        super(player);
    }

    /**
     * The user chooses a card from a pile.
     * <p/>
     * <p>
     * This choice is from an index where 0 is the topmost card. In the
     * case of the deck, 0 is the next to be drawn. In the case of the
     * discard, 0 is most recently discarded.
     * </p>
     *
     * @param indexOfCard the index of the card to use
     */
    @Override
    public void chooseCardFromPile(int indexOfCard) {
        player.getActivationData().add(indexOfCard);
    }
}
