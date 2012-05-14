package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Basilica extends CardBase {

    public final static String NAME = "Basilica";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION = "If a Forum is activated (it must lie directly next to the basilica)," +
            " the player gets 2 more victory points. The Basilica itself is not activiated.";
    final static int COST = 5;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = false;

    public final static int OCCURENCES = 2;

    public static CardHolder makeOne(PlayArea playArea){
        Card card = new Basilica(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Basilica(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    private Basilica(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public boolean activate(Player player, int position) {
        boolean activated = false;

        return activated;
    }
}
