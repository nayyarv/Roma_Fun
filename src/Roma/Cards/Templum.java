package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.CancelAction;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Templum extends CardBase {

    public final static String NAME = "Templum";
    final static String TYPE = Card.BUILDING;
    final static String DESCRIPTION =
            "If a Forum is activated (it must lie directly next to the Templum), the third action die can be \n" +
                    "used to determine the number of additional victory points which the player gets from the general \n" +
                    "stockpile. The action dice must not yet have been used in this go. The Templum itself is not \n" +
                    "activated separately.";
    final static int COST = 2;
    final static int DEFENCE = 2;
    final static boolean ACTIVATE_ENABLED = false;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Templum(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Templum(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Templum(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);
    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        System.err.println("Templum being activated somehow!");
    }

    @Override
    public void activate(Player player, int position) {
        System.err.println("Templum being activated somehow!");
    }

    @Override
    public void enterPlay(Player player, int position) {
        //no enter play action
    }

    @Override
    public void leavePlay() {
        //do nothing when leaving play
    }

}
