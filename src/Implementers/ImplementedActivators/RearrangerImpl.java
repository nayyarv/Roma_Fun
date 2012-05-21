package Implementers.ImplementedActivators;

import Roma.Cards.CardHolder;
import Roma.Player;
import Roma.PlayerInterfaceFiles.PlayerInterface;
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

        int cardIndex = getCardIndex(card);
        player.getActivationData().add(cardIndex); //from indices
        player.getActivationData().add(diceDisc-1); //to indices

    }

    private int getCardIndex(Card card){

        ArrayList<Integer> activation = player.getActivationData();
        ArrayList<Integer> cardIndices = new ArrayList<Integer>();

        //get all the 0,2,4,6 etc indices
        for (int j =0;j<activation.size();j+=2){
            cardIndices.add(activation.get(j));
        }

        int i;
        for (i =0; i<discsList.size();i++){
            if (!cardIndices.contains(i)){ //i,.e. not already chosen
                if(discsList.get(i)!=null){
                    String name = discsList.get(i).getName().replaceAll("\\s", "");
                    if (name.equalsIgnoreCase(card.toString())) return i;
                }
            }
        }

        return PlayerInterface.CANCEL;
    }
}
