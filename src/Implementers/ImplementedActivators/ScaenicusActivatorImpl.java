package Implementers.ImplementedActivators;

import Implementers.ActivatorFunctions;
import Roma.Cards.CardHolder;
import Roma.Player;
import Roma.PlayerInterfaceFiles.PlayerInterface;
import framework.cards.Card;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.ScaenicusActivator;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class ScaenicusActivatorImpl  implements ScaenicusActivator {

    private static final int CANCEL = PlayerInterface.CANCEL;
    private int targetIndex;
    private Player player;
    boolean completed = false;

    public ScaenicusActivatorImpl(Player player) {
        this.player = player;
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
        int targetIndex =diceDisc-1;
        ArrayList<Integer> activationData = player.getActivationData();
        this.targetIndex = targetIndex;
        CardHolder toImitate = player.getDiceDiscsList().get(targetIndex);
        try {
            Card imitated = Card.valueOf(toImitate.getName().toUpperCase().replaceAll(" ", ""));
            if(imitated.equals(Card.SCAENICUS)){
                return this;
            } else {
                CardActivator imitator = ActivatorFunctions.getCorrectActivatorforScaenicus(imitated, player) ;
                return imitator;
            }

        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
        return new  dummyActivator();
    }

    /**
     * Mark the pending activation as complete.
     * <p/>
     * <p>
     * This method must be called when an activation is complete.
     * This method cannot be called until all required activation
     * methods have been called. No other methods in the move maker can
     * be called after a CardActivator has been received until after its
     * complete method is called. This is really important.
     * </p>
     */
    @Override
    public void complete() {
        player.getActivationData().add(0, targetIndex);
        if (!completed) {
            player.performActions();
        }
        completed = true;

    }
}
