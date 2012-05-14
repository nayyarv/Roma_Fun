package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc: Architectus Card
 */
public class Architectus extends CardBase {
    public final static String NAME = "Architectus";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Enables the player to lay as many building cards as they wish free " +
            "of charge. The player is allowed to cover any cards.";
    final static int COST = 3;
    final static int DEFENCE = 4;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;
    
    public static CardHolder makeOne(PlayArea playArea){
        Card card = new Architectus(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Architectus(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }


    private Architectus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    @Override
    public boolean activate(Player player, int position) {
        boolean activated = true;

        ArrayList<CardHolder> tempHand = new ArrayList<CardHolder>();
        ArrayList<CardHolder> hand = player.getHand();
        boolean endSelection = false;
        CardHolder chosenCard = null;
        int targetPosition;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        for(CardHolder card : hand){
            if(card.getType() == Card.BUILDING){
                if(hand.remove(card)){
                    tempHand.add(card);
                }
            }
        }

        while(!endSelection){
            playArea.printStats();
            chosenCard = player.chooseCard(tempHand);
            if(chosenCard == null){
                endSelection = true;
            } else {
                targetPosition = player.chooseCardDisc();
                diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCard);
            }
        }

        return activated;
    }
}
