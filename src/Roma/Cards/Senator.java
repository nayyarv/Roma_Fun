package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Senator extends CardBase {
    public final static String NAME = "Senator";
    final static String TYPE = Card.CHARACTER;
    final static String DESCRIPTION = "Enables the player to lay as many character cards as " +
            "they wish free of " +
            "charge. The player is allowed to cover any cards.";
    final static int COST = 3;
    final static int DEFENCE = 3;
    final static boolean ACTIVATE_ENABLED = true;

    public final static int OCCURENCES = 2;

    @Override
    public CardHolder makeOne(PlayArea playArea){
        Card card = new Senator(playArea);
        CardHolder cardHolder = new CardHolder(card);
        card.setContainer(cardHolder);

        return cardHolder;
    }

    public static ArrayList<CardHolder> playSet(PlayArea playArea){
        ArrayList<CardHolder> set = new ArrayList<CardHolder>();
        CardHolder cardHolder;
        Card card;

        for(int i = 0; i < OCCURENCES; i++){
            card = new Senator(playArea);
            cardHolder = new CardHolder(card);
            card.setContainer(cardHolder);
            set.add(cardHolder);
        }

        return set;
    }

    Senator(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }


    public boolean activate(Player player, int position) {
        boolean activated = true;

        ArrayList<CardHolder> tempHand = new ArrayList<CardHolder>();
        ArrayList<CardHolder> hand = player.getHand();
        boolean endSelection = false;
        CardHolder chosenCard = null;
        int targetPosition;
        DiceDiscs diceDiscs = playArea.getDiceDiscs();

        for(CardHolder card : hand){
            if(card.getType().equalsIgnoreCase(Card.CHARACTER)){
                if(hand.remove(card)){
                    tempHand.add(card);
                }
            }
        }

        if(tempHand.isEmpty()){
            activated = false;
        } else {
            while(!endSelection){
                playArea.printStats();
                chosenCard = player.chooseCardIndex(tempHand);
                if(chosenCard == null){
                    endSelection = true;
                } else {
                    targetPosition = player.chooseDiceDiscIndex();
                    diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCard);
                }
            }
        }

        return activated;
    }
}
