package Roma.Cards;

import Roma.*;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc: Architectus Card
 */
public class Architectus extends Card {
    private final static String NAME = "Architectus";
    private final static String TYPE = Card.CHARACTER;
    private final static String DESCRIPTION = "Enables the player to lay as many building cards as they wish free " +
            "of charge. The player is allowed to cover any cards.";
    private final static int COST = 3;
    private final static int DEFENCE = 4;

    public final static int OCCURENCES = 2;


    public Architectus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea);

    }

    @Override
    public void activate(Player player, int position) {
        ArrayList<Card> tempHand = new ArrayList<Card>();
        ArrayList<Card> hand = player.getHand();
        boolean endSelection = false;
        Card chosenCard = null;
        int targetPosition;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        for(Card card : hand){
            if(card.getType() == Card.BUILDING){
                if(hand.remove(card)){
                    tempHand.add(card);
                }
            }
        }

        while(!endSelection){
            chosenCard = player.chooseCard(tempHand);
            if(chosenCard == null){
                endSelection = true;
            } else {
                targetPosition = player.chooseCardDisc();
                diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCard);
            }
        }
    }
}
