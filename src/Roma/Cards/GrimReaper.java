package Roma.Cards;

import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 19/05/12
 * Desc:
 */
public class GrimReaper extends CardBase{
    public final static String NAME = "Grim Reaper";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Once placed on a disc this card provides a chance to cheat death. " +
            "The player's other character cards are returned to the player's hand rather than to the discard pile " +
            "whenever they are successfully attacked and defeated by the opponent..";
    final static int COST = 6;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = false;

    public final static int OCCURENCES = 1;

    public GrimReaper(PlayArea playArea){
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void activate(Player player, int position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new GrimReaper(playArea);
        CardHolder cardHolder = new CardHolder(card, playArea);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new GrimReaper(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }
}
