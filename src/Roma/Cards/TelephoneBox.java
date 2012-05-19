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
public class TelephoneBox extends CardBase {

    public final static String NAME = "TelephoneBox";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "When activated by an action die the telephone Box card sends one of the " +
            "owner's cards already on the board forwards or backwards in time.  " +
            "The sent card is called the time-traveling card.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 1;

    public TelephoneBox(PlayArea playArea){
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
    public CardHolder makeOne(PlayArea playArea) {
        Card card = new TelephoneBox(playArea);
        CardHolder cardHolder = new CardHolder(card, playArea);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new TelephoneBox(playArea);
            cardHolder = new CardHolder(card, playArea);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }
}
