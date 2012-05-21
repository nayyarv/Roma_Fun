package Implementers.ImplementedActivators;

import Roma.Cards.CardHolder;
import Roma.Player;
import framework.cards.Card;
import framework.interfaces.activators.ConsiliariusActivator;
import framework.interfaces.activators.MachinaActivator;
import framework.interfaces.activators.Rearranger;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 21/05/12
 * Desc:
 */
public class RearrangerImpl extends simpleActivator implements Rearranger,
        MachinaActivator, ConsiliariusActivator {

    ArrayList<CardHolder> discsList;

    public RearrangerImpl(Player player) {
        super(player);
        discsList = player.getDiceDiscsList();
    }

    /**
     * Place a floating card on to a dice disc.
     * <p/>
     * <p>
     * When cards that allow rearrangement are activated, all the cards
     * affected enter a floating state. Cards are then given new
     * locations with this method. The pending activation cannot be
     * completed while there are floating cards.
     * </p>
     *
     * @param card     the card to place
     * @param diceDisc the location for the card to be placed
     */
    @Override
    public void placeCard(Card card, int diceDisc) {

        int i = 0;
        for (CardHolder cardHolder:discsList){
            if(cardHolder!=null){
                String name = cardHolder.getName().replaceAll("\\s", "");
                if (name.equalsIgnoreCase(card.toString())) break;
            }
            i++;
        }


        player.getActivationData().add(i);
        player.getActivationData().add(diceDisc-1);

    }
}
