package Roma.Cards;

import Roma.*;
import Roma.PlayerInterfaceFiles.PlayerInterface;

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

    @Override
    public CardHolder makeOne(PlayArea playArea){
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


    Architectus(PlayArea playArea) {
        super(NAME, TYPE, DESCRIPTION, COST, DEFENCE, playArea, ACTIVATE_ENABLED);

    }

    private static int COST_SHIFT = 0;
    private static int COST_SCALE = 0;
    private static int DEFENSE_SHIFT = 0;
    private static int DEFENSE_SCALE = 1;

    @Override
    public ArrayList<Integer> gatherData(Player player, int position) {
        ArrayList<Integer> activationData = new ArrayList<Integer>();
        ArrayList<CardHolder> hand = player.getHand();
        int[] handIndices = new int[hand.size()];
        int[] discIndices = new int[hand.size()];
        int handIndex = 0;
        int discIndex = 0;
        PlayerInterface playerInterface = playArea.getPlayerInterface();

        //get player input for which cards to lay
        //collect player input
        int i = 0;
        while(handIndex != PlayerInterface.CANCEL || discIndex != PlayerInterface.CANCEL){
            handIndex = playerInterface.getCardIndexFiltered(hand, Card.BUILDING);
            handIndices[i] = handIndex;
            discIndex = player.getDiceDiscIndex("");
            discIndices[i] = discIndex;
            i++;
        }

        while(i > 0){
            i--;
            activationData.add(handIndices[i]);
            activationData.add(discIndices[i]);
        }

        return activationData;
    }

    @Override
    public boolean activate(Player player, int position, ArrayList<Integer> activationData) {
        boolean activated = true;
        CardHolder card;
        ArrayList<CardHolder> hand = player.getHand();
        WrapperMaker wrapperMaker = new WrapperMaker(COST_SHIFT, COST_SCALE, DEFENSE_SHIFT, DEFENSE_SCALE);
        Wrapper wrapper = null;

        //wrap building cards in hand with costScale = 0 modifier
        for(int i = 0; i < hand.size(); i++){
            card = hand.get(i);
            if(card.getType().equalsIgnoreCase(Card.BUILDING)){
                wrapper = wrapperMaker.insertWrapper(card);

                //add wrappers to endActionClear list
                playArea.addToEndActionList(wrapper);
            }
        }

        //TODO: implement lay cards

        return activated;
    }

    @Override
    public void discarded() {
        //do nothing when discarded
    }
}
