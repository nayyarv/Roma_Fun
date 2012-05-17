package Roma.Cards;

import Roma.DiceDiscs;
import Roma.PlayArea;
import Roma.Player;
import Roma.PlayerInterfaceFiles.CancelAction;

import java.util.ArrayList;

/**
 * File Name:
 * Creator: Varun Nayyar
 * Date: 11/04/12
 * Desc:
 */
public class Senator extends CardBase {
    private static int COST_SHIFT = Wrapper.INITIAL_SHIFT;
    private static int COST_SCALE = 0;
    private static int DEFENSE_SHIFT = Wrapper.INITIAL_SHIFT;
    private static int DEFENSE_SCALE = Wrapper.INITIAL_SCALE;
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


//    public boolean activate(Player player, int position) {
//        boolean activated = true;
//
//        ArrayList<CardHolder> tempHand = new ArrayList<CardHolder>();
//        ArrayList<CardHolder> hand = player.getHand();
//        boolean endSelection = false;
//        CardHolder chosenCard = null;
//        int targetPosition;
//        DiceDiscs diceDiscs = playArea.getDiceDiscs();
//
//        for(CardHolder card : hand){
//            if(card.getType().equalsIgnoreCase(Card.CHARACTER)){
//                if(hand.remove(card)){
//                    tempHand.add(card);
//                }
//            }
//        }
//
//        if(tempHand.isEmpty()){
//            activated = false;
//        } else {
//            while(!endSelection){
//                playArea.printStats();
//                chosenCard = player.chooseCardIndex(tempHand);
//                if(chosenCard == null){
//                    endSelection = true;
//                } else {
//                    targetPosition = player.getDiceDiscIndex("");
//                    diceDiscs.layCard(player.getPlayerID(), targetPosition, chosenCard);
//                }
//            }
//        }
//
//        return activated;
//    }

    @Override
    public void gatherData(Player player, int position) throws CancelAction {
        //TODO: fill in
    }

    //activationData: ([cardHandIndex][positionIndex])*repeated as desired

    @Override
    public void activate(Player player, int position) {
        CardHolder card;
        ArrayList<CardHolder> hand = player.getHand();
        WrapperMaker wrapperMaker = new WrapperMaker(COST_SHIFT, COST_SCALE, DEFENSE_SHIFT, DEFENSE_SCALE);
        Wrapper wrapper = null;
        ArrayList<Integer> activationData = player.getActivationData();
        ArrayList<Integer> handIndices = new ArrayList<Integer>();
        ArrayList<Integer> discIndices = new ArrayList<Integer>();
        ArrayList<CardHolder> cards = new ArrayList<CardHolder>();
        int discIndex;

        //wrap building cards in hand with costScale = 0 modifier
        for(int i = 0; i < hand.size(); i++){
            card = hand.get(i);
            if(card.getType().equalsIgnoreCase(Card.CHARACTER)){
                wrapper = wrapperMaker.insertWrapper(card);

                //add wrappers to endActionClear list
                playArea.addToEndActionList(wrapper);
            }
        }

        //retrieve data from activationData
        while(!activationData.isEmpty()){
            handIndices.add(activationData.remove(0));
            discIndices.add(activationData.remove(0));
        }

        //get cards from hand without removing to preserve indices
        for(int i : handIndices){
            cards.add(hand.get(i));
        }

        //remove cards chosen from hand
        hand.removeAll(cards);

        for(int i = 0; i < cards.size(); i++){
            card = cards.get(i);
            discIndex = discIndices.get(i);
            player.layCard(card, discIndex);
        }
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
